<?
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
$estabelecimentos = $db->listaEstabelecimentos();
$response = null;


if ($estabelecimentos != null) {

	$json_str = json_encode($estabelecimentos );
	
	echo '{ "estabelecimentos" :' . $json_str . '}';
}

?>