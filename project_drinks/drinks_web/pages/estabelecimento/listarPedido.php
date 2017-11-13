<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codEstabelecimento = null;
if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = $_GET['codEstabelecimento'];
}else{
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
}
$dados = buscarRegistroPorId(PEDIDO, $codEstabelecimento, 'codEstabelecimento');
$dado = detalhesPedido();
?>




<!DOCTYPE html>
<html lang="pt-br">
<head>

	<meta charset="UTF-8">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
       
<title>Lista de Pedidos</title>
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
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">Lista de Pedidos</div>
				<div class="panel-body">

					<input class="form-control" id="myInput" type="text"
						placeholder="Pesquisar..." style="width: 400px;"> <br>

					<!-- TABLE -->
					<table border="1" class="table table-bordered table-striped">
						<thead class="blue-grey lighten-4">
							<tr>
								<th>CodPedido</th>
								<th>Cliente</th>
								<th>Dt. Pedido</th>
								<th>Pagamento</th>
								<th>Vl. Total</th>
								<th>Status</th>
							</tr>
						</thead>
            			
            	<?php
            	if(count($dados) > 0){
            	        foreach ($dados as $pedido){
                ?>
            			
                    <tbody id="myTable">
    					<tr>
                            <td><?php echo $pedido['codPedido']; ?></td>
                            <td><?php echo $pedido['codUsuario']; ?></td>
                            <td><?php echo $pedido['dataPedido']; ?></td>
							<td><?php echo $pedido['pagamento']; ?></td>
							<td><?php echo "R$ ".$pedido['valorTotal']; ?></td>
							<td><?php echo $pedido['status']; ?></td>
                            <td align="center">
    						<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal<?php echo $pedido['codPedido']; ?>	">Detalhes</button>
                            </td>
    					</tr>   					
    					                       
                        <!--Inicio Modal -->
                        <div class="modal fade" id="myModal<?php echo $pedido['codPedido']; ?>" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                          <div class="modal-dialog" role="document">
                            <div class="modal-content">
                            
                              <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title text-center" id="myModalLabel"><?php echo "Pedido de Número: " . $pedido['codPedido']; ?></h4>
                              </div>
                              
                              <!-- Dados no Modal-->
                              <div class="modal-body">
                                <div class="row">                    
                                    <div class="form-group col-md-3">
                                      <label for="codPedido">Cod.Pedido</label>      
                                      <input type="text" class="form-control" id="codPedido" name="codPedido" value="<?php echo $pedido['codPedido']; ?> " readonly>
                                	</div>
                                    
                                    <div class="form-group col-md-7">
                                      <label for="codUsuario">Cliente</label>
                                      <input type="text" class="form-control" name="codUsuario" value="<?php echo $pedido['codUsuario']; ?>"readonly>
                                	</div>
                            	</div>
                            	
                            	<div class="row">
                                    <div class="form-group col-md-4">
                                      <label for="dataPedido">Dt. Pedido</label>
                                      <input type="datetime" class="form-control" name="dataPedido" value="<?php echo $pedido['dataPedido']; ?>"readonly>
                                	</div>
                                	<div class="form-group col-md-3">
                                      <label for="pagamento">Pagamento</label>
                                      <input type="text" class="form-control" name="pagamento" value="<?php echo $pedido['pagamento']; ?>"readonly>
                                	</div>
                                    <div class="form-group col-md-3">
                                      <label for="valorTotal">Valor</label>
                                      <input type="text" class="form-control" name="valorTotal" value="<?php echo "R$ " . $pedido['valorTotal']; ?>"readonly>
                                	</div>
                            	</div>
                            	
                            	<div class="row">
                                	<div class="form-group col-md-7">
                                      <label for="rua">Rua</label>
                                      <input type="text" class="form-control" name="rua" value="<?php echo $pedido['rua']; ?>">
                                	</div>
                                	
                                	<div class="form-group col-md-2">
                                      <label for="numero">Numero</label>
                                      <input type="text" class="form-control" id="numero" name="numero" value="<?php echo $pedido['numero']; ?>">
                                	</div>
                            	</div>
                            	
                            	<div class="row">
                                	<div class="form-group col-md-4">
                                      <label for="bairro">Bairro</label>
                                      <input type="text" class="form-control" name="bairro" value="<?php echo $pedido['bairro']; ?>">
                                	</div>
                                	<div class="form-group col-md-4">
                                      <label for="cidade">Cidade</label>
                                  	<input type="text" class="form-control" name="cidade" value="<?php echo $pedido['cidade']; ?>">
                            		</div>             	
                            	</div>
                            	
                            	
                            	 	
                			</div>
                              
                              
                              <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                <button type="button" class="btn btn-primary">Salvar Alterações</button>
                              </div>
                             </div>
                            
                          </div>
                        </div>
                        <!--Fim Modal -->
                    </tbody>
            			
            	<?php }
                    }?>            			
            		</table>
					<!-- END TABLE -->
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>