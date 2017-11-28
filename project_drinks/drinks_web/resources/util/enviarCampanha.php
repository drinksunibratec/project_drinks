<?php
/*
 *
 * Created by Tercio Lima on 28/11/2017
 *
 */
require 'PHPMailerAutoload.php';

$Mailer = new PHPMailer();

//Define que será usado SMTP
$Mailer->IsSMTP();

//Enviar e-mail em HTML
$Mailer->isHTML(true);

//Aceitar carasteres especiais
$Mailer->Charset = 'UTF-8';

//Configurações
$Mailer->SMTPAuth = true;
$Mailer->SMTPSecure = 'ssl';

//nome do servidor
$Mailer->Host = 'comunidademaanaim.org.br';
//Porta de saida de e-mail
$Mailer->Port = 587;

//Dados do e-mail de saida - autenticação
$Mailer->Username = 'drinks@comunidademaanaim.org.br';
$Mailer->Password = '$Drinks123$';

//E-mail remetente (deve ser o mesmo de quem fez a autenticação)
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

// $headers = "From: terciosky@gmail.com";

// $corpoemail = '<b>Teste de Campanha</b>'
    
// 			   Titulo: '   $titulo.' /n
// 			   Descricao:'   .$descricao.'/n


// if(!$enviarEmail->Send()){    
    
//     echo "<script>alert('Mensagem enviada com sucesso!');</script>";
//     header("Location: listarPedido.php");
    
// } else{
    
//     echo "<script>alert('Falha ao enviar e-mail, tente novamente...);</script>";
// }
?>