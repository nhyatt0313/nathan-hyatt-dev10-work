$(document).ready(function () {
    var userId = getUrlLogin()["userId"];
    getReports();
});

function getReports() {

    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/vehicle/admin/inventory/true',
        success: function (data, status) {

            $.each(data, function (index, item) {
                var year = item.year;
                var make = item.make;
                var model = item.model;
                var count = item.count;
                var stockValue = item.stockValue;


                var inventoryRow = '<tr><td>' + year + '</td>\n\
                                        <td>' + make + '</td>\n\
                                        <td>' + model + '</td>\n\
                                        <td>' + count + '</td>\n\
                                        <td>' + stockValue + '</td></tr>';



                $('#inventory-new-report').append(inventoryRow);




            });



        },
        error: function (response) {
            $('#errorMessage')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text(response.responseText));
        }
    });


    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/vehicle/admin/inventory/false',
        success: function (data, status) {

            $.each(data, function (index, item) {
                var year = item.year;
                var make = item.make;
                var model = item.model;
                var count = item.count;
                var stockValue = item.stockValue;


                var inventoryRow = '<tr><td>' + year + '</td>\n\
                                        <td>' + make + '</td>\n\
                                        <td>' + model + '</td>\n\
                                        <td>' + count + '</td>\n\
                                        <td>' + stockValue + '</td></tr>';



                $('#inventory-used-report').append(inventoryRow);




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


function getUrlLogin() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}