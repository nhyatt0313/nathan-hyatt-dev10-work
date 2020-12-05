$(document).ready(function () {
    $('#login-submit').click(function(event){
        login();
    });
});

function login() {
    var username = $('#username').val();
    var password = $('#password').val();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/user/admin/login',
        data: JSON.stringify({
            'email': username,
            'password': password
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        success: function (data, status) {
            // clear errorMessages
            $('#errorMessages').empty();
            // Clear the form
            var role = data.role;
            if (role === "Admin"){
                // go to admin page
                location.assign('admin/admin.html?userId='+data.id);
            }
            if (role === "Sales"){
                // go to sales page
                location.assign('sales/sales.html?userId='+data.id);
            }
        },
        error: function (response) {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text(response.responseText));
        }
    });
}
