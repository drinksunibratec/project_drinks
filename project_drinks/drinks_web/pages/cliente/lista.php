<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');


$dados = buscarTodosOsRegistros('USUARIOS');
?>




<!DOCTYPE html>
<html lang="pt-br">
    <head>
    	<title>Administra√ß√£o de Clientes</title>
    </head>

<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">Lista de Usu&aacute;rio</div>
    			<div class="panel-body">
	
              		<!-- TABLE -->
            		<table class="table table-bordered table-striped">
            			<thead  class="blue-grey lighten-4">
            				<tr>
            					<th>Nome</th>
            					<th>E-mail</th>
            					<th>Telefone</th>
            					<th>Valores Totais</th>
            				</tr>
            			</thead>
            			
            			<?php foreach ($dados as $usuarios){ 
            			?>
            			
            			<tbody>
            				<tr>
            					<td><?php echo $usuarios['nome']; ?></td>
            					<td><?php echo $usuarios['email']; ?></td>
            					<td><?php echo Mask('(##) #####-####',$usuarios['telefone']); ?></td>
            					<td><?php echo "Total em compras" ?></td>
                    <!-- Consulta onde relaciono ID do cliente independente do estabelecimento
                            e somo os valores pagos. ImplementaÁaı a ser discutida!
                    SELECT SUM(valorTotal) FROM `pedido` WHERE codUsuario := codUsuario -->
            				</tr>
            			</tbody>
            			
            			<?php }?>
            			
            		</table>
            		<!-- END TABLE -->
        		</div>
    		</div>
	</div>
</body>
</html>