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
$Novembro = pedidoNovembro(PEDIDO);
$fatNov = faturamentoNovembro(PEDIDO);
$Agosto = pedidoAgosto(PEDIDO);
$Setembro = pedidoSetembro(PEDIDO);
$Outubro = pedidoOutubro(PEDIDO);
$fatAgo = faturamentoAgosto(PEDIDO);
$fatSet = faturamentoSetembro(PEDIDO);
$fatOut = faturamentoOutubro(PEDIDO);

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
            $count_Nov = null;
            $faturamentoBruto = null;
            $ticket = null;
            $count_Nov = $Novembro['teste'];
            $faturamentoBruto = $fatNov['valor'];
            $faturamentoBrutoAgosto = $fatAgo['valorAgosto'];
            $faturamentoBrutoSetembro = $fatSet['valorSetembro'];
            $faturamentoBrutoOutubro = $fatOut['valorOutubro'];
            $ticketAgosto = null;
            $ticket = null;
            $ticketSetembro = null;
            $ticketOutubro = null;
            $count_Ago = $Agosto['agosto'];
            $count_Set = $Setembro['setembro'];
            $count_Out = $Outubro['outubro'];
            if (count($dado) > 0) {
                foreach ($dado as $pedido) {
                    
                    
                    if ($count_Nov > 0) {
                        //$faturamentoBruto = $pedido['valorTotal'] + $faturamentoBruto;
                        $ticket = $faturamentoBruto / $count_Nov;
                    }

                    if($count_Ago > 0) {
                        $ticketAgosto = $faturamentoBrutoAgosto / $count_Ago;
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
           ['Agosto', <?php echo $count_Ago; ?>, <?php echo $faturamentoBrutoAgosto ?>, <?php echo $ticketAgosto; ?>],
          ['Setembro', <?php echo $count_Set; ?>, <?php echo $faturamentoBrutoSetembro ?>, <?php echo $ticketSetembro ?>],
          ['Outubro', <?php echo $count_Out; ?>, <?php echo $faturamentoBrutoOutubro ?>, <?php echo $ticketOutubro ?>],
          ['Novembro', <?php echo $count_Nov; ?>, <?php echo $faturamentoBruto ?>, <?php echo $ticket ?>]
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