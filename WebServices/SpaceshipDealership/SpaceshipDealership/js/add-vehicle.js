$(document).ready(function () {
    loadMakes();
    $('#save-button').click(function (event) {
        saveVehicle();
    });
});

$('#upload').change(function () {
    var filename = $(this).val();
    var lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex + 1);
    }
    $('#filename').val(filename);
});

function saveVehicle() {
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
    var newerVehicle = $('#isNew').is(':checked');
    var newDescription = $('#description').val();
    var newFeatured = $('#isFeatured').is(':checked');
    var newSold = "false";

    var userId = getUrlVars()["userId"];

    var makeId = $('#make option:selected').val();
    var modelId = $('#model option:selected').val();

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/vehicle/admin/addvehicle',
        data: JSON.stringify({
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
            location.assign('admin-vehicle.html');
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

function loadMakes() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/make/admin/allmake',
        success: function (data, status) {
            $.each(data, function (index, make) {
                $('#make').append('<option value=' + make.id + '>' + make.name + '</select>');
            });
        },
        error: function (response) {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text(response.responseText));
        }

    });
}

function loadModels() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/model/admin/allmodel',
        success: function (data, status) {

            var id = $('#make option:selected').val();
            $('#model').empty();
            $('#defult-make').hide();
            $('#model').append('<option value=null >Choose Model</select>');
            $.each(data, function (index, model) {
                if (model.make.id == id) {
                    $('#model').append('<option value=' + model.id + ' >' + model.name + '</select>');
                }
            });
        },
        error: function (response) {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text(response.responseText));
        }

    });
}

function hideDefaultModel() {
    $('#defult-model').hide();
}