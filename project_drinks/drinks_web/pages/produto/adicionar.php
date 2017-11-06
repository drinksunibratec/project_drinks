<?php
require_once ('../include/header.php');
require_once ('../menu/menu.php');


//if (isset($_POST['nome']) && empty($_POST['nome']) == false) {
//    
//    $caracters = array(
//        "R",
//        "$"
//    );
//    
//    $inserir = $_POST;
//    foreach ($inserir as $key => $value) {
//        if($key == 'preco'){
//            $inserir[$key] = str_replace($caracters, "", $value);
//            
//        }
//        
//    }
//    $inserir['codEstabelecimento'] = $_SESSION['id'];
//    insert(PRODUTO, $inserir);
//    
//    header("Location: lista.php");
//}
$host = "localhost";
$user = "root";
$senha = "";
$banco = "comumana_drinks";
//$estabelecimento = $_GET['codEstabelecimento'];

$mysqli = new mysqli($host, $user, $senha, $banco);
if($mysqli->connect_errno){
    echo 'Falha na Conexão: ('.$mysqli->connect_errno.')'.$mysqli->connect_error;
}

if(isset($_POST['confirmar'])){
    //REGISTRO DOS DADOS
    //Verificar se a seção foi aberta
    if (!isset($_SESSION))
        session_start ();
    
    //vai percorrer todas as variaveis post que existem e pegar a chave valor dela
    foreach ($_POST as $chave=>$valor){//para cada post com chave-valor irei criar uma sessão
        $_SESSION[$chave] = $mysqli->real_escape_string($valor);
    }
//    VALIDAÇÃO DOS DADOS
    if(strlen($_SESSION['nome']) == 0)
        $erro[] = "Preencha o nome do produto.";
    
    if(strlen($_SESSION['descricao']) == 0)
        $erro[] = "Preencha a descrição do produto.";
    
    if(strlen($_SESSION['gelada']) == 0)
        $erro[] = "Escolha o modo do produto.";
    
    if(strlen($_SESSION['preco']) == 0)
        $erro[] = "Preencha o preço do produto.";
    
    //INSERÇÃO NO BANCO
//    if (count($erro) == 0){
        $sql_code = "INSERT INTO produto ("
                . "descricao, "
                . "gelada, "
                . "nome, "
                . "preco, "
                . "codEstabelecimento)"
                . "VALUES("
                . "'$_SESSION[descricao]',"
                . "'$_SESSION[gelada]',"
                . "'$_SESSION[nome]',"
                . "'$_SESSION[preco]',"
                . "'$_SESSION[codEstabelecimento]'"
                . ");";
        $confirma = $mysqli->query($sql_code)or die($mysqli->error);
//        $estabelecimento = $_GET['codEstabelecimento'];
//        $consulta = "SELECT * FROM produto WHERE codEstabelecimento='$estabelecimento'";
//        $con = $mysqli->query($consulta)or die($mysqli->error);
//    }
        
//        header("Location: lista.php");
//        if ($confirma){
//            unset(
//                $_SESSION[descricao],
//                $_SESSION[gelada],
//                $_SESSION[nome],
//                $_SESSION[preco],
//                $_SESSION[codEstabelecimento]
//            );
//            
//            echo "<script> location.href='adicionar.php?p=lista';</script>";
//        } else {
//            $erro[] = $confirma;
//        }
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
	<h2>Novo Produto</h2>
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
					<label for="nome">Nome</label> <input type="text"
						class="form-control" id="nome" name="nome" required>
				</div>

				<div class="form-group col-md-3">
					<label for="descricao">Descri&ccedil;&atilde;o</label> <input
						type="text" class="form-control" name="descricao" required>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-3">
					<label for="gelada">Gelada</label> 
					<select class="form-control selectpicker" name="gelada" id="gelada" required>
							<option value="">--Selecione--</option>
                        	<option value="1">SIM</option>
                        	<option value="0">NÃO</option>
					</select>
					
				</div>

				<div class="form-group col-md-3">
					<label for="preco">Pre&ccedil;o</label> <input type="text"
						class="form-control" id="preco" name="preco" required>
				</div>

			</div>

			<div class=row>
				<div class="form-group col-md-4">
                                    <input type="submit" value="&#10003 Cadastrar" href="lista.php"
                                           class="btn btn-primary" name="confirmar" /> 
					<a href="adicionar.php" class="btn btn-danger">&#10005 Cancelar</a>
				</div>
			</div>

		</form>
	</div>
</div>
</body>
</html>
