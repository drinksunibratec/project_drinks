<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');


$codPedido = 0;


if (isset($_GET['$codPedido']) && empty($_GET['$codPedido']) == false) {
    $codPedido = addslashes($_GET['$codPedido']);
}

if (isset($_POST['$codPedido']) && empty($_POST['$codPedido']) == false) {
    
    update(PEDIDO, $codPedido, $dados, '$codPedido');   

}

$dados = buscarRegistroPorId(PEDIDO, $codPedido, 'codPedido');

?>

<!DOCTYPE html>
<head>
    <title>Editar Pedido</title>
</head>

<body>
	<div class="container">
		
		<?php foreach ($dados as $pedido) {?>
		
			<form method="POST">

                <div class="row">
                    
                    <div class="form-group col-md-3">
                      <label for="codPedido">Cod.Pedido</label>      
                      <input type="text" class="form-control" id="codPedido" name="codPedido" value="<?php echo $pedido['codPedido']; ?> " readonly>
                	</div>
                    
                    <div class="form-group col-md-3">
                      <label for="codUsuario">Cliente</label>
                      <input type="text" class="form-control" name="codUsuario" value="<?php echo $pedido['codUsuario']; ?>"readonly>
                	</div>
                    
                    <div class="form-group col-md-3">
                      <label for="dataPedido">Dt. Pedido</label>
                      <input type="datetime" class="form-control" name="dataPedido" value="<?php echo $pedido['dataPedido']; ?>"readonly>
                	</div>
                	
                	<div class="form-group col-md-3">
                      	<label for="pagamento">Pagamento</label>
                    	<?php selected_Status($pedido['pagamento'])?>                       
                  	</div>
                	                	
                </div>
                
                <div class="row">
                	<div class="form-group col-md-4">
                      <label for="rua">Rua</label>
                      <input type="text" class="form-control" name="rua" value="<?php echo $pedido['rua']; ?>">
                	</div>
                	
                	<div class="form-group col-md-2">
                      <label for="numero">Numero</label>
                      <input type="text" class="form-control" id="numero" name="numero" value="<?php echo $pedido['numero']; ?>">
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="bairro">Bairro</label>
                      <input type="text" class="form-control" name="bairro" value="<?php echo $pedido['bairro']; ?>">
                	</div>
                	<div class="form-group col-md-4">
                      <label for="cidade">Cidade</label>
                      <input type="text" class="form-control" name="cidade" value="<?php echo $pedido['cidade']; ?>">
                	</div>             	
                </div>
                
                <div class=row>           	
                  	<div class="form-group col-md-2">
                      <label for="latitute">Latitude</label>
                      <input type="text" class="form-control" name="latitude" value="<?php echo $pedido['latitude']; ?>">
                	</div>
                  	
                  	<div class="form-group col-md-2">
                      <label for="longitude">Longitude</label>
                      <input type="text" class="form-control" name="longitude" value="<?php echo $pedido['longitude']; ?>">
                	</div>
                </div>
                
                       <div class="form-group col-md-3">
                      	<label for="status">Status</label>
                    	<?php selected_Status($pedido['status'])?>                       
                  	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="valorTotal">Valor</label>
                      <input type="text" class="form-control" name="valorTotal" value="<?php echo $pedido['valorTotal']; ?>"readonly>
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
	</div>

	</body>
