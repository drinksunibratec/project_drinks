<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codProduto = 0;
$caracters = array(
        "R",
        "$"
    );
$dados = $_POST;
if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = addslashes($_GET['codEstabelecimento']);
}

if (isset($_POST['nome']) && empty($_POST['nome']) == false) {
    
    foreach ($dados as $key => $value) {
        if ($key == 'preco') {
            $dados[$key] = str_replace($caracters, "", $value);
        }
    }
    
    update('produto', $codProduto, $dados, 'codProduto');
    
    header("Location: lista.php");
}

//$dados = buscarRegistroPorId(ESTABELECIMENTO, $codEstabelecimento, 'codEstabelecimento');

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
    <?php 
	if(count($dados) > 0){
            foreach ($dados as $dado) 
        {?>
            <h2>Novo Produto </h2>
        
                <div class="jumbotron">
                    <form method="POST">
                        <div class="row">

				<div class="form-group col-md-3">
					<label for="nome">Nome</label> 
                                        <input type="text" value="<?php echo $produto['nome']; ?>"
						class="form-control" id="nome" name="nome" required>
				</div>

				<div class="form-group col-md-3">
					<label for="descricao">Descri&ccedil;&atilde;o</label> 
                                        <input value="<?php echo $produto['descricao']; ?>"
						type="text" id="descricao" class="form-control" 
                                                name="descricao" required>
				</div>
                            
				<div class="form-group col-md-3">
					<label for="gelada">Ean</label> 
					<input value="<?php echo $produto['ean']; ?>"
						type="text" id="ean" class="form-control" name="ean" required>
					
				</div>
			</div>

			<div class=row>
				<div class="form-group col-md-4">                                
                                        
                                        <input type="submit" value="&#10003 Cadastrar" class="btn btn-primary" /> 
    					<a href="lista.php" class="btn btn-danger">&#10005 Cancelar</a>
				</div>
			</div>

		</form>
	</div>
                <?php } ?>
            <?php } ?>
</div>
</body>
</html>
