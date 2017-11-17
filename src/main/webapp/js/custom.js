$(document).ready(function() {
	
	/*de lei*/
	/* Gestion de DataTables */
	var table = $('.ordenable').DataTable(
			{
			    language: { url: '/CancionesApp/vendors/datatables/i18n/Spanish.json' }		
	
			});
	table.column( '0:visible' ).order( 'desc' ).draw();
	
	/*de ander
	$('.ordenable').DataTable({
		"order": [[ 0, "desc" ]]
	});	
*/
	
	/* gestion de inputs para seleccionar todo el value cuando hacemos click */
	$('.selectAll').click(function(){		
		$(this).select();
	});
	
	
	$(".confirmDelete").click(function(event){
		//prevenir comportamiento del click en el href
		event.preventDefault();
		
		var href = $(this).attr('href');		
		$("#btn_eliminar").attr('href', href );		
		//abrir modal
		$("#modalEliminar").modal();
				
		//cancelar el 1 enlace
		//return false;		
	});
	
	$('#exampleModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var recipient = button.data('whatever') // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  modal.find('.modal-title').text('New message to ' + recipient)
		  modal.find('.modal-body input').val(recipient)
		})
	
});