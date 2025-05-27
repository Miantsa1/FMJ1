-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 15, 2025 at 11:25 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `FMJ`
--

-- --------------------------------------------------------

--
-- Table structure for table `Classement`
--

CREATE TABLE `Classement` (
  `id_classement` int(11) NOT NULL,
  `classement` int(11) DEFAULT NULL,
  `nom_judoka` varchar(100) DEFAULT NULL,
  `prenom_judoka` varchar(200) DEFAULT NULL,
  `club` varchar(20) DEFAULT NULL,
  `categorie_age` varchar(20) DEFAULT NULL,
  `Poids` varchar(20) DEFAULT NULL,
  `rang` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `Type_competition` varchar(100) DEFAULT NULL,
  `Date_competition` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Combattant`
--

CREATE TABLE `Combattant` (
  `id_combattant` int(11) NOT NULL,
  `nom_combattant` varchar(100) DEFAULT NULL,
  `prenom_combattant` varchar(200) DEFAULT NULL,
  `nom_club` varchar(50) DEFAULT NULL,
  `Date_de_naissance` varchar(50) DEFAULT NULL,
  `categorie_age` varchar(20) DEFAULT NULL,
  `Poids` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Classement`
--
ALTER TABLE `Classement`
  ADD PRIMARY KEY (`id_classement`);

--
-- Indexes for table `Combattant`
--
ALTER TABLE `Combattant`
  ADD PRIMARY KEY (`id_combattant`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Classement`
--
ALTER TABLE `Classement`
  MODIFY `id_classement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `Combattant`
--
ALTER TABLE `Combattant`
  MODIFY `id_combattant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
