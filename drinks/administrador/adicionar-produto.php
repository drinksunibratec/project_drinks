<?php
require_once ('../config.php');
require_once ('../biblioteca/menu/menu.php');

if (isset($_POST['nome']) && empty($_POST['nome']) == false) {
    
    $nome = $_POST['nome'];
    $descricao = $_POST['descricao'];
    $gelada = $_POST['gelada'];
    $preco = $_POST['preco'];
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
    
    $caracters = array(
        "R",
        "$"
    );
    $preco = str_replace($caracters, "", $preco);
    
    $sql = "INSERT INTO produto (nome, descricao, gelada, preco, CodEstabelecimento) VALUES ( '$nome','$descricao', '$gelada', '$preco', '$codEstabelecimento')";
    
    $retorno = $PDO->query($sql);
    $_SESSION['message'] = 'Produto cadastrado com sucesso.';
    $_SESSION['type'] = 'success';
    header("Location: cadastrar-produto.php");
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
<script src="../biblioteca/jquery/jquery.maskMoney.min.js"></script>

<title>Editar Produto</title>

<!-- Bootstrap core CSS -->
<link href="../biblioteca/bootstrap-3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="../biblioteca/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jumbotron.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="../biblioteca/bootstrap-3.3.7/docs/assets/jsie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- MÃ�SCARA -->
<script>
      jQuery(function($){
             $('#preco').maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false}); 
      });
     </script>
</head>






<div class="container">
	<h2>Novo Produto</h2>
	<div class="jumbotron">

		<form method="POST">

			<div class="row">

				<div class="form-group col-md-3">
					<label for="nome">Nome</label> <input type="text"
						class="form-control" id="nome" name="nome" required>
				</div>

				<div class="form-group col-md-3">
					<label for="descricao">Descri&ccedil;&atilde;o</label> <input
						type="text" class="form-control" name="descricao" required>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-3">
					<label for="gelada">Gelada</label> 
					<select class="form-control selectpicker" name="uf" id="gelada" required>
							<option value="">--Selecione--</option>
                        	<option value="1">SIM</option>
                        	<option value="0">NÃO</option>
					</select>
					
				</div>

				<div class="form-group col-md-3">
					<label for="preco">Pre&ccedil;o</label> <input type="text"
						class="form-control" id="preco" name="preco" required>
				</div>

			</div>

			<div class=row>
				<div class="form-group col-md-4">
					<input type="submit" value="&#10003 Cadastrar"
						class="btn btn-primary" /> <a href="cadastrar-produto.php"
						class="btn btn-danger">&#10005 Cancelar</a>
				</div>
			</div>

		</form>
	</div>
</div>



<hr>


<div id="carousel-example-generic" class="carousel slide"
	data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#carousel-example-generic" data-slide-to="0"
			class="active"></li>
		<li data-target="#carousel-example-generic" data-slide-to="1"></li>
	</ol>

	<footer>
		<p>&copy; 2016 Drinks, Inc.</p>
	</footer>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
<script src="..biblioteca/bootstrap-3.3.7/dist/jsbootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script
	src="..biblioteca/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
