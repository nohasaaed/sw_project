-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 24, 2019 at 02:19 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `socialnetwork`
--

-- --------------------------------------------------------

--
-- Table structure for table `friendlist`
--

CREATE TABLE `friendlist` (
  `firstID` int(11) NOT NULL,
  `secondID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friendlist`
--

INSERT INTO `friendlist` (`firstID`, `secondID`) VALUES
(4, 5),
(4, 6),
(5, 4),
(5, 6),
(6, 4),
(6, 5);

-- --------------------------------------------------------

--
-- Table structure for table `friendrequest`
--

CREATE TABLE `friendrequest` (
  `sentID` int(11) NOT NULL,
  `reseveID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `birthDate` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `isPremium` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `gender`, `firstName`, `lastName`, `address`, `birthDate`, `email`, `password`, `isPremium`) VALUES
(4, '0', 'Kareema', 'Hussein', 'Alex', '27/06/1994', 'kareemahussein@gmail.com', '123456', 1),
(5, '0', 'Abdulrhman', 'Adel', 'Alex', '27/06/1994', 'hamza.aboo78@gmail.com', '123456', 1),
(6, '0', 'Abdo', 'Adel', 'Alex', '27/06/1994', 'abdulrhmanadel60@gmail.com', '123456', 1),
(9, 'Male', 'ahmed', 'ali', 'alex', '2/2/219', 'ahmed@gmail.com', '12345', 0),
(10, 'Female', 's', 's', 's', 's', 's', 's', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `friendlist`
--
ALTER TABLE `friendlist`
  ADD PRIMARY KEY (`firstID`,`secondID`);

--
-- Indexes for table `friendrequest`
--
ALTER TABLE `friendrequest`
  ADD PRIMARY KEY (`sentID`,`reseveID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
