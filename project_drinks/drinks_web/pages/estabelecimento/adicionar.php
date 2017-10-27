<?php
  
require_once ('../include/header.php');
require_once ('../menu/menu.php');
 
  if (isset($_POST['cnpj']) && empty($_POST['cnpj']) == false) {

      $caracters = array("/", "-", ".", "(", ")");


      $inserir = $_POST;
      foreach ($inserir as $key => $value) {
          if($key == 'cnpj' || $key == 'telefone' || $key == 'cep'){
              $inserir[$key] = str_replace($caracters, "", $value);
              
          }
          
      }
      insert(ESTABELECIMENTO, $inserir);
      

    header("Location: lista.php");
  }
?>

<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Cadastrar Estabelecimento</title>

    <!-- MASCARA -->
     <script>
      jQuery(function($){
             $("#cnpj").mask("99.999.999/9999-99");
             $("#telefone").mask("(99)99999-9999");
             $("#cep").mask("99999-999");     
      });

      
     </script>
  </head>

	<div class="container">
		<h2>Novo Estabelecimento</h2> 
        <div class="jumbotron">
            <form method = "POST" data-toggle="validator">
             <div class="row">
                    
                    <div class="form-group col-md-2">
                      <label for="cnpj">CNPJ</label>
                      <input type="text" class="form-control" id="cnpj" name="cnpj" required>
                	</div>
                    
                    <div class="form-group col-md-3">
                      <label for="razaoSocial">Raz&atilde;o Social</label>
                      <input type="text" class="form-control" name="razaoSocial" required>
                	</div>
                    
                    <div class="form-group col-md-3">
                      <label for="nomeFantasia">Nome Fantasia</label>
                      <input type="text" class="form-control" name="nomeFantasia" required>
                	</div>
                	
                	<div class="form-group col-md-4">
                      <label for="email">E-mail</label>
                      <input type="email" class="form-control" name="email" placeholder="email@exemplo.com" required>
                	</div>
                	                	
                </div>
                
                <div class="row">
                	<div class="form-group col-md-4">
                      <label for="rua">Rua</label>
                      <input type="text" class="form-control" name="rua" required>
                	</div>
                	
                	<div class="form-group col-md-2">
                      <label for="numero">Numero</label>
                      <input type="text" class="form-control" name="numero" required>
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="bairro">Bairro</label>
                      <input type="text" class="form-control" name="bairro" required>
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="cep">CEP</label>
                      <input type="text" class="form-control" id = "cep" name="cep" required>
                	</div>
                
                </div>
                
                <div class=row>
                	<div class="form-group col-md-4">
                      <label for="cidade">Cidade</label>
                      <input type="text" class="form-control" name="cidade" required>
                	</div>
                	
                	<div class="form-group col-md-3">
                      	<label for="uf">UF</label>
                    	<select class="form-control selectpicker" name="uf" id="uf" required>
                        	<option value="">--Selecione--</option>
                        	<option value="AC">AC</option>
                        	<option value="AL">AL</option>
                        	<option value="AM">AM</option>
                        	<option value="AP">AP</option>
                        	<option value="BA">BA</option>
                        	<option value="CE">CE</option>
                        	<option value="DF">DF</option>
                        	<option value="ES">ES</option>
                        	<option value="GO">GO</option>
                        	<option value="MA">MA</option>
                        	<option value="MG">MG</option>
                        	<option value="MS">MS</option>
                        	<option value="MT">MT</option>
                        	<option value="PA">PA</option>
                        	<option value="PB">PB</option>
                        	<option value="PE">PE</option>
                        	<option value="PI">PI</option>
                        	<option value="PR">PR</option>
                        	<option value="RJ">RJ</option>
                        	<option value="RN">RN</option>
                        	<option value="RS">RS</option>
                        	<option value="RO">RO</option>
                        	<option value="RR">RR</option>
                        	<option value="SC">SC</option>
                        	<option value="SE">SE</option>
                        	<option value="SP">SP</option>
                        	<option value="TO">TO</option>
                         </select>  
                      
                  	</div>
                  	
                  	
                  	<div class="form-group col-md-2">
                      <label for="latitute">Latitude</label>
                      <input type="text" class="form-control" name="latitude" required>
                	</div>
                  	
                  	<div class="form-group col-md-2">
                      <label for="longitude">Longitude</label>
                      <input type="text" class="form-control" name="longitude" required>
                	</div>
                </div>
                
                <div class=row>
                	<div class="form-group col-md-3">
                      <label for="telefone">Telefone</label>
                      <input type="text" class="form-control" id = "telefone" name="telefone" required>
                	</div>
                	
                	<div class="form-group col-md-3">
                      <label for="senha">Senha</label>
                      <input type="password" class="form-control" name="senha" required>
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