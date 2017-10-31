<?php

function selected_Status($status = null)
{
    $array_status = array("Pendente","Entregue","Cancelado");
    $select = '<select class="form-control selectpicker" name="status" id="status" required>';
    $select .= '<option value="">--Selecione--</option>';
    foreach($array_status as $val){
        $sel = ($val == $status)?'selected="selected"':'';
        
        $select .= '<option value="'.$val.'" '.$sel.'>'.$val.'</option>';
    }
    $select .= '</select>';
    echo $select;
}
