<?php
$servername = "localhost";
$username = "comumana";
$password = "478ab7h@s";
$conn = new mysqli($servername, $username, $password);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
$sql = "CREATE DATABASE IF NOT EXISTS comumana_drinksapp";
if (!$conn->query($sql) === TRUE) {
    echo "Erro ao criar banco de dados: " . $conn->error;
}
$sql = "CREATE TABLE IF NOT EXISTS comumana_drinksapp.Produto (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
nome VARCHAR(100) NOT NULL,
estabelecimento VARCHAR(100) NOT NULL,
endereco VARCHAR(100) NOT NULL,
bairro VARCHAR(100) NOT NULL,
preco VARCHAR(30) NOT NULL
)";
if ($conn->query($sql) === FALSE) {
    echo "Erro ao criar tabela: " . $conn->error;}
	
$metodoHttp = $_SERVER['REQUEST_METHOD'];
if ($metodoHttp == 'POST') {
	$stmt = $conn->prepare(
		"INSERT INTO comumana_drinksapp.Produto (nome, estabelecimento, endereco, bairro, preco) VALUES (?, ?, ?, ?, ?)");
    $json = json_decode(file_get_contents('php://input'));
    $nome     = $json->{'nome'};
    $estabelecimento = $json->{'estabelecimento'};
	$endereco = $json->{'endereco'};
	$bairro = $json->{'bairro'};
	$preco = $json->{'preco'};
    $stmt->bind_param("sssss", $nome, $estabelecimento, $endereco, $bairro, $preco);
    $stmt->execute();
    $stmt->close();
    $id = $conn->insert_id;
    $jsonRetorno = array("id"=>$id);
    echo json_encode($jsonRetorno);
} else if ($metodoHttp == 'GET') {
    $jsonArray = array();
    $sql = "SELECT id, nome, estabelecimento, endereco, bairro, preco FROM comumana_drinksapp.Produto";
    $result = $conn->query($sql);
    if ($result && $result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $jsonLinha = array(
                 "id"       => $row["id"],
                "nome"     => $row["nome"],
                "estabelecimento" => $row["estabelecimento"],
				"endereco" => $row["endereco"],
				"bairro" => $row["bairro"],
				"preco" => $row["preco"]);
            $jsonArray[] = $jsonLinha;    	    
        }
    }
    echo json_encode($jsonArray);
} else if ($metodoHttp == 'PUT') {
    $stmt = $conn->prepare(
        "UPDATE comumana_drinksapp.Produto SET nome=?, estabelecimento=?, endereco=?, bairro=?, preco=? WHERE id=?");
    $json  = json_decode(file_get_contents('php://input'));
    $id       = $json->{'id'};
    $nome     = $json->{'nome'};
    $rg = $json->{'rg'};
	$vulgo = $json->{'vulgo'};
	$historico = $json->{'historico'};
	$mae = $json->{'mae'};
	$estrelas = $json->{'estrelas'};
    $stmt->bind_param("sssssi", $nome, $estabelecimento, $endereco, $bairro, $preco, $id);
    $stmt->execute();
    $stmt->close();
    $jsonRetorno = array("id"=>$id);
    echo json_encode($jsonRetorno);
} else if ($metodoHttp == 'DELETE') {
    $stmt = $conn->prepare("DELETE FROM comumana_drinksapp.Produto WHERE id=?");
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