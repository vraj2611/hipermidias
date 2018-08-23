-- phpMyAdmin SQL Dump
-- version 4.7.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 23-Ago-2018 às 22:21
-- Versão do servidor: 5.6.34
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_log`
--
CREATE DATABASE IF NOT EXISTS `db_log` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_log`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `teste` (`x` INT)  BEGIN
declare saida varchar(100);
if x = 2 THEN
	set saida = 'SELECT nome FROM clientes WHERE id = x;';
ELSE
	set saida = "Outro";
end IF;
/*select saida;*/
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargos_func`
--

CREATE TABLE `cargos_func` (
  `id` int(11) NOT NULL,
  `descricao` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cargos_func`
--

INSERT INTO `cargos_func` (`id`, `descricao`) VALUES
(1, 'Motorista'),
(2, 'Gerente');

-- --------------------------------------------------------

--
-- Estrutura da tabela `centros`
--

CREATE TABLE `centros` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `centros`
--

INSERT INTO `centros` (`id`, `nome`) VALUES
(1, 'Recife'),
(2, 'Salvador'),
(3, 'Vitoria'),
(4, 'Rio de Janeiro'),
(5, 'São Paulo'),
(6, 'Curitiba'),
(7, 'Florianopolis'),
(8, 'Porto Alegre');

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cpf_cnpj` varchar(11) NOT NULL,
  `telefone` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id`, `nome`, `cpf_cnpj`, `telefone`) VALUES
