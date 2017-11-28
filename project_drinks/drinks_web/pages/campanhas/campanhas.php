<?php
/*
 *
 * Created by Tercio Lima on 22/11/2017
 *
 */
require_once ('../include/header.php');
require_once ('../menu/menu.php');

require 'PHPMailerAutoload.php';

$Mailer = new PHPMailer();

//Define que ser� usado SMTP
$Mailer->IsSMTP();

//Enviar e-mail em HTML
$Mailer->isHTML(true);

//Aceitar carasteres especiais
$Mailer->Charset = 'UTF-8';

//Configura��es
$Mailer->SMTPAuth = true;
$Mailer->SMTPSecure = 'ssl';

//nome do servidor
$Mailer->Host = 'comunidademaanaim.org.br';
//Porta de saida de e-mail
$Mailer->Port = 587;

//Dados do e-mail de saida - autentica��o
$Mailer->Username = 'drinks@comunidademaanaim.org.br';
$Mailer->Password = '$Drinks123$';

//E-mail remetente (deve ser o mesmo de quem fez a autentica��o)
$Mailer->From = 'drinks@comunidademaanaim.org.br';

//Nome do Remetente
$Mailer->FromName = 'Drinks';

//Assunto da mensagem
$Mailer->Subject = 'Titulo - Drinks Teste';

//Corpo da Mensagem
$Mailer->Body = 'Teste de Campanha';

//Corpo da mensagem em texto
$Mailer->AltBody = 'Teste de Campanha';

//Destinatario
$Mailer->AddAddress('terciosky@gmail.com');

if($Mailer->Send()){
    echo "E-mail enviado com sucesso";
}else{
    echo "Erro no envio do e-mail: " . $Mailer->ErrorInfo;
}


?>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-rc1/js/bootstrap.min.js"></script>
</head>

<body>

	<div class="container">

		<div class="jumbotron">

			<form action="" method="post" class="contactForm">

				<div class="form-group">
					<label for="titulo">T�tulo:</label> <input type="text"
						class="form-control" name="titulo" />
				</div>

				<div class="form-group">
					<label for="descricao">Descri��o da Campanha</label>
					<textarea class="form-control" name="descricao" rows="5"
						data-rule="required"
						placeholder="Digite aqui os detalhes da campanha..."></textarea>
				</div>

				<div class="fileupload fileupload-new" data-provides="fileupload">
					<span class="btn btn-primary btn-file">
							
							<input
						type="file" /></span> <span class="fileupload-preview"></span>
				</div><br>

				<input type="submit" name="btn_enviar" value="Enviar" />
			</form>

		</div>
</body>
</html>