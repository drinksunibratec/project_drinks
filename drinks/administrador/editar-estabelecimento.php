<?php
require_once ('../config.php');
require_once ('../biblioteca/menu/menu.php');

$codEstabelecimento = 0;
if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = addslashes($_GET['codEstabelecimento']);
}

if (isset($_POST['nomeFantasia']) && empty($_POST['nomeFantasia']) == false) {
    $nomeFantasia = addslashes($_POST['nomeFantasia']);
    $email = addslashes($_POST['email']);
    $bairro = addslashes($_POST['bairro']);
    $cep = addslashes($_POST['cep']);
    $latitude = addslashes($_POST['latitude']);
    $longitude = addslashes($_POST['longitude']);
    $numero = addslashes($_POST['numero']);
    $uf = addslashes($_POST['uf']);
    $razaoSocial = addslashes($_POST['razaoSocial']);
    $senha = md5(addslashes($_POST['senha']));
    $telefone = addslashes($_POST['telefone']);
    
    $sql = "UPDATE estabelecimento SET cnpj = '$cnpj', email = '$eMail', bairro = '$bairro', cep = '$cep', cidade = '$cidade', latitude ='$latitude', longitude = '$longitude',  numero ='$numero', uf = '$uf', nomeFantasia = '$nomeFantasia', razaoSocial = '$razaoSocial' , senha = '$senha', telefone = '$telefone' WHERE codEstabelecimento = '$codEstabelecimento'";
    $PDO->query($sql);
    
    header("Location: cadastro-estabelecimentos.php");
}

$sql = "SELECT * FROM estabelecimento WHERE codEstabelecimento = '$codEstabelecimento'";
$sql = $PDO->query($sql);
if ($sql->rowCount() > 0) {
    $dado = $sql->fetch();
} else {
    header("Location: cadastro-estabelecimentos.php");
}
?>

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

<script src="../biblioteca/jquery/jquery-1.5.2.min.js"></script>
<script src="../biblioteca/jquery/jquery.maskedinput-1.3.min.js"></script>

<title>Editar estabelecimento</title>

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
</head>

<body>
	<div class="container">

		<h3>Dados cadastrais de <?php echo $dado['nomeFantasia']; ?>:</h3>
		<div class="jumbotron">
			<form method="POST">


				CNPJ <br /> <input type="text" name="cnpj"
					value="<?php echo $dado['cnpj']; ?> " /> <br /> Email <br /> <input
					type="text" name="email" value="<?php echo $dado['eMail']; ?> " />
				<br /> Bairro <br /> <input type="text" name="bairro"
					id="campoBairro" value="<?php echo $dado['bairro']; ?> " /> <br />
				Cep <br /> <input type="text" name="cep" id="campoCep"
					value="<?php echo $dado['cep']; ?> " /> <br /> <br /> Cidade <br />
				<input type="text" name="cidade" id="campoCidade"
					value="<?php echo $dado['cidade']; ?> " /> <br /> <br /> Latitude <br />
				<input type="text" name="latitude" id="campoLatitude"
					value="<?php echo $dado['latitude']; ?> " /> <br /> <br />
				Longitude <br /> <input type="text" name="longitude"
					id="campoLongitude" value="<?php echo $dado['longitude']; ?> " /> <br />
				<br /> Numero <br /> <input type="text" name="numero"
					id="campoNumero" value="<?php echo $dado['numero']; ?> " /> <br />
				<br /> Rua <br /> <input type="text" name="rua" id="campoRua"
					value="<?php echo $dado['rua']; ?> " /> <br /> <br /> UF <br /> <input
					type="text" name="uf" id="campoUf"
					value="<?php echo $dado['uf']; ?> " /> <br /> <br /> Nome Fantasia
				<br /> <input type="text" name="nomeFantasia" id="campoNomeFantasia"
					value="<?php echo $dado['nomeFantasia']; ?> " /> <br /> <br />
				Razao Social <br /> <input type="text" name="cep"
					id="campoRazaoSocial" value="<?php echo $dado['razaoSocial']; ?> " />
				<br /> <br /> Senha <br /> <input type="text" name="cep"
					id="campoSenha" value="<?php echo $dado['senha']; ?> " /> <br /> <br />
				Telefone <br /> <input type="text" name="cep" id="campoTelefone"
					value="<?php echo $dado['telefone']; ?> " /> <br /> <br /> <input
					type="submit" value="&#10003 Alterar" class="btn btn-primary" /> <a
					href="cadastro-estabelecimento.php" class="btn btn-danger">&#10005
					Cancelar</a>

			</form>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="../../js/vendor/jquery.min.js"><\/script>')</script>
	<script src="..biblioteca/bootstrap-3.3.7/dist/jsbootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="..biblioteca/bootstrap-3.3.7/docs/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>