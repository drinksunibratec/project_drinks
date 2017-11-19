<?php
/*
 *
 * Created by Tercio Lima on 02/11/2017
 *
 */
function selected_Pagamento($pagamento = null)
{
    $array_pagamento = array("ESPECIE","CARTAO","VIA_APP");
    $select = '<select class="form-control selectpicker" name="pagamento" id="pagamento" required>';
    $select .= '<option value="">--Selecione--</option>';
    foreach($array_pagamento as $val){
        if($pagamento){
            $sel = ($val == $pagamento)?'selected="selected"':'';
            $select .= '<option value="'.$val.'" '.$sel.'>'.$val.'</option>';
        }else{
            $select .= '<option value="'.$val.'">'.$val.'</option>';
        }
        
    }
    $select .= '</select>';
    echo $select;
}