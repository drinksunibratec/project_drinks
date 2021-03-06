<?php
/*
 *
 * Created by Tercio Lima on 17/11/2017
 *
 */
require_once ('../include/header.php');
require_once ('../menu/menu.php');

$codEstabelecimento = null;
$codPedido = null;
$mensagens = new Mensagens();


if (isset($_GET['codEstabelecimento']) && empty($_GET['codEstabelecimento']) == false) {
    $codEstabelecimento = $_GET['codEstabelecimento'];
} else {
    $codEstabelecimento = $_SESSION['codEstabelecimento'];
}

if (isset($_GET['codPedido']) && empty($_GET['codPedido']) == false) {
    $codPedido = addslashes($_GET['codPedido']);
}

if (isset($_POST['codPedido']) && empty($_POST['codPedido']) == false) {
    $status = $_POST['status'];
//    unset($dados['codPedido']);
    $dados = array(
        "status" => $status
    );


    update(PEDIDO, $codPedido, $dados, 'codPedido');

    header("Location: listarPedido.php");
}

$dados = detalhesPedido($codEstabelecimento, $codPedido);
$pdo = itens($codEstabelecimento, $codPedido);
//var_dump($pdo);
?>

<!DOCTYPE html>
<html lang="pt-br">
    <body>
        <div class="container">

<?php
if (count($dados) > 0) {
    foreach ($dados as $pedido) {
        ?>
                    <div class="panel panel-default">
                                    <div class="panel-heading"><h4>Detalhes do Pedido Nº: <?php echo $pedido['codPedido']; ?></h4></div>
                                    
                    <div class="jumbotron">
                        
                        <form method="POST">
                            
                            <div class="row">
                                
<!--                            <div class="row">
                                <div class="row">-->
                                    
                                    <div class="form-group col-md-3">
                                        
                                        <label for="codPedido">Cod.Pedido</label> 
                                        <input type="text"
                                               class="form-control" id="codPedido" name="codPedido"
                                               value="<?php echo $pedido['codPedido']; ?> " readonly>
                                    </div>

                                    <div class="form-group col-md-7">
                                        <label for="codUsuario">Cliente</label> 
                                        <input type="text"  class="form-control" name="codUsuario"
                                         value="<?php echo $pedido['usuario']; ?>" readonly>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-md-4">
                                        <label for="dataPedido">Data Pedido</label> <input type="datetime"
                                                                                           class="form-control" name="dataPedido"
                                                                                           value="<?php echo $pedido['dataPedido']; ?>" readonly>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="pagamento">Pagamento</label> <input type="text"
                                                                                        class="form-control" name="pagamento"
                                                                                        value="<?php echo $pedido['pagamento']; ?>" readonly>
                                    </div>
                                    <div class="form-group col-md-3">
                                        <label for="valorTotal">Valor</label> <input type="text"
                                                                                     class="form-control" name="valorTotal"
                                                                                     value="<?php echo " R$ " . $pedido['valorTotal']; ?>" readonly>
                                    </div>
                                </div>
                                <br>
                                <br>
                                <br>
                                <!-- La�o para verificar quantos s�o os produtos e add na table -->
                                <div class="row">
                                    <table border="1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Cod.</th>
                                                <th>Produto</th>
                                                <th>Preço</th>
                                                <th>Quantidade</th>
                                                <th>Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>

        <?php
        
          if (count($pdo) > 0) {
            foreach ($pdo as $row) {
                echo '<tr>';
                echo '<td>' . $row['codProduto'] . '</td>';
                echo '<td>' . $row['nome'] . '</td>';
                echo '<td>' . "R$ " . $row['preco'] . '</td>';
                echo '<td>' . $row['quantidade'] . '</td>';
                echo '<td>' . "R$ " . $row['precoTotal'] . '</td>';
                echo '</tr>';
            }
        }
        
        ?>

                                        </tbody>
                                    </table>
                                </div>
                                
                                <br>
                                <br>
                                <br>
                                <!-- Fim do la�o! -->

                                <div class="row">
                                    <div class="form-group col-md-7">
                                        <label for="rua">Rua</label> <input type="text"
                                                                            class="form-control" name="rua"
                                                                            value="<?php echo $pedido['rua']; ?>" readonly>
                                    </div>

                                    <div class="form-group col-md-2">
                                        <label for="numero">Numero</label> <input type="text"
                                                                                  class="form-control" id="numero" name="numero"
                                                                                  value="<?php echo $pedido['numero']; ?>" readonly>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-md-4">
                                        <label for="bairro">Bairro</label> <input type="text"
                                                                                  class="form-control" name="bairro"
                                                                                  value="<?php echo $pedido['bairro']; ?>" readonly>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="cidade">Cidade</label> <input type="text"
                                                                                  class="form-control" name="cidade"
                                                                                  value="<?php echo $pedido['cidade']; ?>" readonly>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-md-4">
                                        <label for="status">Status</label>
        <?php selected_Status($pedido['status']) ?>
                                    </div>
                                </div>

                                <div class=row>
                                    <div class="form-group col-md-4">
                                        <?php if($pedido['status'] != "ENTREGUE"){ ?> 
                                            <input type="submit" value="&#10003 Alterar" class="btn btn-primary"  /> 
                                       <?php }else {?>
                                       		<input type="submit" value="&#10003 Alterar" class="btn btn-primary" disabled /> 
                                       <?php } ?>
                                        <a href="listarPedido.php"
                                           class="btn btn-danger">&#10005Cancelar</a>
                                    </div>
                                </div>

                        </form>
                    </div>
        <?php
    }
}
?>
        </div>
    </body>