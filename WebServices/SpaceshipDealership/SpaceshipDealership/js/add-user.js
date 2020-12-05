$(document).ready(function() {

    $('#addUser').click(function(event){
      var rol = $('#select-role');
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/user/admin/adduser',
            data: JSON.stringify({
                firstName: $('#firstName').val(),
                lastName: $('#lastName').val(),
                email: $('#email').val(),
                password: $('#password').val(),
                role: $("#select-role option:selected").text()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data, status) {
                $('#errorMessages').empty();
                $('#firstName').val('');
                $('#lastName').val('');
                $('#email').val('');
                $('#password').val('');
                $('#select-role').val('');
                
                alert("User Added");
                window.location.assign('admin-user.html');

            },
            error: function(response) {
                $('#errorMessages')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text(response.responseText));
            }

        })
    })

});