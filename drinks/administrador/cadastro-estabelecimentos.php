<?php
  require_once('../config.php'); 
 ?> 
    <div class="container">
      
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="inicial-administrador.php">Drinks</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="inicial-administrador.php">Home</a></li>
              <li><a href="cadastro-administrador.php">Administrador</a></li>
              <li><a href="cadastro-usuario.php">Clientes</a></li>              
              <li class="active"><a href="cadastro-estabelecimento.php">Estabelecimentos</a></li>  
              <li><a href="cadastro-produto.php">Produtos</a></li> 
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
      

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon2.ico">

    <title>Administração de Estabelicimento</title>

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
    $sql = "SELECT * FROM estabelecimento"; 
    $sql = $PDO->query($sql);
    if($sql->rowCount() == 0){
      echo '<div class="alert alert-danger" role="alert">';
      echo '<strong>Aviso!</strong> Você ainda não possui Estabelecimentos cadastrados.';
      echo '</div>';

      echo'<div class="jumbotron">';
      echo'<div class="container" style="width:600px; height: 400px; overflow: auto;">';
      echo'<img src="../biblioteca/img/broken-file.png">';
    }
  ?>

        <?php
          $r = 'excluir';
          $sql = "SELECT * FROM estabelecimento"; 
          $sql = $PDO->query($sql);
          
          if($sql->rowCount() > 0){

            echo'<div class="jumbotron">';
            echo'<div class="container" style="width:1200px; height: 400px; overflow: auto;">';
            echo'<h2>Lista de Estabelecimentos</h2>';
            echo '<table class="table table-hover">';
            echo  '<thead>';
            echo'<tr>';
            echo'<th>';
            echo'<th>CNPJ</th>';
            echo'<th>Email</th>';
            //echo'<th>bairro</th>';
            //echo'<th>CEP</th>';
            echo'<th>cidade</th>';
            //echo'<th>Latitude</th>';
            //echo'<th>Longitude</th>';
            //echo'<th>Numero</th>';
            //echo'<th>Rua</th>';
            echo'<th>UF</th>';
            echo'<th>Nome Fantasia</th>';
            echo'<th>Razao Social</th>';
            //echo'<th>Senha</th>';
            echo'<th>Telefone</th>';
            echo'</tr>';
            echo'</thead>';
            echo '<tbody>';
            echo '<tr>';

            foreach ($sql-> fetchAll() as $estabelecimento) {
                    echo '<td rowspan="2">'.$estabelecimento['codEstabelecimento'].'</td>';
                    echo'<td>'.$estabelecimento['cnpj']. '</td>';
                    echo'<td>'.$estabelecimento['eMail'].'</td>';
                    //echo'<td>'.$estabelecimento['bairro'].'</td>';
                    //echo'<td>'.$estabelecimento['cep'].'</td>';
                   // echo'<td>'.$estabelecimento['latitude'].'</td>';
                    //echo'<td>'.$estabelecimento['longitude'].'</td>';
                   // echo'<td>'.$estabelecimento['numero'].'</td>';
                   // echo'<td>R$ '.$estabelecimento['rua'].'</td>';
                    echo'<td>'.$estabelecimento['cidade'].'</td>';
                    echo'<td>'.$estabelecimento['uf'].'</td>';
                    echo'<td>'.$estabelecimento['nomeFantasia'].'</td>';
                    echo'<td>'.$estabelecimento['razaoSocial'].'</td>';
                    //echo'<td>'.$estabelecimento['senha'].'</td>';
                    echo'<td>'.$estabelecimento['telefone'].'</td>';
                    echo '<td><a href="editar-estabelecimento.php?codEstabelecimento='.$estabelecimento['codEstabelecimento'].'" class="btn btn-warning">&#9999; Editar</a></td>';
                    echo '<td><a href="excluir-estabelecimento.php?codEstabelecimento='.$estabelecimento['codEstabelecimento'].'" class="btn btn-danger">&#10006; Excluir</a></td>';
                echo '</tbody>';
              echo '</tr>';
            } 
          } 
        ?>
        </table>
      </div>
    </div>
      <a href="adicionar-estabelecimento.php" class="btn btn-success">&#10010 Novo estabelecimento</a>

      
    <div class="container theme-showcase" role="main">

    <br>
  <br>

      <!-- Main jumbotron for a primary marketing message or call to action -->
      

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