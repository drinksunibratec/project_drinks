<?php
require '../../resources/functions/DB_Connect.php';

print_r($_POST);

if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false)  {
    $codProduto = addslashes($_GET['codProduto']);
    $eanProduto = addslashes($_GET['ean']);  
    
    $preco = $_REQUEST['preco'];
       
    
    session_start();
    $codEstabelecimento = addslashes($_SESSION['codEstabelecimento']);
//  echo $eanProduto;
    
    $database = open_database();
    
   
     
    
    $sql = "INSERT INTO produto_estab(codProduto, codEstabelecimento, ean, preco)"
            . "VALUES ($codProduto, $codEstabelecimento, $eanProduto, $preco);";
   echo $sql;
    try {
//        $database->query($sql);
        
        $_SESSION['message'] = 'Registro Cadastrado com sucesso.';
        $_SESSION['type'] = 'success';
//        header("Location: listaProdutoVinculados.php");
    } catch (Exception $e) {
        
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao.';
        $_SESSION['type'] = 'danger';
        header("Location: listaProdutoEstabelecimento.php");
    }
    
    close_database($database);
}