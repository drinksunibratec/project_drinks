<?php
$servername = "localhost";
$username = "root";
$password = "1234";
$conn = new mysqli($servername, $username, $password);
 
	
							
							// ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!! ATENÇÃO!!
							//ESSA PARTE DE INSERIR ESTABELECIMENTO É APENAS PARA TESTE. MOBILE SO UTILIZARÁ O METODO DE LEITURA
							
$metodoHttp = $_SERVER['REQUEST_METHOD'];
if ($metodoHttp == 'POST') {
	$stmt = $conn->prepare(
		"INSERT INTO drinksdb.Estabelecimento (cnpj, eMail, login, bairro, cep, latitude, longetude, numero, rua, uf, nomeFantasia, razaoSocial, senha, telefone) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?, ?, ?, ?)");
    $json = json_decode(file_get_contents('php://input'));
    $cnpj     = $json->{'cnpj'};
    $eMail = $json->{'eMail'};
	$login = $json->{'login'};
	$bairro = $json->{'bairro'};
	$cep = $json->{'cep'};
	$latitude = $json->{'latitude'};
	$longetude = $json->{'longetude'};
	$numero = $json->{'numero'};
	$rua = $json->{'rua'};
	$uf = $json->{'uf'};
	$nomeFantasia = $json->{'nomeFantasia'};
	$razaoSocial = $json->{'razaoSocial'};
	$senha = $json->{'senha'};
	$telefone = $json->{'telefone'};
    $stmt->bind_param("ssssssssssssss", $cnpj, $eMail, $login, $bairro, $cep, $latitude, $longetude, $numero, $rua, $uf, $nomeFantasia, $razaoSocial, $senha, $telefone);
    $stmt->execute();
    $stmt->close();
    $id = $conn->insert_id;
    $jsonRetorno = array("id"=>$id);
    echo json_encode($jsonRetorno);	
}else if ($metodoHttp == 'GET') {
    $jsonArray = array();
    $sql = "SELECT id, cnpj, eMail, login, bairro, cep, latitude, longetude, numero, rua, uf, nomeFantasia, razaoSocial, senha, telefone FROM drinksdb.Estabelecimento";
    $result = $conn->query($sql);
    if ($result && $result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $jsonLinha = array(
                 "id" => $row["id"],
				 "cnpj"     => $row["cnpj"],
				 "eMail"     => $row["eMail"],
				 "login"     => $row["login"],
				 "bairro" => $row["bairro"],
				 "cep" => $row["cep"],
				 "latitude" => $row["latitude"],
				 "longetude" => $row["longetude"],
				 "numero" =>$row["numero"],
				 "rua" => $row["rua"],
				 "uf" => $row["uf"],
				 "razaoSocial"     => $row["razaoSocial"],
				 "senha"     => $row["senha"],
				 "telefone"     => $row["telefone"]);
            $jsonArray[] = $jsonLinha;    	    
        }
    }
    echo json_encode($jsonArray);
}else if ($metodoHttp == 'PUT') {
    $stmt = $conn->prepare(
        "UPDATE drinksdb.Estabelecimento SET cnpj=?, eMail=?, login=?, bairro=?, cep=?, latitude=?, longetude=?, numero=?, rua=?, uf=?, nomeFantasia=?, razaoSocial=?, senha=?, telefone=? WHERE id=?");
    $json  = json_decode(file_get_contents('php://input'));
    $cnpj     = $json->{'cnpj'};
    $eMail = $json->{'eMail'};
	$login = $json->{'login'};
	$bairro = $json->{'bairro'};
	$cep = $json->{'cep'};
	$latitude = $json->{'latitude'};
	$longetude = $json->{'longetude'};
	$numero = $json->{'numero'};
	$rua = $json->{'rua'};
	$uf = $json->{'uf'};
	$nomeFantasia = $json->{'nomeFantasia'};
	$razaoSocial = $json->{'razaoSocial'};
	$senha = $json->{'senha'};
	$telefone = $json->{'telefone'};
    $stmt->bind_param("ssssssssssssss", $cnpj, $eMail, $login, $bairro, $cep, $latitude, $longetude, $numero, $rua, $uf, $nomeFantasia, $razaoSocial, $senha, $telefone);
    $stmt->execute();
    $stmt->close();
    $id = $conn->insert_id;
    $jsonRetorno = array("id"=>$id);
    echo json_encode($jsonRetorno);
} else if ($metodoHttp == 'DELETE') {
    $stmt = $conn->prepare("DELETE FROM drinksdb.Estabelecimento WHERE id=?");
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