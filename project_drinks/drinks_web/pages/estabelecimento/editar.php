<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codEstabelecimento = 0;
$caracters = array(
    "/",
    "-",
    ".",
    "(",
    ")",
    " "
);

if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = addslashes($_GET['codEstabelecimento']);
}

if (isset($_POST['cnpj']) && empty($_POST['cnpj']) == false) {
    $dados = $_POST;
    foreach ($dados as $key => $value) {
        if ($key == 'cnpj' || $key == 'telefone' || $key == 'cep') {
            $dados[$key] = str_replace($caracters, "", $value);
        }
    }
    
    update('estabelecimento', $codEstabelecimento, $dados, 'codEstabelecimento');
    
    header("Location: lista.php");
}

$dados = buscarRegistroPorId(ESTABELECIMENTO, $codEstabelecimento, 'codEstabelecimento');

?>

<!DOCTYPE html>

<head>
<title>Editar Estabelecimento</title>

<!-- MASCARA  -->
<script>
      jQuery(function($){
             $("#cnpj").mask("99.999.999/9999-99");
             $("#telefone").mask("(99) 99999-9999");
             $("#cep").mask("99999-999");     
      });
</script>

</head>

<body>
	<div class="container">
	
		<?php foreach ($dados as $dado) {?>
		
		<h3>Dados cadastrais de <?php echo $dado['nomeFantasia']; ?>:</h3>
		<div class="jumbotron">
			<form method="POST">

				<div class="row">

					<div class="form-group col-md-3">
						<label for="cnpj">CNPJ</label> <input type="text"
							class="form-control" id="cnpj" name="cnpj"
							value="<?php echo $dado['cnpj']; ?> " readonly>
					</div>

					<div class="form-group col-md-5">
						<label for="razaoSocial">Raz&atilde;o Social</label> <input
							type="text" class="form-control" name="razaoSocial"
							value="<?php echo $dado['razaoSocial']; ?>" maxlength="100"
							required>
					</div>

					<div class="form-group col-md-5">
						<label for="nomeFantasia">Nome Fantasia</label> <input type="text"
							class="form-control" name="nomeFantasia"
							value="<?php echo $dado['nomeFantasia']; ?>" maxlength="100"
							required>
					</div>

					<div class="form-group col-md-3">
						<label for="email">E-mail</label> <input type="text"
							class="form-control" name="email"
							value="<?php echo $dado['eMail']; ?>" maxlength="100" required>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-3">
						<label for="cep">CEP<span id='mensagem'></span></label> <input
							id="cep" name="cep" type="text" class="form-control"
							placeholder="Digite seu CEP..."
							value="<?php echo $dado['cep']; ?>"
							onblur="javascript:buscarEndereco('formInscricao')" />
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-4">
						<label for="rua">Rua</label> <input type="text"
							class="form-control" name="rua"
							value="<?php echo $dado['rua']; ?>" maxlength="150" required>
					</div>

					<div class="form-group col-md-2">
						<label for="numero">Numero</label> <input type="text"
							class="form-control" id="numero" name="numero"
							value="<?php echo $dado['numero']; ?>" maxlength="6" required>
					</div>

					<div class="form-group col-md-3">
						<label for="bairro">Bairro</label> <input type="text"
							class="form-control" name="bairro"
							value="<?php echo $dado['bairro']; ?>" maxlength="50" require>
					</div>
				</div>

				<div class=row>
					<div class="form-group col-md-4">
						<label for="cidade">Cidade</label> <input type="text"
							class="form-control" name="cidade"
							value="<?php echo $dado['cidade']; ?>" maxlength="50" require>
					</div>

					<div class="form-group col-md-3">
						<label for="uf">UF</label>
                    	<?php selected_UF($dado['uf'])?> 
                      
                  	</div>


					<div class="form-group col-md-2">
						<label for="latitute">Latitude</label> <input type="text"
							class="form-control" name="latitude"
							value="<?php echo $dado['latitude']; ?>">
					</div>

					<div class="form-group col-md-2">
						<label for="longitude">Longitude</label> <input type="text"
							class="form-control" name="longitude"
							value="<?php echo $dado['longitude']; ?>">
					</div>
				</div>

				<div class=row>
					<div class="form-group col-md-3">
						<label for="telefone">Telefone</label> <input type="text"
							class="form-control" name="telefone"
							value="<?php echo $dado['telefone']; ?>">
					</div>

					<div class="form-group col-md-3">
						<label for="senha">Senha</label> <input type="password"
							class="form-control" name="senha"
							value="<?php echo $dado['senha']; ?>">
					</div>

				</div>

				<div class=row>
					<div class="form-group col-md-4">
						<input type="submit" value="&#10003 Alterar"
							class="btn btn-primary" /> <a href="lista.php"
							class="btn btn-danger">&#10005Cancelar</a>
					</div>
				</div>

			</form>
		</div>
		<?php }?>
	</div>

</body>
