$(document).ready(function () {
    loadSpecials();
});

function loadSpecials() {

    clearSpecials();

    var specials = $('#special');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/special/allspecial',
        success: function (data, status) {

            $.each(data, function (index, special) {
                var title = special.title;
                var details = special.description;

                var displayDiv = '<div class="row" \n\
                                        style="border-style: solid;\n\
                                            background-color: #A5BFC4;\n\
                                            margin-left: 5px;\n\
                                            margin-right: 5px; \n\
                                            margin-top: 10px; \n\
                                            margin-bottom: 10px; \n\
                                            padding: 10px;">\n\
                                        <div class="col-md-2">\n\
                                            <img src="../images/money.png" \n\
                                                alt="Special Image" \n\
                                                style="display: block; \n\
                                                 width: 100%; \n\
                                                 height: 100%; \n\
                                                 margin-top: auto; \n\
                                                 margin-bottom: auto;">\n\
                                        </div>\n\
                                     <div class="col-md-10">\n\
                                         <h2>' + title + '</h2>\n\
                                            <p> ' + details + '</p>\n\
                                     </div>\n\
                                </div>';
                
                specials.append(displayDiv);
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
function clearSpecials() {
    $('#special').empty();
}