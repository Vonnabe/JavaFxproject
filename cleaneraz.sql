-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 09, 2023 at 03:36 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cleaneraz`
--

-- --------------------------------------------------------

--
-- Table structure for table `checkers`
--

CREATE TABLE `checkers` (
  `checkersid` int(11) NOT NULL,
  `completed` tinyint(1) NOT NULL,
  `ordername` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `places`
--

CREATE TABLE `places` (
  `placesid` int(11) NOT NULL,
  `BuildingA` tinyint(1) NOT NULL,
  `BuildingB` tinyint(1) NOT NULL,
  `BuildingC` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `taskid` int(11) NOT NULL,
  `tasktype` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`taskid`, `tasktype`) VALUES
(1, 'Mopping'),
(2, 'Vaccuming'),
(3, 'Dusting'),
(4, 'Window Cleaning'),
(5, 'Spraying'),
(6, 'Checking');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userid` int(11) NOT NULL,
  `name` text NOT NULL,
  `lastname` text NOT NULL,
  `password` text NOT NULL,
  `usertype` int(11) NOT NULL,
  `position` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userid`, `name`, `lastname`, `password`, `usertype`, `position`) VALUES
(1, 'Pavlos', 'Koullolli', '1', 0, 'Cleaner'),
(2, 'Maria', 'Dadaki', '2', 0, 'Cleaner'),
(3, 'Kostas', 'Papakis', '3', 0, 'Assistant Cleaner'),
(4, 'admin', 'admin', '00', 1, 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `workertable`
--

CREATE TABLE `workertable` (
  `nameId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `position` varchar(50) NOT NULL,
  `task` varchar(50) NOT NULL,
  `area` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `workertable`
--

INSERT INTO `workertable` (`nameId`, `name`, `position`, `task`, `area`) VALUES
(0, 'Pavlos', 'Cleaner', 'Mopping', 'Building A'),
(0, 'George', 'Cleaner', 'Mopping', 'Building B'),
(0, 'Wiktoria', 'Asssitant Cleaner', 'Dusting', 'Building A'),
(0, 'Maria', 'Asssitant Cleaner', 'Dusting', 'Building B');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `checkers`
--
ALTER TABLE `checkers`
  ADD PRIMARY KEY (`checkersid`);

--
-- Indexes for table `places`
--
ALTER TABLE `places`
  ADD PRIMARY KEY (`placesid`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`taskid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
