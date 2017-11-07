<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$mensagens = new Mensagens();
$codEstabelecimento = null;
    if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
$codEstabelecimento = $_GET['codEstabelecimento'];
}else{
$codEstabelecimento = $_SESSION['codEstabelecimento'];
}
$dados = buscarRegistroPorId(PRODUTO, $codEstabelecimento, 'codEstabelecimento');

$host = "localhost";
$user = "root";
$senha = "";
$banco = "comumana_drinks";

$mysqli = new mysqli($host, $user, $senha, $banco);
if ($mysqli->connect_errno) {
    echo 'Falha na Conexão: (' . $mysqli->connect_errno . ')' . $mysqli->connect_error;
}


$consulta = "SELECT * FROM produto WHERE codEstabelecimento=$codEstabelecimento";
$con = $mysqli->query($consulta) or die($mysqli->error);

?>




<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Administração de Produtos</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!--FUN��O PARA PESQUISA-->
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

					<input class="form-control" id="myInput" type="text"
						placeholder="Pesquisar..." style="width: 400px;"> <br>

					<!-- TABLE -->
					<table border="1" class="table table-bordered table-striped">
						<thead class="blue-grey lighten-4">
							<tr>
								<th>Nome</th>
								<th>Preço</th>
								<th>Descrição</th>
								<th>Ações</th>
							</tr>
						</thead>
            			
            			<?php
            // foreach ($dados as $produto){
            while ($dados = $con->fetch_array()) { // pegando cada uma das colunas e armazena na variavel dados
                ?>
            			
                    <tbody id="myTable">
				<tr>
                                    <td><?php echo $dados['nome']; ?></td>
                                    <td><?php echo "R$ ".$dados['preco']; ?></td>
                                    <td><?php echo $dados['descricao']; ?></td>
                                    <td align="center">
                                        <a title="Alterar" href="editar.php?codProduto=<?php echo  $dados['codProduto']?>"
									class="btn btn-sm btn-warning">&#9999; Alterar</a>
                                  <?php 
                                  if(isset($_POST['excluir'])){
                                  $codProduto = $_GET['codProduto'];
                                  buscarRegistroPorId(PRODUTO, $codProduto);
                                    deleta(PRODUTO, "where codProduto = ".$codProduto);
                                  }
                                  ?>
                                    <a name="excluir" title="Excluir" id="btn-excluir" href="lista.php?codEstabelecimento= <?php echo $dados['codProduto']?>'"
                                       class="btn btn-sm btn-danger tooltipBtn"> &#10006; Excluir</a>
                                    </td>
				</tr>
                    </tbody>
            			
            	<?php }?>
            			
            		</table>
					<!-- END TABLE -->
				</div>
                                <?php
                                
//                                if(isset($_POST['excluir'])){
//                                    //REGISTRO DOS DADOS
//                                    //Verificar se a seção foi aberta
//                                    if (!isset($_SESSION))
//                                        session_start ();
//                                    
//                                    $codProdutoDel = intval($_GET['codEstabelecimento']);
//                                
//                                    $sql_code = "DELETE FROM produto WHERE codigo = '$codProdutoDel'";
//                                    $sql_query = $mysqli->query($sql_code) or die($mysqli->error);
//                                    
//                                    header("Location: lista.php");
//                                }
                                ?>
			</div>
		</div>
	</div>
</body>
</html>