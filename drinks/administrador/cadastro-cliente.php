<?php
  require_once('../config.php');
  require_once('../biblioteca/menu/menu.php');
 ?> 
    <div class="container">
      
     
      

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../biblioteca/img/favicon2.ico">

    <title>Administração de Usuários</title>

    <!-- Bootstrap core CSS -->
    <link href="../biblioteca/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="../biblioteca/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
     <link href="../biblioteca/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="theme.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../biblioteca/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>

  <body>
  <?php
    $sql = "SELECT * FROM cliente"; 
    $sql = $PDO->query($sql);
    if($sql->rowCount() == 0){
      echo '<div class="alert alert-danger" role="alert">';
      echo '<strong>Aviso!</strong> Você ainda não possui cliente cadastrados.';
      echo '</div>';

      echo'<div class="jumbotron">';
      echo'<div class="container" style="width:300px; height: 200px; overflow: auto;">';
      echo'<img src="../biblioteca/img/broken-file.png">';
    }
  ?>

        <?php
          $r = 'excluir';
          $sql = "SELECT * FROM cliente"; 
          $sql = $PDO->query($sql);
          
          if($sql->rowCount() > 0){

            echo'<div class="jumbotron">';
            echo'<div class="container" style="width:1200px; height: 400px; overflow: auto;">';
            echo'<h2>Lista de cliente</h2>';
            echo '<table class="table table-hover">';
            echo  '<thead>';
            echo'<tr>';
            echo'<th>Nome</th>';
            echo'<th>Email</th>';
            echo'<th>telefone</th>';
            echo'</tr>';
            echo'</thead>';
            echo '<tbody>';
            echo '<tr>';

            foreach ($sql-> fetchAll() as $cliente) {
                    echo'<td>'.$cliente['nome']. '</td>';
                    echo'<td>'.$cliente['email'].'</td>';
                    echo'<td>'.$cliente['telefone'].'</td>';
                echo '</tbody>';
              echo '</tr>';
            } 
          } 
        ?>
        </table>
      </div>
    </div>
     	
      
    <div class="container theme-showcase" role="main">

    <br>
  <br>

     

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../biblioteca/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
    <script src="../biblioteca/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../biblioteca/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>