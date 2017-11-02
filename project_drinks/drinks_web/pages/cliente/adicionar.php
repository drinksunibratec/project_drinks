<?php
  require_once('../config.php'); 
 
  if(isset($_POST['nome']) && empty($_POST['nome']) == false){
    $email = addslashes($_POST['email']);

    $nome = addslashes($_POST['nome']);
    $telefone = addslashes($_POST['telefone']);
    $senha = md5(addslashes($_POST['senha']));
    

    $id = 0;

    // Usuario
    $sql = "INSERT INTO cliente SET nome = '$nome', email = '$email', telefone = '$telefone', senha = '$senha'";
    $PDO->query($sql);

    header("Location: cadastro-cliente.php");
  }
?>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags must come first in the head; any other head content must come after these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon2.ico">

    <meta charset="UTF-8">
    <script src="../biblioteca/jquery/jquery-1.5.2.min.js"></script>
    <script src="../biblioteca/jquery/jquery.maskedinput-1.3.min.js"></script>

    <title>Editar Usuário</title>

    <!-- Bootstrap core CSS -->
    <link href="../biblioteca/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../biblioteca/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../biblioteca/bootstrap-3.3.7/docs/assets/jsie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- MASCARA -->
     <script>
      jQuery(function($){
             $("#campoData").mask("99/99/9999");
             $("#campoCpf").mask("999.999.999-99");     
      });
     </script>
  </head>

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
              <li class="active"><a href="cadastro-administrador.php">Administrador</a></li>
              <li><a href="cadastro-cliente.php">Cliente</a></li>              
              <li><a href="cadastro-funcionario.php">Estabelecimento</a></li>  
              <li><a href="cadastro-produto">Produtos</a></li>  
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
      
      <div>

    <div class="jumbotron">
      <form method = "POST">
        <h2>Novo Cliente</h2> 
        Email:<br/> 
        <input type="text" class="form-control" style="width: 25%;" name="email"  required="true"/>
        Nome:<br/>
        <input type="text" class="form-control" style="width: 25%;" name="nome" required="true"/>
        Telefone:<br/>
        <input type="text" class="form-control" style="width: 25%;" name="telefone" />
        Senha:<br/>
        <input type="text" class="form-control" style="width: 25%;" name="senha" id="campoSenha" required="true"/>
       

        <input class="btn btn-success" type="submit" value = "&#10003 Cadastrar" />
        <a href="cadastro-cliente.php" class="btn btn-danger">&#10005 Cancelar</a>  
      </form>
  </div>

   

      <hr>


    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
      </ol>

      <footer>
        <p>&copy; 2016 Drinks, Inc.</p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
    <script src="..biblioteca/bootstrap-3.3.7/dist/jsbootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="..biblioteca/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>