<?php
  require_once ('../config.php');

if(isset($_GET['id']) && empty($_GET['id']) == false){
  $id = addslashes($_GET['id']);

  $sql = "DELETE FROM estabelecimento WHERE codEstabelecimento = '$codEstabelecimento'";
  $PDO->query($sql);

  header("Location: cadastro-estabelecimentos.php");

} else {
  header("Location: cadastro-estabelecimentos.php");
}