(1, 'Adriano Cesar', '50062977926', '22938737823'),
(2, 'Alexandre Castro', '65543945776', '22931229368'),
(3, 'Alvaro Crisanto', '15781727532', '22972376609'),
(4, 'Ana Clara', '56979914991', '22984572348'),
(5, 'Ana Patricia', '93336225455', '22956088119'),
(6, 'Andre Defendi', '56629383410', '22940147017'),
(7, 'Andre Gomes', '65195664655', '22933917284'),
(8, 'Andrei Gromyko', '40418826543', '22959911588'),
(9, 'Antonio Carlos', '78812732045', '22917963046'),
(10, 'Arthur Sales', '12341234567', '22123123123'),
(14, 'zzzzzz', '1234142', '1233213'),
(15, 'aasdsadsa', 'ewrwerwerw', 'sadasda'),
(16, 'ayuyuy', 'wqwqw', 'qwqw'),
(17, 'Zack', '1213', '123'),
(18, 'Ze', '321', '123'),
(19, 'Ze', '321', '123'),
(20, 'Aaron', '9485', '9532'),
(21, 'Alberto', '2222', '9999');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios`
--

CREATE TABLE `funcionarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `id_cargo` int(11) NOT NULL,
  `centro` int(11) DEFAULT NULL,
  `hr_acesso` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionarios`
--

INSERT INTO `funcionarios` (`id`, `nome`, `id_cargo`, `centro`, `hr_acesso`) VALUES
(1, 'Bruno Borguignon', 2, 1, '2018-08-23 19:12:18'),
(2, 'Bruno Cavalcanti', 2, 2, '2018-08-21 20:21:44'),
(3, 'Bruno Huoya', 2, 3, '2018-08-21 20:36:54'),
(4, 'Caio Carduz', 2, 4, '2018-08-21 19:21:26'),
(5, 'Carlos Alberto', 2, 5, '2018-08-21 19:21:26'),
(6, 'Claudio Leandro', 2, 6, '2018-08-21 19:21:26'),
(7, 'Cristiano Icaro', 2, 7, '2018-08-21 19:21:26'),
(8, 'Danielle dos', 2, 8, '2018-08-21 20:21:49'),
(9, 'Elder Marco', 1, 1, '2018-08-21 19:21:26'),
(10, 'Eliane Born', 1, 2, '2018-11-21 20:11:09'),
(11, 'Emilio Eduardo', 1, 3, '2018-08-21 20:30:30'),
(12, 'Enock Fernandes', 1, 4, '2018-08-21 19:21:26'),
(13, 'Erick Silva', 1, 5, '2018-08-21 19:21:26'),
(14, 'Esmeraldino Aleluia', 1, 6, '2018-08-21 19:21:26'),
(15, 'Evandro Graeser', 1, 7, '2018-08-21 19:21:26'),
(16, 'Fabio Soares', 1, 8, '2018-08-21 19:21:26'),
(17, 'Felipe Barbosa', 1, 1, '2018-08-21 19:21:26'),
(18, 'Gilmario dos', 1, 2, '2018-08-21 19:21:26'),
(19, 'Guilherme Ruzza', 1, 3, '2018-08-21 20:30:38'),
(20, 'Hugo Albuquerque', 1, 4, '2018-08-21 19:21:26'),
(21, 'Hugo Mathias', 1, 5, '2018-08-21 19:21:26'),
(22, 'Ingrid Alves', 1, 6, '2018-08-21 19:21:26'),
(23, 'Jonatas Lima', 1, 7, '2018-08-21 19:21:26'),
(24, 'Julio Aratanha', 1, 8, '2018-08-21 19:21:26'),
(25, 'Leonardo Costa', 1, 1, '2018-08-21 19:21:26'),
(26, 'Luiz Henrique', 1, 2, '2018-08-21 19:21:26'),
(27, 'Marcella Maria', 1, 3, '2018-08-21 19:21:26'),
(28, 'Marcelo Cotta', 1, 4, '2018-08-21 19:21:26'),
(29, 'Marcelo Goncalves', 1, 5, '2018-08-21 19:21:26'),
(30, 'Marcelo Nascimento', 1, 6, '2018-08-21 19:21:26'),
(31, 'Marcus Vinicius', 1, 7, '2018-08-21 19:21:26'),
(32, 'Maria Clara', 1, 8, '2018-08-21 19:21:26'),
(33, 'Maria Taryn', 1, 1, '2018-08-21 19:21:26'),
(34, 'Paulo Roberto', 1, 2, '2018-08-21 19:21:26'),
(35, 'Pedro Lemes', 1, 3, '2018-08-21 19:21:26'),
(36, 'Theo Tarzo', 1, 4, '2018-08-21 19:21:26'),
(37, 'Thiago Borges', 1, 5, '2018-08-21 19:21:26'),
(38, 'Thiago Zhornack', 1, 6, '2018-08-21 19:21:26'),
(39, 'Tiago Santos', 1, 7, '2018-08-21 19:21:26'),
(40, 'Vinicius Ferreira', 1, 8, '2018-08-21 19:21:26');

--
-- Acionadores `funcionarios`
--
DELIMITER $$
CREATE TRIGGER `registrar_acesso` AFTER UPDATE ON `funcionarios` FOR EACH ROW insert into log_acesso (id_func, hr_acesso) VALUES (NEW.id, now())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `log_acesso`
--

CREATE TABLE `log_acesso` (
  `id_func` int(11) NOT NULL,
  `hr_acesso` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `log_acesso`
--

INSERT INTO `log_acesso` (`id_func`, `hr_acesso`) VALUES
(1, '2018-08-21 20:21:38'),
(2, '2018-08-21 20:21:44'),
(8, '2018-08-21 20:21:49'),
(11, '2018-08-21 20:30:30'),
(19, '2018-08-21 20:30:38'),
(1, '2018-08-21 20:36:21'),
(3, '2018-08-21 20:36:29'),
(3, '2018-08-21 20:36:54'),
(1, '2018-08-21 20:57:57'),
(1, '2018-08-21 21:01:22'),
(1, '2018-08-21 21:04:10'),
(1, '2018-08-21 21:11:18'),
(1, '2018-08-21 21:18:17'),
(1, '2018-08-21 21:24:46'),
(1, '2018-08-21 21:26:30'),
(1, '2018-08-22 07:19:23'),
(1, '2018-08-22 07:30:32'),
(1, '2018-08-22 07:37:07'),
(1, '2018-08-22 07:42:32'),
(1, '2018-08-22 07:51:27'),
(1, '2018-08-22 08:00:53'),
(1, '2018-08-22 08:05:58'),
(1, '2018-08-22 08:18:49'),
(1, '2018-08-22 08:22:10'),
(1, '2018-08-22 08:25:20'),
(1, '2018-08-22 08:25:49'),
(1, '2018-08-22 08:35:42'),
(1, '2018-08-22 09:17:20'),
(1, '2018-08-23 19:12:18');

-- --------------------------------------------------------

--
-- Estrutura da tabela `movimentacoes`
--

CREATE TABLE `movimentacoes` (
  `id` int(11) NOT NULL,
  `origem` varchar(20) NOT NULL,
  `destino` varchar(20) NOT NULL,
  `id_motorista` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `movimentacoes`
--

INSERT INTO `movimentacoes` (`id`, `origem`, `destino`, `id_motorista`, `status`) VALUES
(1, 'Origem', '1', 17, 1),
(2, '1', 'Destino', 17, 1),
(3, 'Origem', '2', 10, 1),
(4, '2', '3', 18, 1),
(5, '3', 'Destino', 11, 1),
(6, '1', '2', 9, 1),
(7, 'Origem', '1', 17, 0),
(8, 'Origem', '1', 9, 0),
(9, 'Origem', '2', 10, 1),
(11, '1', '3', 9, 0),
(12, '1', '5', 9, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `mov_pedidos`
--

CREATE TABLE `mov_pedidos` (
  `id_mov` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `mov_pedidos`
--

INSERT INTO `mov_pedidos` (`id_mov`, `id_pedido`) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(1, 3),
(2, 3),
(1, 4),
(2, 4),
(3, 5),
(4, 5),
(5, 5),
(3, 6),
(4, 6),
(5, 6),
(3, 7),
(4, 7),
(5, 7),
(3, 8),
(4, 8),
(5, 8),
(3, 9),
(4, 9),
(5, 9),
(3, 10),
(4, 10),
(5, 10),
(3, 11),
(4, 11),
(5, 11),
(3, 12),
(4, 12),
(5, 12),
(3, 13),
(4, 13),
(5, 13),
(3, 14),
(4, 14),
(5, 14),
(6, 15),
(6, 16),
(6, 17),
(7, 18),
(7, 19),
(8, 20),
(8, 21),
(12, 34),
(12, 35),
(11, 37),
(11, 38);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidos`
--

