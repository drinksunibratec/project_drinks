<?php  
require_once ('../include/header.php');
require_once ('../menu/menu.php');

  if (isset($_POST['nome']) && empty($_POST['nome']) == false) {

      $caracters = array(
        "R",
        "$"
    );


      $inserir = $_POST;
      foreach ($inserir as $key => $value) {
          if($key == 'preco'){
              $inserir[$key] = str_replace($caracters, "", $value);
              
          }
          
      }
      
      
                  
      $dados = insertProduto(PRODUTO, $inserir);
      

    header("Location: lista.php");
  }
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>

<title>Cadastrar Produto</title>

<!-- MASCARA -->
<script>
      jQuery(function($){
             $('#preco').maskMoney({prefix:'R$ ', allowNegative: false, thousands:'.', decimal:',', affixesStay: false}); 
      });
     </script>
</head>



<body>
<div class="container">
    <h2>Novo Produto </h2>
        
	<div class="jumbotron">
                <?php
//                if(count($erro) > 0){ 
//                    echo "<div class='erro'>"; 
//                    foreach ($erro as $valor) 
//                        echo "$valor <br>"; 
//                    echo "</div>";
//                }
                ?>
            
		<form method="POST">

			<div class="row">

				<div class="form-group col-md-3">
					<label for="nome">Nome</label> 
                                        <input type="text"
						class="form-control" id="nome" name="nome" required>
				</div>

				<div class="form-group col-md-3">
					<label for="descricao">Descri&ccedil;&atilde;o</label> 
                                        <input
						type="text" id="descricao" class="form-control" name="descricao" required>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-3">
					<label for="gelada">Ean</label> 
					<input
						type="text" id="ean" class="form-control" name="ean" required>
					
				</div>

			</div>

			<div class=row>
				<div class="form-group col-md-4">                                
                                        
                                        <input type="submit" value="&#10003 Cadastrar" class="btn btn-primary" /> 
    					<a href="lista.php" class="btn btn-danger">&#10005 Cancelar</a>
				</div>
			</div>

		</form>
	</div>
</div>
</body>
</html>
