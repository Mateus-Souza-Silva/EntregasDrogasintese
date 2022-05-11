-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 11-Maio-2022 às 00:10
-- Versão do servidor: 5.7.31
-- versão do PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `entregasdrogasintese`
--

DELIMITER $$
--
-- Procedimentos
--
DROP PROCEDURE IF EXISTS `quantidadeEntrega`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `quantidadeEntrega` ()  BEGIN
   SELECT 
   		COUNT(entrega.entregaido) as qtdCadastrada
   FROM entrega
   WHERE entrega.dataentrega = CURRENT_DATE;
   
   SELECT 
   		COUNT(entrega.entregaido) as qtdFinalizada
   FROM entrega
   where entrega.datapagamento = CURRENT_DATE;
   
   END$$

DROP PROCEDURE IF EXISTS `QuantidadeEntregas`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `QuantidadeEntregas` ()  SQL SECURITY INVOKER
BEGIN
SELECT
	count(entrega.entregaido) as QtdCadastrada
from entrega
where entrega.dataentrega = CURRENT_DATE;

SELECT
	count(entrega.entregaido) as QtdFinalizada
from entrega
where entrega.datapagamento = CURRENT_DATE;
END$$

DROP PROCEDURE IF EXISTS `selecionar_leitor`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selecionar_leitor` ()  BEGIN
   SELECT count(entrega.entregaido)  FROM entrega;
   END$$

DROP PROCEDURE IF EXISTS `valorcobranca`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `valorcobranca` ()  BEGIN

DECLARE COBRANCAIDO INTEGER;
DECLARE DATACOBRANCA DATE;
DECLARE NFCOBRANCA CHAR(100);
DECLARE VALORPARCELA DOUBLE;
DECLARE VALOR DOUBLE;
DECLARE VENCIMENTO DATE;
DECLARE OBSERVACAO CHAR(100);
DECLARE DATAPAGAMENTO DATE;
DECLARE CLIENTEIDO INTEGER;
DECLARE NOMECLIENTE VARCHAR(100);
DECLARE SETORIDO INTEGER;
DECLARE DESCRICAOSETOR VARCHAR(100);
DECLARE SITUACAOIDO INTEGER;
DECLARE DESCRICAOSITUACAO VARCHAR(100);
DECLARE TIPOPAGAMENTOIDO INTEGER;
DECLARE descricaotipopagamento VARCHAR(100);
DECLARE pagamentoido INTEGER;
DECLARE descricaopagamento VARCHAR(100);

loop1: LOOP
	SELECT
		cobranca.cobrancaido,
		cobranca.datacobranca,
		coalesce(cobranca.nfcobranca,'') AS nfcobranca,
		   COALESCE((SELECT sum(pagamento_parcela.valor_pagamento_parcela)
		   from pagamento_parcela
		   where pagamento_parcela.cobrancaido = cobranca.cobrancaido),0) as valorparcela,
		cobranca.valor,
		coalesce(cobranca.vencimento,'') AS vencimento,
		COALESCE(cobranca.observacao,'') AS observacao,
		cobranca.datapagamento AS datapagamento,
		cobranca.clienteido,
		pessoa.nome as nomecliente,
		cobranca.setorido,
		setor.descricao as descricaosetor,
		cobranca.situacaoido,
		situacao.descricao as descricaosituacao,
		coalesce(cobranca.tipopagamentoido,'') AS tipopagamentoido,
		coalesce(tipopagamento.descricao,'') AS descricaotipopagamento,
		coalesce(cobranca.pagamentoido,'') AS pagamentoido,
		coalesce(pagamento.descricao,'') AS descricaopagamento
	INTO cobrancaido, datacobranca, nfcobranca, valorparcela, valor, vencimento, observacao, datapagamento, clienteido, nomecliente, setorido, descricaosetor,situacaoido,
	descricaosituacao, tipopagamentoido, descricaotipopagamento, pagamentoido, descricaopagamento	
	FROM cobranca
	INNER JOIN cliente ON cliente.clienteido = cobranca.clienteido
	INNER JOIN pessoa ON pessoa.pessoaido = cliente.pessoaido
	INNER JOIN setor ON setor.setorido = cobranca.setorido
	INNER JOIN situacao ON situacao.situacaoido = cobranca.situacaoido
	left JOIN tipopagamento ON tipopagamento.tipopagamentoido = cobranca.tipopagamentoido
	LEFT JOIN pagamento ON pagamento.pagamentoido = cobranca.pagamentoido
	ORDER BY cobranca.cobrancaido DESC;
	
	IF (valorparcela > 0) THEN
		SET valor = valorparcela;
	END IF;
	
END LOOP;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cidade`
--

