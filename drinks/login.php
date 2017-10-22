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
        echo '<div class="alert alert-danger alert-dismissible" role="alert">';
        echo '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
        echo '<strong>Aviso!</strong> Login ou Senha inv&aacute;lidos.';
        echo '</div>';
    }
    
}
?>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=euc-jp">
    	<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
       
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        
        <!-- CSS -->
        <link href="theme.css" rel="stylesheet">
    	<link href="jumbotron.css" rel="stylesheet">
        <link href="biblioteca/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="biblioteca/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css" rel="stylesheet">
		<link href="biblioteca/css/estilo.css" rel="stylesheet" />        
        <link href="biblioteca/materialize/css/materialize.min.css" rel="stylesheet" type="text/css" >
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        <script src="biblioteca/jquery/jquery-1.5.2.min.js"></script>
    	<script src="biblioteca/jquery/jquery.maskedinput-1.3.min.js"></script>
        <script src="biblioteca/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>
       
        <link type="text/css" rel="stylesheet" href="biblioteca/css/ui.css?ln=css" />
        <link type="text/css" rel="stylesheet" href="biblioteca/css/messages.css?ln=css" />
        <link type="text/css" rel="stylesheet" href="biblioteca/css/bootstrap.css?ln=css" />
        
    </head>

<body>

		<div id="wrapper">
    		<div class="panel-body"></div>
    		
        	<div class="container">
    			<div class="row">
				<div class="col-md-3 col-md-offset-2 text-center">
					<img src="biblioteca/img/logo_marca_drinks.png?ln=images" width="170" style="margin-top: 20%;" />
				</div>    			
    			
                	<div class="col-md-4" style="margin-top: 6%;">
                    	<div class="login-panel panel panel-default">
                    		<div class="panel-heading">
                    			<h3 class="panel-title">Login</h3>
                    		</div>
                    		
                    		<form class="col s12" method="POST" class="login-form">
                        		<div class="panel-body">
                    				<div class="form-group input-group">
                    					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    					 <input id="email" name="email" type="email" placeholder="Informe o nome de usu&aacute;rio" class="form-control" required/>
                    				</div>
                    
                    				<div class="form-group input-group">
                    					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
                    					<input id="senha" name="senha" type="password" placeholder="Informe a senha" class="form-control" />
                    				</div>
                    				
                    				<input tabindex="2" type="submit" value="Entrar" class="btn btn-lg btn-primary btn-block" style="text-transform: uppercase;" required/>
                        		</div>
                    		</form>
                    		
                    	</div>
                	</div>
        		</div>
        	</div>
        </div>
</body>

</html>