$(document).ready(function () {
    loadCarousel();
    loadFeatured();
});
function loadCarousel() {
    clearSpecials();
    var start = 0;
    var indicate = $('.carousel-indicators');
    var inner = $('.carousel-inner');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/special/allspecial',
        success: function (data, status) {
            $.each(data, function (index, special) {
                var title = special.title;
                var details = special.description;

                var listIndicate = '<li data-target="#myCarousel" data-slide-to="'+ start + '"></li>';

                var listInner = '<div class="item">\n\
                                    <img src="../images/banner_rocket001.jpg" style="width: 100%;">\n\
                                    <div class="carousel-caption" style="color: black; padding-top: 10px; height: 100%;">\n\
                                        <h3 style="font-size: 60px;">' + title + '</h3>\n\
                                        <p style="font-size: 20px;">' + details + '</p>\n\
                                    </div>\n\
                                </div>';

                start++;

                indicate.append(listIndicate);
                inner.append(listInner);

                $('.item').first().addClass('active');
                $('.carousel-indicators > li').first().addClass('active');
                $('#myCarousel').carousel();

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
    $('.carousel-indicators').empty();
    $('.carousel-inner').empty();
}

function loadFeatured() {

    clearFeaturedVehicles();

    var cardDeck = $('.card-deck');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/vehicle/admin/getfeatured',
        success: function (data, status) {
            $.each(data, function (index, vehicle) {
                var year = vehicle.year;
                var make = vehicle.make.name;
                var model = vehicle.model.name;
                var price = vehicle.salePrice;
                var fileImg = vehicle.fileImg;
                var description = vehicle.description;

                var card = '<div class="col-sm-3" >\n\
                                <div style="padding: 10px;">\n\
                                    <img src= "../images/'+ fileImg+'" alt= "'+ description +'" style="width:100%">\n\
                                    <div>\n\
                                        <h5 class="card-title">' + year + ' ' + make + ' ' + model + '</h5>\n\
                                        <p class="card-text">$' + price + '</p>\n\
                                    </div>\n\
                                </div>\n\
                            </div>';

                cardDeck.append(card);
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

function clearFeaturedVehicles() {
    $('.card-deck').empty();
}