-- phpMyAdmin SQL Dump
-- version 4.0.10.14
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tempo de Gera√ß√£o: 27/10/2017 √ s 21:42
-- Vers√£o do servidor: 5.5.56-MariaDB-cll-lve
-- Vers√£o do PHP: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de dados: `comumana_drinks`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `estabelecimento`
--

CREATE TABLE IF NOT EXISTS `estabelecimento` (
  `codEstabelecimento` int(11) NOT NULL AUTO_INCREMENT,
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
  `administrador` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codEstabelecimento`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Fazendo dump de dados para tabela `estabelecimento`
--

INSERT INTO `estabelecimento` (`codEstabelecimento`, `cnpj`, `eMail`, `login`, `bairro`, `cep`, `cidade`, `latitude`, `longitude`, `numero`, `rua`, `uf`, `nomeFantasia`, `razaoSocial`, `senha`, `telefone`, `administrador`)VALUES
(1, '99999999999999', 'admin@admin.com', 'admin', '', '', '', '', '', '', '', '', 'SuperAdmin', 'SuperAdmin', '123456', '99999999999', 1),
(2, '10657358000145', 'gerencia@saladereboco.com.br', 'saladereboco', 'Cordeiro', '50720740', 'Recife', '-8.0536974', '-34.9222478', 264, 'Rua GregÛrio J˙nio', 'PE', 'Sala de Reboco', 'Sala De Reboco Bar & Comedoria Ltda - Me', '123456', '81999750204', 0),
(3, '11004583000145', 'afabricabar@hotmail.com', 'fabrica', 'Carmo', '53120160', 'Olinda', '-8.0144249', '-34.8475361', 9, 'Travessa Municipal', 'PE', 'A F·brica Bar', 'A Fabrica Bar e Restaurante Ltda - Me', '123456', '81987209705', 0),
(4, '10636838000120', 'grutabar.camaragibe@gmail.com', 'grutabar', 'Vila da Fabrica', '54759135', 'Camaragibe', '-8.0139369', '-34.9788453', 387, 'Rua Carlos Alberto de Menezes', 'PE', 'GRUTA BAR', 'M J DE SOUZA BAR - ME', '123456', '81999998146', 0),
(5, '12972637000129', 'betosbaroficial@gmail.com', 'betosbar', 'Candeias', '54440620', 'Jaboatao Dos Guararapes', '-8.0139369', '-34.9788453', 4738, 'Av. Bernardo Vieira De Melo', 'PE', 'BetoS Bar', 'BetoS Restaurante e Pizzaria Ltda - Epp', '123456', '8134692995', 0),
(6, '09202813000139', 'bardobarriga@gmail.com', 'bardobarriga', 'Pilar', '53900000', 'Ilha de Itamaraca', '-8.0139369', '-34.9788453', 30, 'Rua AssunÁ„o', 'PE', 'Bar Do Barriga O Rei Da Tainha', 'Bar Do Barriga O Rei Da Tainha', 'c20ad4d76fe97759', '81991915773', 0);

-- Bar Do Barriga O Rei Da Tainha ter· dados fictÌcios, pois n„o foram encontrados na rede;----------------------------------------------

--
-- Estrutura para tabela `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `codPedido` int(11) NOT NULL AUTO_INCREMENT,
  `dataPedido` datetime NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cep` varchar(9) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `latitude` varchar(10) NOT NULL,
  `longitude` varchar(10) NOT NULL,
  `numero` int(11) NOT NULL,
  `rua` varchar(150) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `pagamento` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `valorTotal` double NOT NULL,
  `codUsuario` int(11) NOT NULL,
  `codEstabelecimento` int(11) NOT NULL,
  PRIMARY KEY (`codPedido`),
  KEY `FK_codEstabelecimento` (`codEstabelecimento`),
  KEY `FK_codUsuario` (`codUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido_produto`
--

CREATE TABLE IF NOT EXISTS `pedido_produto` (
  `codPedido` int(11) NOT NULL,
  `codProduto` int(11) NOT NULL,
  KEY `FK55E4DBFF3CBCE51E` (`codPedido`),
  KEY `FK55E4DBFFE4370969` (`codProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `codProduto` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(400) NOT NULL,
  `gelada` tinyint(1) DEFAULT NULL,
  `nome` varchar(150) NOT NULL,
  `preco` double NOT NULL,
  `codEstabelecimento` int(11) NOT NULL,
  PRIMARY KEY (`codProduto`),
  KEY `FK_Produto_Estabelecimento` (`codEstabelecimento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Fazendo dump de dados para tabela `produto`
--

INSERT INTO `produto` (`codProduto`, `descricao`, `gelada`, `nome`, `preco`, `codEstabelecimento`) VALUES
(1, 'Gelada', 0, 'Cerveja', 5.2, 6),
(3, '', 0, 'Cerveja', 11.11, 1),
(6, 'Cerveja', 0, 'Cerveja', 11, 1),
(7, 'Cerveja', 0, 'Cerveja22', 11, 1),
(8, 'Cerveja', 0, 'Cerveja23', 12, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `codUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `encrypted_password` varchar(80) NOT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`codUsuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Fazendo dump de dados para tabela `usuarios`
--

INSERT INTO `usuarios` (`codUsuario`, `nome`, `email`, `encrypted_password`, `telefone`) VALUES
(1, 'Emerson Francisco', 'ems@gmail.com', 'jLIjfQZ5yojbZGTqxg2pY0VROWQ=', '81986854814'),
(2, 'Tercio Lima', 'terciosky@gmail.com', 'QL0AFWMIX8NRZTKeof9cXsvbvu8=', '81996644179'),
(3, 'Juliana', 'souza3408@gmail.com', 'a15q4UVZBErnR7WyvhHqQ2Sq/p0=', '81986226795'),
(4, 'Silvio', 'engeheirocedrim@gmail.com', 'fEqNCco3Yq9h5ZUglD3CZJT4lBs=', '81998237293');

--
-- Restri√ß√µes para dumps de tabelas
--

--
-- Restri√ß√µes para tabelas `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_codEstabelecimento` FOREIGN KEY (`codEstabelecimento`) REFERENCES `estabelecimento` (`codEstabelecimento`),
  ADD CONSTRAINT `FK_codUsuario` FOREIGN KEY (`codUsuario`) REFERENCES `usuarios` (`codUsuario`);

--
-- Restri√ß√µes para tabelas `pedido_produto`
--
ALTER TABLE `pedido_produto`
  ADD CONSTRAINT `FK_Pedido_produto_codPedido` FOREIGN KEY (`codPedido`) REFERENCES `pedido` (`codPedido`),
  ADD CONSTRAINT `FK_Pedido_produto_codProduto` FOREIGN KEY (`codProduto`) REFERENCES `produto` (`codProduto`);

--
-- Restri√ß√µes para tabelas `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `FK_Produto_Estabelecimento` FOREIGN KEY (`codEstabelecimento`) REFERENCES `estabelecimento` (`codEstabelecimento`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
