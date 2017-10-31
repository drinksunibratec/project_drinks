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
$dados = buscarRegistroPorId('produto',  $codEstabelecimento, 'codEstabelecimento');
?>




<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>Administração de Produtos</title>
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
        	<?php $mensagens->imprimirMensagem(); ?>
    	</div>
    	<div class="row">
        		<div class="panel panel-default">
        		<div class="panel-heading">Lista de Produtos</div>
        			<div class="panel-body">

            			<!-- TABLE -->
            		<table class="table table-bordered table-striped">
            			<thead  class="blue-grey lighten-4">
            				<tr>
            					<th>Nome</th>
            					<th>Pre&ccedil;o</th>
            					<th>Descri&ccedil;&atilde;o</th>
            					<th>Gelada</th>
            					<th>A&ccedil;&otilde;es</th>
            				</tr>
            			</thead>
            			
            			<?php foreach ($dados as $produto){ 
            			?>
            			
            			<tbody>
            				<tr>
            					<td><?php echo $produto['nome']; ?></td>
            					<td><?php echo $produto['preco']; ?></td>
            					<td><?php echo $produto['descricao']; ?></td>
            					<td><?php echo $produto['gelada']; ?></td>
            					<td align="center">
        							<a title="Alterar" href="editar.php?codProduto=<?php echo  $produto['codProduto']?>" class="btn btn-sm btn-warning" >&#9999; Alterar</a>
           							<a title="Excluir" id="btn-excluir" href="excluir.php?codEstabelecimento=<?php echo $produto['codProduto']?>" class="btn btn-sm btn-danger tooltipBtn">&#10006; Excluir</a>
           						</td>
            				</tr>
            			</tbody>
            			
            			<?php }?>
            			
            		</table>
            		<!-- END TABLE -->
        		</div>
    		</div>
		</div>
	</div>
</body>
</html>