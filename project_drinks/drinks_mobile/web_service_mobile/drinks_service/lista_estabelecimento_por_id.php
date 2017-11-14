<?
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

if (isset($_POST['codEstabelecimento'])){
	$codEstabelecimento = $_POST['codEstabelecimento'];
	$estabelecimento = $db->listaEstabelecimentoPorId($codEstabelecimento);
	$response = null;
	
	
	if ($estabelecimento) {
		
		$json = json_encode($estabelecimento);
		
	
		//$json_str = $json_estabelecimento . ', "produtos" : ' . json_encode($produtos) . '}';
				
		echo $json;
	}
	
}

?>