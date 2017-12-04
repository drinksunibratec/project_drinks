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
$dados = listarPedidoMes($codEstabelecimento);

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

	<div class="container" style="float: center; width: 100%; ">
		<div class="row">

			<div class="panel panel-default">
				<div class="panel-heading">Relatorios</div>
				<div class="panel-body">
           			
            	<?php
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
                
                    <div id="columnchart_values" style="float: left; width: 35%; height: 300px;"></div>

					<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ["Mes", "Pedidos", { role: "style" } ],
        ["Setembro", <?php echo $count_Set; ?>, "#b20000"],
        ["Outubro", <?php echo $count_Out; ?>, "#b20000"],
        ["Novembro", <?php echo $count_Nov; ?>, "#b20000"],
        ["Dezembro", <?php echo $count_Dez; ?>, "#b20000"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "Número de pedidos por mês",
        width: 450,
        height: 350,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
  }
  </script>

  <div id="columnchart_values2" style="float: left; width: 35%;; height: 300px; "></div>

  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ["Mes", "Pedidos", { role: "style" } ],
        ["Setembro", <?php echo $faturamentoBrutoSetembro ?>, "#1919ff"],
        ["Outubro", <?php echo $faturamentoBrutoOutubro ?>, "#1919ff"],
        ["Novembro", <?php echo $faturamentoBruto ?>, "#1919ff"],
        ["Dezembro",  <?php echo $faturamentoBrutoDezembro ?>, "#1919ff"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "Faturamento em reais ",
        width: 450,
        height: 350,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values2"));
      chart.draw(view, options);
  }
  </script>

   <div id="columnchart_values3" style="float: left; width: 30%; height: 300px; "></div> 

   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ["Mes", "Pedidos", { role: "style" } ],
        ["Setembro", <?php echo $ticketSetembro ?>, "#004c00"],
        ["Outubro", <?php echo $ticketOutubro ?>, "#004c00"],
        ["Novembro", <?php echo $ticket ?>, "#004c00"],
        ["Dezembro",  <?php echo $ticketDezembro; ?>, "#004c00"]
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "Ticket Médio ",
        width: 450,
        height: 350,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values3"));
      chart.draw(view, options);
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

    <div class="panel panel-default">
                <div class="panel-heading">Relatório de Fechamento Financeiro <br><small>Mês atual</small></div>
                <div class="panel-body">

                    <input class="form-control" id="myInput" type="text"
                        placeholder="Pesquisar..." style="width: 400px;"> <br>

                    <table border="1" class="table table-bordered table-striped">
                        <thead class="blue-grey lighten-4">
                            <tr>
                                <th>CodPedido</th>
                                <th>Dt. Pedido</th>
                                <th>Pagamento</th>
                                <th>Vl. Total</th>
                                
                            </tr>
                        </thead>

                            <?php
                            if (count($dados) > 0) {
                                foreach ($dados as $pedido) {
                                    ?>

                           <tbody id="myTable">
                <tr>
                                    <td><?php echo $pedido['codPedido']; ?></td>
                                    <td><?php echo $pedido['dataPedido']; ?></td>
                                    <td><?php echo $pedido['pagamento']; ?></td>
                                    <td><?php echo "R$ ".$pedido['valorTotal']; ?></td>
                                    <td align="center">
                                    
                </tr>
               </tbody>                     
                                <?php
                                }
                            }
                            ?>
                        </table>
                        
                </div>
            </div>
        </div>
    </div>

</body>
</html>