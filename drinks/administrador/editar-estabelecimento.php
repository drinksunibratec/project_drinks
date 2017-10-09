<?php
require_once ('../config.php');
require_once ('../biblioteca/menu/menu.php');

$codEstabelecimento = 0;


if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = addslashes($_GET['codEstabelecimento']);
}
if (isset($_POST['cnpj']) && empty($_POST['cnpj']) == false) {
    $email = $_POST['email'];
    $bairro = $_POST['bairro'];
    $cep = $_POST['cep'];
    $cidade = $_POST['cidade'];
    $latitude = $_POST['latitude'];
    $longitude = $_POST['longitude'];
    $numero = $_POST['numero'];
    $uf = $_POST['uf'];
    $rua = $_POST['rua'];
    $nomeFantasia = $_POST['nomeFantasia'];
    $razaoSocial = $_POST['razaoSocial'];
    $senha = md5($_POST['senha']);
    $telefone = $_POST['telefone'];
    
    $sql = "UPDATE estabelecimento SET eMail = '$email', rua = '$rua', bairro = '$bairro', cep = '$cep', cidade = '$cidade', latitude ='$latitude', longitude = '$longitude',  numero ='$numero', uf = '$uf', nomeFantasia = '$nomeFantasia', razaoSocial = '$razaoSocial' , senha = '$senha', telefone = '$telefone' WHERE codEstabelecimento = '$codEstabelecimento'";
    
//     $stmt = $conn->prepare("UPDATE estabelecimento SET eMail =?, bairro =?, cep =?, cidade =?, latitude =?, longitude =?, numero =?, uf =?, nomeFantasia =?, razaoSocial =?, senha =?, telefone =?, administrador=? WHERE codEstabelecimento = ?");
//     $stmt->bind_param("ssssssisssssss", $email, $bairro, $cep, $cidade, $latitude, $longitude, $numero, $uf, $nomeFantasia, $razaoSocial, $senha, $telefone, $codEstabelecimento, $dado['administrador']);
//     $result = $stmt->execute();
//     $stmt->close();
   
    $result = $PDO->query($sql);
    
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
    
    <!-- MASCARA -->
     <script>
      jQuery(function($){
             $("#cnpj").mask("99.999.999/9999-99");
             $("#telefone").mask("(99)99999-9999");
             $("#cep").mask("99999-999");     
      });

      
     </script>
</head>

<body>
	<div class="container">

		<h3>Dados cadastrais de <?php echo $dado['nomeFantasia']; ?>:</h3>
		<div class="jumbotron">
			<form method="POST">

                <div class="row">
                    
                    <div class="form-group col-md-3">
                      <label for="cnpj">CNPJ</label>
                      <input type="text" class="form-control" id="cnpj" name="cnpj" value="<?php echo $dado['cnpj']; ?> " readonly>
                	</div>
                    
                    <div class="form-group col-md-3">
                      <label for="razaoSocial">Raz&atilde;o Social</label>
                      <input type="text" class="form-control" name="razaoSocial" value="<?php echo $dado['razaoSocial']; ?>">
                	</div>
                    
                    <div class="form-group col-md-3">
                      <label for="nomeFantasia">Nome Fantasia</label>
                      <input type="text" class="form-control" name="nomeFantasia" value="<?php echo $dado['nomeFantasia']; ?>">
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="email">E-mail</label>
                      <input type="text" class="form-control" name="email" value="<?php echo $dado['eMail']; ?>">
                	</div>
                	                	
                </div>
                
                <div class="row">
                	<div class="form-group col-md-4">
                      <label for="rua">Rua</label>
                      <input type="text" class="form-control" name="rua" value="<?php echo $dado['rua']; ?>">
                	</div>
                	
                	<div class="form-group col-md-2">
                      <label for="numero">Numero</label>
                      <input type="text" class="form-control" id="numero" name="numero" value="<?php echo $dado['numero']; ?>">
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="bairro">Bairro</label>
                      <input type="text" class="form-control" name="bairro" value="<?php echo $dado['bairro']; ?>">
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="cep">CEP</label>
                      <input type="text" class="form-control" id="cep" name="cep" value="<?php echo $dado['cep']; ?>">
                	</div>
                
                </div>
                
                <div class=row>
                	<div class="form-group col-md-4">
                      <label for="cidade">Cidade</label>
                      <input type="text" class="form-control" name="cidade" value="<?php echo $dado['cidade']; ?>">
                	</div>
                	
                	<div class="form-group col-md-3">
                      	<label for="uf">UF</label>
                    	<select class="form-control selectpicker" name="uf" id="uf">
                        	<option value="">--Selecione--</option>
                        	<option value="AC">AC</option>
                        	<option value="AL">AL</option>
                        	<option value="AM">AM</option>
                        	<option value="AP">AP</option>
                        	<option value="BA">BA</option>
                        	<option value="CE">CE</option>
                        	<option value="DF">DF</option>
                        	<option value="ES">ES</option>
                        	<option value="GO">GO</option>
                        	<option value="MA">MA</option>
                        	<option value="MG">MG</option>
                        	<option value="MS">MS</option>
                        	<option value="MT">MT</option>
                        	<option value="PA">PA</option>
                        	<option value="PB">PB</option>
                        	<option value="PE">PE</option>
                        	<option value="PI">PI</option>
                        	<option value="PR">PR</option>
                        	<option value="RJ">RJ</option>
                        	<option value="RN">RN</option>
                        	<option value="RS">RS</option>
                        	<option value="RO">RO</option>
                        	<option value="RR">RR</option>
                        	<option value="SC">SC</option>
                        	<option value="SE">SE</option>
                        	<option value="SP">SP</option>
                        	<option value="TO">TO</option>
                         </select>  
                      
                  	</div>
                  	
                  	
                  	<div class="form-group col-md-2">
                      <label for="latitute">Latitude</label>
                      <input type="text" class="form-control" name="latitude" value="<?php echo $dado['latitude']; ?>">
                	</div>
                  	
                  	<div class="form-group col-md-2">
                      <label for="longitude">Longitude</label>
                      <input type="text" class="form-control" name="longitude" value="<?php echo $dado['longitude']; ?>">
                	</div>
                </div>
                
                <div class=row>
                	<div class="form-group col-md-3">
                      <label for="telefone">Telefone</label>
                      <input type="text" class="form-control" id="telefone" name="telefone" value="<?php echo $dado['telefone']; ?>">
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="senha">Senha</label>
                      <input type="password" class="form-control" name="senha" value="<?php echo $dado['senha']; ?>">
                	</div>
                	
                </div>
				
				<div class=row>
    				<div class="form-group col-md-4">
    					<input type="submit" value="&#10003 Alterar" class="btn btn-primary" /> 
    					<a href="cadastro-estabelecimentos.php" class="btn btn-danger">&#10005Cancelar</a>
                   	</div>
               	</div>
				
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