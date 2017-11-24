<?
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

$produtos = $db->listarTodosOsProdutos();

if ($produtos) {
	
	$json_str = "{ produtos: ". json_encode($produtos) . "}";
	
	echo $json_str;
}

?>