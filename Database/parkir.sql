-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 24, 2020 at 02:44 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parkir`
--

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `idPengguna` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `subscription` varchar(255) NOT NULL DEFAULT 'easy'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`idPengguna`, `nama`, `email`, `alamat`, `password`, `subscription`) VALUES
(1, 'faisal', 'faisaall@muzakii.com', 'cikoneng', '3f0371e01d75ce9b788cac9beb4ff92a', ''),
(8, 'faisal', 'faisal@muzaki.com', 'cikoneng', '3f0371e01d75ce9b788cac9beb4ff92a', ''),
(11, 'Walim', 'fadil@fadil.com', 'Bogor', '27622e6109d0abc1c6c6ff2b32a2cc82', ''),
(12, 'Walim', 'walim12@walim.co.id', 'Bekasi', '462376e6510f1006cee7bad8a834dcbe', 'easy'),
(13, 'Faisal', 'faisal@faisal.com', 'Bandung', 'a474e17de5a2352cb6654e59adc4f4fe', 'easy'),
(14, 'Dimas', 'dimas@dimas.com', 'Cikoneng', '166e3e7a609da761fd76e4f64f7323a6', 'easy');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`idPengguna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `idPengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