DROP TABLE IF EXISTS `cidade`;
CREATE TABLE IF NOT EXISTS `cidade` (
  `cidadeido` int(11) NOT NULL AUTO_INCREMENT,
  `nome` char(50) DEFAULT NULL,
  PRIMARY KEY (`cidadeido`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cidade`
--

INSERT INTO `cidade` (`cidadeido`, `nome`) VALUES
(1, 'FERNANDOPOLIS'),
(2, 'TESTE'),
(3, 'ITUUITABA'),
(4, 'ITUUITABA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `clienteido` int(11) NOT NULL AUTO_INCREMENT,
  `cep` char(9) DEFAULT NULL,
  `logradouro` char(100) DEFAULT NULL,
  `bairro` char(30) DEFAULT NULL,
  `cidadeido` int(11) NOT NULL,
  `numero` char(30) DEFAULT NULL,
  `complemento` char(100) DEFAULT NULL,
  `pessoaido` int(11) NOT NULL,
  `telefone` char(20) DEFAULT NULL,
  PRIMARY KEY (`clienteido`),
  KEY `pessoaido` (`pessoaido`),
  KEY `cidadeido` (`cidadeido`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`clienteido`, `cep`, `logradouro`, `bairro`, `cidadeido`, `numero`, `complemento`, `pessoaido`, `telefone`) VALUES
(1, '15600-344', 'ALTEARDO', 'JARDIM ALTERADO', 3, 'alt', 'ALTERADO', 4, '321'),
(2, 'alterado', 'ALTERADO', 'ALTERADO', 1, 'alterado', 'ALTERADO', 9, 'alterado'),
(3, '123123123', '1', 'JARDIM RESIDENCIAL POR DO SOL', 1, '11', '123', 11, '1'),
(4, '1', '1', '1', 1, '1', '1', 12, '1'),
(5, '1', '1', '1', 1, '1', '1', 13, '1'),
(6, '123456789', '1', '1', 1, '1', '1', 14, '1'),
(7, '123456789', '1', 'CADASTRO ATUAL', 1, '1', '1', 15, '1'),
(8, '123456789', '1', '1', 1, '1', '1', 16, '1'),
(9, '123456789', '1', 'IGOR', 1, '1', '1', 17, '1'),
(10, '15600-344', '3216', 'CIZIRA BIZELI', 1, '32496', 'COMPLEMENTO', 19, '123456'),
(11, '15600-344', '13', 'CIZIRA BIZELI', 1, '265', '1', 22, '1321312'),
(12, '15610026', '1', 'TESTE', 1, '1', '546', 5, '1'),
(13, '15600-344', '10', '10', 1, '1010', '10', 7, '1010'),
(14, '15610026', 'RUA ANA ARNALDO DA SILVA', 'JARDIM SANTA RITA', 1, '381', 'TESSTE', 10, '17991965244'),
(15, '15600-344', 'RUA CIZIRA BIZELLI ZULIANI', 'JARDIM RESIDENCIAL POR DO SOL', 1, '265', '1', 12, '17991965244'),
(16, '15600-344', 'RUA CIZIRA BIZELLI ZULIANI', 'JARDIM RESIDENCIAL POR DO SOL', 1, '381', '123', 14, '17991965244'),
(17, '15610-026', 'RUA ANA ARNALDO DA SILVA', 'JARDIM SANTA RITA', 1, '10', 'TESTE', 17, '17991925244'),
(18, '15606-112', 'DASDAS', 'SDASDA', 1, '', '', 18, ''),
(19, '15600-344', '...', '...', 1, '', '', 19, ''),
(20, '15600-344', '3216', 'JARDIM RESIDENCIAL POR DO SOL', 1, '132', '123', 23, ''),
(21, '15610026', 'RUA ANA ARNALDO DA SILVA', 'JARDIM SANTA RITA', 1, '132', '', 25, ''),
(22, '15600-344', '10', '10', 2, '10', 'TESTE', 29, '17991965244'),
(23, '15600-344', 'RUA CIZIRA BIZELLI ZULIANI', 'JARDIM RESIDENCIAL POR DO SOL', 1, '265', '2904', 31, '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cobranca`
--

DROP TABLE IF EXISTS `cobranca`;
CREATE TABLE IF NOT EXISTS `cobranca` (
  `cobrancaido` int(11) NOT NULL AUTO_INCREMENT,
  `datacobranca` date NOT NULL,
  `nfcobranca` char(100) DEFAULT NULL,
  `clienteido` int(11) DEFAULT NULL,
  `valor` decimal(15,2) DEFAULT NULL,
  `vencimento` date DEFAULT NULL,
  `setorido` int(11) DEFAULT NULL,
  `observacao` text,
  `pagamentoido` int(11) DEFAULT NULL,
  `situacaoido` int(11) DEFAULT NULL,
  `datapagamento` date DEFAULT NULL,
  `tipopagamentoido` int(11) DEFAULT NULL,
  PRIMARY KEY (`cobrancaido`),
  KEY `clienteido` (`clienteido`),
  KEY `setorido` (`setorido`),
  KEY `pagamentoido` (`pagamentoido`),
  KEY `situacaoido` (`situacaoido`),
  KEY `tipopagamentoido` (`tipopagamentoido`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cobranca`
--

INSERT INTO `cobranca` (`cobrancaido`, `datacobranca`, `nfcobranca`, `clienteido`, `valor`, `vencimento`, `setorido`, `observacao`, `pagamentoido`, `situacaoido`, `datapagamento`, `tipopagamentoido`) VALUES
(1, '2020-01-01', 'TESTE', 1, '1.00', '2020-01-01', 1, '1.00', 1, 2, '2020-01-01', 1),
(2, '2020-01-01', '1', 1, '1.00', '2020-01-01', 1, '12.00', 1, 2, '2020-01-01', 1),
(3, '2020-01-01', '1', 1, '1.00', '2020-01-01', 1, '12.00', 1, 2, '2022-04-06', 1),
(4, '2020-01-01', 'ADAS', 1, '23.00', '0020-12-01', 1, 'ASDAS', 8, 2, '2021-01-26', 1),
(5, '2022-01-19', 'TESTE', 1, '10.00', '2022-03-19', 1, 'TGESTE', 1, 4, '2022-04-06', 1),
(6, '2022-04-07', '10', 5, '10.00', '2022-04-07', 1, '10', 1, 2, '2022-04-13', 1),
(7, '2001-02-02', 'teste', 1, '10.00', '2020-01-01', 1, 'teste', 1, 3, '2022-05-13', 1),
(8, '2001-02-02', '10', 1, '10.00', '2020-01-01', 1, '10', 1, 2, NULL, 1),
(9, '2022-04-13', '10', 1, '10.00', '2022-04-13', 1, '10', NULL, 2, NULL, NULL),
(10, '2022-04-13', '10', 1, '10.00', '2022-04-13', 1, '10', NULL, 2, NULL, NULL),
(11, '2022-04-19', '10', 1, '10.00', '2022-04-19', 1, '10', NULL, 2, NULL, NULL),
(12, '2022-04-19', '10', 5, '120.00', '2022-04-19', 2, '10', 3, 2, '2022-04-28', 1),
(13, '2022-04-20', '10', 2, '10.00', '2022-09-05', 1, '10', 1, 3, NULL, NULL),
(14, '2022-04-25', '10', 1, '10.00', '2022-04-25', 1, '10', 2, 4, NULL, NULL),
(15, '2022-04-27', '2704', 2, '2704.00', '2022-04-27', 3, '2704', 3, 2, '2022-04-27', 2),
(16, '2022-04-28', '2804', 1, '2804.00', '2022-04-30', 2, '2804', 1, 2, '2022-04-28', 1),
(17, '2022-04-28', '2804', 1, '2804.00', '2022-04-28', 1, '2804', NULL, 2, NULL, NULL),
(18, '2001-02-02', '2904', 1, '2904.00', '2022-04-29', 2, '29*04', 3, 2, '2022-05-07', 3),
(19, '2022-04-30', '31', 1, '321.00', '2020-04-01', 1, '321', 1, 2, '2022-05-06', 1),
(20, '2022-04-29', '20', 1, '10.00', '2022-04-29', 2, '10', 3, 3, '2022-05-06', 2),
(21, '2001-02-02', '10', 1, '10.00', '2001-02-02', 2, '10', 3, 2, '2022-05-06', 2),
(22, '2001-02-02', 'ASDADSAD', 1, '10.00', '2001-02-02', 2, '10ASDASDASDAS', 3, 2, '2022-05-02', 2),
(23, '2001-01-02', '10', 1, '10.00', '2001-02-02', 2, '10', 4, 2, '2001-02-02', 2),
(24, '2022-05-06', '12', 1, '15.00', '2022-05-06', 1, '12', 1, 2, '2022-05-14', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `entrega`
--

DROP TABLE IF EXISTS `entrega`;
CREATE TABLE IF NOT EXISTS `entrega` (
  `entregaido` int(11) NOT NULL AUTO_INCREMENT,
  `dataentrega` date DEFAULT NULL,
  `produtos` char(100) DEFAULT NULL,
  `valor` decimal(15,2) DEFAULT NULL,
  `recebedor` char(100) DEFAULT NULL,
  `observacao` char(250) DEFAULT NULL,
  `entregadorido` int(11) DEFAULT NULL,
  `pagamentoido` int(11) DEFAULT NULL,
  `situacaoido` int(11) DEFAULT NULL,
  `clienteido` int(11) DEFAULT NULL,
  `datapagamento` date DEFAULT NULL,
  PRIMARY KEY (`entregaido`),
  KEY `fk_EntregadorEntrega` (`entregadorido`),
  KEY `fk_PagamentoEntrega` (`pagamentoido`),
  KEY `fk_SituacaoEntrega` (`situacaoido`),
  KEY `fk_ClienteEntrega` (`clienteido`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `entrega`
--

INSERT INTO `entrega` (`entregaido`, `dataentrega`, `produtos`, `valor`, `recebedor`, `observacao`, `entregadorido`, `pagamentoido`, `situacaoido`, `clienteido`, `datapagamento`) VALUES
(1, '2022-05-02', 'DAS', '1.00', 'SA', 'SDA', 1, 1, 2, 1, '2022-04-13'),
(2, '2022-05-02', '1', '1.00', '1', '1', 2, 4, 2, 10, '2022-04-13'),
(3, '2022-05-02', '1', '1.00', '1', '1', 1, 1, 2, 1, '2022-04-13'),
(4, '2022-05-02', '321', '21.00', '10', '131', 2, 5, 2, 1, '2022-04-13'),
(5, '2022-05-02', 'TESTE', '10.00', '10', '10', 1, 3, 2, 12, '2022-04-13'),
(6, '2022-05-02', 'TESTE', '10.00', 'MATEUS', 'TESTE OBS\r\n', 3, 2, 2, 1, '2022-04-13'),
(7, '2022-05-02', 'TESTE', '2.00', '2', '2', 1, 1, 2, 1, '2022-04-13'),
(8, '2022-05-02', 'PRODUTOS 07.04', '10.00', '10', '10', 1, 1, 2, 1, '2022-04-13'),
(9, '2022-05-02', 'TESTE', '1.00', '10', 'REWQASDAS', 1, 1, 2, 1, '2022-04-13'),
(10, '2022-05-02', 'EE3', '321.00', '321', '321', 1, 1, 2, 1, NULL),
(11, '2022-05-02', 'TESTE', '1.00', '1', '1', 1, 1, 2, 1, NULL),
(12, '2022-05-02', 'TESTE 19042022', '10.00', '1', '10', 1, NULL, 2, 1, NULL),
(13, '2022-05-02', '10', '10.00', '10', '10', 1, NULL, 2, 1, NULL),
(14, '2022-05-02', '10', '10.00', '10', '10', 1, NULL, 2, 3, NULL),
(15, '2022-05-02', 'TESTE', '10.00', 'MATEUS', 'TESTE OBS\r\n', 3, NULL, 3, 1, NULL),
(16, '2022-05-02', 'PRODUTOS 25.04', '10.00', '10', '10', 2, NULL, 3, 2, NULL),
(17, '2022-05-02', 'PRODUTOS 25.04', '10.00', '10', '10', 3, NULL, 3, 2, NULL),
(18, '2022-05-02', 'PRODUTOS 25.04', '10.00', '10', '10', 2, NULL, 2, 2, NULL),
(19, '2022-05-02', 'PRODUTOS 25.04', '10.00', '10', '10', 3, NULL, 2, 2, NULL),
(20, '2022-05-02', 'PRODUTOS 25.04', '10.00', '10', '10', 10, NULL, 4, 22, NULL),
(21, '2022-05-02', 'TESTE 2504', '10.00', '10', '10', 1, NULL, 2, 1, NULL),
(22, '2022-05-02', 'TESTE 2504', '10.00', '10', '10', 1, NULL, 2, 1, NULL),
(23, '2022-05-02', 'TESTE HOJE 2504', '12.00', '10', '10', 1, NULL, 2, 1, NULL),
(24, '2022-05-02', 'ENTREGA TESTE HOJE 2704', '20.00', '20', '2010', 8, 3, 3, 8, NULL),
(25, '2022-05-02', 'CADASTRO 2704', '2704.00', '2704', '2704', 1, 3, 4, 1, NULL),
(26, '2022-05-02', 'TESTE 2804', '2804.00', '2804', '2804', 1, NULL, 2, 1, NULL),
(27, '2022-05-02', '2904', '2904.00', '2904', '2904', 1, 3, 3, 1, NULL),
(28, '2022-05-02', 'DIPIRONA', '10.00', 'MATEUS SOUZA', 'NENHUMA', 1, NULL, 2, 1, NULL),
(29, '2022-05-02', 'TESTESDAS', '554.00', '54', '54', 1, NULL, 2, 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `entregador`
--

DROP TABLE IF EXISTS `entregador`;
CREATE TABLE IF NOT EXISTS `entregador` (
  `entregadorido` int(11) NOT NULL AUTO_INCREMENT,
  `senha` char(100) NOT NULL,
  `pessoaido` int(11) NOT NULL,
  PRIMARY KEY (`entregadorido`),
  KEY `pessoaido` (`pessoaido`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `entregador`
--

INSERT INTO `entregador` (`entregadorido`, `senha`, `pessoaido`) VALUES
(1, 'ALETRADAO', 3),
(2, 'entregador2', 4),
(3, '10', 6),
(4, '123456', 11),
(5, '123456', 13),
(6, 'entregador', 16),
(7, '10', 22),
(8, '321', 24),
(9, '123', 27),
(10, '2504', 28),
(11, 'teste2904', 30);

-- --------------------------------------------------------

--
-- Estrutura da tabela `farmaceutico`
--

DROP TABLE IF EXISTS `farmaceutico`;
CREATE TABLE IF NOT EXISTS `farmaceutico` (
  `farmaceuticoido` int(11) NOT NULL AUTO_INCREMENT,
  `funcao` char(20) NOT NULL,
  `senha` char(100) NOT NULL,
  `pessoaido` int(11) NOT NULL,
  PRIMARY KEY (`farmaceuticoido`),
  KEY `pessoaido` (`pessoaido`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `farmaceutico`
--

INSERT INTO `farmaceutico` (`farmaceuticoido`, `funcao`, `senha`, `pessoaido`) VALUES
(1, 'TESTE', 'farmaceutico', 1),
(2, 'ALTERADO', '321', 2),
(3, 'FRENTE', 'mateus', 8),
(4, 'FARMACEUTICO', 'farmaceutico1', 9),
(5, 'TESTE', '123456', 15),
(6, 'TESTE', 'f', 20),
(7, '10', '123', 21),
(8, '321', '321', 26);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
CREATE TABLE IF NOT EXISTS `pagamento` (
  `pagamentoido` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` char(50) DEFAULT NULL,
  PRIMARY KEY (`pagamentoido`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pagamento`
--

INSERT INTO `pagamento` (`pagamentoido`, `descricao`) VALUES
(3, 'PIX'),
(4, 'CARTAO'),
(5, 'DINHEIRO'),
(1, 'SELECIONAR');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pagamento_parcela`
--

DROP TABLE IF EXISTS `pagamento_parcela`;
CREATE TABLE IF NOT EXISTS `pagamento_parcela` (
  `pagamento_parcelaido` int(11) NOT NULL AUTO_INCREMENT,
  `valor_pagamento_parcela` decimal(15,2) DEFAULT NULL,
  `cobrancaido` int(11) DEFAULT NULL,
  PRIMARY KEY (`pagamento_parcelaido`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pagamento_parcela`
--

INSERT INTO `pagamento_parcela` (`pagamento_parcelaido`, `valor_pagamento_parcela`, `cobrancaido`) VALUES
(37, '800.00', 18),
(36, '904.00', 18),
(35, '10.00', 21),
(34, '21.00', 19),
(33, '10.00', 20);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
CREATE TABLE IF NOT EXISTS `pessoa` (
  `pessoaido` int(10) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `idade` int(3) NOT NULL,
  `datanascimento` date NOT NULL,
  `datacadastro` date NOT NULL,
  `nivel` char(100) NOT NULL,
  PRIMARY KEY (`pessoaido`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`pessoaido`, `nome`, `idade`, `datanascimento`, `datacadastro`, `nivel`) VALUES
(1, 'MATEUS', 10, '2020-01-01', '2022-03-09', 'F'),
(2, 'FARMACEUTICOALTERADO', 1, '2022-04-20', '2022-03-09', '1'),
(3, 'ENTREGADORALTERADO', 1, '2022-04-20', '2022-03-09', '10'),
(4, 'CLIENTE ALTERADO 20.04', 10, '2020-01-01', '2022-03-09', '1'),
(5, 'CLIENTE MATEUS', 1, '2000-01-01', '2022-03-12', '1'),
(6, 'ENTREGADOR TESTE', 10, '2021-02-01', '2022-03-16', 'E'),
(7, '1010', 10, '2020-10-10', '2022-03-16', 'C'),
(8, 'MATEUS FARMACEUTICO', 21, '2001-02-02', '2022-03-17', 'F'),
(9, 'ALTERAR', 1, '2022-04-19', '2022-03-27', 'F'),
(10, 'MATEUS CLIENTE', 2, '2020-01-01', '2022-04-05', 'C'),
(11, 'ENTREGADOR 06.04', 21, '2001-02-02', '2022-04-06', 'E'),
(12, 'CLIENTE 06.04', 2, '2020-03-19', '2022-04-06', 'C'),
(13, 'ENTREGADOR 07.04', 21, '2001-02-02', '2022-04-07', 'E'),
(14, 'CLIENTE 07.04', 22, '2000-04-04', '2022-04-07', 'C'),
(15, 'FARMACEUTICO 07.04', 22, '2000-04-07', '2022-04-07', 'F'),
(16, 'MATEUS2', 21, '2001-02-02', '2022-04-13', 'E'),
(17, 'MATEUS CLIENTE', 21, '2001-02-02', '2022-04-13', 'C'),
(18, 'MATEUS3', 21, '2001-02-02', '2022-04-13', 'C'),
(19, 'TESTE HOJE', 22, '2000-02-15', '2022-04-13', 'C'),
(20, 'MATEUS1', 21, '2001-02-02', '2022-04-13', 'F'),
(21, 'MATEUS 1904', 21, '2001-02-02', '2022-04-19', 'F'),
(22, 'ENTREGAODR 1904', 1, '2021-02-10', '2022-04-19', 'E'),
(23, 'CLIENTE1ASA', 10, '2020-01-01', '2022-04-19', 'C'),
(24, '321', 2, '2020-02-01', '2022-04-20', 'E'),
(25, 'CLIENTE 20.04', 21, '2001-02-02', '2022-04-20', 'C'),
(26, '321', 21, '2001-02-02', '2022-04-20', 'F'),
(27, 'ENTREGADORALTERADO', 10, '2022-01-01', '2022-04-20', 'E'),
(28, 'MATEUS 25.04 ALTERADO', 21, '2001-02-02', '2022-04-25', 'E'),
(29, 'MATEUS CLIENTE 25.04', 21, '2001-02-02', '2022-04-25', 'C'),
(30, 'TESTE 2904', 21, '2001-02-02', '2022-04-29', 'E'),
(31, 'TESTE 2904 321654', 21, '2001-02-02', '2022-04-29', 'C');

-- --------------------------------------------------------

--
-- Estrutura da tabela `setor`
--

DROP TABLE IF EXISTS `setor`;
CREATE TABLE IF NOT EXISTS `setor` (
  `setorido` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` char(100) NOT NULL,
  PRIMARY KEY (`setorido`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `setor`
--

INSERT INTO `setor` (`setorido`, `descricao`) VALUES
(1, 'SELECIONAR'),
(2, 'FARMACIA'),
(3, 'COBRANCA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `situacao`
--

DROP TABLE IF EXISTS `situacao`;
CREATE TABLE IF NOT EXISTS `situacao` (
  `situacaoido` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` char(30) DEFAULT NULL,
  PRIMARY KEY (`situacaoido`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `situacao`
--

INSERT INTO `situacao` (`situacaoido`, `descricao`) VALUES
(2, 'ABERTO'),
(3, 'FINALIZADA'),
(4, 'VOLTOU');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipopagamento`
--

DROP TABLE IF EXISTS `tipopagamento`;
CREATE TABLE IF NOT EXISTS `tipopagamento` (
  `tipopagamentoido` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` char(100) NOT NULL,
  PRIMARY KEY (`tipopagamentoido`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipopagamento`
--

INSERT INTO `tipopagamento` (`tipopagamentoido`, `descricao`) VALUES
(1, 'SELECIONAR'),
(2, 'TOTAL'),
(3, 'PARCIAL');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
