<?php
require 'DB_Connect.php';

function login($table, $email_login, $senha)
{
    $database = open_database();
    $found = null;
    
    $sql = "SELECT * FROM " . $table . " WHERE eMail = '" . $email_login . "' AND senha = '" . $senha . "'";
    
    $result = $database->query($sql);
    
    if ($result->num_rows > 0) {
        $found = $result->fetch_assoc();
    } else {
        $sql = "SELECT * FROM " . $table . " WHERE login = " . $email_login;
        ;
        $result = $database->query($sql);
        
        if ($result->num_rows > 0) {
            $found = $result->fetch_assoc();
        }
    }
    
    close_database($database);
    
    return $found;
}

function pedidoNovembro ($table = null) {
         $found = null;
    try {
        $database = open_database();

        $sql = "SELECT count(dataPedido) as teste FROM Pedido WHERE dataPedido BETWEEN '2017/11/01' AND '2017/11/30'";
        $result = $database->query($sql);
         if ($result->num_rows > 0) {
            $found = $result->fetch_assoc();
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;

}

function pedidoAgosto ($table = null) {
         $found = null;
    try {
        $database = open_database();

        $sql = "SELECT count(dataPedido) as agosto FROM Pedido WHERE dataPedido BETWEEN '2017/08/01' AND '2017/08/31'";
        $result = $database->query($sql);
         if ($result->num_rows > 0) {
            $found = $result->fetch_assoc();
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;

}

function pedidoSetembro ($table = null) {
         $found = null;
    try {
        $database = open_database();

        $sql = "SELECT count(dataPedido) as setembro FROM Pedido WHERE dataPedido BETWEEN '2017/09/01' AND '2017/09/30'";
        $result = $database->query($sql);
         if ($result->num_rows > 0) {
            $found = $result->fetch_assoc();
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;

}

function pedidoOutubro ($table = null) {
         $found = null;
    try {
        $database = open_database();

        $sql = "SELECT count(dataPedido) as outubro FROM Pedido WHERE dataPedido BETWEEN '2017/10/01' AND '2017/10/31'";
        $result = $database->query($sql);
         if ($result->num_rows > 0) {
            $found = $result->fetch_assoc();
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;

}


function faturamentoNovembro ($table = null) {
         $found = null;
    try {
        $database = open_database();
        
        $sql = "SELECT Sum(valorTotal) as valor FROM Pedido WHERE dataPedido BETWEEN '2017/11/01' AND '2017/11/30'";
        $result = $database->query($sql);
         if ($result->num_rows > 0) {
            $found = $result->fetch_assoc();
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;
        
}

function faturamentoAgosto ($table = null) {
         $found = null;
    try {
        $database = open_database();
        
        $sql = "SELECT Sum(valorTotal) as valorAgosto FROM Pedido WHERE dataPedido BETWEEN '2017/08/01' AND '2017/08/31'";
        $result = $database->query($sql);
         if ($result->num_rows > 0) {
            $found = $result->fetch_assoc();
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;
        
}

function faturamentoSetembro ($table = null) {
         $found = null;
    try {
        $database = open_database();
        
        $sql = "SELECT Sum(valorTotal) as valorSetembro FROM Pedido WHERE dataPedido BETWEEN '2017/09/01' AND '2017/09/30'";
        $result = $database->query($sql);
         if ($result->num_rows > 0) {
            $found = $result->fetch_assoc();
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;
        
}

function faturamentoOutubro ($table = null) {
         $found = null;
    try {
        $database = open_database();
        
        $sql = "SELECT Sum(valorTotal) as valorOutubro FROM Pedido WHERE dataPedido BETWEEN '2017/10/01' AND '2017/10/31'";
        $result = $database->query($sql);
         if ($result->num_rows > 0) {
            $found = $result->fetch_assoc();
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;
        
}

function buscarTodosOsRegistros($table = null)
{
    $found = null;
    try {
        $database = open_database();
        
        $sql = "SELECT * FROM " . $table;
        $result = $database->query($sql);
        if ($result->num_rows > 0) {
            $found = $result->fetch_all(MYSQLI_ASSOC);
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;
}

function buscarRegistroPorId($table = null, $id = null, $nomeId = null)
{
    $found = null;
    try {
        $database = open_database();
        
        $sql = "SELECT * FROM " . $table . " WHERE " . $nomeId . " = " . $id;
        $result = $database->query($sql);
        if ($result->num_rows > 0) {
            $found = $result->fetch_all(MYSQLI_ASSOC);
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;
}

function update($table = null, $id = 0, $data = null, $nomeId=null)
{
    $items = null;
    $database = open_database();
    
    foreach ($data as $key => $value) {
        $items .= trim($key, "'") . "='$value',";
    }
    
    // remove a ultima virgula
    $items = rtrim($items, ',');
    $sql = "UPDATE " . $table;
    $sql .= " SET $items";
    $sql .= " WHERE " . $nomeId . " = " . $id . ";";
//    echo $sql;
    
    try {
        
        $database->query($sql);
//        $_SESSION['message'] = $sql;
        $_SESSION['message'] = 'Registro atualizado com sucesso.';
        $_SESSION['type'] = 'success';
    } catch (Exception $e) {
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao.';
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
}

function insert($table = null, $data = null)
{
    $columns = null;
    $values = null;
    
    $database = open_database();
    
    foreach ($data as $key => $value) {
        $columns .= trim($key, "'") . ",";
        $values .= "'$value',";
    }
    
    // remove a ultima virgula
    $columns = rtrim($columns, ',');
    $values = rtrim($values, ',');
    
    $sql = "INSERT INTO " . $table . "($columns)" . " VALUES " . "($values);";
    
    try {
        
        $database->query($sql);
        
        $_SESSION['message'] = 'Registro cadastrado com sucesso.';
//        $_SESSION['message'] = $sql;
        $_SESSION['type'] = 'success';
    } catch (Exception $e) {
        
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao.';
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
}

function detalhesPedido($nomeId = null, $codPed = null)
{
    $found = null;
    try {
        $database = open_database();        
            
        $sql = "SELECT pedido.codPedido,pedido.dataPedido, pedido.bairro,
                pedido.cidade,pedido.rua,pedido.numero,pedido.pagamento,
                pedido.status,pedido.valorTotal,pedido.codUsuario,
                usuarios.nome AS usuario,usuarios.telefone,usuarios.email,
                pedido_produto.codProduto,pedido_produto.preco,
                pedido_produto.quantidade,pedido_produto.precoTotal,produto.nome

                FROM pedido,pedido_produto,usuarios,produto, estabelecimento

                WHERE pedido.codPedido = pedido_produto.codPedido
                AND pedido.codUsuario = usuarios.codUsuario
                AND produto.codProduto = pedido_produto.codProduto
                AND pedido.codPedido = pedido_produto.codPedido
                AND pedido.codEstabelecimento = estabelecimento.codEstabelecimento
                AND pedido.codEstabelecimento = ".$nomeId.
                " AND pedido.codPedido = " .$codPed.
                " GROUP BY pedido_produto.codPedido;" ;
        
        $result = $database->query($sql);
        if ($result->num_rows > 0) {
            $found = $result->fetch_all(MYSQLI_ASSOC);
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;
}

function listarPedido($nomeId = null)
{
    $found = null;
    try {
        $database = open_database();
        
        $sql = "SELECT pedido.codPedido,pedido.dataPedido, pedido.bairro,
                pedido.cidade,pedido.rua,pedido.numero,pedido.pagamento,
                pedido.status,pedido.valorTotal,pedido.codUsuario,
                usuarios.nome AS usuario,usuarios.telefone,usuarios.email,
                pedido_produto.codProduto,pedido_produto.preco,
                pedido_produto.quantidade,pedido_produto.precoTotal,produto.nome
            
                FROM pedido,pedido_produto,usuarios,produto, estabelecimento
            
                WHERE pedido.codPedido = pedido_produto.codPedido
                AND pedido.codUsuario = usuarios.codUsuario
                AND produto.codProduto = pedido_produto.codProduto
                AND pedido.codPedido = pedido_produto.codPedido
                AND pedido.codEstabelecimento = estabelecimento.codEstabelecimento
                AND pedido.codEstabelecimento = ".$nomeId.
                " GROUP BY pedido.codPedido;" ;
//        echo "$sql";
        $result = $database->query($sql);
        if ($result->num_rows > 0) {
            $found = $result->fetch_all(MYSQLI_ASSOC);
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;
}

function itens($nomeId = null, $codPed=null)
{
    $found = null;
    
    try {
        $database = open_database();
        
        $sql = "SELECT p.codPedido, p.dataPedido, p.bairro, p.cidade, p.rua, p.numero, p.pagamento,
            p.status, p.valorTotal, p.codUsuario, u.nome, u.telefone, u.email, pp.codProduto, pp.preco,
            pp.quantidade, pp.precoTotal, pr.nome            
            FROM pedido as p
            inner join pedido_produto as pp on
            (pp.codPedido = p.codPedido)
            inner join usuarios as u on
            (u.codUsuario = p.codUsuario)
            inner join produto as pr on
            (pr.codProduto = pp.codProduto)
            inner join estabelecimento as e on
            (e.codEstabelecimento = p.codEstabelecimento)
            WHERE p.codEstabelecimento = ".$nomeId." AND p.codPedido = " .$codPed.
            " ORDER BY pp.codProduto asc;"; 
//        echo $sql;
        $result = $database->query($sql);
//        var_dump($result);
        if ($result->num_rows > 0) {
//        if (mysqli_num_rows($result) > 0) {
            $found = $result->fetch_all(MYSQLI_ASSOC);
        }
    } catch (Exception $e) {
        $_SESSION['message'] = $e->GetMessage();
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
    return $found;
}

function updateStatus($table = null, $codPedido = 0, $dados=null)
{
    $items = null;
    $database = open_database();
    
    foreach ($data as $key => $value) {
        $items .= trim($key, "'") . "='$value',";
    }
    
    // remove a ultima virgula
    $items = rtrim($items, ',');
    $sql = "UPDATE " . $table;
    $sql .= " SET $items = ".$dados;
    $sql .= " WHERE " . $codPedido . " = " . $id . ";";
    echo "$sql";
    
    try {
        
        $database->query($sql);
//        $_SESSION['message'] = $sql;
        $_SESSION['message'] = 'Registro atualizado com sucesso.';
        $_SESSION['type'] = 'success';
    } catch (Exception $e) {
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao.';
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
}
