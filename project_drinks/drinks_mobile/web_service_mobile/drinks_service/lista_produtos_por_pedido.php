<?
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

if (isset($_POST['codPedido'])){
	$codPedido = $_POST['codPedido'];
	$produtos_pedido = $db->listarProdutosPedidos($codPedido);
	$response = null;
	
	
	if ($produtos_pedido) {
		
		$json_str = "{ produtos: ". json_encode($produtos_pedido) . "}";
		
	
		//$json_str = $json_estabelecimento . ', "produtos" : ' . json_encode($produtos) . '}';
				
		echo $json_str;
	}
	
}

?>