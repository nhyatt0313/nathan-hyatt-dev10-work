$(document).ready(function () {
    var userId = getUrlLogin()["userId"];
    loadUsers();
    getReports();
});

function getReports() {
    $('#search-button').click(function (event) {
        $('#searchResult').empty();
        var userIdSelect = $('#userIdSelect').val();
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/purchase/admin/salesreport',
            data: JSON.stringify({
                startDate: $('#startDate').val(),
                endDate: $('#endDate').val(),
                userId: userIdSelect
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data, status) {
                var sales = data.sales;
                var vehiclesSold = data.vehiclesSold;
                var userSelection = userIdSelect;
                var row = '<tr>';
                row += '<td>' + userSelection + '</td>';
                row += '<td>$' + sales + '</td>';
                row += '<td>' + vehiclesSold + '</td>';
                row += '</tr>';
                $('#newRows').append(row);
            },
            error: function (response) {
                $('#errorMessage')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text(response.responseText));
            }
        });
    });
}


function loadUsers() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/user/admin/alluser',
        success: function (data, status) {

            $.each(data, function (index, user) {
                var firstName = user.firstName;
                var lastName = user.lastName;
                var id = user.id;
                $('#userIdSelect').append('<option value="' + id + '">' + firstName + ' ' + lastName + '</option>');
            });
        },
        error: function (response) {
            $('#errorMessage')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text(response.responseText));
        }
    });
}


function getUrlLogin() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}