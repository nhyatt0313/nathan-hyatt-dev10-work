$(document).ready(function () {
    loadSearchParameters();
});

function search() {
    $('#searchResults').empty();
    $('#search').show();
    var newUsed = $('#newUsed').val();
    if (newUsed === "1"){
        newUsed = true;
    }
    if (newUsed === "2"){
        newUsed = false;
    }
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/vehicle/admin/search',
        data: JSON.stringify({
            stringSearch: $('#select-item').val(),
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
                var row = '<div class="row">';
                row += '<h4>' + year + ' ' + make + ' ' + model + '</h4>';
                row += '<div class="col-lg-3 col-md-3">';
                row += '<img src="../../images/' + fileImg + '" alt="" style="width:70%;">';
                row += '</div>';
                row += '<div class="col-lg-3 col-md-3">';
                row += '<table>';
                row += '<tr>';
                row += '<th>Body Style:</th>';
                row += '<td>' + style + '</td>';
                row += '</tr>';
                row += '<tr>';
                row += '<th>Trans:</th>';
                row += '<td>' + trans + '</td>';
                row += '</tr>';
                row += '<tr>';
                row += '<th>Color:</th>';
                row += '<td>' + color + '</td>';
                row += '</tr>';
                row += '</table>';
                row += '</div>';
                row += '<div class="col-lg-3 col-md-3">';
                row += '<table>';
                row += '<tr>';
                row += '<th>Interior:</th>';
                row += '<td>' + interior + '</td>';
                row += '</tr>';
                row += '<tr>';
                row += '<th>Mileage:</th>';
                row += '<td>' + mileage + '</td>';
                row += '</tr>';
                row += '<tr>';
                row += '<th>VIN:</th>';
                row += '<td>' + vin + '</td>';
                row += '</tr>';
                row += '</table>';
                row += '</div>';
                row += '<div class="col-lg-3 col-md-3">';
                row += '<table>';
                row += '<tr>';
                row += '<th>Sales Price:</th>';
                row += '<td>' + salePrice + '</td>';
                row += '</tr>';
                row += '<tr>';
                row += '<th>MSRP:</th>';
                row += '<td>' + msrp + '</td>';
                row += '</tr>';
                row += '<tr>';
                row += '<th></th>';
                row += '<td><a href="purchase.html?id='+id+'"><button type="button" class="btn btn-primary">purchase</button></a></td>';
                row += '</tr>';
                row += '</table>';
                row += '</div>';
                row += '</div>';
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
    var newUsed = $('#newUsed').val();
    
    if (newUsed === "Used"){
        year--;
    }
    for (var i = 1; i <= years + 2; i++) {
        $('#yearMin').append("<option value = " + (i + 1) + ">" + year + "</option>");
        $('#yearMax').append("<option value = " + (i + 1) + ">" + year + "</option>");
        year++;
    }

    var money = 1000;
    for (var i = 1; i <= 1000; i++) {
        $('#priceMin').append("<option value = " + (i + 1) + ">" + money + "</option>");
        $('#priceMax').append("<option value = " + (i + 1) + ">" + money + "</option>");
        money += 1000;
    }
}