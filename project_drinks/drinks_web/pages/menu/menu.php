<?php session_start()?>
<div class="container">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" >Drinks</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
    					<?php if(!empty($_SESSION['administrador']) && $_SESSION['administrador'] == 1){?>
    					
    					    <li><a href="../estabelecimento/lista.php">Estabelecimentos</a></li>
    						<li><a href="../cliente/lista.php">Clientes</a></li>
                                                <li><a href="../produto/lista.php">Produtos</a></li>
                                                <li><a href="../pedido/listarPedido.php">Pedidos</a></li>
                                                <li><a href="../estabelecimento/relatorios.php">Relatorios</a></li>
                                                <li><a href="../campanhas/campanhas.php">Campanhas</a></li>
                                                <li><a href="../login/login.php">Sair</a></li>
                                                
                                                
    					<?php }else {?>
                                                <li><a href="../pedido/listarPedido.php">Pedidos</a></li>
                                                <li><a href="../produto/listaProdutoEstabelecimento.php">Catálogo Produtos</a></li>
                                                <li><a href="../produto/listaProdutoVinculados.php">Produtos Vinculados</a></li>
                                                <li><a href="../estabelecimento/relatorios.php">Relatorios</a></li>
                                                <li><a href="../campanhas/campanhas.php">Campanhas</a></li>
                                                <li><a href="../login/login.php">Sair</a></li>
                                              
    					<?php }?>
				</ul>
				
			</div>
			
			<!--/.nav-collapse -->
		</div> 
		<!--/.container-fluid -->
		
	</nav>
</div>