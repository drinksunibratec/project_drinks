<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

define('HOST', 'localhost');
define('USER', 'root');
define('PASS', '');
define('BASE', 'drinks');

try {
    $conexao = 'mysql:host='.HOST.';dbname='.BASE;
    $conecta= new PDO($conexao,USER,PASS);
    $conecta->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $ex) {
    echo $ex->getMessage();
}

if (isset($_POST['nome']) && empty($_POST['nome']) == false) {
    
    $caracters = array(
        "R",
        "$"
    );
    
    $inserir = $_POST;
    foreach ($inserir as $key => $value) {
        if($key == 'preco'){
            $inserir[$key] = str_replace($caracters, "", $value);
            
        }
        
    }
    $inserir['codEstabelecimento'] = $_SESSION['id'];
    insert(PRODUTO, $inserir);
    
    header("Location: lista.php");
}

if(isset($_POST['enviar']) && $_POST['enviar'] == 'cadastrar'){
    
    $nome = strip_tags(trim($_POST['nome']));
    $decricao = strip_tags(trim($_POST['descricao']));
    $preco = strip_tags(trim($_POST['preco']));
    $gelada = strip_tags(trim($_POST['gelada']));
    
    try {
        $cadastrar = $conecta->prepare("INSERT INTO produto SET descricao=?, gelada=?, nome=?, preco=?");
        $cadastrar->bindParam(1,$nome, PDO::PARAM_STR);
        $cadastrar->bindParam(2,$descricao, PDO::PARAM_STR);
        $cadastrar->bindParam(3,$preco, PDO::PARAM_STR);
        $cadastrar->bindParam(4,$gelada, PDO::PARAM_STR);
        $cadastrar->execute();
    } catch (PDOException $erro) {
        echo $erro->getMessage();
    }
}
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>

<title>Cadastrar Produto</title>

<!-- MASCARA -->
<script>
      jQuery(function($){
             $('#preco').maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false}); 
      });
     </script>
</head>




<div class="container">
	<h2>Novo Produto</h2>
	<div class="jumbotron">

		<form method="POST">

			<div class="row">

				<div class="form-group col-md-3">
					<label for="nome">Nome</label> 
                                        <input type="text"class="form-control" id="nome" name="nome" required>
				</div>

				<div class="form-group col-md-3">
					<label for="descricao">Descrição</label> 
                                        <input type="text" class="form-control" name="descricao" required>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-3">
					<label for="gelada">Gelada</label> 
					<select type="text" class="form-control selectpicker" name="gelada" id="gelada" required>
							<option value="">--Selecione--</option>
                        	<option value="1">SIM</option>
                        	<option value="0">NÃO</option>
					</select>
					
				</div>

				<div class="form-group col-md-3">
					<label for="preco">Preço</label> 
                                        <input type="text" class="form-control" id="preco" name="preco" required>
				</div>

			</div>

			<div class=row>
				<div class="form-group col-md-4">
                                    <input type="submit" name="enviar" value="&#10003 Cadastrar" class="btn btn-primary" /> 
					<a href="adicionar.php" class="btn btn-danger">&#10005 Cancelar</a>
				</div>
			</div>

		</form>
	</div>
</div>
</body>
</html>
