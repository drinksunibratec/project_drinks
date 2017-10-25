<?php
require_once ('../../biblioteca/functions/Functions.php');
require_once ('../../biblioteca/functions/DB_Functions.php');
require_once ('../../biblioteca/util/Mensagens.php');
require_once ('../../biblioteca/include/header.php');
require_once ('../../biblioteca/menu/menu.php');

$codEstabelecimento = $_SESSION['codEstabelecimento'];


$mensagens = new Mensagens();
$dados = null;

if($_SESSION['administrador'] == 1){
    $dados = buscarTodosOsRegistros(ESTABELECIMENTO);
}else{
    $dados = buscarRegistroPorId(ESTABELECIMENTO, $codEstabelecimento, 'codEstabelecimento');
}


?>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title>Administração de Estabelicimento</title>
        
        <!-- MASCARA -->
         <script>
          jQuery(function($){
                 $("#cnpj").mask("99.999.999/9999-99");
                 $("#telefone").mask("(99)99999-9999");
                 
          });
         </script>
    
    </head>

<body>
	<div class="container">

    	<header>
    		<div class="row">
        		<?php tituloDaListagem('Lista de Estabelecimentos', 'Seus Dados');
        		      botaoNovo('adicionar-estabelecimento.php', '&#10010', 'primary', 'Novo estabelecimento');?>
    		</div>
    		

    	</header>
         
		<?php $mensagens->imprimirMensagem(); ?>

  <?php
  if (count($dados) == 0) {
    echo '<div class="alert alert-danger" role="alert">';
    echo '<strong>Aviso!</strong> Você ainda não possui Estabelecimentos cadastrados.';
    echo '</div>';
    
    echo '<div class="jumbotron">';
    echo '<div class="container" style="width:600px; height: 400px; overflow: auto;">';
    echo '<img src="../biblioteca/img/broken-file.png">';
} else {
    
    echo '<div class="jumbotron">';
    echo '<div class="container">';
    echo '<tr>';
    echo '<table class="table table-hover">';
    echo '<thead>';
    echo '<tr>';
    echo '<th>CNPJ</th>';
    echo '<th>Email</th>';
    // echo'<th>bairro</th>';
    // echo'<th>CEP</th>';
    echo '<th>Cidade</th>';
    // echo'<th>Latitude</th>';
    // echo'<th>Longitude</th>';
    // echo'<th>Numero</th>';
    // echo'<th>Rua</th>';
    echo '<th>UF</th>';
    echo '<th>Raz&atilde;o Social</th>';
    echo '<th>Telefone</th>';
    echo '<th class="text-center">A&ccedil;&otilde;es</th>';
    echo '</tr>';
    echo '</thead>';
    echo '<tbody>';
    echo '<tr>';
    
    foreach ($dados as $estabelecimento) {
        echo '<td id="cnpj">' . $estabelecimento['cnpj'] . '</td>';
        echo '<td>' . $estabelecimento['eMail'] . '</td>';
        // echo'<td>'.$estabelecimento['bairro'].'</td>';
        // echo'<td>'.$estabelecimento['cep'].'</td>';
        // echo'<td>'.$estabelecimento['latitude'].'</td>';
        // echo'<td>'.$estabelecimento['longitude'].'</td>';
        // echo'<td>'.$estabelecimento['numero'].'</td>';
        // echo'<td>R$ '.$estabelecimento['rua'].'</td>';
        echo '<td>' . $estabelecimento['cidade'] . '</td>';
        echo '<td>' . $estabelecimento['uf'] . '</td>';
        echo '<td>' . $estabelecimento['razaoSocial'] . '</td>';
        echo '<td id="telefone">' . $estabelecimento['telefone'] . '</td>';
        echo '<td><a href="editar-estabelecimento.php?codEstabelecimento=' . $estabelecimento['codEstabelecimento'] . '" class="btn btn-warning">&#9999; Editar</a></td>';
        echo '<td><a href="excluir-estabelecimento.php?codEstabelecimento=' . $estabelecimento['codEstabelecimento'] . '" class="btn btn-danger">&#10006; Excluir</a></td>';
        echo '<td><a href="../produto/consulta_produto-estabelecimento.php?codEstabelecimento=' . $estabelecimento['codEstabelecimento'] . '" class="btn btn-info">&#x1a; Produtos</a></td>';
        echo '</tbody>';
        echo '</tr>';
    }
    echo '</table>';
    echo '</div>';
    echo '</div>';
}
?>


</div>
	


</body>
</html>