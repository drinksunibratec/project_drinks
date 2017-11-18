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

function update($table = null, $id = 0, $data = null, $nomeId)
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
                pedido.status,pedido.valorTotal,usuarios.nome AS usuario,
                usuarios.telefone,usuarios.email,pedido_produto.codProduto,
                pedido_produto.preco,pedido_produto.quantidade,
                pedido_produto.precoTotal,produto.nome

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
                pedido.status,pedido.valorTotal,usuarios.nome AS usuario,
                usuarios.telefone,usuarios.email,pedido_produto.codProduto,
                pedido_produto.preco,pedido_produto.quantidade,
                pedido_produto.precoTotal,produto.nome
            
                FROM pedido,pedido_produto,usuarios,produto, estabelecimento
            
                WHERE pedido.codPedido = pedido_produto.codPedido
                AND pedido.codUsuario = usuarios.codUsuario
                AND produto.codProduto = pedido_produto.codProduto
                AND pedido.codPedido = pedido_produto.codPedido
                AND pedido.codEstabelecimento = estabelecimento.codEstabelecimento
                AND pedido.codEstabelecimento = ".$nomeId.
                " GROUP BY pedido.codPedido;" ;
        
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
        
        $sql = "SELECT pedido.codPedido,pedido.dataPedido, pedido.bairro,
                pedido.cidade,pedido.rua,pedido.numero,pedido.pagamento,
                pedido.status,pedido.valorTotal,usuarios.nome AS usuario,
                usuarios.telefone,usuarios.email,pedido_produto.codProduto,
                pedido_produto.preco,pedido_produto.quantidade,
                pedido_produto.precoTotal,produto.nome
            
                FROM pedido,pedido_produto,usuarios,produto, estabelecimento
            
                WHERE pedido.codPedido = pedido_produto.codPedido
                AND pedido.codUsuario = usuarios.codUsuario
                AND produto.codProduto = pedido_produto.codProduto
                AND pedido.codPedido = pedido_produto.codPedido
                AND pedido.codEstabelecimento = estabelecimento.codEstabelecimento
                AND pedido.codEstabelecimento = ".$nomeId.
                " AND pedido.codPedido = " .$codPed.
                " ORDER BY pedido_produto.codProduto;" ;
        
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

    
