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
$sql = "CREATE TABLE IF NOT EXISTS drinksdb.Estabelecimento (
id INT(6) AUTO_INCREMENT PRIMARY KEY, 
nome VARCHAR(100) NOT NULL,
logradouro VARCHAR(100) NOT NULL,
numero VARCHAR(100) NOT NULL,
bairro VARCHAR(100) NOT NULL,
cidade VARCHAR(30) NOT NULL,
uf VARCHAR(30) NOT NULL,
cep VARCHAR(30) NOT NULL,
latitude VARCHAR(30) NOT NULL,
longetude VARCHAR(30) NOT NULL

)";
if ($conn->query($sql) === FALSE) {
    echo "Erro ao criar tabela: " . $conn->error;}
	
							
							// ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!!
							//ESSA PARTE DE INSERIR ESTABELECIMENTO É APENAS PARA TESTE. MOBILE SO UTILIZARÁ O METODO DE LEITURA
							
	
$metodoHttp = $_SERVER['REQUEST_METHOD'];
if ($metodoHttp == 'POST') {
	$stmt = $conn->prepare(
		"INSERT INTO drinksdb.Estabelecimento (nome, logradouro, numero, bairro, cidade, uf, cep, latitude, longetude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
    $json = json_decode(file_get_contents('php://input'));
    $nome     = $json->{'nome'};
    $logradouro = $json->{'logradouro'};
	$numero = $json->{'numero'};
	$bairro = $json->{'bairro'};
	$cidade = $json->{'cidade'};
	$uf = $json->{'uf'};
	$cep = $json->{'cep'};
	$latitude = $json->{'latitude'};
	$longetude = $json->{'longetude'};
    $stmt->bind_param("sssssssss", $nome, $logradouro, $numero, $bairro, $cidade, $uf, $cep, $latitude, $longetude);
    $stmt->execute();
    $stmt->close();
    $id = $conn->insert_id;
    $jsonRetorno = array("id"=>$id);
    echo json_encode($jsonRetorno);
} else if ($metodoHttp == 'GET') {
    $jsonArray = array();
    $sql = "SELECT id, nome, logradouro, numero, bairro, cidade, uf, cep, latitude, longetude FROM drinksdb.Estabelecimento";
    $result = $conn->query($sql);
    if ($result && $result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $jsonLinha = array(
                 "id"       => $row["id"],
                "nome"     => $row["nome"],
                "logradouro" => $row["logradouro"],
				"numero" => $row["numero"],
				"bairro" => $row["bairro"],
				"cidade" => $row["cidade"],
				"uf" => $row["uf"],
				"cep" => $row["cep"],
				"latitude" => $row["latitude"],
				"longetude" => $row["longetude"]);
            $jsonArray[] = $jsonLinha;    	    
        }
    }
    echo json_encode($jsonArray);
}
$conn->close();
?>