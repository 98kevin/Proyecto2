
function calcularTotal(){
	var numeroDePagos = document.getElementById("cantidad-de-pagos");
	var precio = docuement.getElementById("precioRevista");
	var total = numeroDePagos*precio;
	document.getElementById("total").innerHTML = total;
}
