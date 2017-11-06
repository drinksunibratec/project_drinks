<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');



$codProduto = 0;


if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false) {
    $codEstabelecimento = addslashes($_GET['codProduto']);
}
    
    $inserir['codEstabelecimento'] = $_SESSION['id'];
    update('codProduto', $codProduto, $dados, '$codProduto');
    
    //header("Location: cadastro-estabelecimentos.php");//WTF????

$dados = buscarRegistroPorId(PRODUTO, $codProduto, 'codProduto');

?>

<!DOCTYPE html>

<head>
	<meta charset="UTF-8">
    <title>Editar Produto</title>	
</head>

<body>
	<div class="container">
	
		<?php foreach ($dados as $dado) {?>
		
		<h3>Dados cadastrais de <?php echo $dado['nome']; ?>:</h3>
		<div class="jumbotron">
			<form method="POST">

                <div class="row">
                    
                    <div class="form-group col-md-3">
                      <label for="cnpj">Nome</label>      
                      <input type="text" class="form-control" id="nome" name="nome" value="<?php echo $dado['nome']; ?>"required>
                	</div>
                    
                    <div class="form-group col-md-5">
                      <label for="razaoSocial">Descrição</label>
                      <input type="text" class="form-control" name="descricao" value="<?php echo $dado['descricao']; ?>"required>
                	</div>
                    
					<div class="row">
						<div class="form-group col-md-3">
							<label for="gelada">Gelada</label> 
							<select class="form-control selectpicker" name="gelada" id="gelada">
							<option value="">--Selecione--</option>
                        	<option value="1">SIM</option>
                        	<option value="0">NÃO</option>
					</select>
                	
                	<div class="form-group col-md-3">
                      <label for="email">Preço</label>
                      <input type="text" class="form-control" id="preco" name="preco" value="<?php echo $dado['preco']; ?>"required>
                	</div>
                	                	
                </div>               
                
				
				<div class=row>
    				<div class="form-group col-md-4">
    					<input type="submit" value="&#10003 Alterar" class="btn btn-primary" /> 
    					<a href="lista.php" class="btn btn-danger">&#10005Cancelar</a>
                   	</div>
               	</div>
				
			</form>
		</div>
		<?php }?>
	</div>

	</body>
