<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codEstabelecimento = $_GET['codEstabelecimento'];
print_r($codEstabelecimento);
$dados = buscarRegistroPorId('produto', $codEstabelecimento, 'codEstabelecimento');
?>




<!DOCTYPE html>
<html lang="pt-br">

    <body>
    	<div class="container">
    		<header>
    			<div class="row">
    				<div class="col-sm-6">
    					<h2>Lista de Produtos</h2>
    				</div>
    			</div>
    		</header>
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
            				</tr>
            			</thead>
            			
            			<?php foreach ($dados as $produto){ 
            			?>
            			
            			<tbody>
            				<tr>
            					<td><?php echo $produto['nome']; ?></td>
            					<td><?php echo $produto['preco']; ?></td>
            					<td><?php echo $produto['descricao']; ?></td>
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