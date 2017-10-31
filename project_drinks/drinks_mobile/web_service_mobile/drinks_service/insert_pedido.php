<?php 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

if (isset($_POST['json_pedido'])){
	$json_input_string = $_POST['json_pedido'];
	$input_data = json_decode($json_input_string);
	$db->inserirPedido($input_data);
	
	$pedido = $db->consultaUltimoPedido();
	$retorno = json_encode($pedido);
	echo $retorno;
}


?>