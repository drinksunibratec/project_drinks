-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 22-Nov-2017 às 21:13
-- Versão do servidor: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `drinks_final`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `estabelecimento`
--

CREATE TABLE `estabelecimento` (
  `codEstabelecimento` int(11) NOT NULL,
  `cnpj` varchar(14) NOT NULL,
  `eMail` varchar(100) NOT NULL,
  `login` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `latitude` varchar(12) DEFAULT NULL,
  `longitude` varchar(12) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `rua` varchar(150) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `nomeFantasia` varchar(100) NOT NULL,
  `razaoSocial` varchar(100) NOT NULL,
  `senha` varchar(16) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `administrador` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `estabelecimento`
--

INSERT INTO `estabelecimento` (`codEstabelecimento`, `cnpj`, `eMail`, `login`, `bairro`, `cep`, `cidade`, `latitude`, `longitude`, `numero`, `rua`, `uf`, `nomeFantasia`, `razaoSocial`, `senha`, `telefone`, `administrador`) VALUES
(1, '99999999999999', 'admin@admin.com', 'admin', '', '', '', '', '', 0, '', '', 'SuperAdmin', 'SuperAdmin', '123456', '99999999999', 1),
(2, '10657358000145', 'gerencia@saladereboco.com.br', 'saladereboco', 'Cordeiro', '50720740', 'Recife', '-8.0536974', '-34.9222478', 264, 'Rua Gregório Júnio', 'PE', 'Sala de Reboco', 'Sala De Reboco Bar & Comedoria Ltda - Me', '123456', '81999750204', 0),
(3, '11004583000145', 'afabricabar@hotmail.com', 'fabrica', 'Carmo', '53120160', 'Olinda', '-8.0144249', '-34.8475361', 9, 'Travessa Municipal', 'PE', 'A Fábrica Bar', 'A Fabrica Bar e Restaurante Ltda - Me', '123456', '81987209705', 0),
(4, '10636838000120', 'grutabar.camaragibe@gmail.com', 'grutabar', 'Vila da Fábrica', '54759135', 'Camaragibe', '-8.0139369', '-34.9788453', 387, 'Rua Carlos Alberto de Menezes', 'PE', 'GRUTA BAR', 'M J DE SOUZA BAR - ME', '123456', '81999998146', 0),
(5, '12972637000129', 'betosbaroficial@gmail.com', 'betosbar', 'Candeias', '54440620', 'Jaboatao Dos Guararapes', '-8.0139369', '-34.9788453', 4738, 'Av. Bernardo Vieira De Melo', 'PE', 'BetoS Bar', 'BetoS Restaurante e Pizzaria Ltda - Epp', '123456', '8134692995', 0),
(6, '09202813000139', 'bardobarriga@gmail.com', 'bardobarriga', 'Pilar', '53900000', 'Ilha de Itamaracá', '-8.0139369', '-34.9788453', 30, 'Rua Assunção', 'PE', 'Bar Do Barriga O Rei Da Tainha', 'Bar Do Barriga O Rei Da Tainha', 'c20ad4d76fe97759', '81991915773', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

CREATE TABLE `pedido` (
  `codPedido` int(11) NOT NULL,
  `dataPedido` text NOT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `cidade` varchar(50) DEFAULT NULL,
  `latitude` varchar(10) DEFAULT NULL,
  `longitude` varchar(10) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `rua` varchar(150) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `pagamento` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `valorTotal` double NOT NULL,
  `codUsuario` int(11) NOT NULL,
  `codEstabelecimento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pedido`
--

INSERT INTO `pedido` (`codPedido`, `dataPedido`, `bairro`, `cep`, `cidade`, `latitude`, `longitude`, `numero`, `rua`, `uf`, `pagamento`, `status`, `valorTotal`, `codUsuario`, `codEstabelecimento`) VALUES
(1, '31/10/2017 19:16:35', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ESPECIE', 'AGUARDANDO', 25.57, 5, 5),
(2, '31/10/2017 19:28:53', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ESPECIE', 'AGUARDANDO', 40.74, 4, 2),
(3, '01/11/2017 20:18:12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CARTAO', 'AGUARDANDO', 81.15, 2, 3),
(4, '05/11/2017 19:05:25', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CARTAO', 'AGUARDANDO', 66.93, 1, 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido_produto`
--

CREATE TABLE `pedido_produto` (
  `codPedido` int(11) NOT NULL,
  `codProduto` int(11) NOT NULL,
  `preco` double DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `precoTotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `codProduto` int(11) NOT NULL,
  `descricao` varchar(400) NOT NULL,
  `ean` int(11) NOT NULL,
  `nome` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codProduto`, `descricao`, `ean`, `nome`) VALUES
(1, 'Gelo', 320, 'Gelo em Cubos'),
(2, 'Cerveja ANTARCTICA Pilsen Lata 350ml', 321, 'Cerveja ANTARCTICA 350mL'),
(3, 'Refrigerante Fanta Lata gelada ', 322, 'Fanta - Lata'),
(4, 'Refrigerante Coca-cola Lata gelada ', 323, 'Coca-Cola Lata 350mL'),
(5, 'Cerveja KAISER Lager Pilsen Lata 350ml', 324, 'Cerveja KAISER 350mL'),
(6, 'Cerveja HEINEKEN Lager Beer Premium Lata 350ml', 325, 'Cerveja HEINEKEN 350mL'),
(7, 'Cerveja DEVASSA Tropical Lager Long Neck 355mla', 326, 'Cerveja DEVASSA 355mL'),
(8, 'Gelo', 327, 'Gelo em Escamas'),
(9, 'Cerveja ANTARCTICA Pilsen Lata 350mL', 328, 'Cerveja ANTARCTICA 350mL'),
(10, 'Vinho Tinto Seco 750 mL Salton Classic Cabernet Sauvignon 2016', 329, 'Vinho Tinto Seco 750 mL'),
(11, 'Vinho Branco Seco 750mL Miolo Seleção Chardonnay Viognier 2016', 330, 'Vinho Branco Seco 750mL'),
(12, 'Refrigerante Coca-cola 2L gelada', 331, 'Coca-Cola - 2L'),
(13, 'Cerveja DEVASSA 350mL', 332, 'Cerveja DEVASSA Tropical Lager Lata 350mL'),
(14, 'Refrigerante Sprite 2L gelada', 333, 'Sprite - 2L'),
(15, 'Gelo', 334, 'Gelo em Cubos'),
(17, 'Pitu', 335, 'Cachaça Pitu'),
(18, 'Cerveja BOHEMIA Pilsen Long Neck 355mL', 336, 'Cerveja BOHEMIA 350mL'),
(20, 'Vinho Bodega Vieja Tinto Suave 750mL', 337, 'Vinho Tinto Suave 750mL');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto_estabelecimento`
--

CREATE TABLE `produto_estabelecimento` (
  `codProduto` int(11) NOT NULL,
  `codEstabelecimento` int(11) NOT NULL,
  `preco` double NOT NULL DEFAULT '0',
  `ean` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `codUsuario` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `telefone` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`codUsuario`, `nome`, `email`, `encrypted_password`, `telefone`) VALUES
(1, 'Emerson Francisco', 'ems@gmail.com', 'jLIjfQZ5yojbZGTqxg2pY0VROWQ=', '81986854814'),
(2, 'Tercio Lima', 'terciosky@gmail.com', 'QL0AFWMIX8NRZTKeof9cXsvbvu8=', '81996644179'),
(3, 'Juliana', 'souza3408@gmail.com', 'a15q4UVZBErnR7WyvhHqQ2Sq/p0=', '81986226795'),
(4, 'Silvio', 'engeheirocedrim@gmail.com', 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', '81998237293'),
(5, 'italo', 'italo@italo.com', 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', '81995974462');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `estabelecimento`
--
ALTER TABLE `estabelecimento`
  ADD PRIMARY KEY (`codEstabelecimento`),
  ADD UNIQUE KEY `login` (`login`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`codPedido`),
  ADD KEY `codUsuario` (`codUsuario`),
  ADD KEY `codEstabelecimento` (`codEstabelecimento`);

--
-- Indexes for table `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD KEY `codPedido` (`codPedido`),
  ADD KEY `codProduto` (`codProduto`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`codProduto`);

--
-- Indexes for table `produto_estabelecimento`
--
ALTER TABLE `produto_estabelecimento`
  ADD KEY `codProduto` (`codProduto`),
  ADD KEY `codEstabelecimento` (`codEstabelecimento`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`codUsuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `estabelecimento`
--
ALTER TABLE `estabelecimento`
  MODIFY `codEstabelecimento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `codPedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `codProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `codUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`codUsuario`) REFERENCES `usuarios` (`codUsuario`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`codEstabelecimento`) REFERENCES `estabelecimento` (`codEstabelecimento`);

--
-- Limitadores para a tabela `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD CONSTRAINT `pedido_produto_ibfk_1` FOREIGN KEY (`codPedido`) REFERENCES `pedido` (`codPedido`),
  ADD CONSTRAINT `pedido_produto_ibfk_2` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codProduto`);

--
-- Limitadores para a tabela `produto_estabelecimento`
--
ALTER TABLE `produto_estabelecimento`
  ADD CONSTRAINT `produto_estabelecimento_ibfk_1` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codProduto`),
  ADD CONSTRAINT `produto_estabelecimento_ibfk_2` FOREIGN KEY (`codEstabelecimento`) REFERENCES `estabelecimento` (`codEstabelecimento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
