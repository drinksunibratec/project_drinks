<?php
/*
 *
 * Created by Tercio Lima on 22/11/2017
 *
 */
require_once ('../include/header.php');
require_once ('../menu/menu.php');

<!DOCTYPE html>
<html lang="pt-br">
<body>
<div class="container">

			<div class="jumbotron">

			<form action="enviar.php" method="post" class="contactForm">
						
			<div class="form-group">
			     <label for="titulo">Título:</label>
			     <input type="text" class="form-control" name="titulo" />

			</div>
			     

			<div class="form-group">
			     <label for="descricao">Descrição da Campanha</label>
			     <textarea class="form-control" name="descricao" rows="5" data-rule="required"
			         placeholder="Digite aqui os detalhes da campanha..."></textarea>
			
			</div>
			
			<input type="submit" name="btn_enviar"  value="Enviar"/>
			</form>

        </div>

</body>






















