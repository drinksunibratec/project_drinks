<?php

function selected_Pagamento($pagamento = null)
{
    $array_pagamento = array("Especie","Cartao","ViaApp");
    $select = '<select class="form-control selectpicker" name="pagamento" id="pagamento" required>';
    $select .= '<option value="">--Selecione--</option>';
    foreach($array_pagamento as $val){
        $sel = ($val == $pagamento)?'selected="selected"':'';
        
        $select .= '<option value="'.$val.'" '.$sel.'>'.$val.'</option>';
    }
    $select .= '</select>';
    echo $select;
}
