-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 06, 2024 at 06:33 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `td1java`
--

-- --------------------------------------------------------

--
-- Table structure for table `banque`
--

CREATE TABLE `banque` (
  `banque_id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `Pays` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `banque`
--

INSERT INTO `banque` (`banque_id`, `nom`, `adresse`, `telephone`, `email`, `Pays`) VALUES
(1, 'Banque A', '123 Rue A, Ville', '0123456789', 'contact@banquea.com', ''),
(2, 'Banque B', '456 Rue B, Ville', '0987654321', 'info@banqueb.com', ''),
(3, 'Banque C', '789 Rue C, Ville', '0112233445', 'support@banquec.com', ''),
(4, 'CIH BANK', 'IIIIIIIIIIIIIII', '78787887878', 'CIH4@gmail.com', ''),
(5, 'SALAFBANK', 'Gueliz , Marrakech', '068854123', 'Salf8@gmail.com', ''),
(6, 'AbdouBank', 'California , Casablanca', '068854123', 'AbdouBankDigi@gmail.com', 'Maroc'),
(7, 'fecabank', 'california , CASABLANCA', '0688541253', 'fecaBank6@gmail.com', 'Maroc'),
(8, 'fecabank', '123 Rue B , France', '068874521', 'fecabankfr@gmail.com', 'France'),
(9, 'dfd', 'hjhj', 'jhhj', 'jhjhj', 'hj'),
(10, 'vcvc', 'bv', 'vbvb', 'vbv', 'bvb'),
(11, 'dfd', 'jkjk', 'kjkjk', 'kjkjk', 'jk');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `banque_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`client_id`, `nom`, `prenom`, `adresse`, `telephone`, `email`, `banque_id`) VALUES
(1, 'Ahmed', 'El Mansouri', '123 Avenue Hassan II, Casablanca', '0661122334', 'ahmed.elm@example.com', NULL),
(2, 'Fatima', 'Bennani', '456 Rue Mohamed V, Rabat', '0672233445', 'fatima.bennani@example.com', NULL),
(3, 'Omar', 'Bouhassoun', '789 Route de Fes, Marrakech', '0653344556', 'omar.bouh@example.com', NULL),
(4, 'Sara', 'El Fassi', '321 Boulevard Abdelmoumen, Casablanca', '0665566778', 'sara.elfassi@example.com', NULL),
(5, 'Youssef', 'Kabbaj', '654 Rue Ibn Sina, Agadir', '0656677889', 'youssef.kabbaj@example.com', NULL),
(6, 'Abdelghafour', 'Dadda', '160 Avenue Miller 55 , California', '0688554123', 'abdouex@gmail.com', NULL),
(7, 'Nada', 'Maliki', '184 , Hay el hassani , Casablanca', '0685412358', 'nadam@gmail.com', NULL),
(8, 'kjk', 'kjk', 'jkjkkj', 'kjk', 'ahmed.elm@example.com', NULL),
(9, 'kjk', 'kjk', 'jkjkkj', 'kjk', 'ahmed.elm@example.com', NULL),
(10, 'kjk', 'kjk', 'jkjkkj', 'kjk', 'ahmed.elm@example.com', NULL),
(11, 'HH', 'HH', 'HH', '058585412', 'fdfdfdf@gmail.com', NULL),
(12, 'abroud', 'oo', 'kk@gmail.com', '0685412236', 'gg@gmail.com', 2),
(13, 'mohssine', 'Alami', 'El jadida', '056874541', 'jhjhj@gmail.com', 2),
(14, 'Simo', 'Elalami', 'Eljadida ', '787787887', 'jj@gmail.com', 2),
(15, 'testcl', 'testcl', 'essaouira', '88787', 'fdf@gmail.com', 6),
(16, 'kjjkjk', 'jkjkjk', 'essaouira', '0898959', 'ggg@gmail.com', 6),
(17, 'Mohammed', 'Zniber', 'quartierEzzahra , chichaoua', '0685412365', 'mohammedz5@gmail.com', 3),
(18, 'Mounir', 'Errajraji', 'Douar Iziki , rue 3300', '066988745', 'mounirerr@gmail.com', 8),
(19, 'dffd', 'hjj', 'hjhj', 'jhj', 'jh', NULL),
(20, 'abdou', 'dadda', 'abdoud@gmail.com', '0644904533', 'abdoud@gmail.com', NULL),
(21, 'YO', 'yo', 'gfg', 'fdf', 'fdf', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `compte`
--

CREATE TABLE `compte` (
  `compte_id` int(11) NOT NULL,
  `num_compte` varchar(20) NOT NULL,
  `date_creation` datetime NOT NULL,
  `date_update` datetime DEFAULT NULL,
  `devise` varchar(10) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `banque_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `compte`
--

INSERT INTO `compte` (`compte_id`, `num_compte`, `date_creation`, `date_update`, `devise`, `client_id`, `banque_id`) VALUES
(2, 'CPT-002', '2024-10-02 00:00:00', '2024-10-05 00:00:00', 'USD', 1, 1),
(3, 'CPT-003', '2024-10-03 00:00:00', '2024-10-05 00:00:00', 'MAD', 2, 2),
(4, 'CPT-004', '1970-01-01 00:00:00', '1970-01-01 00:00:00', 'nn', 3, 5),
(5, 'CPT-005', '1970-01-01 00:00:00', '1970-01-01 00:00:00', 'OLP', 7, 3),
(6, 'CPT-006', '1970-01-01 00:00:00', '1970-01-01 00:00:00', 'USD', 13, 4),
(7, 'CPT-007', '1970-01-01 00:00:00', '1970-01-01 00:00:00', 'HH', 14, 3),
(8, 'CPT-008', '1970-01-01 00:00:00', '1970-01-01 00:00:00', 'bb', 15, 2),
(9, 'CPT-009', '1970-01-01 00:00:00', '1970-01-01 00:00:00', 'vcvv', 16, 6),
(10, 'CPT-010', '1970-01-01 00:00:00', '1970-01-01 00:00:00', 'EUR', 17, 7),
(11, 'CPT-011', '1970-01-01 00:00:00', '1970-01-01 00:00:00', 'DHS', 18, 8),
(12, 'CPT-012', '1970-01-01 00:00:00', '1970-01-01 00:00:00', 'YIN', 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `reference` varchar(50) NOT NULL,
  `timestamp` datetime NOT NULL,
  `type` varchar(20) NOT NULL,
  `compte_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `reference`, `timestamp`, `type`, `compte_id`) VALUES
(3, 'TRX-20241003', '2024-10-03 14:45:00', 'virchac', 2),
(4, 'TRX-20241004', '2024-10-04 16:00:00', 'vir1ulta', 2),
(7, 'REF20241006120618', '2024-10-06 12:06:18', 'Virchac', 3),
(8, 'REF-1728214295201', '2024-10-06 12:31:35', 'virin', 2),
(9, 'REF-1730637676997', '2024-11-03 13:41:16', 'virest', 2),
(10, 'REF-1730643575784-1470', '2024-11-03 15:19:35', 'VIREST', 2),
(18, 'REF-1730644768851-7801', '2024-11-03 15:39:28', 'VIREST', 3),
(19, 'REF-1730644809682-3729', '2024-11-03 15:40:09', 'VIREST', 6),
(20, 'REF-1730644922277-4521', '2024-11-03 15:42:02', 'VIRIN', 8),
(21, 'REF-1730836377082-6982', '2024-11-05 20:52:57', 'VIRMULTA', 4),
(22, 'REF-1730836379880-1586', '2024-11-05 20:52:59', 'VIRMULTA', 4),
(24, 'REF-1730837186782-9906', '2024-11-05 21:06:26', 'VIRMULTA', 9),
(25, 'REF-1730881139286-1250', '2024-11-06 09:18:59', 'VIREST', 10),
(26, 'REF-1730881549145-6642', '2024-11-06 09:25:49', 'VIRIN', 6),
(27, 'REF-1730881572352-4662', '2024-11-06 09:26:12', 'VIRMULTA', 8),
(28, 'REF-1730881600839-4838', '2024-11-06 09:26:40', 'VIRMULTA', 10),
(29, 'REF-1730896352314-5571', '2024-11-06 13:32:32', 'VIRMULTA', 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banque`
--
ALTER TABLE `banque`
  ADD PRIMARY KEY (`banque_id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`compte_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `banque_id` (`banque_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `compte_id` (`compte_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banque`
--
ALTER TABLE `banque`
  MODIFY `banque_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `compte`
--
ALTER TABLE `compte`
  MODIFY `compte_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `fk_banque_id` FOREIGN KEY (`banque_id`) REFERENCES `banque` (`banque_id`);

--
-- Constraints for table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `compte_ibfk_2` FOREIGN KEY (`banque_id`) REFERENCES `banque` (`banque_id`) ON DELETE SET NULL;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`compte_id`) REFERENCES `compte` (`compte_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
