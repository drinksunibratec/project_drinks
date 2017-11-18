<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codEstabelecimento = null;

if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = $_GET['codEstabelecimento'];
} else {
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
}

$dados = listarPedido($codEstabelecimento);

?>

<!DOCTYPE html>
<html lang="pt-br">

<head>

<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Lista de Pedidos</title>
<script>
            $(document).ready(function() {
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
                            if (count($dados) > 0) {
                                foreach ($dados as $pedido) {
                                    ?>

                           <tbody id="myTable">
							<tr>
								<td><?php echo $pedido['codPedido']; ?></td>
								<td><?php echo $pedido['usuario']; ?></td>
								<td><?php echo $pedido['dataPedido']; ?></td>
								<td><?php echo $pedido['pagamento']; ?></td>
								<td><?php echo "R$ ".$pedido['valorTotal']; ?></td>
								<td><?php echo $pedido['status']; ?></td>
								<td align="center">
                                <a title="Detalhes" href="detalhePedido.php?codPedido=<?php echo  $pedido['codPedido']?>"
    						class="btn btn-sm btn-warning">&#9999; Detalhes</a>
									</td>
							</tr>
						</tbody>						
                                <?php
                                }
                            }
                            ?>
                        </table>
					<!-- END TABLE -->

					<!-- *** Inicio Modal *** -->

					<!--Fim Modal -->
					<!--Aqui era o Fim PHP -->

				</div>
			</div>
		</div>
	</div>
</body>

</html>