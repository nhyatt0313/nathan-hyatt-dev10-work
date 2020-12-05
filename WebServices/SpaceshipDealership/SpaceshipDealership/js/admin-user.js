$(document).ready(function () {
    loadUser();
});

function clearUserRows() {
    $('#userRows').empty();
}

function loadUser() {
    clearUserRows();
    var showTable = $('#userTableDiv');
    showTable.show();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/user/admin/alluser',
        success: function (data, status) {
            $.each(data, function (index, user) {
                var userID = user.id;
                var firstName = user.firstName;
                var lastName = user.lastName;
                var email = user.email;
                var role = user.role;


                var row = '<tr>';
                row += '<td>' + firstName + '</td>';
                row += '<td>' + lastName + '</td>';
                row += '<td>' + email + '</td>';
                row += '<td>' + role + '</td>';
                row += '<td><a href="edit-user.html?id=' + userID + '">Edit</a></td>';
                row += '/tr';

                $('#userRows').append(row);
            });
        },
        error: function (response) {
            $('#errorMessages').append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text(response.responseText));
        }
    });
}

function addUser(){
    location.assign('add-user.html');
}