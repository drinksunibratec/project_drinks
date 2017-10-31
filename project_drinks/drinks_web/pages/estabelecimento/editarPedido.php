<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');



$codPedido = 0;


if (isset($_GET['$codPedido']) && empty($_GET['$codPedido']) == false) {
    $codPedido = addslashes($_GET['$codPedido']);
}

if (isset($_POST['$codPedido']) && empty($_POST['$codPedido']) == false) {
    $dados = $_POST;
     
    update('pedido', $codPedido, $dados, '$codPedido');
    

}

$dados = buscarRegistroPorId(PEDIDO, $codPedido, '$codPedido');

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
                      <input type="text" class="form-control" id="codPedido" name="codPedido" value="<?php echo $dado['codPedido']; ?> " readonly>
                	</div>
                    
                    <div class="form-group col-md-3">
                      <label for="codUsuario">Cliente</label>
                      <input type="text" class="form-control" name="codUsuario" value="<?php echo $dado['codUsuario']; ?>"readonly>
                	</div>
                    
                    <div class="form-group col-md-3">
                      <label for="dataPedido">Dt. Pedido</label>
                      <input type="datetime" class="form-control" name="dataPedido" value="<?php echo $dado['dataPedido']; ?>"readonly>
                	</div>
                	
                	<div class="form-group col-md-3">
                      	<label for="pagamento">Pagamento</label>
                    	<?php selected_Status($dado['pagamento'])?>                       
                  	</div>
                	                	
                </div>
                
                <div class="row">
                	<div class="form-group col-md-4">
                      <label for="rua">Rua</label>
                      <input type="text" class="form-control" name="rua" value="<?php echo $dado['rua']; ?>">
                	</div>
                	
                	<div class="form-group col-md-2">
                      <label for="numero">Numero</label>
                      <input type="text" class="form-control" id="numero" name="numero" value="<?php echo $dado['numero']; ?>">
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="bairro">Bairro</label>
                      <input type="text" class="form-control" name="bairro" value="<?php echo $dado['bairro']; ?>">
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="cep">CEP</label>
                      <input type="text" class="form-control" id="cep" name="cep" value="<?php echo $dado['cep']; ?>">
                	</div>
                
                </div>
                
                <div class=row>
                	<div class="form-group col-md-4">
                      <label for="cidade">Cidade</label>
                      <input type="text" class="form-control" name="cidade" value="<?php echo $dado['cidade']; ?>">
                	</div>
                	
                	<div class="form-group col-md-3">
                      	<label for="uf">UF</label>
                    	<?php selected_UF($dado['uf'])?> 
                      
                  	</div>
                  	
                  	
                  	<div class="form-group col-md-2">
                      <label for="latitute">Latitude</label>
                      <input type="text" class="form-control" name="latitude" value="<?php echo $dado['latitude']; ?>">
                	</div>
                  	
                  	<div class="form-group col-md-2">
                      <label for="longitude">Longitude</label>
                      <input type="text" class="form-control" name="longitude" value="<?php echo $dado['longitude']; ?>">
                	</div>
                </div>
                
                       <div class="form-group col-md-3">
                      	<label for="status">Status</label>
                    	<?php selected_Status($dado['status'])?>                       
                  	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="valorTotal">Valor</label>
                      <input type="text" class="form-control" name="valorTotal" value="<?php echo $dado['valorTotal']; ?>"readonly>
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
