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
            if (count($dado) > 0) {
                foreach ($dado as $pedido) {
                    
                    if (count($pedido['codPedido']) > 0) {
                        $count_Nov ++;
                    }
                    
                    if ($pedido['valorTotal'] > 0) {
                        $faturamentoBruto = $pedido['valorTotal'] + $faturamentoBruto;
                        $ticket = $faturamentoBruto / $count_Nov;
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
          ['Novembro', <?php echo $count_Nov; ?>, <?php echo $faturamentoBruto ?>, <?php echo $ticket; ?>],
          ['Dezembro', 1170, 460, 250],
          ['Janeiro', 660, 1120, 300],
          ['Fevereiro', 1030, 540, 350]
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