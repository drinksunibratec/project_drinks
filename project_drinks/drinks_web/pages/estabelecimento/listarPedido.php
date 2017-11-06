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
$dados = buscarRegistroPorId(PEDIDO, $codEstabelecimento, 'codEstabelecimento');

$host = "localhost";
$user = "root";
$senha = "";
$banco = "comumana_drinks";

$mysqli = new mysqli($host, $user, $senha, $banco);
if ($mysqli->connect_errno) {
    echo 'Falha na Conexão: (' . $mysqli->connect_errno . ')' . $mysqli->connect_error;
}

// $nome = "SELECT usuarios.nome FROM pedido,usuarios,estabelecimento WHERE pedido.codUsuario = usuarios.codUsuario and pedido.codEstabelecimento=estabelecimento.$codEstabelecimento";
// $con = $mysqli->query($consulta) or die($mysqli->error);
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
            						<th>Pagamento</th>
            						<th>Vl. Total</th>
            						<th>Status</th>
            						<th align="center">A&ccedil;&otilde;es</th>
            					</tr>
            				</thead>
            				
            				<?php foreach ($dados as $pedido){?> 
            				
            				<tbody>
            					<tr>
            						<td id="codPedido"><?php echo $pedido['codPedido']; ?></td>
            						<td><?php echo $pedido['codUsuario']; ?></td>
            						<td><?php echo $pedido['dataPedido']; ?></td>
            						<td><?php echo $pedido['pagamento']; ?></td>
            						<td><?php echo "R$ ".$pedido['valorTotal']; ?></td>
            						<td><?php echo $pedido['status']; ?></td>
            						<td align="center">
            						<a title="Detalhes" href="detalhesPedido.php?codPedido=<?php echo  $dados['codPedido']?>" class="btn btn-sm btn-warning" >&#9999; Detalhes</a>
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