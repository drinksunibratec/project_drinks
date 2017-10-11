<?php
include (HEADER_TEMPLATE);
;

if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = addslashes($_GET['codEstabelecimento']);
    $sql = "DELETE FROM estabelecimento WHERE codEstabelecimento = '$codEstabelecimento'";
    $PDO->query($sql);
    
    header("Location: cadastro-estabelecimentos.php");
} else {
    header("Location: cadastro-estabelecimentos.php");
}