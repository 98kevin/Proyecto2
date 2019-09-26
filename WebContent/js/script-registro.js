
$("#profileImage").click(function(e) {
    $("#imageUpload").click();
});

function fasterPreview( uploader ) {
    if ( uploader.files && uploader.files[0] ){
          $('#profileImage').attr('src', 
             window.URL.createObjectURL(uploader.files[0]) );
    }
}

$("#imageUpload").change(function(){
    fasterPreview( this );
});
 
function evaluarPassword(){
	var p1 = document.getElementById("password").value;
	var p2 = document.getElementById("password2").value;
	var espacios = false;
	var cont = 0;
	
	while (!espacios && (cont < p1.length)) {
		  if (p1.charAt(cont) == " ")
		    espacios = true;
		  cont++;
	}
 
	if (espacios) {
		alert ("La contraseña no puede contener espacios en blanco");
	}

	if (p1.length == 0 || p2.length == 0) {
	    	alert("Los campos de la password no pueden quedar vacios");
	  }

	if (p1 != p2) {
	    alert("Las passwords deben de coincidir");
	  }
}