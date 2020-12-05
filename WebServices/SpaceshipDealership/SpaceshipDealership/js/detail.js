$(document).ready(function () {
    var id = getUrlVars()["id"];
    
    var vehicleDisplay = $('#vehicle');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/vehicle/admin/getvehicle/'+id,
        success: function (vehicle, status) {
            
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
                var description = vehicle.description;
                var row = '<div class="content-box">\n\
                                <h4>' + year + ' ' + make + ' ' + model + '</h4>\n\
                                <div class="col-lg-3 col-md-3">\n\
                                    <img src="../images/' + fileImg + '" alt="" style="width:70%;">\n\
                                </div>\n\
                                <div class="row">\n\
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
                                                <td></td>\n\
                                            </tr>\n\
                                        </table>\n\
                                    </div>\n\
                                </div>\n\
                                <div class="row" style="margin-top: 20px;">\n\
                                    <div class="col-lg-3"></div>\n\
                                    <div calss="col-lg-9">\n\
                                        <table>\n\
                                            <th style="text-align: right;">Description:</th>\n\
                                            <td style="text-align: left;">'+ description +'</td>\n\
                                        </table>\n\
                                    </div>\n\
                                </div>\n\
                                <div class="row">\n\
                                    <div class="col-md-9">\n\
                                    </div>\n\
                                    <div class="col-md-3">\n\
                                        <table>\n\
                                            <th></th>\n\
                                            <td><button type="button" onclick="showContactPage(' + id + ')">Contact Us</button></td>\n\
                                        </table>\n\
                                    </div>\n\
                                </div>\n\
                            </div>';
                $('#vehicle').append(row);
        },
        error: function (response) {
            $('#errorMessage')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text(response.responseText));
        }
    });
});

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}