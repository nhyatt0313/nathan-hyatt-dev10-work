$(document).ready(function(){
  loadSnacks();

  $('#dollar-button').click(function(event){
    //addMoney(1.00);

    var money = 1.00;

    var currentMoney = parseFloat($('#money-total').val());
    var total = currentMoney + money;
    $('#money-total').val(total.toFixed(2));
  });
  $('#quarter-button').click(function(event){
    addMoney(0.25);
  });
  $('#dime-button').click(function(event){
    addMoney(0.10);
  });
  $('#nickel-button').click(function(event){
    addMoney(0.05);
  });
  $('#penny-button').click(function(event){
    addMoney(0.01);
  });
});

function loadSnacks(){
  clearSnackTable();

  var contentRows = $('#contentRows');

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/VendingMachine/snacks',
    success: function(snackArray){
      $.each(snackArray, function(index, snack){
        var id = snack.id;
        var type = snack.type;
        var price = snack.price;
        var numInStock = snack.numInStock;

        var row = '<tr>';
        row += '<td>' + id + '</td>';
        row += '<td>' + type + '</td>';
        row += '<td>' + price + '</td>';
        row += '<td>' + numInStock + '</td>';
        row += '<td><a onClick="buySnack(\'' + id + '\')">Buy</a></td>';
        row += '</tr>';

        contentRows.append(row);
      });
    },
    error: function(data){
      $('#errorMessages')
      .append($('<li>')
      .attr({class: 'list-group-item list-group-item-danger'})
      .text(data.responseText));
    }
  });
}

function clearSnackTable(){
  $('#contentRows').empty();
}

function addMoney(money){
  var currentMoney = parseFloat($('#money-total').val());
  var total = currentMoney + money;
  $('#money-total').val(total.toFixed(2));
}

function buySnack(id){

  // get Money
  var money = $('#money-total').val();

    $.ajax({
      type: 'put',
      url: 'http://localhost:8080/VendingMachine/buy/' + id + '/' + money,
      success: function(change){
        $('#change-dollars').val(change.dollars);
        $('#change-quarters').val(change.quarters);
        $('#change-dimes').val(change.dimes);
        $('#change-nickels').val(change.nickels);
        $('#change-pennies').val(change.pennies);

        alert("Snack Bought");
        clearMoney();
        loadSnacks();
        $('#errorMessages').empty();

      },
      error: function(data){
        $('#errorMessages')
        .append($('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text(data.responseText));
      }
    });
}
function clearMoney(){
  $('#money-total').val('0.00');
}
