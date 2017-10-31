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
	
	public function inserirPedido($pedido){
		foreach ($pedido as $key => $value) {
			$columns .= trim($key, "'") . ",";
			$values .= "'$value',";
		}
		
		// remove a ultima virgula
		$columns = rtrim($columns, ',');
		$values = rtrim($values, ',');
		
		$sql = "INSERT INTO pedido (" . $columns . ") VALUES (" . $values . ");";
		echo $sql;	
		$db->query($sql);
	}
	
	public function inserirProdutosDoPedido($input_data){
		
		foreach($input_data as $key => $produtos){
			
			foreach ($produtos as $key => $value) {
				$columns .= trim($key, "'") . ",";
				$values .= "'$value',";
			}
			
			// remove a ultima virgula
			$columns = rtrim($columns, ',');
			$values = rtrim($values, ',');
			
			$sql = "INSERT INTO pedido_produto (" . $columns . ") VALUES (" . $values . ");";
			echo $sql;	
			$db->query($sql);
		}
		
		
	}
    
	public function listaEstabelecimentos(){
	$estabelecimentos = null;
	$sql = "SELECT * from estabelecimento WHERE administrador <> 1";

	$result = $this->conn->query($sql);
	
	if ($result->num_rows > 0) {
		$estabelecimentos = $result->fetch_all(MYSQLI_ASSOC);
	}

	return $estabelecimentos;
	}

	public function listaProdutosPorEstabelecimento($codEstabelecimento){
		$produtos = null;
		$sql = "SELECT * FROM produto p WHERE p.codEstabelecimento = " . $codEstabelecimento;

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$produtos = $result->fetch_all(MYSQLI_ASSOC);
		}

		return $produtos;
	}
	
	public function listaEstabelecimentoPorId($codEstabelecimento){
		$produtos = null;
		$sql = "SELECT * FROM estabelecimento e WHERE e.codEstabelecimento = " . $codEstabelecimento;

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$produtos = $result->fetch_assoc();
		}

		return $produtos;
	}
	
	public function consultaUltimoPedido(){
		$produtos = null;
		$sql = "SELECT MAX(codPedido) FROM pedido;

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$produtos = $result->fetch_assoc();
		}

		return $produtos;
	}
	

		

    public function listaPedidosPorID($codPedido) {
        $pedidos = null;
        $sql = "SELECT * FROM pedido p WHERE p.pedido = " .$codPedido;

        $result = $this->conn->query($sql);
        
        if ($result->num_rows > 0) {
            $pedidos = $result->fetch_assoc();
        }

        return $pedidos;
    }

    public function listaProdutosPorPedido($codProduto,$codPedido){
        $produtos = null;
        $sql = "SELECT nome,descricao FROM pedido p JOIN pedido_produto pp ON pp.codPedido=p.codPedido JOIN produto pr ON pr.codProduto=pp.codProduto WHERE p.codPedido '. $codPedido .' AND pr.codProduto" . $codProduto;
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
