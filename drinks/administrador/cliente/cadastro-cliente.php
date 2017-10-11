<?php
require_once ('../../biblioteca/functions/Functions.php');
require_once ('../../biblioteca/functions/DB_Functions.php');
require_once ('../../biblioteca/util/Mensagens.php');
require_once ('../../biblioteca/include/header.php');
require_once ('../../biblioteca/menu/menu.php');
?>




<!DOCTYPE html>
<html lang="pt-br">
    <head>
    	<title>Administração de Clientes</title>
    </head>

<body>
	<div class="container">
  
  <?php
$clientes = buscarTodosOsRegistros('cliente');
if (count($clientes) == 0) {
    echo '<div class="alert alert-danger" role="alert">';
    echo '<strong>Aviso!</strong> Você ainda não possui cliente cadastrados.';
    echo '</div>';
    
    echo '<div class="jumbotron">';
    echo '<div class="container" style="width:300px; height: 200px; overflow: auto;">';
    echo '<img src="../biblioteca/img/broken-file.png">';
} else {
    
    echo '<div class="jumbotron">';
    echo '<div class="container" style="width:1200px; height: 400px; overflow: auto;">';
    echo '<h2>Lista de cliente</h2>';
    echo '<table class="table table-hover">';
    echo '<thead>';
    echo '<tr>';
    echo '<th>Nome</th>';
    echo '<th>Email</th>';
    echo '<th>telefone</th>';
    echo '</tr>';
    echo '</thead>';
    echo '<tbody>';
    echo '<tr>';
    
    foreach ($clientes as $cliente) {
        echo '<td>' . $cliente['nome'] . '</td>';
        echo '<td>' . $cliente['email'] . '</td>';
        echo '<td>' . $cliente['telefone'] . '</td>';
        echo '</tbody>';
        echo '</tr>';
    }
}
?>
        </table>
	</div>
	</div>


	<div class="container theme-showcase" role="main">

		<br> <br>



		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
		<script src="../biblioteca/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
		<script src="../biblioteca/js/docs.min.js"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="../biblioteca/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>