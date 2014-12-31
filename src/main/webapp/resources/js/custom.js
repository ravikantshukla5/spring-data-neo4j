/**
 * Contains custom JavaScript code
 */
var urlHolder = new Object();

function loadTable() {
	$.get(urlHolder.records, function(response) {
		
 		$('#tableUsers').find('tbody').remove();
 		
 		for (var i=0; i<response.authors.length; i++) {
			var row = '<tr>';
			row += '<td><input type="radio" name="index" id="index" value="'+i+'"></td>';
			row += '<td>' + response.authors[i].username + '</td>';
			row += '<td>' + response.authors[i].firstName + '</td>';
			row += '<td>' + response.authors[i].lastName + '</td>';
			row += '<td>' + response.authors[i].designation + '</td>';
			row += '<td>' + getArticle(response.authors[i].article) + '</td>';
			row += '</tr>';
	 		$('#tableUsers').append(row);
 		}
 		
 		$('#tableUsers').data('model', response.authors);
		toggleForms('hide'); ;
 	});
}

function submitNewRecord() {
	$.post(urlHolder.add, {
			username: $('#newUsername').val(),
			password: $('#newPassword').val(),
			firstName: $('#newFirstName').val(),
			lastName: $('#newLastName').val(),
			designation: $('#newDesignation').val(),
			article: $('#newArticle').val()
		}, 
		function(response) {
			if (response != null) {
				loadTable();
				toggleForms('hide'); ;
				toggleCrudButtons('show');
				alert('Success! Record has been added.');
			} else {
				alert('Failure! An error has occurred!');
			}
		}
	);	
}

function submitDeleteRecord() {
	var selected = $('input:radio[name=index]:checked').val();
	
	$.post(urlHolder.del, {
			username: $('#tableUsers').data('model')[selected].username
		}, 
		function(response) {
			if (response == true) {
				loadTable(urlHolder.records);
				alert('Success! Record has been deleted.');
			} else {
				alert('Failure! An error has occurred!');
			}
		}
	);
}

function submitUpdateRecord() {
	$.post(urlHolder.edit, {
			username: $('#editUsername').val(),
			firstName: $('#editFirstName').val(),
			lastName: $('#editLastName').val(),
			designation: $('#newDesignation').val(),
			article: $('#editArticle').val()
		}, 
		function(response) {
			if (response != null) {
				loadTable();
				toggleForms('hide'); ;
				toggleCrudButtons('show');
				alert('Success! Record has been edited.');
			} else {
				alert('Failure! An error has occurred!');
			}
		}
	);
}

function getArticle(article) {
	if (article == 1) {
		return 'Neo4J-Database';
	} else if (article == 2) {
		return 'Spring-Data-Neo4J';
	} else {
		return 'Unknown';
	} 
}

function hasSelected() {
	var selected = $('input:radio[name=index]:checked').val();
	if (selected == undefined) { 
		alert('Select a record first!');
		return false;
	}
	
	return true;
}

function fillEditForm() {
	var selected = $('input:radio[name=index]:checked').val();
	$('#editUsername').val( $('#tableUsers').data('model')[selected].username );
	$('#editFirstName').val( $('#tableUsers').data('model')[selected].firstName );
	$('#editLastName').val( $('#tableUsers').data('model')[selected].lastName );
	$('#editDesignation').val( $('#tableUsers').data('model')[selected].designation );
	$('#editArticle').val( $('#tableUsers').data('model')[selected].article );
}

function resetNewForm() {
	$('#newUsername').val('');
	$('#newPassword').val('');
	$('#newFirstName').val('');
	$('#newLastName').val('');
	$('#newRole').val('2');
}

function resetEditForm() {
	$('#editFirstName').val('');
	$('#editLastName').val('');
	$('#editRole').val('2');
}

function toggleForms(id) {
	if (id == 'hide') {
		$('#newForm').hide();
		$('#editForm').hide();
		
	} else if (id == 'new') {
 		resetNewForm();
 		$('#newForm').show();
 		$('#editForm').hide();
 		
	} else if (id == 'edit') {
 		resetEditForm();
 		$('#newForm').hide();
 		$('#editForm').show();
	}
}

function toggleCrudButtons(id) {
	if (id == 'show') {
		$('#newBtn').removeAttr('disabled');
		$('#editBtn').removeAttr('disabled');
		$('#deleteBtn').removeAttr('disabled');
		$('#reloadBtn').removeAttr('disabled');
		
	} else if (id == 'hide'){
		$('#newBtn').attr('disabled', 'disabled');
		$('#editBtn').attr('disabled', 'disabled');
		$('#deleteBtn').attr('disabled', 'disabled');
		$('#reloadBtn').attr('disabled', 'disabled');
	}
}
