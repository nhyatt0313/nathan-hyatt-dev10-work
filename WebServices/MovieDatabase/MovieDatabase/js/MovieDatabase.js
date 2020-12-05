$(document).ready(function () {
	loadMovies();

	$('#add-button').click(function(event){

		$.ajax({
			type: 'POST',
			url: 'http://localhost:8080/MovieDatabase/add',
			data: JSON.stringify({
				title: $('#add-title').val(),
				releaseDate: $('#add-release-date').val(),
				mpaaRating: $('#add-mpaa-rating').val(),
				directorName: $('#add-director-name').val(),
				studio: $('#add-studio').val(),
				userRating: $('#add-user-rating').val()
			}),
			headers: {
				'Accept': 'application/json',
                'Content-Type': 'application/json'
			},
			'dataType': 'json',
			success: function() {

				$('#errorMessages').empty();

				$('#add-title').val('');
				$('#add-release-date').val('');
				$('#add-mpaa-rating').val('');
				$('#add-director-name').val('');
				$('#add-studio').val('');
				$('#add-user-rating').val('');
				loadMovies();
			},
			error: function(){
				$('#errorMessages')
					.append($('<li>')
					.attr({class: 'list-group-item list-group-item-danger'})
					.text('Error calling web service.  Please try again later.'));
			}
		});
	});

	$('#edit-button').click(function(event){

		$.ajax({
			type: 'PUT',
			url: 'http://localhost:8080/MovieDatabase/edit/' + $('#edit-movie-id').val(),
			data: JSON.stringify({
				movieId: $('#edit-movie-id').val(),
				title: $('#edit-title').val(),
				releaseDate: $('#edit-release-date').val(),
				mpaaRating: $('#edit-mpaa-rating').val(),
				directorName: $('#edit-director-name').val(),
				studio: $('#edit-studio').val(),
				userRating: $('#edit-user-rating').val()
			}),
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			'dataType': 'json',
			success: function() {
				$('#errorMessages').empty();
				hideEditForm();
				loadMovies();

			},
			error: function(a, b, c){
				$('#errorMessages')
					.append($('<li>')
					.attr({class: 'list-group-item list-group-item-danger'})
					.text('Error calling web service.  Please try again later.'));
			}
		});
	});

	$('#delete-button').click(function(event){
		$.ajax({
			type: 'DELETE',
			url: 'http://localhost:8080/MovieDatabase/delete/' + $('#delete-movie-id').val(),
			success: function(){
				alert("Movie "+$('#delete-movie-id').val()+" successfully deleted.");
				loadMovies();
				hideViewDeleteForm();
			},
			error: function(a,b,c){
			$('#errorMessages')
				.append($('<li>')
				.attr({class: 'list-group-item list-group-item-danger'})
				.text('Error calling web service.  Please try again later.'));
		}
		})
	});

});


function clearMovieTable(){
	$('#contentRows').empty();
}

function loadMovies(){

	clearMovieTable();

	var contentRows = $('#contentRows');

	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/MovieDatabase/movies',
		success: function(movieArray){
			$.each(movieArray, function(index, movie){
				var id = movie.movieId;
				var title = movie.title;


				var row = '<tr>';
					row += '<td>' + id + '</td>';
					row += '<td>' + title + '</td>';
					row += '<td><a onClick="showViewForm('+id+')">View</a></td>';
					row += '<td><a onClick="showEditForm('+id+')">Edit</a></td>';
					row += '<td><a onClick="showDeleteForm('+id+')">Delete</a></td>';

					row += '</tr>';

				contentRows.append(row);
			});
		},
		error: function(){
			$('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
		}
	});
}

function showViewForm(movieId){
	$('delete-button').hide();
	$('#errorMessages').empty();

	$.ajax ({
		type: 'GET',
		url: 'http://localhost:8080/MovieDatabase/movies/' + movieId,
		success: function(data, status){
			$('#view-title').text(data.title);
			$('#view-release-date').text(data.releaseDate);
			$('#view-mpaa-rating').text(data.mpaaRating);
			$('#view-director-name').text(data.directorName);
			$('#view-studio').text(data.studio);
			$('#view-user-rating').text(data.userRating);
		},
		error: function(){
			$('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
		}

	});
	$('#movieTableDiv').hide();
	$('#viewMovieDiv').show();
}

function hideViewDeleteForm(){
	$('#errorMessages').empty();
	$('#movieTableDiv').show();
	$('#viewMovieDiv').hide();
}

function showEditForm(movieId){
	$('#errorMessages').empty();

	$.ajax ({
		type: 'GET',
		url: 'http://localhost:8080/MovieDatabase/movies/' + movieId,
		success: function(data, status){
			$('#edit-title').val(data.title);
			$('#edit-release-date').val(data.releaseDate);
			$('#edit-mpaa-rating').val(data.mpaaRating);
			$('#edit-director-name').val(data.directorName);
			$('#edit-studio').val(data.studio);
			$('#edit-user-rating').val(data.userRating);

			$('#edit-movie-id').val(movieId);
		},
		error: function(a, b, c){
			$('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service.  Please try again later.'));
		}
	});
	$('#movieTableDiv').hide();
	$('#editMovieDiv').show();
}


function hideEditForm(){
	$('#errorMessages').empty();

	$('#edit-title').val('');
	$('#edit-release-date').val('');
	$('#edit-mpaa-rating').val('');
	$('#edit-director-name').val('');
	$('#edit-studio').val('');
	$('#edit-user-rating').val('');

	$('#editMovieDiv').hide();
	$('#movieTableDiv').show();
}

function showDeleteForm(movieId){
	showViewForm(movieId);
	$('#delete-movie-id').val(movieId);
	$('#delete-button').show();
}
