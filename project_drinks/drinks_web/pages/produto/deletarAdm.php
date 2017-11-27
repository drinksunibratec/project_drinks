<?php
require '../../resources/functions/DB_Connect.php';

if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false) {
    $codProduto = addslashes($_GET['codProduto']);

    session_start();
    
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
    $database = open_database();
//    
    $q = "SELECT COUNT(*) FROM pedido_produto WHERE codProduto =$codProduto";
//    echo $q;
//   
       $result = $database->query($q);
       $row = mysqli_fetch_row($result);
        if ($row[0] > 0) {
//            echo "tem dependencia";
//            echo 'Nao foi possivel realizar a operacao. Este Produto pode estar num pedido!';
            echo "<script>"
                    . "alert('PROIBIDO! Pedido Vinculado a este Produto!');"
                    . "window.self.location.href = 'lista.php';"
               . "</script>";
        } else {
           $sql2 = "DELETE FROM produto WHERE codProduto = $codProduto";
           $result2 = $database->query($sql2);
           $row2 = mysqli_affected_rows($result2);
        
                if ($row2[0] > 0){
                   echo "<script>"
                        . "alert('PROIBIDO! Produto Vinculado a um Estabelecimento!');"
                        . "window.self.location.href = 'lista.php';"
                   . "</script>";
               }else {
                    $sql = "DELETE FROM produto_estab WHERE codProduto = $codProduto AND codEstabelecimento = $codEstabelecimento";
                    echo $sql;
                    echo "<br>";

                    echo $sql2;
                    try {
                         $database->query($sql);
                         $database->query($sql2);
                         $_SESSION['message'] = 'Excluído com sucesso.';
                         $_SESSION['type'] = 'success';
                         header("Location: lista.php");
                     } catch (Exception $ex) {
                         $_SESSION['message'] = 'Nao foi possivel realizar a operacao. Este Produto pode estar num pedido!';
                         $_SESSION['type'] = 'danger';
                          header("Location: lista.php");
                     }
                }
        }
            
    close_database($database);
}
