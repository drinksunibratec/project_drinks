<?php
require '../../resources/functions/DB_Connect.php';


if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false) {
    $codProduto = addslashes($_GET['codProduto']);

    session_start();
    
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
    $database = open_database();
    
    $sql = "DELETE FROM produto_estab WHERE codProduto = $codProduto AND codEstabelecimento = $codEstabelecimento";
//    echo $sql;
    try {
        $database->query($sql);
        $_SESSION['message'] = 'Excluído com sucesso.';
        $_SESSION['type'] = 'success';
        header("Location: listaProdutoEstabelecimento.php");
    } catch (Exception $ex) {
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao.';
        $_SESSION['type'] = 'danger';
         header("Location: listaProdutoEstabelecimento.php");
    }
}