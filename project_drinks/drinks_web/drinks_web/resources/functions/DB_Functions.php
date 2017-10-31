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
        $_SESSION['type'] = 'success';
    } catch (Exception $e) {
        
        $_SESSION['message'] = 'Nao foi possivel realizar a operacao.';
        $_SESSION['type'] = 'danger';
    }
    close_database($database);
}

//function cadastrarProduto(){
//    is_bool($cadastrou = false);
//    
//    
//    $nomeProduto = $_POST['nome'];
//    $descricaoProduto = $_POST['descricao'];
//    $gelada = $_POST['gelada'];
//    $precoProduto = $_POST['preco'];
//    
//    $codigoCadastro = "INSERT INTO produto (descricao, gelada, nome, preco)"
//            . "VALUES ('$nomeProduto','$descricaoProduto','$gelada','$precoProduto')";
//    $database = open_database();
//    
//    try {
//        $instrucaoSQL = $mysqli->preparee->$codigoCadastro;
//        $instrucaoSQL->execute();
//        $cadastrou = true;
//        $database = close_database($conn);
//    } catch (SQLException $ex) {
//        echo 'Erro na preparação da instrução SQL';
//    }
//    
//    return $cadastrou;
//    
//}
    
function cadastrarProduto(){
    
}