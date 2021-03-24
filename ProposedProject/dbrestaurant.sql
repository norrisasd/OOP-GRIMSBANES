-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 24, 2021 at 05:30 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbrestaurant`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblreserve`
--

CREATE TABLE `tblreserve` (
  `reservation_id` int(11) NOT NULL,
  `date` varchar(20) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `guestsno` int(11) DEFAULT NULL,
  `tableno` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblreserve`
--

INSERT INTO `tblreserve` (`reservation_id`, `date`, `time`, `guestsno`, `tableno`, `user_id`) VALUES
(16, '2021-03-26', '14 PM', 4, 1, 2),
(17, '2021-03-26', '17 PM', 4, 2, 6),
(18, '2021-03-26', '16 PM', 2, 1, 9),
(19, '2021-03-26', '10 AM', 3, 1, 10),
(22, '2021-03-26', '16 PM', 2, 2, 1),
(23, '2021-03-26', '16 PM', 3, 3, 1),
(24, '2021-03-26', '16 PM', 4, 4, 1),
(25, '2021-03-26', '16 PM', 3, 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tblschedule`
--

CREATE TABLE `tblschedule` (
  `schedule_id` int(11) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `reserveesqty` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblschedule`
--

INSERT INTO `tblschedule` (`schedule_id`, `date`, `time`, `reserveesqty`) VALUES
(6, '2021-03-26', '17 PM', 1),
(7, '2021-03-26', '16 PM', 5),
(8, '2021-03-26', '15 PM', 0),
(9, '2021-03-26', '14 PM', 1),
(10, '2021-03-26', '13 PM', 0),
(11, '2021-03-26', '12 PM', 0),
(13, '2021-03-26', '10 AM', 1),
(18, '2021-03-26', '09 AM', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tblusers`
--

CREATE TABLE `tblusers` (
  `user_id` int(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `middlename` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `contact` int(11) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblusers`
--

INSERT INTO `tblusers` (`user_id`, `username`, `password`, `firstname`, `middlename`, `lastname`, `email`, `contact`, `isAdmin`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'admin', 'admin', 123, 1),
(2, 'user', 'user', 'user', 'user', 'user', 'user', 123456, 0),
(6, 'norris', 'norris', 'Norris', 'Gabaleo', 'Hipolito', 'norrishipolito@gmail.com', 451579100, 0),
(9, 'user123', 'user', 'users', 'users', 'users', 'user@gmail.com', 123456, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblreserve`
--
ALTER TABLE `tblreserve`
  ADD PRIMARY KEY (`reservation_id`);

--
-- Indexes for table `tblschedule`
--
ALTER TABLE `tblschedule`
  ADD PRIMARY KEY (`schedule_id`);

--
-- Indexes for table `tblusers`
--
ALTER TABLE `tblusers`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblreserve`
--
ALTER TABLE `tblreserve`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `tblschedule`
--
ALTER TABLE `tblschedule`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tblusers`
--
ALTER TABLE `tblusers`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
