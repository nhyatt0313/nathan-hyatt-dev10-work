$(document).ready(function () {

    loadMake();
    $('#save-button').click(function (event) {
        var dropS = $('#new-make');
        //alert(dropS.text());
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/make/admin/addmake',
            data: JSON.stringify({
                name: $('#new-make').val(),
                user: {
                    id: 2
                }

            }),

            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (make, status) {
                var makeName = make.name;
                var date = make.dateAdded;
                var user = make.user.email;

                var row = '<tr>';
                row += '<td>' + makeName + '</td>';
                row += '<td>' + date + '</td>';
                row += '<td>' + user + '</td>';
                row += '/tr';


                $('#newMakeRows').append(row);

            },
            error: function () {
                $('#errorMessage')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text('Error calling web service.  Please try again later.'));
            }
        });
    });

});

function clearMakeRows() {
    $('#newMakeRows').empty();
}

function loadMake() {
    clearMakeRows();
    var showTable = $('#makeTableDiv');
    showTable.show();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/make/admin/allmake',
        success: function (data, status) {

            $.each(data, function (index, make) {
                var makeName = make.name;
                var date = make.dateAdded;
                var user = make.user.email;

                var row = '<tr>';
                row += '<td>' + makeName + '</td>';
                row += '<td>' + date + '</td>';
                row += '<td>' + user + '</td>';
                row += '/tr';

                $('#newMakeRows').append(row);

            });


        },
        error: function (response) {
            $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text(response.responseText));

        }
    });
}