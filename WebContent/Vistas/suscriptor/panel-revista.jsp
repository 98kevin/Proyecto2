<%@include file="../../style/style.html" %>

<div class="btn-group  btn-group-lg btn-group-justified">
  <button type="button" id="like" class="btn btn-default"><img src="../../../img/me-gusta.png"  alt="like" />Me gusta</button>
  <input type="text" size="30">
  <button type="button"  id="comentario"class="btn btn-default"><img src="../../../img/comentario.png" alt="comentario" />Comentar</button> 
</div>

<script> 
	like.addEventListener("click", modificarMeGusta);
	
	 function modificarMeGusta() {$.get('controladorMeGusta'), function(data) { 
 			alert(data);
 			$('#data').text(data);
 	};}
</script>