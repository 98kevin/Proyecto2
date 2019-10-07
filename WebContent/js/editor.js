		reporteSuscripciones = document.getElementById("reporteSuscripciones").addEventListener('click',reporteSuscripciones,false);
		
		reporteGanancias = document.getElementById("reporteGanancias").addEventListener('click',reporteGanancias,false);
		
		function reporteSuscripciones(){
			var idUsuarioVar = $('#id-usuario').val();
			$.get('ReporteEditorSuscripciones', {
				idUsuario : idUsuarioVar
			}, function(responseText) {
				$('#tablaDeResultados').html(responseText);
			});
		}
		
		function reporteGanancias(){
			var idUsuarioVar = $('#id-usuario').val();
			$.get('ReporteEditorGanancias', {
				idUsuario : idUsuarioVar
			}, function(responseText) {
				$('#tablaDeResultados').html(responseText);
			});
		}
		
		
		