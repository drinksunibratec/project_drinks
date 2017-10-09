<?php
require_once ('../config.php');
require_once ('../biblioteca/menu/menu.php');
require_once ('../biblioteca/util/mensagens.php');
?>




<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../biblioteca/img/favicon2.ico">

<title>Administração de Produtos</title>

<!-- Bootstrap core CSS -->
<link href="../biblioteca/bootstrap-3.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link
	href="../biblioteca/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link
	href="../biblioteca/bootstrap-3.3.7/docs/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="theme.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="../biblioteca/bootstrap-3.3.7/docs/assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<div class="container">
<header>
		<div class="row">
			<div class="col-sm-6">
				<h2>Lista de Produtos</h2>
			</div>
			<div class="col-sm-6 text-right h2" align="right">
				<a href="adicionar-produto.php" class="btn btn-primary">&#10010;
					Novo Produto</a>
			</div>

		</div>

</header>

	<?php if (!empty($_SESSION['message'])) : ?>
    	<div class="alert alert-<?php echo $_SESSION['type']; ?> alert-dismissible" role="alert">
    		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    		<?php echo $_SESSION['message']; ?>
    	</div>
    	<?php clear_message('message'); clear_message('type'); ?>
	<?php endif; ?>
      
  <?php
$sql = "SELECT * FROM produto where CodEstabelecimento = " . $_SESSION['codEstabelecimento'];
$sql = $PDO->query($sql);
if ($sql->rowCount() == 0) {
    echo '<div class="alert alert-danger" role="alert">';
    echo '<strong>Aviso!</strong> Você ainda não possui Produtos cadastrados.';
    echo '</div>';
} else {
    
    echo '<div class="jumbotron">';
    // echo '<div class="container" overflow: auto;">';
    echo '<table class="table table-hover">';
    echo '<thead>';
    echo '<tr>';
    echo '<th>';
    echo '<th>Nome</th>';
    echo'<th>Descri&ccedil;&atilde;o</th>';
    echo '<th>Gelada</th>';
    echo'<th>Pre&ccedil;o</th>';
    echo '<th>A&ccedil;&otilde;es</th>';
    echo '</tr>';
    echo '</thead>';
    echo '<tbody>';
    echo '<tr>';
    
    foreach ($sql->fetchAll() as $produto) {
        echo '<td></td>';
        echo '<td>' . $produto['nome'] . '</td>';
        echo '<td>' . $produto['descricao'] . '</td>';
        echo '<td>' . $produto['gelada'] . '</td>';
        echo '<td>' . $produto['preco'] . '</td>';
        echo '<td><a href="editar-cliente.php?id=' . $produto['codProduto'] . '" class="btn btn-warning">&#9999; Editar</a></td>';
        echo '<td><a href="excluir-cliente.php?id=' . $produto['codProduto'] . '" class="btn btn-danger">&#10006; Excluir</a></td>';
        echo '</tbody>';
        echo '</tr>';
    }
    echo '</table>';
    echo '</div>';
}
?>
        
	</div>



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