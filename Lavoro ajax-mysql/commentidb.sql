-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 26, 2020 alle 23:37
-- Versione del server: 10.4.11-MariaDB
-- Versione PHP: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `commentidb`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `commenti`
--

CREATE TABLE `commenti` (
  `id_commento` int(11) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `commento` text NOT NULL,
  `id_risposta` int(11) NOT NULL,
  `commento_risposta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `commenti`
--

INSERT INTO `commenti` (`id_commento`, `nome`, `commento`, `id_risposta`, `commento_risposta`) VALUES
(1, 'Youness', 'Sem è molto intelligente', 0, 0),
(2, 'de grimani', 'questo commento è una prova', 0, 0),
(3, 'sem', 'non è vero', 1, 0),
(5, 'Ruben', 'ciao a tutti e buongiorno', 0, 0),
(7, 'Youness', 'Mio fratellino si chiama Imad', 0, 0),
(8, 'Fagiolino', 'Mi piace molto la pasta con i fagioli', 0, 0),
(9, 'Shrek', 'FIONA COSA FAI', 0, 0),
(10, 'mi sto divertendo molto ', 'caspiterina', 0, 0),
(11, 'dfas', 'asdf', 0, 0),
(13, 'Youness', 'si invece', 1, 0),
(16, 'Semmo', 'no invece', 1, 0),
(25, 'MArio', 'Prova', 2, 0),
(26, 'fdsafdas', 'adfsasdffdas', 0, 0),
(27, 'asdfasdfasdf', 'asdfasdfsdfa', 0, 0),
(28, 'dfasdf', 'adfsfdasfd', 2, 1),
(29, 'adsasdf', 'asdfasdfasdfasdfasdfasdfasdffasdfasd', 0, 0),
(30, 'adsasdf', 'sdfghjv', 0, 0),
(31, 'Silvio', 'pagherò in natura in futuro', 0, 0),
(32, 'Leonardo', 'Pagherò in natura in futuro', 0, 0),
(33, 'Youness', 'Ci conto', 32, 1),
(34, 'sdfkljlòdfasjlòasdfdf', 'asdfasdfasdfasdfasdfasdf', 26, 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `commenti`
--
ALTER TABLE `commenti`
  ADD PRIMARY KEY (`id_commento`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `commenti`
--
ALTER TABLE `commenti`
  MODIFY `id_commento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
