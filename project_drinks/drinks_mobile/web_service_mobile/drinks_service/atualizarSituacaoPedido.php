<?php 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

if (isset($_POST['codPedido']) && isset($_POST['status'])) {

	$codPedido = $_POST['codPedido'];
	$situacao = $_POST['status'];
	
	$db->updateSituacaoPedido($codPedido, $situacao);
	
	$pedido = $db->consultaPedidoPorId($codPedido);


	if ($pedido != null) {
	
		$json_str = json_encode($pedido);
		
		echo $json_str;
	}
	
}
?>