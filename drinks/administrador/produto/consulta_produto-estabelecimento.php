<?php
  
  require_once ('../../biblioteca/include/header.php');
  require_once ('../../biblioteca/menu/menu.php');
  require_once ('../../biblioteca/functions/Functions.php');
  require_once ('../../biblioteca/functions/DB_Functions.php');
  
  $codEstabelecimento = $_GET['codEstabelecimento'];
  $dados = buscarRegistroPorId('produto',  $codEstabelecimento, 'CodEstabelecimento');
 ?> 

      
      

<!DOCTYPE html>
<html lang="pt-br">

  <body>
    <div class="container">
    <header>
        <div class="row">
            <div class="col-sm-6">
            	<h2>Lista de Produtos</h2>
            </div>
        </div>
    
    </header>

  <?php
    if(count($dados) == 0){
      echo '<div class="alert alert-danger" role="alert">';
      echo '<strong>Aviso!</strong> Você ainda não possui produtos cadastrados.';
      echo '</div>';
      
    } else{

            echo'<div class="jumbotron">';
            echo'<div class="container">';
            echo '<table class="table table-hover">';
            echo  '<thead>';
            echo'<tr>';
            echo'<th>';
            echo'<th>Nome</th>';
            echo'<th>Pre&ccedil;o</th>';
            echo'<th>Descri&ccedil;&atilde;o</th>';
            echo'</tr>';
            echo'</thead>';
            echo '<tbody>';
            echo '<tr>';

            foreach ($dados as $produto) {
                    echo '<td rowspan="2"></td>';///.$produto['codProduto'].'</td>';
                    echo'<td rowspan="2">'.$produto['nome']. '</td>';
                    echo'<td>'.$produto['preco'].'</td>';
                    echo'<td>'.$produto['descricao'].'</td>';
                echo '</tbody>';
              echo '</tr>';
            } 
            echo '</table>';
            echo ' </div>';
            echo '</div>';
          } 
        ?>
        </div>
  </body>
</html>