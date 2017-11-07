<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codEstabelecimento = $_SESSION['codEstabelecimento'];

$mensagens = new Mensagens();
$dados = null;

if ($_SESSION['administrador'] == 1) {
    $dados = buscarTodosOsRegistros(ESTABELECIMENTO);
} else {
    $dados = buscarRegistroPorId(ESTABELECIMENTO, $codEstabelecimento, 'codEstabelecimento');
}

?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Administração de Estabelicimento</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- MASCARA -->
<script>
          jQuery(function($){
                 $("#cnpj").mask("99.999.999/9999-99");
                 $("#telefone").mask("(99)99999-9999");
                 
          });
         </script>

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
        		<?php
        
        tituloDaListagem('', '');
        botaoNovo('adicionar.php', '&#10010', 'primary', 'Novo estabelecimento');
        ?>
    		</div>


		</header>
		<div class="row">
        	<?php $mensagens->imprimirMensagem(); ?>
    	</div>
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">Lista de Estabelecimentos</div>
				<div class="panel-body">

					<input class="form-control" id="myInput" type="text"
						placeholder="Pesquisar.." style="width: 400px;"> <br>

					<!-- TABLE -->
					<table class="table table-bordered table-striped">
						<thead class="blue-grey lighten-4">
							<tr>
								<th>CNPJ</th>
								<th>Raz&atilde;o Social</th>
								<th>E-mail</th>
								<th>Bairro</th>
								<th>Cidade</th>
								<th>UF</th>
								<th>Telefone</th>
								<th align="center">A&ccedil;&otilde;es</th>
							</tr>
						</thead>
            				
            				<?php
                
foreach ($dados as $estabelecimento) {
                    ?>
            				
            				<tbody id="myTable">
							<tr>
								<td id="cnpj"><?php echo Mask('##.###.###/####-##',$estabelecimento['cnpj']); ?></td>
								<td><?php echo $estabelecimento['razaoSocial']; ?></td>
								<td><?php echo $estabelecimento['eMail']; ?></td>
								<td><?php echo $estabelecimento['bairro']; ?></td>
								<td><?php echo $estabelecimento['cidade']; ?></td>
								<td><?php echo $estabelecimento['uf']; ?></td>
								<td id="telefone"><?php echo Mask('(##) #####-####',$estabelecimento['telefone']); ?></td>
								<td align="center">
								<a title="Alterar" href="editar.php?codEstabelecimento=<?php echo  $estabelecimento['codEstabelecimento']?>"
									class="btn btn-sm btn-warning">&#9999; Alterar</a>
								<a href="../produto/lista.php?codEstabelecimento=<?php echo  $estabelecimento['codEstabelecimento']?>"
									class="btn btn-sm btn-info">&#x1a; Produtos</a></td>
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