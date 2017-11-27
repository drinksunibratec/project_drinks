<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codEstabelecimento = null;
if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = $_GET['codEstabelecimento'];
} else {
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
}

$dado = listarPedido($codEstabelecimento);
$Novembro = pedidoNovembro($codEstabelecimento);
$fatNov = faturamentoNovembro($codEstabelecimento);
$Dezembro = pedidoDezembro($codEstabelecimento);
$Setembro = pedidoSetembro($codEstabelecimento);
$Outubro = pedidoOutubro($codEstabelecimento);
$fatDez = faturamentoDezembro($codEstabelecimento);
$fatSet = faturamentoSetembro($codEstabelecimento);
$fatOut = faturamentoOutubro($codEstabelecimento);
$bairro = bairroBoaViagem($codEstabelecimento);
$bairroIpsep = bairroIpsep($codEstabelecimento);
$bairroBoaVista = bairroBoaVista($codEstabelecimento);
$bairroCandeias = bairroCandeias($codEstabelecimento);
$bairroTejipio = bairroTejipio($codEstabelecimento);

?>



<!DOCTYPE html>
<html lang="pt-br">
<head>

<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Lista de Pedidos</title>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">Relatorios</div>
				<div class="panel-body">
           			
            	<?php
            //$count_Nov = null;
            $faturamentoBruto = null;
            $ticket = null;
            $count_Nov = $Novembro['teste'];
            $faturamentoBruto = $fatNov['valor'];
            $faturamentoBrutoDezembro = $fatDez['valorDezembro'];
            $faturamentoBrutoSetembro = $fatSet['valorSetembro'];
            $faturamentoBrutoOutubro = $fatOut['valorOutubro'];
            $ticketDezembro = null;
            $ticket = null;
            $ticketSetembro = null;
            $ticketOutubro = null;
            $count_Dez = $Dezembro['dezembro'];
            $count_Set = $Setembro['setembro'];
            $count_Out = $Outubro['outubro'];
            $count_Bv = $bairro['boaviagem'];
            $count_Ip = $bairroIpsep['ipsep'];
            $count_BoaV = $bairroBoaVista['boavista'];
            $count_Can = $bairroCandeias['candeias'];
            $count_Tej = $bairroTejipio['tejipio'];

            //$selecionarBairro = $bairro['bairro'];

           // echo $bairro['boaviagem'];

            
            
            if (count($dado) > 0) {
                foreach ($dado as $pedido) {
                    
                    
                    
                    if ($count_Nov > 0) {
                        //$faturamentoBruto = $pedido['valorTotal'] + $faturamentoBruto;
                        $ticket = $faturamentoBruto / $count_Nov;
                    }

                    if($count_Dez > 0) {
                        $ticketDezembro = $faturamentoBrutoDezembro / $count_Dez;
                    }

                    if($count_Set > 0) {
                        $ticketSetembro = $faturamentoBrutoSetembro / $count_Set;
                    }

                    if($count_Out > 0) {
                        $ticketOutubro = $faturamentoBrutoOutubro / $count_Out;
                    }
                    
                    
                    
                    ?>
                
                    <div id="barchart_material"
						style="width: 900px; height: 500px;"></div>
					<script type="text/javascript"
						src="https://www.gstatic.com/charts/loader.js"></script>
					<script type="text/javascript">
                    google.charts.load('current', {'packages':['bar']});
                    google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Mes', 'Pedidos', 'Faturamento', 'Ticket Medio'],
           ['Setembro', <?php echo $count_Set; ?>, <?php echo $faturamentoBrutoSetembro ?>, <?php echo $ticketSetembro ?>],
          ['Outubro', <?php echo $count_Out; ?>, <?php echo $faturamentoBrutoOutubro ?>, <?php echo $ticketOutubro ?>],
          ['Novembro', <?php echo $count_Nov; ?>, <?php echo $faturamentoBruto ?>, <?php echo $ticket ?>],
          ['Dezembro', <?php echo $count_Dez; ?> , <?php echo $faturamentoBrutoDezembro ?>, <?php echo $ticketDezembro; ?>]
        ]);

        var options = {
          chart: {
            title: 'Grafico ',
            subtitle: 'Mes, Pedidos, Faturamento: 2014-2017',
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

        var chart = new google.charts.Bar(document.getElementById('barchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));


      }
    </script>

    <div id="donutchart" style="width: 900px; height: 500px;"></div>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Bairro', 'Percentual de pedidos / Bairro'],
          ['Boa Viagem',     <?php echo $count_Bv; ?>],
          ['Ipsep',      <?php echo $count_Ip; ?>],
          ['Boa Vista',  <?php echo $count_BoaV; ?>],
          ['Candeias', <?php echo $count_Can; ?>],
          ['Tejipio',   <?php echo $count_Tej; ?> ]
        ]);

        var options = {
          title: 'Percentual de pedidos / Bairro',
          pieHole: 0.4,
        };

        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        chart.draw(data, options);
      }
    </script>

    
            			
            	<?php
                
}
            }
            ?>            			
				
				</div>
			</div>
		</div>
	</div>
</body>
</html>