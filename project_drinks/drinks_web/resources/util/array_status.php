<?php
/*
 *
 * Created by Tercio Lima on 02/11/2017
 *
 */
function selected_Status($status = null)
{
    $array_status = array("AGUARDANDO", "EM TRANSITO", "ENTREGUE", "CANCELADO");
    if($status == "ENTREGUE"){
        $disabled = 'disabled';
    }
    $select = '<select class="form-control selectpicker" name="status" id="status"' .$disabled . '>';
    $select .= '<option value="">--Selecione--</option>';
    foreach($array_status as $val){
        if($status){
            $sel = ($val == $status)?'selected="selected"':'';
            
            $select .= '<option value="'.$val.'" '.$sel.'>'.$val.'</option>';
        }else{
            $select .= '<option value="'.$val.'">'.$val.'</option>';
        }
        
    }
    $select .= '</select>';
    echo $select;
}