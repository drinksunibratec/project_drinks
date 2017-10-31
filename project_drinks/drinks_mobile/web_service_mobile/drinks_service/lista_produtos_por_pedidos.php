<?
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

if (isset($_POST['codPedido'])){
	$codPedido = $_POST['codPedido'];
	$pedido = $db->listaPedidosPorID($codPedido);
	$produtos = $db->listaProdutosPorPedido($codPedido);
	$response = null;
	
	
	if ($produtos) {
	
		
		$json_pedido = json_encode($pedido);
		$json_pedido = substr($json_pedido ,0,-1);
		$json_produto = json_encode($pedido);
		
	
		$json_str = $json_pedido . ', "produtos" : ' . json_encode($produtos) . '}';
		//$json_str = json_encode($estabelecimento ) . ', ' . json_encode($produtos) ;
		
		
		echo $json_str;
	}
	
}

?>