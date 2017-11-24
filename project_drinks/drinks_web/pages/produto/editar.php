<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codProduto = $_GET['codProduto'];
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
    <br>
    <br>
    <br>
    
	<div class="container">
            	
                <div class="jumbotron">
			<form method="POST">

				<div class="row">
                                <div class="panel panel-default">
				<div class="panel-heading">Editar Produtos</div>
				<div class="panel-body">
                            <br>
                            <br>
                            <br>

				<div class="form-group col-md-3">
					<label for="nome">Nome</label> 
                                        <input type="text" value="<?php echo $_GET['nome']; ?>"
						class="form-control" id="nome" name="nome" required>
				</div>

				<div class="form-group col-md-3">
					<label for="descricao">Descri&ccedil;&atilde;o</label> 
                                        <input value="<?php echo $_GET['descricao']; ?>"
						type="text" id="descricao" class="form-control" 
                                                name="descricao" required>
				</div>
                            
				<div class="form-group col-md-3">
					<label for="ean">Ean</label> 
                                        <input 
                                               type="text" id="ean" class="form-control" 
                                                name="ean" required >
				</div>
                            
			</div>
                                    <br>
                                    <br>
                                    <br>

			<div class=row>
				<div class="form-group col-md-4">                                
                                        
                                        <input type="submit" value="&#10003 Cadastrar" class="btn btn-primary" /> 
    					<a href="lista.php" class="btn btn-danger">&#10005 Cancelar</a>
				</div>
			</div>

		</form>		
            		</table>
					<!-- END TABLE -->
				</div>
			</div>
		</div>
	</div>
</body>
</html
