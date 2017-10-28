<?php

/**
 * @author Silvio Cedrim
 */

require_once 'include/DB_Functions.php';
$db = new DB_Functions();



if (isset($_POST['nome']) && isset($_POST['email']) && isset($_POST['senha']) && isset($_POST['telefone'])) {

	// receiving the post params
	$nome = $_POST['nome'];
	$email = $_POST['email'];
	$senha = $_POST['senha'];
	$telefone = $_POST['telefone'];

    if ($db->isUserExisted($email)) {
	// Usuario j? existe
	$response["erro"] = TRUE;
	$response["tipo_erro"] = 1;
	$response["codUsuario"] = "0";
	$response["nome"] = "0";
	$response["email"] = "0";
	$response["telefone"] = "0";
	echo json_encode($response);
    } else {
        // create a new user
        $usuario = $db->storeUser($nome, $email, $senha, $telefone);
        if ($usuario) {
            	// user stored successfully
           	$response["erro"] = FALSE;
		$response["tipo_erro"] = 0;
		$response["codUsuario"] = $usuario["codUsuario"];
		$response["nome"] = $usuario["nome"];
		$response["email"] = $usuario["email"];
            	$response["telefone"] = $usuario["telefone"];
            	echo json_encode($response);
        } else {
	            // user failed to store
		$response["error"] = TRUE;
		$response["tipo_error"] = 2;
		$response["codUsuario"] = "0";
		$response["nome"] = "0";
		$response["email"] = "0";
		$response["telefone"] = "0";
				
            	echo json_encode($response);
        }
    }
} else {
    	$response["erro"] = TRUE;
	$response["tipo_erro"] = 3;
	$response["codUsuario"] = "0";
	$response["nome"] = "0";
	$response["email"] = "0";
	$response["telefone"] = "0";
	echo json_encode($response);
}
?>

