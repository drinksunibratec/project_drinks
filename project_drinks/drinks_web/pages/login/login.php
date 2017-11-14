<?php

require_once ('../include/header.php');

session_start();


$mensagens = new Mensagens();

$mensagens->clear_message('codEstabelecimento');
$mensagens->clear_message('administrador');


if (isset($_POST['email']) && empty($_POST['email']) == false) {
    $email = addslashes($_POST['email']);
    $senha = addslashes($_POST['senha']);
    
    $result = login(ESTABELECIMENTO, $email, $senha);
    $_SESSION['idEstabelecimento'] = 5;
    if ($result) {
        $_SESSION['codEstabelecimento'] = $result['codEstabelecimento'];
        $_SESSION['administrador'] = $result['administrador'];
        
        header("Location: ../estabelecimento/lista.php");
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
    	
    </head>

<body>

		<div id="wrapper">
    		<div class="panel-body"></div>
    		
        	<div class="container">
    			<div class="row">
				<div class="col-md-3 col-md-offset-2 text-center">
					<img src="../../resources/img/logo_marca_drinks.png?ln=images" width="170" style="margin-top: 20%;" />
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