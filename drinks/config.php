<?php
$dsn = "mysql:dbname=drinks;host=localhost";
$dbuser = "root";
$dbpass = "";

define("DB_HOST", "localhost");
define("DB_USER", "root");
define("DB_PASSWORD", "");
define("DB_DATABASE", "drinks");

try {
    $PDO = new PDO($dsn, $dbuser, $dbpass);
    $conn = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
} catch (PDOException $e) {
    echo "Falhou a conexao: " . $e->getMessage();
}

?>