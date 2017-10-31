<?php session_start()?>
<div class="container" >
	<nav class="navbar navbar-default">
		<div class="container-fluid" style="background: #CD853F; width: 100%; height: 100px">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
                            <a class="navbar-brand" style="color: white; font-family: fantasy; font-size: 36px">Drinks</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav navbar-right">
				
					<li><a href="../estabelecimento/lista.php"  style="color: white; font-family: fantasy; font-size: 20px">Estabelecimentos</a></li>
					<?php if(!empty($_SESSION['administrador']) && $_SESSION['administrador'] == 1){ ?>
						<li><a href="../cliente/lista.php"  style="color: white; font-family: fantasy; font-size: 20px">Clientes</a></li>
					<?php }else{?>
						<li><a href="../produto/lista.php"  style="color: white; font-family: fantasy; font-size: 20px">Produtos</a></li>
					<?php }?>
					
					
					   <li><a href="../login/login.php" style="color: white; font-family: fantasy; font-size: 18px">Sair</a></li>
				</ul>
				
			</div>
			
			<!--/.nav-collapse -->
		</div> 
		<!--/.container-fluid -->
		
	</nav>
</div>