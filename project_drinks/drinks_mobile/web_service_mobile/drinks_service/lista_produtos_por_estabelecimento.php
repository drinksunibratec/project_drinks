<?
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

if (isset($_POST['codEstabelecimento'])){
	$codEstabelcimento = $_POST['codEstabelecimento'];
	$estabelecimento = $db->listaEstabelecimentoPorId($codEstabelcimento);
	$produtos = $db->listaProdutosPorEstabelecimento($codEstabelcimento);
	$response = null;
	
	
	if ($produtos) {
	
		
		$json_estabelecimento = json_encode($estabelecimento);
		$json_estabelecimento = substr($json_estabelecimento ,0,-1);
		$json_produto = json_encode($estabelecimento);
		
	
		$json_str = $json_estabelecimento . ', "produtos" : ' . json_encode($produtos) . '}';
		//$json_str = json_encode($estabelecimento ) . ', ' . json_encode($produtos) ;
		
		
		echo $json_str;
	}
	
}

?>