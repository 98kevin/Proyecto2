https://code.jquery.com/jquery-3.3.1.min.js	

$(document).ready(function() {
		$('#submit').click(function(event) {
			var nombreVar = $('#nombre').val();
			var apellidoVar = $('#apellido').val();
			var edadVar = $('#edad').val();
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('ActionServlet', {
				nombre : nombreVar,
				apellido: apellidoVar,
				edad: edadVar
			}, function(responseText) {
				$('#revistas').html(responseText);
			});
		});
    });


    
	misRevistas= document.getElementById("misRevistas").addEventListener('click',misRevistas,false);

	function misRevistas(){
		var correoVar = $('#idCorreo').val();
		var idUsuarioVar = $('#id-usuario').val();
		$.get('ControladorSuscriptor', {
			correo : correoVar,
			idUsuario : idUsuarioVar
		}, function(responseText) {
			$('#revistas').html(responseText);
		});
	}
	