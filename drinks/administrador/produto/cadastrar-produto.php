<?php
require_once ('../../biblioteca/include/header.php');
require_once ('../../biblioteca/menu/menu.php');
require_once ('../../biblioteca/functions/Functions.php');
require_once ('../../biblioteca/functions/DB_Functions.php');
require_once ('../../biblioteca/util/Mensagens.php');

$mensagens = new Mensagens();
$codEstabelecimento = $_SESSION['codEstabelecimento'];
?>




<!DOCTYPE html>
<html lang="pt-br">
<head>
	<title>Administração de Produtos</title>
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
 		<?php $mensagens->imprimirMensagem(); ?>
      
  <?php
  $dados = buscarRegistroPorId('produto',  $codEstabelecimento, 'CodEstabelecimento');
if (count($dados) == 0) {
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
    echo '<th>Descri&ccedil;&atilde;o</th>';
    echo '<th>Gelada</th>';
    echo '<th>Pre&ccedil;o</th>';
    echo '<th>A&ccedil;&otilde;es</th>';
    echo '</tr>';
    echo '</thead>';
    echo '<tbody>';
    echo '<tr>';
    
    foreach ($dados as $produto) {
        echo '<td></td>';
        echo '<td>' . $produto['nome'] . '</td>';
        echo '<td>' . $produto['descricao'] . '</td>';
        echo '<td>' . $produto['gelada'] . '</td>';
        echo '<td>' . $produto['preco'] . '</td>';
        echo '<td><a href="editar-produto.php?id=' . $produto['codProduto'] . '" class="btn btn-warning">&#9999; Editar</a></td>';
        echo '<td><a href="excluir-produto.php?id=' . $produto['codProduto'] . '" class="btn btn-danger">&#10006; Excluir</a></td>';
        echo '</tbody>';
        echo '</tr>';
    }
    echo '</table>';
    echo '</div>';
}
?>
        
	</div>


</body>
</html>