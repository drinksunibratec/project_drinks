<?php

/**
 * @author Ravi Tamada
 * @link http://www.androidhive.info/2012/01/android-login-and-registration-with-php-mysql-and-sqlite/ Complete tutorial
 */

class DB_Functions {

    private $conn;

    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }

    // destructor
    function __destruct() {
        
    }

    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($nome, $email, $senha, $telefone) {
	$uuid = uniqid('', true);
        $hash = $this->hashSSHA($senha);
        $encrypted_password = $hash["encrypted"];
	
        $stmt = $this->conn->prepare("INSERT INTO usuarios(nome, email, encrypted_password, telefone) VALUES(?, ?, ?, ?)");
        $stmt->bind_param("ssss",$nome, $email, $encrypted_password, $telefone);
        $result = $stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM usuarios WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $usuario = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $usuario;
        } else {
            return false;
        }
    }

    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($email, $senha) {

        $stmt = $this->conn->prepare("SELECT * FROM usuarios WHERE email = ?");

        $stmt->bind_param("s", $email);

        if ($stmt->execute()) {
            $usuario = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            // verifying user password
            
            $encrypted_password = $usuario['encrypted_password'];
            $hash = $this->checkhashSSHA($senha);
            // check for password equality
            if ($encrypted_password == $hash) {
                // user authentication details are correct
                return $usuario;
            }
        } else {
            return NULL;
        }
    }

    /**
     * Check user is existed or not
     */
    public function isUserExisted($email) {
        $stmt = $this->conn->prepare("SELECT email from usuarios WHERE email = ?");

        $stmt->bind_param("s", $email);

        $stmt->execute();

        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }
	public function hashSSHA($senha) {

        $encrypted = base64_encode(sha1($senha, true));
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }
	public function checkhashSSHA($senha) {

        $hash = base64_encode(sha1($senha, true));

        return $hash;
    }
}

?>