CREATE TABLE `pedidos` (
  `id_pedido` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `quant` float NOT NULL,
  `valor` float NOT NULL,
  `origem` varchar(100) NOT NULL,
  `destino` varchar(100) NOT NULL,
  `local` varchar(20) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pedidos`
--

INSERT INTO `pedidos` (`id_pedido`, `id_cliente`, `quant`, `valor`, `origem`, `destino`, `local`, `status`) VALUES
(1, 1, 5, 85, 'Rua UT, n996', 'Rua WC, n344', 'Destino', 'Entregue'),
(2, 2, 2, 34, 'Rua FG, n166', 'Rua XR, n261', 'Destino', 'Entregue'),
(3, 3, 5, 85, 'Rua OH, n705', 'Rua KK, n203', 'Destino', 'Entregue'),
(4, 1, 6, 102, 'Rua OG, n213', 'Rua UR, n465', 'Destino', 'Entregue'),
(5, 2, 2, 34, 'Rua HL, n78', 'Rua UA, n426', 'Destino', 'Entregue'),
(6, 3, 5, 85, 'Rua WS, n241', 'Rua MX, n648', 'Destino', 'Entregue'),
(7, 1, 4, 68, 'Rua JH, n395', 'Rua MV, n834', 'Destino', 'Entregue'),
(8, 2, 1, 17, 'Rua PM, n529', 'Rua HA, n362', 'Destino', 'Entregue'),
(9, 3, 7, 119, 'Rua QO, n195', 'Rua RR, n344', 'Destino', 'Entregue'),
(10, 1, 5, 85, 'Rua CF, n555', 'Rua LU, n862', 'Destino', 'Entregue'),
(11, 2, 1, 17, 'Rua VB, n864', 'Rua XN, n657', 'Destino', 'Entregue'),
(12, 3, 8, 136, 'Rua BG, n769', 'Rua TD, n942', 'Destino', 'Entregue'),
(13, 1, 5, 85, 'Rua EN, n745', 'Rua HP, n36', 'Destino', 'Entregue'),
(14, 2, 1, 17, 'Rua GR, n302', 'Rua GS, n230', 'Destino', 'Entregue'),
(15, 3, 1, 17, 'Rua KP, n372', 'Rua VJ, n69', '1', 'No Centro de Distribuição'),
(16, 1, 6, 102, 'Rua PL, n125', 'Rua OA, n686', '1', 'No Centro de Distribuição'),
(17, 2, 7, 119, 'Rua QC, n97', 'Rua BO, n580', '1', 'No Centro de Distribuição'),
(18, 3, 9, 153, 'Rua FD, n89', 'Rua KV, n192', '1', 'Em Transito'),
(19, 1, 5, 85, 'Rua HA, n959', 'Rua BM, n391', '1', 'Em Transito'),
(20, 2, 7, 119, 'Rua OS, n575', 'Rua VI, n216', '2', 'Em Recolhimento'),
(21, 3, 5, 85, 'Rua LM, n95', 'Rua BW, n841', '2', 'Em Recolhimento'),
(22, 1, 2, 34, 'Rua IJ, n78', 'Rua DC, n136', '2', 'No Centro de Distribuição'),
(23, 2, 7, 119, 'Rua NQ, n512', 'Rua TA, n839', '2', 'No Centro de Distribuição'),
(24, 3, 3, 51, 'Rua AS, n973', 'Rua NE, n243', '2', 'No Centro de Distribuição'),
(25, 1, 7, 119, 'Rua HU, n601', 'Rua RW, n805', 'Origem', 'Aguardando Recolhimento'),
(26, 2, 3, 51, 'Rua SD, n169', 'Rua CE, n220', 'Origem', 'Aguardando Recolhimento'),
(27, 3, 6, 102, 'Rua VH, n171', 'Rua JG, n35', 'Origem', 'Aguardando Recolhimento'),
(28, 1, 1, 17, 'Rua GQ, n353', 'Rua DV, n503', 'Origem', 'Aguardando Recolhimento'),
(29, 2, 8, 136, 'Rua OA, n606', 'Rua KS, n79', 'Origem', 'Aguardando Recolhimento'),
(30, 3, 2, 34, 'Rua NQ, n62', 'Rua JJ, n458', 'Origem', 'Aguardando Recolhimento'),
(31, 1, 4, 68, 'Rua DB, n477', 'Rua OP, n751', 'Origem', 'Aguardando Recolhimento'),
(32, 1, 9, 0, 'Rua ZZ,n2', 'Rua AA, n6', 'Origem', 'Aguardando Recolhimento'),
(33, 1, 3, 0, 'Rua BA,da', 'adasd', 'Origem', 'Aguardando Recolhimento'),
(34, 1, 1, 0, 'aqui', 'la', 'Origem', 'Aguardando Recolhimento'),
(35, 1, 5, 0, 'Rua UT, n996', 'Rua WC, n344', 'Origem', 'Aguardando Recolhimento'),
(36, 1, 99, 0, 'here', 'there', 'Origem', 'Aguardando Recolhimento'),
(37, 1, 7, 0, 'aqwui', 'la', 'Origem', 'Aguardando Recolhimento'),
(38, 1, 11, 0, 'aaaa', 'bbbb', 'Origem', 'Aguardando Recolhimento');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cargos_func`
--
ALTER TABLE `cargos_func`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `centros`
--
ALTER TABLE `centros`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cargo` (`id_cargo`),
  ADD KEY `centro` (`centro`);

--
-- Indexes for table `movimentacoes`
--
ALTER TABLE `movimentacoes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_motorista` (`id_motorista`);

--
-- Indexes for table `mov_pedidos`
--
ALTER TABLE `mov_pedidos`
  ADD PRIMARY KEY (`id_mov`,`id_pedido`),
  ADD KEY `id_pedidos` (`id_pedido`);

--
-- Indexes for table `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cargos_func`
--
ALTER TABLE `cargos_func`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `centros`
--
ALTER TABLE `centros`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `funcionarios`
--
ALTER TABLE `funcionarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `movimentacoes`
--
ALTER TABLE `movimentacoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD CONSTRAINT `funcionarios_ibfk_1` FOREIGN KEY (`id_cargo`) REFERENCES `cargos_func` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `funcionarios_ibfk_2` FOREIGN KEY (`centro`) REFERENCES `centros` (`id`) ON UPDATE CASCADE;

--
-- Limitadores para a tabela `movimentacoes`
--
ALTER TABLE `movimentacoes`
  ADD CONSTRAINT `movimentacoes_ibfk_1` FOREIGN KEY (`id_motorista`) REFERENCES `funcionarios` (`id`) ON UPDATE CASCADE;

--
-- Limitadores para a tabela `mov_pedidos`
--
ALTER TABLE `mov_pedidos`
  ADD CONSTRAINT `mov_pedidos_ibfk_1` FOREIGN KEY (`id_mov`) REFERENCES `movimentacoes` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `mov_pedidos_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON UPDATE CASCADE;

--
-- Limitadores para a tabela `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
