<?php
$servername = "localhost";
$username = "root";
$password = "1234";
$conn = new mysqli($servername, $username, $password);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$sql = "CREATE DATABASE IF NOT EXISTS drinksdb";
if (!$conn->query($sql) === TRUE) {
    echo "Erro ao criar banco de dados: " . $conn->error;
}
	
$metodoHttp = $_SERVER['REQUEST_METHOD'];
if ($metodoHttp == 'POST') {
	$stmt = $conn->prepare(
		"INSERT INTO drinksdb.Produto (descricao, gelada, nome, preco, codEstabelecimento) VALUES (?, ?, ?, ?, ?)");
    $json = json_decode(file_get_contents('php://input'));
	$descricao = $json->{'descricao'};
	$gelada = $json->{'gelada'};
	$nome     = $json->{'nome'};
	$preco = $json->{'preco'};
    $codEstabelecimento = $json->{'codEstabelecimento'};
    $stmt->bind_param("sssss", $descricao, $gelada, $nome, $preco, $codEstabelecimento);
    $stmt->execute();
    $stmt->close();
    $id = $conn->insert_id;
    $jsonRetorno = array("id"=>$id);
    echo json_encode($jsonRetorno);
} else if ($metodoHttp == 'GET') {
    $jsonArray = array();
    $sql = "SELECT id, descricao, gelada, nome, preco, codEstabelecimento FROM drinksdb.Produto";
    $result = $conn->query($sql);
    if ($result && $result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $jsonLinha = array(
                 "id"       => $row["id"],
				"descricao" => $row["descricao"],
				"gelada" => $row["gelada"],
				"nome"     => $row["nome"],
				"preco" => $row["preco"],
                "codEstabelecimento" => $row["codEstabelecimento"]);
            $jsonArray[] = $jsonLinha;    	    
        }
    }
    echo json_encode($jsonArray);
} else if ($metodoHttp == 'PUT') {
    $stmt = $conn->prepare(
        "UPDATE drinksdb.Produto SET descricao=?, gelada=?, nome=?, codEstabelecimento=?, preco=? WHERE id=?");
    $json  = json_decode(file_get_contents('php://input'));
    $id       = $json->{'id'};
    $descricao = $json->{'descricao'};
	$gelada = $json->{'gelada'};
	$nome     = $json->{'nome'};
	$preco = $json->{'preco'};
    $codEstabelecimento = $json->{'codEstabelecimento'};
    $stmt->bind_param("sssssi", $descricao, $gelada, $nome, $preco, $codEstabelecimento, $id);
    $stmt->execute();
    $stmt->close();
    $jsonRetorno = array("id"=>$id);
    echo json_encode($jsonRetorno);
} else if ($metodoHttp == 'DELETE') {
    $stmt = $conn->prepare("DELETE FROM drinksdb.Produto WHERE id=?");
    $segments = explode("/", $_SERVER["REQUEST_URI"]);
    $id = $segments[count($segments)-1];
    $stmt->bind_param("i", $id);
    $stmt->execute();
    $stmt->close();
    $jsonRetorno = array("id"=>$id);
    echo json_encode($jsonRetorno);
}
$conn->close();
?>