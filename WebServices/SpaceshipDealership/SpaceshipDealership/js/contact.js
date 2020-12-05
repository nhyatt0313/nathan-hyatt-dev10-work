$(document).ready(function() {
    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
            vars[key] = value;
        });
        return vars;
    }

    var vin = getUrlVars()["vin"];
    $('#add-message').append(vin);
});

function sendContactForm(){
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/contact/admin/addcontact',
        data: JSON.stringify({
            name: $('#name').val(),
            phone: $('#phone').val(),
            email: $('#email').val(),
            message: $('#message').val()
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
            $('#name').val('');
            $('#phone').val('');
            $('#email').val('');
            $('#message').val('');
            
            alert("Thanks for submitting your request. You can expect a response in 3 to 7 years.");
        },
        error: function (response) {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text(response.responseTest));
        }
    });
}