-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: 127.0.0.1
-- Χρόνος δημιουργίας: 02 Μάη 2023 στις 12:22:55
-- Έκδοση διακομιστή: 10.4.25-MariaDB
-- Έκδοση PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `cleaneraz`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `checkers`
--

CREATE TABLE `checkers` (
  `checkersid` int(11) NOT NULL,
  `completed` tinyint(1) NOT NULL,
  `ordername` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `places`
--

CREATE TABLE `places` (
  `placesid` int(11) NOT NULL,
  `BuildingA` tinyint(1) NOT NULL,
  `BuildingB` tinyint(1) NOT NULL,
  `BuildingC` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `tasks`
--

CREATE TABLE `tasks` (
  `taskid` int(11) NOT NULL,
  `tasktype` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `tasks`
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
-- Δομή πίνακα για τον πίνακα `users`
--

CREATE TABLE `users` (
  `userid` int(11) NOT NULL,
  `name` text NOT NULL,
  `lastname` text NOT NULL,
  `password` text NOT NULL,
  `usertype` int(11) NOT NULL,
  `position` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `users`
--

INSERT INTO `users` (`userid`, `name`, `lastname`, `password`, `usertype`, `position`) VALUES
(1, 'Pavlos', 'Koullolli', '1', 0, 'Cleaner'),
(2, 'Maria', 'Dadaki', '2', 0, 'Cleaner'),
(3, 'Kostas', 'Papakis', '3', 0, 'Assistant Cleaner'),
(4, 'admin', 'admin', '00', 1, 'admin');

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `checkers`
--
ALTER TABLE `checkers`
  ADD PRIMARY KEY (`checkersid`);

--
-- Ευρετήρια για πίνακα `places`
--
ALTER TABLE `places`
  ADD PRIMARY KEY (`placesid`);

--
-- Ευρετήρια για πίνακα `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`taskid`);

--
-- Ευρετήρια για πίνακα `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
