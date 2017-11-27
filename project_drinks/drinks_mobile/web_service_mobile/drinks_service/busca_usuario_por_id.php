
<?
require_once 'include/DB_Functions.php';
$db = new DB_Functions();

if (isset($_POST['codUsuario'])){
	$codUsuario = $_POST['codUsuario'];
	$usuario = $db->getUsuarioPorId($codUsuario);
	$response = null;
	
	
	if ($usuario) {
		
		$json = json_encode($usuario);
				
		echo $json;
	}
	
}

?>