<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codEstabelecimento = null;
$codPedido = null;
$mensagens = new Mensagens();

if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = $_GET['codEstabelecimento'];
} else {
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
}

if (isset($_GET['codPedido']) && empty($_GET['codPedido']) == false) {
    $codPedido = addslashes($_GET['codPedido']);
}

$dados = detalhesPedido($codEstabelecimento, $codPedido);
$pdo = itens($codEstabelecimento, $codPedido);

// update(PEDIDO, $codPedido , $pdo, 'codPedido');

?>

<!DOCTYPE html>
<html lang="pt-br">
<head>

<!-- MASCARA -->
<script>
jQuery(function($){
    $('#preco').maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false});
});
    </script>
</head>


<body>


<body>
	<div class="container">
	
		<?php
if (count($dados) > 0) {
    foreach ($dados as $pedido) {
        ?>
		
		<h3>Detalhes do Pedido: <?php echo $pedido['codPedido']; ?></h3>
		<div class="jumbotron">
			<form method="POST">

				<div class="row">
					<div class="row">
						<div class="form-group col-md-3">
							<label for="codPedido">Cod.Pedido</label> <input type="text"
								class="form-control" id="codPedido" name="codPedido"
								value="<?php echo $pedido['codPedido']; ?> " readonly>
						</div>

						<div class="form-group col-md-7">
							<label for="codUsuario">Cliente</label> <input type="text"
								class="form-control" name="codUsuario"
								value="<?php echo $pedido['usuario']; ?>" readonly>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-4">
							<label for="dataPedido">Dt. Pedido</label> <input type="datetime"
								class="form-control" name="dataPedido"
								value="<?php echo $pedido['dataPedido']; ?>" readonly>
						</div>
						<div class="form-group col-md-3">
							<label for="pagamento">Pagamento</label> <input type="text"
								class="form-control" name="pagamento"
								value="<?php echo $pedido['pagamento']; ?>" readonly>
						</div>
						<div class="form-group col-md-3">
							<label for="valorTotal">Valor</label> <input type="text"
								class="form-control" name="valorTotal"
								value="<?php echo " R$ " . $pedido['valorTotal']; ?>" readonly>
						</div>
					</div>

					<!-- Laço para verificar quantos são os produtos e add na table -->
					<div class="row">
						<table border="1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Cod.</th>
									<th>Produto</th>
									<th>Preço</th>
									<th>Quant.</th>
									<th>Pr. Total</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
							
                           <?php
        
        foreach ($pdo as $row) {
            echo '<tr>';
            echo '<td>' . $row['codProduto'] . '</td>';
            echo '<td>' . $row['nome'] . '</td>';
            echo '<td>' . "R$ " . $row['preco'] . '</td>';
            echo '<td>' . $row['quantidade'] . '</td>';
            echo '<td>' . "R$ " . $row['precoTotal'] . '</td>';
            echo '<td>' . '<a title="Excluir" id="btn-excluir" href="deletar.php?codProduto=' . $row['codProduto'] . '"' . 'class="btn btn-sm btn-danger tooltipBtn"> &#10006; Excluir</a>' . '</td>';
            echo '</tr>';
        }
        ?>
                                                                        
                        </tbody>
						</table>
					</div>
					<!-- Fim do laço! -->

					<div class="row">
						<div class="form-group col-md-7">
							<label for="rua">Rua</label> <input type="text"
								class="form-control" name="rua"
								value="<?php echo $pedido['rua']; ?>">
						</div>

						<div class="form-group col-md-2">
							<label for="numero">Numero</label> <input type="text"
								class="form-control" id="numero" name="numero"
								value="<?php echo $pedido['numero']; ?>">
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-4">
							<label for="bairro">Bairro</label> <input type="text"
								class="form-control" name="bairro"
								value="<?php echo $pedido['bairro']; ?>">
						</div>
						<div class="form-group col-md-4">
							<label for="cidade">Cidade</label> <input type="text"
								class="form-control" name="cidade"
								value="<?php echo $pedido['cidade']; ?>">
						</div>
					</div>
					<div class="form-group col-md-4">
						<!-- Div Status -->
						<label for="status">Status</label> <select
							class="form-control selectpicker" name="status" id="status"
							required>
							<option value="">
                                                    <?php echo $pedido['status']; ?>
                                                </option>
							<option value="1">CANCELADO</option>
							<option value="0">ENTREGUE</option>
						</select>
					</div>

					<div class=row>
						<div class="form-group col-md-4">
							<input type="submit" value="&#10003 Alterar"
								class="btn btn-primary" /> <a href="listarPedido.php"
								class="btn btn-danger">&#10005Cancelar</a>
						</div>
					</div>
			
			</form>
		</div>
		<?php
    }
}
?>
		</div>
</body>