
-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 17-Nov-2017 às 02:08
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
-- Database: `comumana_drinks`
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
(2, '10657358000145', 'gerencia@saladereboco.com.br', 'saladereboco', 'Cordeiro', '50720740', 'Recife', '-8.0536974', '-34.9222478', 264, 'Rua Gregï¿½rio Jï¿½nio', 'PE', 'Sala de Reboco', 'Sala De Reboco Bar & Comedoria Ltda - Me', '123456', '81999750204', 0),
(3, '11004583000145', 'afabricabar@hotmail.com', 'fabrica', 'Carmo', '53120160', 'Olinda', '-8.0144249', '-34.8475361', 9, 'Travessa Municipal', 'PE', 'A Fábrica Bar', 'A Fabrica Bar e Restaurante Ltda - Me', '123456', '81987209705', 0),
(4, '10636838000120', 'grutabar.camaragibe@gmail.com', 'grutabar', 'Vila da Fábrica', '54759135', 'Camaragibe', '-8.0139369', '-34.9788453', 387, 'Rua Carlos Alberto de Menezes', 'PE', 'GRUTA BAR', 'M J DE SOUZA BAR - ME', '123456', '81999998146', 0),
(5, '12972637000129', 'betosbaroficial@gmail.com', 'betosbar', 'Candeias', '54440620', 'Jaboatao Dos Guararapes', '-8.0139369', '-34.9788453', 4738, 'Av. Bernardo Vieira De Melo', 'PE', 'BetoS Bar', 'BetoS Restaurante e Pizzaria Ltda - Epp', '123456', '8134692995', 0),
(6, '09202813000139', 'bardobarriga@gmail.com', 'bardobarriga', 'Pilar', '53900000', 'Ilha de Itamaracï¿½', '-8.0139369', '-34.9788453', 30, 'Rua Assunï¿½ï¿½o', 'PE', 'Bar Do Barriga O Rei Da Tainhas', 'Bar Do Barriga O Rei Da Tainha', '123456', '81991915773', 0),
(7, '99999999999999', 'teste@teste.com.br', NULL, 'Santa MÃ´nicaÂ ', '54767400', 'Camaragibe', '-8.0298807', '-35.0011523', 116, 'Margarida Pereira dos Santos', 'PE', 'Teste', 'Teste', '123456', '99999999999', 0);

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
(4, '05/11/2017 19:05:25', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CARTAO', 'AGUARDANDO', 66.93, 1, 5),
(5, '16/11/2017 19:50:33', 'Ipsep', '51350020', 'Recife', NULL, NULL, 21, 'Rua Joa', 'PE', 'ESPECIE', 'AGUARDANDO', 18.84, 2, 3),
(6, '16/11/2017 20:08:00', 'Ipsep', '51350020', 'Recife', NULL, NULL, 23, 'Rua Joa', 'PE', 'CARTAO', 'AGUARDANDO', 52.42, 5, 3);

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

--
-- Extraindo dados da tabela `pedido_produto`
--

INSERT INTO `pedido_produto` (`codPedido`, `codProduto`, `preco`, `quantidade`, `precoTotal`) VALUES
(1, 16, 3.99, 1, 3.99),
(1, 7, 3.2, 5, 16),
(1, 2, 2.79, 2, 5.58),
(2, 7, 3.2, 6, 19.2),
(2, 4, 3.59, 6, 21.54),
(3, 10, 25.9, 3, 77.7),
(3, 14, 3.45, 1, 3.45),
(4, 16, 3.99, 1, 3.99),
(4, 19, 6.49, 6, 38.94),
(4, 22, 4, 6, 24),
(5, 13, 1.99, 6, 11.94),
(5, 14, 3.45, 2, 6.9),
(6, 9, 2.69, 6, 16.14),
(6, 12, 5.19, 2, 10.38),
(6, 10, 25.9, 1, 25.9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `codProduto` int(11) NOT NULL,
  `ean` varchar(15) NOT NULL,
  `descricao` varchar(400) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `bandeiraCartao` varchar(15) NOT NULL,
  `ref_img` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`codProduto`, `ean`,`descricao`, `nome`, `ref_img`) VALUES
(1, 7898358970013,'GELO EM CUBOS GELO DRINK 5KG', 'Gelo em Cubos', 'NULL'),
(2, 7891991000796,'Cerveja ANTARCTICA Pilsen Lata 350ml', 'Cerveja ANTARCTICA 350mL', 'NULL'),
(3, 7894900039849,'REFRIGERANTE FANTA LARANJA LATA', 'Fanta - Lata', 'NULL'),
(4, 7894900010015,'REFRIGERANTE COCA COLA LATA 350ML', 'Coca-Cola Lata 350mL', 'NULL'),
(5, 7897395060107,'CERVEJA CRISTAL PILSEN LATA 350ml', 'Cerveja KAISER 350mL', 'NULL'),
(6, 5701598031586,'BEAR BEER PREMIUM LAGER LATA 1 UNIDADE 350ml', 'Cerveja HEINEKEN 350mL', 'NULL'),
(7, 7898904771019,'DEVASSA LOURA PILSEN LONG NECK 1 UNIDADE ', 'Cerveja DEVASSA 355mL', 'NULL'),
(8, 7898233620347,'GELO ESCAMAS 5KG GELONESE', 'Gelo em Escamas', 'NULL'),
(9, 7891991010023,'CERVEJA ANTARCTICA SUB ZERO LATA 350ML', 'Cerveja ANTARCTICA 350mL', 'NULL'),
(10, 7896756802660,'ALMADEN CABERNET T|750|WINE | VINHO ALMADEN CABERNET TINTO 7', 'Vinho Tinto Seco 750 mL', 'NULL'),
(11, 7896756801151,'SELEÇÃO MIOLO BRANCO CHARDONNAY VIOGNIER 2010', 'Vinho Branco Seco 750mL', 'NULL'),
(12, 7894900011517,'REFRIGERANTE COCA COLA 2L', 'Coca-Cola - 2L', 'NULL'),
(13, 7898904771606,'CERV DEVASSA 350ML TROPICAL', 'Cerveja DEVASSA 350mL', 'NULL'),
(14, 7894900068399,'REFRIGERANTE SPRITE 2LT ', 'Sprite - 2L', 'NULL'),
(15, 7894900069846,'REFRIGERANTE SPRITE LATA ', 'Sprite Lata', 'NULL'),
(16, 7892840800000,'REFRIGERANTE DE COLA PEPSI 2L', 'Refigerente PEPSI 2L', 'NULL'),
(17, 7896607100372,'AGUARDENTE PITU GF', 'Cachaça Pitu', 'NULL'),
(18, 7891991009881,'CERVEJA BOHEMIA L NECK 355ml', 'Cerveja BOHEMIA 350mL', 'NULL'),
(19, 7891991001373,'REFRIGERANTE GUARANÁ ANTARCTICA 2 LITROS ', 'Coca-Cola - 2L', 'NULL'),
(20, 7898152997032,'VINHO CHI BOD VIEJA SUAVE BC 750ML', 'Vinho Tinto Suave 750mL', 'NULL'),
(22, 9789114944080,'REFRIGERANTE SUKITA 2LT', 'SUKITA - 2L', 'NULL');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto_estab`
--

CREATE TABLE `produto_estab` (
`codProduto` int(11) NOT NULL,
`codEstabelecimento` int(11) NOT NULL,
`ean` varchar(15) NOT NULL,
`preco` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produto_estab`
--

INSERT INTO `produto_estab` (`codProduto`, `ean`,`preco`, `codEstabelecimento`) VALUES
(1, 7898358970013, 3.99, 2),
(2, 7891991000796, 2.79, 2),
(3, 7894900039849, 3.59, 2),
(4, 7894900010015, 3.59, 2),
(5, 7897395060107, 1.79, 2),
(6, 5701598031586, 3.59, 2),
(7, 7898904771019, 3.2, 2),
(8, 7898233620347, 2.99, 3),
(9, 7891991010023, 2.69, 3),
(10, 7896756802660, 25.9, 3),
(11, 7896756801151, 35.59, 3),
(12, 7894900011517, 5.19, 3),
(13, 7898904771606, 1.99, 3),
(14, 7894900068399, 3.45, 3),
(15, 7894900069846, 3.5, 5),
(16, 7892840800000, 3.99, 5),
(17, 7896607100372, 10.59, 5),
(18, 7891991009881, 3.49, 5),
(19, 7891991001373, 6.49, 3),
(20, 7898152997032, 35.5,  5),
(22, 9789114944080, 4, 5);

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
(5, 'Italo', 'italo@italo.com', 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', '81995974462');

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
  ADD KEY `FK_codEstabelecimento` (`codEstabelecimento`),
  ADD KEY `FK_codUsuario` (`codUsuario`);

--
-- Indexes for table `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD KEY `FK55E4DBFF3CBCE51E` (`codPedido`),
  ADD KEY `FK55E4DBFFE4370969` (`codProduto`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`codProduto`);

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
  MODIFY `codEstabelecimento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `codPedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `codProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `codUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD CONSTRAINT `FK_Pedido_produto_codPedido` FOREIGN KEY (`codPedido`) REFERENCES `pedido` (`codPedido`),
  ADD CONSTRAINT `FK_Pedido_produto_codProduto` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codProduto`);
COMMIT;

--
-- Limitadores para a tabela `pedido_estab`
--
ALTER TABLE `produto_estab`
  ADD CONSTRAINT `FK_Produto_estab_codProduto` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codProduto`),
  ADD CONSTRAINT `FK_Produto_estab_codEstabelecimento` FOREIGN KEY (`codEstabelecimento`) REFERENCES `estabelecimento` (`codEstabelecimento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
