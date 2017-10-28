<?php

/**
 * @author Silvio Cedrim
 * 
 */

require_once 'include/DB_Functions.php';
$db = new DB_Functions();


if (isset($_POST['email']) && isset($_POST['senha'])) {

    // receiving the post params
    $email = $_POST['email'];
    $senha = $_POST['senha'];

    // get the user by email and password
    $usuario = $db->getUserByEmailAndPassword($email, $senha);

    if ($usuario) {
       
        echo json_encode($usuario);
    }
} 
?>

