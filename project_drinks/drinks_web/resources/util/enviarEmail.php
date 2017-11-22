<?php
/*
 *
 * Created by Tercio Lima on 19/11/2017
 *
 */
require_once 'PHPMailerAutoload.php';

$codEstabelecimento = null;
$codPedido = null;

if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = $_GET['codEstabelecimento'];
} else {
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
}

if (isset($_GET['codPedido']) && empty($_GET['codPedido']) == false) {
    $codPedido = addslashes($_GET['codPedido']);
    
}

$dados = detalhesPedido($codEstabelecimento, $codPedido);

if (count($dados) > 0) {
    foreach ($dados as $info) {
         $codPedido = $info['codPedido'];
         $nomeUsuario = $info['usuario'];
         $emailUsuario = $info['email'];
         $dataPedido = $info['dataPedido'];
         $valorTotal = $info['valorTotal'];
         $status = $info['status'];
         $nomeFantasia = $info['nomeFantasia'];
         $eMailEstab = $info['eMail'];
         $senhaEstab = $info['senha'];
         echo $info['codPedido'];
    }
}


$enviarEmail = new PHPMailer();
$enviarEmail->isSMTP;
$enviarEmail->SMTPDebug = false;
$enviarEmail->SMTPSecure = 'ssl';
$enviarEmail->Port = ;
$enviarEmail->SMTPAuth = 'true';
$enviarEmail->Username = $eMailEstab;//'nome da conta';
$enviarEmail->Password = $senhaEstab;//'sua senha';
$enviarEmail->FromName = $nomeFantasia;//'Seu nome';
$enviarEmail->From = $eMailEstab;//'seu email';
$enviarEmail->addAddress($emailUsuario,$nomeUsuario);//('e-mail do destinattário','Nome do destinatário');
$enviarEmail->Subject = 'Título do e-mail';
$enviarEmail->Body = '<span style=\'font-weight:bold;\'>Olá' .$nomeUsuario.
'<br>
Seu Pedido de número '.$codPedido.' mudou de status e agora é '.$status.'
</span>';
$enviarEmail->isHTML(true);
$enviarEmail->Priority = 3;
$enviarEmail->CharSet = 'UTF-8';

if(!$enviarEmail->Send()){    
    
    echo "<script>alert('Mensagem enviada com sucesso!');</script>";
    header("Location: listarPedido.php");
    
} else{
    
    echo "<script>alert('Falha ao enviar e-mail, tente novamente...);</script>";
}
?>