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
    
    $inserir['codEstabelecimento'] = $codEstabelecimento;
    $inserir['ean'] = $_POST['ean'];
    $inserir['preco'] = $_POST['preco'];
    $inserir['codProduto'] = $codProduto;
    
    insert(PRODUTO_ESTABELECIMENTO, $inserir);
    header("Location: listaProdutoVinculados.php");
}

?>
<!DOCTYPE html>
<html lang="pt-br">
<head>

	<meta charset="UTF-8">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
       
<title>Alterar Produto</title>
<script>
        $(document).ready(function(){
          $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function() {
              $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
          });
        });
        
</script>

</head>

<body>
	<div class="container">
	
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

<input type="submit" value="Cadastrar" class="btn btn-primary">
                                                    <a href="listaProdutoEstabelecimento.php"
							class="btn btn-danger">&#10005Cancelar</a>
					</div>
				</div>

			</form>
		</div>
		<?php }
		}?>
	</div>

</body>
