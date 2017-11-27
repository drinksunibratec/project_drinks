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
	    
	function logMe($msg){
		// Abre ou cria o arquivo bloco1.txt
		// "a" representa que o arquivo é aberto para ser escrito
		$fp = fopen("log.txt", "a");
		
		// Escreve a mensagem passada através da variável $msg
		$escreve = fwrite($fp, $msg);
		
		// Fecha o arquivo
		fclose($fp); 
	
	}

    /**
     * Storing new user
     * returns user details
     */
	    public function storeUser($nome, $email, $senha, $telefone) {
		
	        $stmt = $this->conn->prepare("INSERT INTO usuarios(nome, email, senha, telefone) VALUES(?, ?, ?, ?)");
	        $stmt->bind_param("ssss",$nome, $email, $senha, $telefone);
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
		$columns = null;
    		$values = null;
    		foreach ($pedido as $key => $value) {
			$columns .= trim($key, "'") . ",";
			$values .= "'$value',";
		}
		
		// remove a ultima virgula
		$columns = rtrim($columns, ',');
		$values = rtrim($values, ',');
		
		$sql = "INSERT INTO pedido (" . $columns . ") VALUES (" . $values . ");";
		error_log("Insert pedido:" . $sql );
		$this->conn->query($sql);
	}
	
	public function updateSituacaoPedido($codPedido, $situacao){
	
		$sql = "UPDATE pedido SET status = '" . $situacao . "' WHERE codPedido = " . $codPedido;
		error_log("Update situação pedido:" . $sql );
		$this->conn->query($sql);
	
	}
	
	public function listarPedidosDeUsuario($codUsuario){
		$pedidos = null;
		$sql = "select * from pedido p WHERE p.codUsuario = " . $codUsuario;

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$pedidos = $result->fetch_all(MYSQLI_ASSOC);
		}

		return $pedidos;
	}
	
	public function listarProdutosPedidos($codPedido){
		$pedidos = null;
		$sql = "SELECT pr.nome, pe.preco, pr.ean, pr.descricao, pp.quantidade, pp.precoTotal FROM pedido_produto pp JOIN produto_estab pe ON pe.codProduto = pp.codProduto JOIN produto pr ON pr.ean = pe.ean WHERE pp.codPedido  = " . $codPedido;

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$pedidos = $result->fetch_all(MYSQLI_ASSOC);
		}

		return $pedidos;
	}
	
	public function listarTodosOsProdutos(){
		$pedidos = null;
		$sql = "SELECT * FROM produto";

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$pedidos = $result->fetch_all(MYSQLI_ASSOC);
		}

		return $pedidos;
	}
	
	public function inserirProdutosDoPedido($input_data){
		
		foreach($input_data as $key => $produtos){
			$columns = null;
    			$values = null;
			foreach ($produtos as $key => $value) {
				$columns .= trim($key, "'") . ",";
				$values .= "'$value',";
			}
			
			// remove a ultima virgula
			$columns = rtrim($columns, ',');
			$values = rtrim($values, ',');
			
			$sql = "INSERT INTO pedido_produto (" . $columns . ") VALUES (" . $values . ");";
			error_log("Insert produto Pedido: " .  $sql);
			$this->conn->query($sql);
		}
		
		
	}
	
	public function consultaUltimoPedido(){
		$produtos = null;
		$sql = "SELECT MAX(codPedido) as codPedido FROM pedido";

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$produtos = $result->fetch_assoc();
		}

		return $produtos;
	}
	
	public function consultaPedidoPorId($codPedido){
		$pedido = null;
		$sql = "SELECT * FROM pedido where codPedido = " . $codPedido;

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$pedido = $result->fetch_assoc();
		}

		return $pedido;
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
		$sql = "select pe.codProduto, p.nome, p.descricao, p.ean, pe.preco from produto_estab pe join produto p on p.ean = pe.ean where pe.codEstabelecimento = " . $codEstabelecimento;

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$produtos = $result->fetch_all(MYSQLI_ASSOC);
		}

		return $produtos;
	}
	

	public function listaProdutosPorEan($ean){
		$produtos = null;
		$sql = "SELECT pe.codProduto, p.nome, p.descricao, p.ean, pe.preco, pe.codEstabelecimento FROM produto_estab pe JOIN produto p ON p.ean = pe.ean WHERE p.ean =  " . $ean;

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$produtos = $result->fetch_all(MYSQLI_ASSOC);
		}

		return $produtos;
	}
	
	public function listaEstabelecimentoPorId($codEstabelecimento){
		$estabelecimento= null;
		$sql = "SELECT * FROM estabelecimento e WHERE e.codEstabelecimento = " . $codEstabelecimento;

		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$estabelecimento= $result->fetch_assoc();
		}

		return $estabelecimento;
	}

	    /**
	     * Get user by email and password
	     */
	    public function getUserByEmailAndPassword($email, $senha) {
	
	       	$usuario = null;
	     
	     	$sql = "SELECT * FROM usuarios WHERE email  = '" . $email . "' and senha = '" . $senha . "'";
		error_log("Busca Usuario: " . $sql);
		
		$result = $this->conn->query($sql);
		
		if ($result->num_rows > 0) {
			$usuario = $result->fetch_assoc();
		}
		return $usuario;
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
		
		 public function getUsuarioPorId($codUsuario) {
	
	       	$usuario = null;
	     
	     	$sql = "SELECT * FROM usuarios WHERE codUsuario = " . $codUsuario;
		
		$result = $this->conn->query($sql);
		
		if($result != null){
			if ($result->num_rows > 0) {
				$usuario = $result->fetch_assoc();
			}
		}
		return $usuario;
	    }
		
}

?>
