
$(document).ready(function () {
    getMake();
    loadModels();


    $('#save-button').click(function (event) {
        var drop = $('#select-make').val();
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/model/admin/addmodel',
            data: JSON.stringify({
                name: $('#new-model').val(),
                make: {
                    id: $('#select-make option:selected').val()
                },
                user: {
                    id: 2
                }
            }),

            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (model, status) {


                var modelName = model.name;
                var date = model.dateAdded;
                var user = model.user.email;
                var make = model.make.name;

                var row = '<tr>';
                row += '<td>' + make + '</td>';
                row += '<td>' + modelName + '</td>';
                row += '<td>' + date + '</td>';
                row += '<td>' + user + '</td>';
                row += '/tr';

                $('#modelTable').append(row);

            },
            error: function (response) {
                $('#errorMessage')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text(response.responseText));
            }
        });
    });

});

function getMake() {
    var dropDown = $('#select-make');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/make/admin/allmake',
        success: function (data, status) {

            $.each(data, function (index, make) {
                var makeName = make.name;
                var id = make.id;
                var select = '<option value = "' + id + '">' + makeName + '</option>';

                dropDown.append(select);

            });


        },
        error: function (response) {
            $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text(response.responseText));

        }
    });
}

function loadModels() {
    var showTable = $('#modelTableDiv');
    showTable.show();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/model/admin/allmodel',
        success: function (data, status) {

            $.each(data, function (index, model) {
                var makeName = model.make.name;
                var modelName = model.name;
                var date = model.dateAdded;
                var user = model.user.email;

                var row = '<tr>';
                row += '<td>' + makeName + '</td>';
                row += '<td>' + modelName + '</td>';
                row += '<td>' + date + '</td>';
                row += '<td>' + user + '</td>';
                row += '/tr';

                $('#modelTable').append(row);

            });


        },
        error: function (response) {
            $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text(response.responseText));

        }
    });
}