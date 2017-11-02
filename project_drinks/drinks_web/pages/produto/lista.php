<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

//$mensagens = new Mensagens();
//$codEstabelecimento = null;
//if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
//    $codEstabelecimento = $_GET['codEstabelecimento'];
//}else{
//    $codEstabelecimento = $_SESSION['codEstabelecimento'];
//}
//$dados = buscarRegistroPorId(PRODUTO,  $codEstabelecimento, 'codEstabelecimento');


$host = "localhost";
$user = "root";
$senha = "";
$banco = "comumana_drinks";
$estabelecimento = $_GET['codEstabelecimento'];

$mysqli = new mysqli($host, $user, $senha, $banco);
if($mysqli->connect_errno){
    echo 'Falha na Conexão: ('.$mysqli->connect_errno.')'.$mysqli->connect_error;
}

//$consulta = "SELECT p.descricao, p.gelada, p.nome, p.preco "
//        . "FROM produto as p "
//        . "INNER JOIN estabelecimento as e on "
//        . "(e.codEstabelecimento = p.codEstabelecimento)";
$consulta = "SELECT * FROM produto WHERE codEstabelecimento='$estabelecimento'";
$con = $mysqli->query($consulta)or die($mysqli->error);
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
					<a href="adicionar.php" class="btn btn-sm btn-primary">&#10010;
						Novo Produto</a>
				</div>

			</div>

		</header>
 		<div class="row">
        	<?php //$mensagens->imprimirMensagem(); ?>
    	</div>
    	<div class="row">
        		<div class="panel panel-default">
        		<div class="panel-heading"></div>
        			<div class="panel-body">

            			<!-- TABLE -->
            		<table border="1" class="table table-bordered table-striped">
            			<thead  class="blue-grey lighten-4">
            				<tr>
            					<th>Nome</th>
            					<th>Preço</th>
            					<th>Descrição</th>
            					<th>Gelada</th>
            					<th>Ações</th>
            				</tr>
            			</thead>
            			
            			<?php // foreach ($dados as $produto){ 
                                    while ($dados = $con->fetch_array()){//pegando cada uma das colunas e armazena na variavel dados
            			?>
            			
            			<tbody>
            				<tr>
            					<td><?php echo $dados['nome']; ?></td>
            					<td><?php echo $dados['preco']; ?></td>
            					<td><?php echo $dados['descricao']; ?></td>
            					<td><?php echo $dados['gelada']; ?></td>
            					<td align="center">
                                                    <a title="Alterar" href="editar.php?codProduto=<?php echo  
                                                    $produto['codProduto']?>" class="btn btn-sm btn-warning" >&#9999; 
                                                        Alterar</a>
                                                    <a title="Excluir" id="btn-excluir" 
                                                       href="excluir.php?codEstabelecimento=
                                                           <?php echo $produto['codProduto']?>" 
                                                           class="btn btn-sm btn-danger tooltipBtn">
                                                        &#10006; Excluir</a>
                                                </td>
            				</tr>
            			</tbody>
            			
            			<?php }?>
            			
            		</table>
            		<!-- END TABLE -->
        		</div>
    		</div>
		</div>
	</div>
</body>
</html>