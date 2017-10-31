<?php 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

if (isset($_POST['json_produtos_pedido'])){
	$json_input_string = $_POST['json_produtos_pedido'];
	$input_data = json_decode($json_input_string);
	$db->inserirProdutosDoPedido($input_data);
}


?>