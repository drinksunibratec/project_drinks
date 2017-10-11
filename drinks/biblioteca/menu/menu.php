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
				
					<li><a href="../../administrador/estabelecimento/cadastro-estabelecimentos.php">Estabelecimentos</a></li>
					<?php if(!empty($_SESSION['administrador']) && $_SESSION['administrador'] == 1){ ?>
						<li><a href="../../administrador/cliente/cadastro-cliente.php">Clientes</a></li>
					<?php }else{?>
						<li><a href="../../administrador/produto/cadastrar-produto.php">Produtos</a></li>
					<?php }?>
					
					
					   <li><a href="../../login.php">Sair</a></li>
				</ul>
				
			</div>
			
			<!--/.nav-collapse -->
		</div> 
		<!--/.container-fluid -->
		
	</nav>
</div>