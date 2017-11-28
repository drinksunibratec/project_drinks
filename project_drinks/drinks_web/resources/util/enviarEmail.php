<?php
/*
 *
 * Created by Tercio Lima on 19/11/2017
 *
 */
require 'PHPMailerAutoload.php';


$enviarEmail = new PHPMailer();
$enviarEmail->isSMTP;
$enviarEmail->SMTPDebug = true;
$enviarEmail->SMTPSecure = 'ssl';
$enviarEmail->Port = 587;
$enviarEmail->SMTPAuth = 'false';
$enviarEmail->Username = 'drinks@comunidademaanaim.org.br';//'nome da conta';
 $enviarEmail->Password = '$Drinks123$';//'sua senha';
$enviarEmail->FromName = 'Admin Drinks';//'Seu nome';
$enviarEmail->From = 'drinks@comunidademaanaim.org.br';//'seu email';
$enviarEmail->addAddress('terciosky@gmail.com','Tercio');//('e-mail do destinattário','Nome do destinatário');

$enviarEmail->Subject = 'Título do e-mail';
$enviarEmail->Body = '<span style=\'font-weight:bold;\'>Olá isso é um teste</span>';

$enviarEmail->isHTML(true);
$enviarEmail->Priority = 3;
$enviarEmail->CharSet = 'UTF-8';

if(!$enviarEmail->Send()){    
    
    echo "<script>alert('Mensagem enviada com sucesso!');</script>";
    header("Location: campanhas.php");
    
} else{
    
    echo "<script>alert('Falha ao enviar e-mail, tente novamente...);</script>";
}
?>