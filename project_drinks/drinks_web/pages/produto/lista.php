<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$mensagens = new Mensagens();
$codEstabelecimento = null;
if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = $_GET['codEstabelecimento'];
}else{
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
}
$dados = buscarRegistroPorId(PRODUTO, $codEstabelecimento, 'codEstabelecimento');
?>




<!DOCTYPE html>
<html lang="pt-br">
<head>

	<meta charset="UTF-8">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
       
<title>Administração de Produtos</title>
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
		<header>
			<div class="row">
				<div class="col-sm-6">
					<h2>Lista de Produtos</h2>
				</div>
				<div class="col-sm-6 text-right h2" align="right">
					<a href="adicionar.php" class="btn btn-sm btn-primary">&#10010;
						Novo Produto</a>
				</div>

			</div>

		</header>
		<div class="row">
        	<?php //$mensagens->imprimirMensagem(); ?>
    	</div>
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading"></div>
				<div class="panel-body">

					<input class="form-control" id="myInput" type="text"
						placeholder="Pesquisar..." style="width: 400px;"> <br>

					<!-- TABLE -->
					<table border="1" class="table table-bordered table-striped">
						<thead class="blue-grey lighten-4">
							<tr>
								<th>Nome</th>
								<th>Preço</th>
								<th>Descrição</th>
								<th>Ações</th>
							</tr>
						</thead>
            			
            	<?php
            	if(count($dados) > 0){
            	        foreach ($dados as $produto){
                ?>
            			
                    <tbody id="myTable">
    					<tr>
                            <td><?php echo $produto['nome']; ?></td>
                            <td><?php echo "R$ ".$produto['preco']; ?></td>
                            <td><?php echo $produto['descricao']; ?></td>
                            <td align="center">
                                <a title="Alterar" href="editar.php?codProduto=<?php echo  $produto['codProduto']?>"
    						class="btn btn-sm btn-warning">&#9999; Alterar</a>
                          <?php 
//                           if(isset($_POST['excluir'])){
//                           $codProduto = $_GET['codProduto'];
//                           buscarRegistroPorId(PRODUTO, $codProduto);
//                             deleta(PRODUTO, "where codProduto = ".$codProduto);
//                           }
                          ?>
                            <a title="Excluir" id="btn-excluir" href="lista.php?codEstabelecimento= <?php echo $produto['codProduto']?>'"
                               class="btn btn-sm btn-danger tooltipBtn"> &#10006; Excluir</a>
                            </td>
    					</tr>
                    </tbody>
            			
            	<?php }
                    }?>
            			
            		</table>
					<!-- END TABLE -->
				</div>
                                <?php
                                
//                                if(isset($_POST['excluir'])){
//                                    //REGISTRO DOS DADOS
//                                    //Verificar se a seção foi aberta
//                                    if (!isset($_SESSION))
//                                        session_start ();
//                                    
//                                    $codProdutoDel = intval($_GET['codEstabelecimento']);
//                                
//                                    $sql_code = "DELETE FROM produto WHERE codigo = '$codProdutoDel'";
//                                    $sql_query = $mysqli->query($sql_code) or die($mysqli->error);
//                                    
//                                    header("Location: lista.php");
//                                }
                                ?>
			</div>
		</div>
	</div>
</body>
</html>