<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');
//require 'DB_Connect.php';
require_once '../Classes/PHPExcel.php';

$objPHPExcel = new PHPExcel();


$objPHPExcel->getProperties()->setCreator("Data do Pedido")
->setLastModifiedBy("Bairro")
->setTitle("CEP")
->setSubject("Cidade")
->setDescription("RUA")
->setKeywords("UF")
->setCategory("PAGAMENTO");

$objPHPExcel->setActiveSheetIndex(0)
->setCellValue('A1', 'Título do Exemplo da Classe PHPExcel (vai ficar a negrito)')
->setCellValue('A2', 'uma excelente classe (vai ficar em itálico a azul)');


$objPHPExcel->getActiveSheet()->getColumnDimension('A')->setAutoSize(true);
 
// Formatar a célula A1 a negrito
$objPHPExcel->getActiveSheet()->getStyle('A1')->getFont()->setBold(true);
 
// Formatar a célula A2 a itálico
$objPHPExcel->getActiveSheet()->getStyle('A2')->getFont()->setItalic(true);
 
// Formatar a cor do texto da célula A2 a azul
$objPHPExcel->getActiveSheet()->getStyle('A2')->getFont()->getColor()->setARGB(PHPExcel_Style_Color::COLOR_BLUE);
 
// Formatar o topo da célula A2 com uma borda
$objPHPExcel->getActiveSheet()->getStyle('A2')->getBorders()->getTop()->setBorderStyle(PHPExcel_Style_Border::BORDER_THICK);
 
// Colocar uma borda em torno da área A1:A5
$objPHPExcel->getActiveSheet()->getStyle('A1:A5')->getBorders()->getOutline()->setBorderStyle(PHPExcel_Style_Border::BORDER_THIN);

$objPHPExcel->setActiveSheetIndex(0)
->setCellValue('A3', '15')
->setCellValue('A4', '20')
->setCellValue('A5', '=A3+A4');

$objWriter = PHPExcel_IOFactory::createWriter($objPHPExcel, 'Excel5');

header('Content-Type: application/vnd.ms-excel');
header('Content-Disposition: attachment;filename="teste.xls"');
header('Cache-Control: max-age=0');
$objWriter->save('php://output');


?>