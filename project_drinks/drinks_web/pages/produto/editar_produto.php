<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');



if (isset($_GET['codProduto']) && empty($_GET['codProduto']) == false) {
    $codProduto = addslashes($_GET['codProduto']);
    $dados = $_POST;
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
    
    $database = open_database();
    $preco = "SELECT pe.preco 
               FROM `produto_estab` as pe
               INNER JOIN produto as p on 
               (p.codProduto = pe.codProduto)
               INNER JOIN estabelecimento as e on
               (e.codEstabelecimento = pe.codEstabelecimento)
               WHERE e.codEstabelecimento = ".$codEstabelecimento." AND pe.codProduto = $codProduto";
//    echo $preco;
    var_dump($preco);
    $sql = "UPDATE `produto_estab` SET `preco`=$preco WHERE codEstabelecimento =$codEstabelecimento";
//    echo $sql;
    try {
//        $database->query($sql);
        $_SESSION['message'] = 'Preço Alterado com sucesso.';
        $_SESSION['type'] = 'success';
//        header("Location: listaProdutoEstabelecimento.php");
    } catch (Exception $ex) {
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao.';
        $_SESSION['type'] = 'danger';
         header("Location: listaProdutoEstabelecimento.php");
    }
}
?>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Editar Produto</title>	
</head>

<body>
	<div class="container">
	
		<?php 
                if(count($dados) > 0){
                    foreach ($dados as $dado) {?> 
		
		<h3>Dados cadastrais de <?php echo $dado['nome']; ?>:</h3>
		<div class="jumbotron">
		<form method="POST">
                    <div class="row">
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
                <?php }?>
	</div>

	</body>
