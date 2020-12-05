$(document).ready(function () {
    var idv = getUrlVars()["id"];
    var userId = getUrlVars()["userId"];

    showVehicleDetails(idv);


});



function showVehicleDetails(id) {


    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/vehicle/admin/getvehicle/' + id,
        success: function (data, status) {
            var year = data.year;
            var make = data.make.name;
            var model = data.model.name;
            var salePrice = data.salePrice;
            var fileImg = data.fileImg;
            var style = data.style;
            var trans = data.trans;
            var color = data.color;
            var interior = data.interior;
            var mileage = data.mileage;
            var vin = data.vin;
            var msrp = data.msrp;
            var id = data.id;
            var isNew = data.newVehicle;
            var description = data.description;
            var featured = data.featured;
            var modelId = data.model.id;
            var makeId = data.make.id;

            $('#make').val(make);
            $('#model').val(model);
            $('#year').val(year);
            $('#type').val(isNew);
            $('#style').val(style);
            $('#transmission').val(trans);
            $('#exterior').val(color);
            $('#interior').val(interior);
            $('#mileage').val(mileage);
            $('#vin').val(vin);
            $('#description').val(description);
            $('#msrp').val(msrp);
            $('#salesPrice').val(salePrice);
            $('#isFeatured').attr('checked', featured);

            $('#filename').val(fileImg);

            var row = '<div class="content-box">';
            row += '<div class="row">';
            row += '<h4>' + year + ' ' + make + ' ' + model + '</h4>';
            row += '</div>';
            row += '</div>';
            var buttonRow2 = '<button type="button" class="btn btn-primary" id="Save-button" onclick="saveVehicle(' + id + ',' + makeId + ',' + modelId + ')" > Save </button>';


            $('#deleteAndSave').append(buttonRow2);

            $('#detailResults').append(row);
            var imageRow = '<div class="col-lg-3 col-md-3">';
            imageRow += '<img src="../images/' + fileImg + '" alt="" style="width:70%;">';
            imageRow += '</div>';
            $('#carImage').append(imageRow);
        },
        error: function (response) {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text(response.responseText));
        }
    });

}



$('#upload').change(function () {
    var filename = $(this).val();
    var lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex + 1);
    }
    $('#filename').val(filename);
});

function saveVehicle(idv, makeId, modelId) {
    var newYear = $('#year').val();
    var newPrice = $('#salesPrice').val();
    var newImg = $('#filename').val();
    var newStyle = $('#style').val();
    var newTrans = $('#transmission').val();
    var newExterior = $('#exterior').val();
    var newInterior = $('#interior').val();
    var newMilage = parseInt($('#mileage').val());
    var newVin = $('#vin').val();
    var newMsrp = $('#msrp').val();
    var newerVehicle = $('#type').val();
    var newDescription = $('#description').val();
    var newFeatured = $('#isFeatured').val();
    var newSold = "false";
    var userId = getUrlVars()["userId"];

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/vehicle/admin/editvehicle',
        data: JSON.stringify({
            id: idv,
            year: newYear,
            make: {id: makeId},
            model: {id: modelId},
            salePrice: newPrice,
            fileImg: newImg,
            style: newStyle,
            trans: newTrans,
            color: newExterior,
            interior: newInterior,
            mileage: newMilage,
            vin: newVin,
            msrp: newMsrp,
            user: {id: userId},
            newVehicle: newerVehicle,
            description: newDescription,
            featured: newFeatured,
            sold: newSold
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success: function (data, status) {
            // clear errorMessages
            $('#errorMessages').empty();
            alert("Vehicle Saved");
            location.assign('admin-vehilce.html');
        },
        error: function (response) {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text(response.responseText));
        }
    });
}



function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}