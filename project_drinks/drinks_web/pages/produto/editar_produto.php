<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');


$codProduto = 0;
$caracters = array(
        "R",
        "$"
    );

 
if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false) {
    $codProduto = addslashes($_GET['codProduto']);
}


$codEstabelecimento = $_SESSION['codEstabelecimento'];
$dados = buscarProdutoCadastro($codProduto);

if(isset($_POST['preco']) && empty($_POST['preco']) == false){
    
//    $inserir['codEstabelecimento'] = $codEstabelecimento;
//    $inserir['ean'] = $_POST['ean'];
    $inserir['preco'] = $_POST['preco'];
//    $inserir['codProduto'] = $codProduto;
    
    update(PRODUTO_ESTABELECIMENTO, $codProduto, $inserir, 'codProduto');
    header("Location: listaProdutoVinculados.php");
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



<body>
<div class="container">
    <h2>Alterar Produto </h2>
        
	<div class="jumbotron">
                
            <?php
		if(count($dados) > 0){
                    foreach ($dados as $dado) {?>
		<div class="jumbotron">
                    <form method="POST" id="formProduto">

				<div class="row">

					<div class="form-group col-md-3">
						<label for="nome">Nome:</label> <input type="text"
							class="form-control" id="cnpj" name="nome"
							value="<?php echo $dado['nome']; ?> " readonly>
					</div>

					<div class="form-group col-md-5">
						<label for="descricao">Descrição:</label> <input
							type="text" class="form-control" name="descricao"
							value="<?php echo $dado['descricao']; ?>" 
							readonly>
					</div>

					<div class="form-group col-md-5">
						<label for="ean">Ean:</label> 
                                                <input type="text"
							class="form-control" name="ean"
							value="<?php echo $dado['ean']; ?>" 
							readonly>
					</div>

					<div class="form-group col-md-3">
						<label for="preco">Preco</label> 
                                                <input type="text"  id="preco"
							class="form-control" name="preco"
                                                        required>
					</div>
				</div>


			<div class=row>
				<div class="form-group col-md-4">                                
                                        
                                        <input type="submit" value="&#10003 Alterar" class="btn btn-primary" /> 
    					<a href="lista.php" class="btn btn-danger">&#10005 Cancelar</a>
				</div>
			</div>

		</form>
                    <?php }?>
                    <?php }?>
	</div>
</div>
</body>
</html>
