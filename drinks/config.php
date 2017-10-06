<?php
$dsn = "mysql:dbname=drinks;host=localhost";
$dbuser = "root";
$dbpass = "";

try {
		$PDO = new PDO($dsn, $dbuser, $dbpass);
} catch (PDOException $e) {
	echo "Falhou a conexao: " .$e->getMessage();
}

?>