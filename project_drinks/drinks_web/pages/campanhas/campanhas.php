<?php
/*
 *
 * Created by Tercio Lima on 22/11/2017
 *
 */
require_once ('../include/header.php');
require_once ('../menu/menu.php');
?>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/js/bootstrap.min.js"></script>
</head>

<body>

	<div class="container">

		<div class="jumbotron">

			<form action="enviar.php" method="post" class="contactForm">

				<div class="form-group">
					<label for="titulo">Título:</label> <input type="text"
						class="form-control" name="titulo" />
				</div>

				<div class="form-group">
					<label for="descricao">Descrição da Campanha</label>
					<textarea class="form-control" name="descricao" rows="5"
						data-rule="required"
						placeholder="Digite aqui os detalhes da campanha..."></textarea>
				</div>

				<div class="fileupload fileupload-new" data-provides="fileupload">
					<span class="btn btn-primary btn-file"><span class="fileupload-new">Select
							file</span> <span class="fileupload-exists">Change</span><br>
							
							<input
						type="file" /></span> <span class="fileupload-preview"></span> <a
						href="#" class="close fileupload-exists" data-dismiss="fileupload"
						style="float: none">×</a>
				</div><br>

				<input type="submit" name="btn_enviar" value="Enviar" />
			</form>

		</div>
</body>
</html>