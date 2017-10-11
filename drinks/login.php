<?php
require_once ('biblioteca/util/Mensagens.php');
require_once ('biblioteca/functions/DB_Functions.php');

session_start();


$mensagens = new Mensagens();

$mensagens->clear_message('codEstabelecimento');
$mensagens->clear_message('administrador');


if (isset($_POST['email']) && empty($_POST['email']) == false) {
    $email = addslashes($_POST['email']);
    $senha = addslashes($_POST['senha']);
    
    $result = login(ESTABELECIMENTO, $email, $senha);
    
    if ($result) {
        $_SESSION['codEstabelecimento'] = $result['codEstabelecimento'];
        $_SESSION['administrador'] = $result['administrador'];
        
        header("Location: administrador/estabelecimento/cadastro-estabelecimentos.php");
    }else{
        echo '<div class="alert alert-danger" role="alert">';
        echo '<strong>Aviso!</strong> Verifique se digitou o login corretamente.';
        echo '</div>';
    }
    
}
?>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-jp">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="biblioteca/materialize/css/materialize.min.css">
<link href="css/login.css" rel="stylesheet">
</head>

<body>
	<div class="section"></div>
	<main>
	<center>
		<div class="section"></div>

		<h5 class="indigo-text">Por favor, entre na sua conta</h5>
		<div class="section"></div>

		<div class="container">
			<div class="z-depth-1 grey lighten-5 row"
				style="display: inline-block; padding: 32px 48px 0px 48px; border: 1px solid #EEE;">

				<form class="col s12" method="POST">
					<div class='row'>
						<div class='col s12'></div>
					</div>

					<div class='row'>
						<div class='input-field col s12'>
							<input class='validate' type='email' name='email' id='email' required/> <label
								for='email' >Coloque seu email</label>
						</div>
					</div>

					<div class='row'>
						<div class='input-field col s12'>
							<input class='validate' type='password' name='senha'
								id='password' required/> <label for='password'>Coloque sua senha</label>
						</div>
						<!-- <label style='float: right;'> <a class='pink-text' href='#!'><b>Esqueceu a senha?</b></a></label> -->

						
					</div>

					<br />
					<div class='row'>
						<button type='submit' name='btn_login'
							class='col s12 btn btn-large waves-effect indigo'>Entrar</button>
					</div>
				</form>
			</div>
		</div>
		<!-- <a href="#!">Create account</a> -->
	</center>

	<div class="section"></div>
	<div class="section"></div>
	</main>

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
</body>

</html>