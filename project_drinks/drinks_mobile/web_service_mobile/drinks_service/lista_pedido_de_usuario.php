<?
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

if (isset($_POST['codUsuario'])){
	$codUsuario = $_POST['codUsuario'];
	$pedidos = $db->listarPedidosDeUsuario($codUsuario);
	$response = null;
	
	
	if ($pedidos) {
		
		$json_str = "pedidos: ". json_encode($pedidos);
		
	
		//$json_str = $json_estabelecimento . ', "produtos" : ' . json_encode($produtos) . '}';
				
		echo $json_str;
	}
	
}

?>