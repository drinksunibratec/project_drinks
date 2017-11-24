<?php
require '../../resources/functions/DB_Connect.php';

if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false) {
    $codProduto = addslashes($_GET['codProduto']);
    
    
    session_start();
     $database = open_database();
     
     $codEstabelecimento = addslashes($_SESSION['codEstabelecimento']);
    
    $sql = "INSERT INTO `produto_estab`(`codProduto`, `codEstabelecimento`, `ean`, `preco`)  VALUES ($codProduto, $codEstabelecimento, '', 0);";
    echo $sql;
    try {
        
        $database->query($sql);
        
        $_SESSION['message'] = 'Registro Vinculado com sucesso.';
//        $_SESSION['message'] = $sql;
        $_SESSION['type'] = 'success';
        header("Location: listaProdutoVinculados.php");
    } catch (Exception $e) {
        
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao.';
        $_SESSION['type'] = 'danger';
        header("Location: listaProdutoEstabelecimento.php");
    }
    
    close_database($database);
}