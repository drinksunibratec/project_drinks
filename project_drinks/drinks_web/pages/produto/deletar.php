<?php
require '../../resources/functions/DB_Connect.php';

if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false) {
    $codProduto = addslashes($_GET['codProduto']);
    
     $database = open_database();
    
    $sql = "DELETE FROM produto WHERE codProduto = '$codProduto'";
    
    try {
        
        $database->query($sql);
        
        $_SESSION['message'] = 'Registro excluido com sucesso.';
//        $_SESSION['message'] = $sql;
        $_SESSION['type'] = 'success';
        header("Location: lista.php");
    } catch (Exception $e) {
        
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao.';
        $_SESSION['type'] = 'danger';
         header("Location: lista.php");
    }
    
    close_database($database);
}