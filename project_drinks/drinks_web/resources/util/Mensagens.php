<?php

class Mensagens
{

    public function clear_message($data)
    {
        unset($_SESSION[$data]);
    }

    public function imprimirMensagem()
    {
        if (! empty($_SESSION['message'])) {
            $mensagem = '<div class="alert alert-' . $_SESSION['type'] . ' alert-dismissible" role="alert">';
            $mensagem .= '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
            $mensagem .= $_SESSION['message'];
            $mensagem .= '</div>';
            
            $this->clear_message('message');
            $this->clear_message('type');
            
            echo $mensagem;
        }
    }
}
?>