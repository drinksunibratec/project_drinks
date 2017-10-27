<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');
$dados = buscarTodosOsRegistros('cliente');
?>




<!DOCTYPE html>
<html lang="pt-br">
    <head>
    	<title>Administração de Clientes</title>
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
            				</tr>
            			</thead>
            			
            			<?php foreach ($dados as $usuario){ 
            			?>
            			
            			<tbody>
            				<tr>
            					<td><?php echo $usuario['nome']; ?></td>
            					<td><?php echo $usuario['email']; ?></td>
            					<td><?php echo $usuario['telefone']; ?></td>
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