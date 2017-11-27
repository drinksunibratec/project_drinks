<?php
require '../../resources/functions/DB_Connect.php';

if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false) {
    $codProduto = addslashes($_GET['codProduto']);

    session_start();
    
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
    $database = open_database();
    
    
    $sql = "DELETE FROM produto_estab WHERE codProduto = $codProduto AND codEstabelecimento = $codEstabelecimento";
//    $sql = "DELETE  produto_estab FROM produto_estab as pe LEFT OUTER JOIN pedido_produto pp"
//            . "ON pe.codProduto = pp.codProduto WHERE pp.codProduto = $codProduto IS NULL"
//            . " AND codEstabelecimento = $codEstabelecimento";
//    $sql = "DELETE produto_estab WHERE codProduto = $codProduto NOT IN (SELECT codProduto FROM"
//            . " pedido_produto) AND codEstabelecimento = $codEstabelecimento";
//    echo $sql;
    try {
        $database->query($sql);
        $_SESSION['message'] = 'Excluído com sucesso.';
        $_SESSION['type'] = 'success';
        header("Location: listaProdutoEstabelecimento.php");
    } catch (Exception $ex) {
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao. Este Produto pode estar num pedido!';
        $_SESSION['type'] = 'danger';
         header("Location: listaProdutoEstabelecimento.php");
    }
    close_database($database);
}