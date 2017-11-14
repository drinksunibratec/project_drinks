<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

  $caracters = array(
        "R",
        "$"
    );
  
if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false) {
    $codProduto = addslashes($_GET['codProduto']);
}

if (isset($_POST['nome']) && empty($_POST['nome']) == false) {
    $dados = $_POST;
    foreach ($dados as $key => $value) {
        if ($key == 'preco') {
            $dados[$key] = str_replace($caracters, "", $value);
        }
    }
    $codProduto = addslashes($_GET['codProduto']);
    $s_estabelecimento = $_GET['codEstabelecimento'];
    update(PRODUTO, $codProduto, $dados, 'codProduto');
    
    header("Location: lista.php");
}

//$dados = buscarRegistroPorId(PRODUTO, $codProduto, 'codProduto');


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



<body>
<div class="container">
	<h2>Novo Produto</h2>
	<div class="jumbotron">
            
		<form method="POST">

			<div class="row">

				<div class="form-group col-md-3">
					<label for="nome">Nome</label> <input type="text"
                                        value="<?php echo  $_GET['nome']?>" class="form-control" id="nome" name="nome" required>
				</div>

				<div class="form-group col-md-3">
					<label for="descricao">Descri&ccedil;&atilde;o</label> 
                                        <input value="<?php echo  $_GET['descricao']?>"
						type="text" class="form-control" name="descricao" required>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-3">
					<label for="gelada">Gelada</label> 
					<select selected="<?php echo  $_GET['gelada']?>" 
                                                class="form-control selectpicker" name="gelada" id="gelada" required>
                                            <option selected="selected value=">--Selecione--</option>
                        	<option value="1">SIM</option>
                        	<option value="0">NÃO</option>
					</select>
					
				</div>

				<div class="form-group col-md-3">
					<label for="preco">Pre&ccedil;o</label> <input type="text"
					value="<?php echo  $_GET['preco']?>"	
                                        class="form-control" id="preco" name="preco" required>
				</div>

			</div>

			<div class=row>
				<div class="form-group col-md-4">
                                    <input type="submit" value="&#10003 Atualizar" href="lista.php"
                                           class="btn btn-primary"/> 
					<a href="lista.php" class="btn btn-danger">&#10005 Cancelar</a>
				</div>
			</div>

		</form>
	</div>
</div>
</body>
</html>