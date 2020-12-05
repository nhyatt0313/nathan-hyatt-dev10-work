$(document).ready(function () {
    var userId = getUrlLogin()["userId"];
    $('#admin-links').append('  <li><a href="admin-vehicle.html?userId='+userId+'">Vehicles</a></li>\n\
                                <li><a href="admin-user.html?userId='+userId+'">Users</a></li>\n\
                                <li><a href="admin-special.html?userId='+userId+'">Specials</a></li>\n\
                                <li><a href="admin-reports.html?userId='+userId+'">Reports</a></li>');
});

function getUrlLogin() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}
