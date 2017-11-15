<?php

function isAdministrador()
{
    if ($_SESSION['administrador'] == 1) {
        return true;
    } else {
        return false;
    }
}

function tituloDaListagem($opcao1, $opcao2)
{
    $titulo = '<div class="col-sm-6">';
    
    if (isAdministrador()) {
        $titulo .= '<h2>' . $opcao1 . '</h2>';
    } else {
        $titulo .= '<h2>' . $opcao2 . '</h2>';
    }
    
    $titulo .= '</div>';
    
    echo $titulo;
}

function botaoNovo($ref, $icon, $tipo, $titulo)
{
    if (isAdministrador()) {
        $botao = '<div class="col-sm-6 text-right h2" align="right">';
        $botao .= '<a href="' . $ref . '" class=" btn btn-sm btn-' . $tipo . '">' . $icon . ' ' . $titulo . '</a>';
        $botao .= '</div>';
        echo $botao;
    }
}

//Máscara Dinâmicas 
function Mask($mask,$str){
    
    $str = str_replace(" ","",$str);
    
    for($i=0;$i<strlen($str);$i++){
        $mask[strpos($mask,"#")] = $str[$i];
    }
    
    return $mask;    
}
?>