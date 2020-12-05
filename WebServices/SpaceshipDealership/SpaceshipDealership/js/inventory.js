$(document).ready(function () {
    loadSearchParameters();
});

function search(newUsed) {
    $('#searchResults').empty();
    $('#search').show();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/vehicle/admin/search',
        data: JSON.stringify({
            searchString: $('#select-item').val(),
            minPrice: $('#priceMin').val(),
            maxPrice: $('#priceMax').val(),
            minYear: $('#yearMin').val(),
            maxYear: $('#yearMax').val(),
            newVehicle: newUsed
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'

        },
        'dataType': 'json',
        success: function (data, status) {
            $.each(data, function (index, vehicle) {
                var year = vehicle.year;
                var make = vehicle.make.name;
                var model = vehicle.model.name;
                var salePrice = vehicle.salePrice;
                var fileImg = vehicle.fileImg;
                var style = vehicle.style;
                var trans = vehicle.trans;
                var color = vehicle.color;
                var interior = vehicle.interior;
                var mileage = vehicle.mileage;
                var vin = vehicle.vin;
                var msrp = vehicle.msrp;
                var id = vehicle.id;
                var row = '<div class="row">\n\
                                <h4>' + year + ' ' + make + ' ' + model + '</h4>\n\
                                <div class="col-lg-3 col-md-3">\n\
                                    <img src="../images/' + fileImg + '" alt="" style="width:70%;">\n\
                                </div>\n\
                                <div class="col-lg-3 col-md-3">\n\
                                    <table>\n\
                                        <tr>\n\
                                            <th>Body Style:</th>\n\
                                            <td>' + style + '</td>\n\
                                        </tr>\n\
                                        <tr>\n\
                                            <th>Trans:</th>\n\
                                            <td>' + trans + '</td>\n\
                                        </tr>\n\
                                        <tr>\n\
                                            <th>Color:</th>\n\
                                            <td>' + color + '</td>\n\
                                        </tr>\n\
                                    </table>\n\
                                </div>\n\
                                <div class="col-lg-3 col-md-3">\n\
                                    <table>\n\
                                        <tr>\n\
                                            <th>Interior:</th>\n\
                                            <td>' + interior + '</td>\n\
                                        </tr>\n\
                                        <tr>\n\
                                            <th>Mileage:</th>\n\
                                            <td>' + mileage + '</td>\n\
                                        </tr>\n\
                                        <tr>\n\
                                            <th>VIN:</th>\n\
                                            <td>' + vin + '</td>\n\
                                        </tr>\n\
                                    </table>\n\
                                </div>\n\
                                <div class="col-lg-3 col-md-3">\n\
                                    <table>\n\
                                        <tr>\n\
                                            <th>Sales Price:</th>\n\
                                            <td>' + salePrice + '</td>\n\
                                        </tr>\n\
                                        <tr>\n\
                                            <th>MSRP:</th>\n\
                                            <td>' + msrp + '</td>\n\
                                        </tr>\n\
                                        <tr>\n\
                                            <th></th>\n\
                                            <td><a href="detail.html?id='+id+'"><button type="button" class="btn btn-primary">Details</button></a></td>\n\
                                        </tr>\n\
                                    </table>\n\
                                </div>\n\
                            </div>\n\
                        <hr/>';
                $('#searchResults').append(row);
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

function loadSearchParameters() {
    var date = new Date();
    var currentYear = date.getFullYear();
    var years = currentYear - 2000;
    var year = 2000;
    
    var newUsed = $('newUsed').val();
    if (newUsed === "Used"){
        year--;
    }
    for (var i = 1; i <= years + 2; i++) {
        $('#yearMin').append("<option value = " + year + ">" + year + "</option>");
        $('#yearMax').append("<option value = " + year + ">" + year + "</option>");
        year++;
    }

    var money = 1000;
    for (var i = 1; i <= 1000; i++) {
        $('#priceMin').append("<option value = " + money + ">" + money + "</option>");
        $('#priceMax').append("<option value = " + money + ">" + money + "</option>");
        money += 1000;
    }
}