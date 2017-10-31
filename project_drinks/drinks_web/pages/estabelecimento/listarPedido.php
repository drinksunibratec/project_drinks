<?php

require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codEstabelecimento = $_SESSION['codPedido'];


$mensagens = new Mensagens();
$dados = null;

if($_SESSION['administrador'] == 1){
    $dados = buscarTodosOsRegistros(PEDIDO);
}else{
    $dados = buscarRegistroPorId(PEDIDO, $codPedido, 'codPedido');
}
?>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Pedidos</title>
    </head>

<body>

	<div class="container">
    	<header>
    		<div class="row">
        		<?php tituloDaListagem('Listagem de Pedidos', 'Seus Dados'); ?>
    		</div>
    		

    	</header>
        <div class="row">
        	<?php $mensagens->imprimirMensagem(); ?>
    	</div> 
        	<div class="row">
    			<div class="panel panel-default">
    			<div class="panel-heading">Lista de Pedidos</div>
    				<div class="panel-body">
            			<!-- TABLE -->
            			<table class="table table-bordered table-striped">
            				<thead  class="blue-grey lighten-4">
            					<tr>
            						<th>CodPedido</th>
            						<th>Cliente</th>
            						<th>Data Pedido</th>
            						<th>Bairro</th>
            						<th>Cidade</th>
            						<th>Status</th>
            						<th>Valor</th>
            						<th align="center">A&ccedil;&otilde;es</th>
            					</tr>
            				</thead>
            				
            				<?php foreach ($dados as $pedido){ 
            				?>
            				
            				<tbody>
            					<tr>
            						<td id="codPedido"><?php echo $pedido.['codPedido']; ?></td>
            						<td><?php echo $pedido[$cliente->$nome]; ?></td>
            						<td><?php echo $pedido['dataPedido']; ?></td>
            						<td><?php echo $pedido['bairro']; ?></td>
            						<td><?php echo $pedido['cidade']; ?></td>
            						<td><?php echo $pedido['status']; ?></td>
            						<td><?php echo $pedido['valorTotal']; ?></td>
            						<td align="center">
            						<a title="Detalhes" href="editarPedido.php?codPedido=<?php echo  $pedido['codPedido']?>" class="btn btn-sm btn-warning" >&#9999; Detalhes</a>
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
</html>