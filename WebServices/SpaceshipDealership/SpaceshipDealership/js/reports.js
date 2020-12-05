$(document).ready(function () {
    var userId = getUrlLogin()["userId"];
    $('#sales-links').append('  <li><a href="sales-reports.html?userId='+userId+'">Sales Report</a></li>\n\
                                <li><a href="inventory-reports.html?userId='+userId+'">Inventory Report</a></li>');
});

function getUrlLogin() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}
