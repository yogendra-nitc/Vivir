-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 24, 2018 at 07:52 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id5251988_db_vivir`
--

-- --------------------------------------------------------

--
-- Table structure for table `apartment`
--

CREATE TABLE `apartment` (
  `aptId` varchar(50) NOT NULL,
  `aptName` varchar(50) NOT NULL,
  `locality` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `ownerid` varchar(50) NOT NULL,
  `aptType` varchar(50) NOT NULL,
  `rentAmt` varchar(50) NOT NULL,
  `img` varchar(200) NOT NULL DEFAULT 'default.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `apartment`
--

INSERT INTO `apartment` (`aptId`, `aptName`, `locality`, `city`, `ownerid`, `aptType`, `rentAmt`, `img`) VALUES
('APT101V', 'dev kunj', 'devnagar', 'Varanasi', 'jack@gmail.com', '4', '10000', 'default.jpg'),
('APT102V', 'Dev Kunj', 'Nizamabad', 'Azamgarh', 'jack@gmail.com', '4', '12500', 'home1.jpg'),
('APT103V', 'dev kunj', 'devnagar', 'Varanasi', 'jack@gmail.com', '4', '10000', 'home2.jpg'),
('APT104V', 'dev', 'devnagar', 'Varanasi', 'ipek@gmail.com', '3', '7000', 'home3.jpg'),
('APT105V', 'Basant kunj', 'basant nagar', 'Varanasi', 'ipek@gmail.com', '4', '10000', 'home4.jpg'),
('APT107V', 'Doruk Villa', 'Kannoor', 'Calicut', 'ipek@gmail.com', '4', '12000', 'home5.jpg'),
('APT109V', 'Ashish Villa', 'Nizamabad', 'Azamgarh', 'jack@gmail.com', '4', '8500', 'home6.jpg'),
('APT110V', 'Sarvesh Villa', 'Chandeshar', 'Azamgarh', 'ipek@gmail.com', '4', '15000', 'home7.jpg'),
('APT111V', 'Abhi apartment', 'Tanda', 'Ambedkar Nagar', 'jack@gmail.com', '4', '6000', 'default.jpg'),
('APT112V', 'MBa apt', 'kattangle', 'calicut', 'jack@gmail.com', '4', '12000', 'default.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bid` int(50) NOT NULL,
  `aptId` varchar(50) NOT NULL,
  `tid` varchar(50) NOT NULL,
  `bdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bid`, `aptId`, `tid`, `bdate`) VALUES
(9, 'APT103V', 'edward@gmail.com', '2018-05-16');

-- --------------------------------------------------------

--
-- Table structure for table `complain`
--

CREATE TABLE `complain` (
  `cid` int(50) NOT NULL,
  `sid` varchar(50) NOT NULL,
  `rid` varchar(50) NOT NULL,
  `cdate` date NOT NULL,
  `content` varchar(1000) NOT NULL,
  `cstatus` varchar(10) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `complain`
--

INSERT INTO `complain` (`cid`, `sid`, `rid`, `cdate`, `content`, `cstatus`) VALUES
(1, 'edward@gmail.com', 'jack@gmail.com', '2018-05-02', 'My complain. is send .', '1'),
(2, 'jack@gmail.com', 'ricky@gmail.com', '2018-05-02', 'Your complain is resolved.', '1'),
(3, 'jack@gmail.com', 'edward@gmail.com', '2018-05-13', 'Nothing', '1'),
(4, 'avn@gmail.com', 'jack@gmail.com', '2018-05-14', 'hello sir please, there is lots of noise near my flat. please do something', '1'),
(5, 'avn@gmail.com', 'jack@gmail.com', '2018-05-14', 'hello sir please, there is lots of noise near my flat. please do something', '1'),
(6, 'james@gmail.com', 'james@gmail.com', '2018-05-14', 'My fan is not working ,please send an electrician.', '1'),
(7, 'edward@gmail.com', 'jack@gmail.com', '2018-05-20', 'jack', '1');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `nid` int(50) NOT NULL,
  `userTo` varchar(50) NOT NULL,
  `content` varchar(500) NOT NULL,
  `ndate` date NOT NULL,
  `nstatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`nid`, `userTo`, `content`, `ndate`, `nstatus`) VALUES
(47, 'jack@gmail.com', 'Yogendra Maurya has requested for booking your flat...', '2018-05-13', 1),
(48, 'edward@gmail.com', 'Your booking has been completed.Your apartment id = APT101V', '2018-05-13', 1),
(49, 'jack@gmail.com', 'You have a pending request for approval of rent.Apartment - APT101V', '2018-05-13', 1),
(50, 'edward@gmail.com', 'Your rent has been approved.Apartment id = APT101V', '2018-05-13', 1),
(51, 'jack@gmail.com', 'You have a pending LEAVING request for the apartment -APT101V', '2018-05-13', 1),
(52, 'edward@gmail.com', 'Your leaving request has been accepted.Now you are no more tenant of this apartment.Apartment id = APT101V', '2018-05-13', 1),
(53, 'jack@gmail.com', 'Yogendra Maurya has requested for booking your flat...', '2018-05-13', 1),
(54, 'edward@gmail.com', 'Your booking has been completed.Your apartment id = APT102V', '2018-05-13', 1),
(55, 'ipek@gmail.com', 'avnish agr has requested for booking your flat...', '2018-05-14', 0),
(56, 'jack@gmail.com', 'avnish has requested for booking your flat...', '2018-05-14', 1),
(57, 'jack@gmail.com', 'James Dev has requested for booking your flat...', '2018-05-14', 1),
(58, 'james@gmail.com', 'Your booking has been completed.Your apartment id = APT103V', '2018-05-14', 1),
(59, 'jack@gmail.com', 'You have a pending LEAVING request for the apartment -APT103V', '2018-05-14', 1),
(60, 'james@gmail.com', 'Your leaving request has been accepted.Now you are no more tenant of this apartment.Apartment id = APT103V', '2018-05-14', 1),
(61, 'jack@gmail.com', 'Yogendra Maurya has requested for booking your flat...', '2018-05-16', 1),
(62, 'edward@gmail.com', 'Your booking has been completed.Your apartment id = APT103V', '2018-05-16', 1),
(63, 'avnmnksnk@gmail.com', 'Your booking request for apartment APT102V has been cancelled.', '2018-05-16', 0),
(64, 'jack@gmail.com', 'Yogendra Maurya has requested for booking your flat...', '2018-05-19', 1),
(65, 'edward@gmail.com', 'Your booking has been completed.Your apartment id = APT102V', '2018-05-19', 1),
(66, 'jack@gmail.com', 'You have a pending LEAVING request for the apartment -APT102V', '2018-05-20', 1),
(67, 'edward@gmail.com', 'Your leaving request for apartment APT102V has been cancelled.', '2018-05-20', 1),
(68, 'jack@gmail.com', 'You have a pending LEAVING request for the apartment -APT102V', '2018-05-20', 1),
(69, 'edward@gmail.com', 'Your leaving request has been accepted.Now you are no more tenant of this apartment.Apartment id = APT102V', '2018-05-20', 1),
(70, 'jack@gmail.com', 'Yogendra Maurya has requested for booking your flat...', '2018-05-22', 1),
(71, 'ipek@gmail.com', 'Yogendra Maurya has requested for booking your flat...', '2018-05-23', 0),
(72, 'ipek@gmail.com', 'Yogendra Maurya has requested for booking your flat...', '2018-05-23', 0),
(73, 'ipek@gmail.com', 'Yogendra Maurya has requested for booking your flat...', '2018-05-23', 0);

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE `owner` (
  `uid` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `rdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`uid`, `email`, `rdate`) VALUES
('vdavido', 'david@gmail.com', '2018-05-19'),
('vipeko', 'ipek@gmail.com', '2018-04-13'),
('vjacko', 'jack@gmail.com', '2018-04-11');

-- --------------------------------------------------------

--
-- Table structure for table `rent`
--

CREATE TABLE `rent` (
  `rentId` int(50) NOT NULL,
  `tid` varchar(50) NOT NULL,
  `aptId` varchar(50) NOT NULL,
  `amtPaid` varchar(50) NOT NULL,
  `total` varchar(50) NOT NULL,
  `pdate` date NOT NULL,
  `approved` varchar(50) NOT NULL DEFAULT 'no'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rent`
--

INSERT INTO `rent` (`rentId`, `tid`, `aptId`, `amtPaid`, `total`, `pdate`, `approved`) VALUES
(14, 'edward@gmail.com', 'APT103V', '0', '0', '2018-05-16', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `rid` int(50) NOT NULL,
  `tid` varchar(50) NOT NULL,
  `ownerid` varchar(50) NOT NULL,
  `rtype` varchar(50) NOT NULL,
  `aptId` varchar(50) NOT NULL,
  `rdate` date NOT NULL DEFAULT '0000-00-00',
  `rstatus` varchar(50) NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`rid`, `tid`, `ownerid`, `rtype`, `aptId`, `rdate`, `rstatus`) VALUES
(38, 'avn@gmail.com', 'ipek@gmail.com', 'booking', 'APT107V', '2018-05-14', 'pending'),
(46, 'edward@gmail.com', 'jack@gmail.com', 'booking', 'APT102V', '2018-05-22', 'pending'),
(47, 'edward@gmail.com', 'ipek@gmail.com', 'booking', 'APT110V', '2018-05-23', 'pending'),
(48, 'edward@gmail.com', 'ipek@gmail.com', 'booking', 'APT105V', '2018-05-23', 'pending'),
(49, 'edward@gmail.com', 'ipek@gmail.com', 'booking', 'APT107V', '2018-05-23', 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `tenant`
--

CREATE TABLE `tenant` (
  `uid` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `rdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tenant`
--

INSERT INTO `tenant` (`uid`, `email`, `rdate`) VALUES
('varsht', 'arsh@gmail.com', '2018-05-12'),
('vavnmnksnkt', 'avnmnksnk@gmail.com', '2018-05-14'),
('vavnt', 'avn@gmail.com', '2018-05-14'),
('vdevesht', 'devesh@gmail.com', '2018-04-23'),
('vdevt', 'dev@gmail.com', '2018-05-12'),
('vdinesht', 'dinesh@gmail.com', '2018-04-07'),
('vedwardt', 'edward@gmail.com', '2018-04-11'),
('vjamest', 'james@gmail.com', '2018-05-14'),
('vmanushit', 'manushi@gmail.com', '2018-04-11'),
('vmanut', 'ashish@gmail.com', '2018-04-11'),
('vrahult', 'rahul@gmail.com', '2018-04-23'),
('vrickyt', 'ricky@gmail.com', '2018-04-11'),
('vutkarsht', 'utkarsh@gmail.com', '2018-04-24');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `userType` varchar(50) NOT NULL,
  `image` varchar(200) DEFAULT 'DefaultUserImage.png',
  `city` varchar(50) DEFAULT 'N/A',
  `state` varchar(50) NOT NULL DEFAULT 'N/A',
  `contact` varchar(15) NOT NULL DEFAULT 'N/A',
  `regDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`email`, `password`, `name`, `userType`, `image`, `city`, `state`, `contact`, `regDate`) VALUES
('', '', ' ', 'tenant', 'DefaultUserImage.png', 'N/A', 'N/A', 'N/A', '2018-05-23'),
('arsh@gmail.com', 'yogendra', 'Mohammad Arsh', 'tenant', 'abc.jpg', 'Calicut', 'Kerala', '9876543210', '2018-05-05'),
('ashish@gmail.com', 'ashish', 'Manu Bhaker', 'tenant', 'DefaultUserImage.png', 'N/A', 'N/A', '', '0000-00-00'),
('avn@gmail.com', 'avn', 'avnish agr', 'tenant', 'DefaultUserImage.png', 'calicut', 'kerala', '9876543210', '2018-05-14'),
('avnmnksnk@gmail.com', 'avnmnksnk', 'avnish', 'tenant', 'DefaultUserImage.png', 'varanasi', 'up', '8574778342', '2018-05-14'),
('david@gmail.com', 'david', 'David Malan', 'owner', 'DefaultUserImage.png', 'N/A', 'N/A', 'N/A', '2018-05-19'),
('dev@gmail.com', 'dev', 'devansh maurya', 'tenant', 'DefaultUserImage.png', 'N/A', 'N/A', 'N/A', '2018-05-12'),
('devesh@gmail.com', 'devesha', 'Devesh Singh', 'tenant', 'DefaultUserImage.png', 'Lucknow', 'UP', '8756986535', '0000-00-00'),
('dinesh@gmail.com', 'dinesh', 'DineshMaurya', 'tenant', 'DefaultUserImage.png', 'N/A', '', '', '0000-00-00'),
('edward@gmail.com', 'edward', 'Yogendra Maurya', 'tenant', 'DefaultUserImage.png', 'Calicut', 'Uttar Pradesh', '9876543210', '0000-00-00'),
('ipek@gmail.com', 'ipek', 'Ipek Ujun', 'owner', 'DefaultUserImage.png', 'N/A', 'N/A', 'N/A', '0000-00-00'),
('jack@gmail.com', 'jack', 'Doruk Sarsilmaz', 'owner', 'DefaultUserImage.png', 'Azamgarh', 'Uttar Pradesh', '9876543210', '2018-05-06'),
('james@gmail.com', 'james', 'James Dev', 'tenant', 'DefaultUserImage.png', 'Calicut', 'Kerala', '9876543210', '2018-05-14'),
('manushi@gmail.com', 'manushi', 'Manushi Chhillar', 'tenant', 'DefaultUserImage.png', 'N/A', 'N/A', '', '0000-00-00'),
('rahul@gmail.com', 'rahul', 'rahul maurya', 'tenant', 'DefaultUserImage.png', 'N/A', 'N/A', 'N/A', '0000-00-00'),
('ricky@gmail.com', 'ricky', 'Ricky Maurya', 'tenant', 'DefaultUserImage.png', 'N/A', 'N/A', 'N/A', '0000-00-00'),
('utkarsh@gmail.com', 'utkarsh', 'Utkarsh Maurya', 'tenant', 'DefaultUserImage.png', 'N/A', 'N/A', 'N/A', '0000-00-00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apartment`
--
ALTER TABLE `apartment`
  ADD PRIMARY KEY (`aptId`),
  ADD KEY `ownerid` (`ownerid`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bid`),
  ADD KEY `aptId` (`aptId`),
  ADD KEY `tid` (`tid`);

--
-- Indexes for table `complain`
--
ALTER TABLE `complain`
  ADD PRIMARY KEY (`cid`),
  ADD KEY `sid` (`sid`),
  ADD KEY `rid` (`rid`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`nid`),
  ADD KEY `userTo` (`userTo`);

--
-- Indexes for table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`rentId`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`rid`),
  ADD KEY `aptId` (`aptId`),
  ADD KEY `request_ibfk_1` (`tid`),
  ADD KEY `request_ibfk_2` (`ownerid`);

--
-- Indexes for table `tenant`
--
ALTER TABLE `tenant`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `complain`
--
ALTER TABLE `complain`
  MODIFY `cid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `nid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `rent`
--
ALTER TABLE `rent`
  MODIFY `rentId` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `rid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `apartment`
--
ALTER TABLE `apartment`
  ADD CONSTRAINT `apartment_ibfk_1` FOREIGN KEY (`ownerid`) REFERENCES `owner` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`aptId`) REFERENCES `apartment` (`aptId`),
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `tenant` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `complain`
--
ALTER TABLE `complain`
  ADD CONSTRAINT `complain_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `complain_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`userTo`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `owner`
--
ALTER TABLE `owner`
  ADD CONSTRAINT `owner_ibfk_1` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`tid`) REFERENCES `tenant` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`ownerid`) REFERENCES `owner` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_3` FOREIGN KEY (`aptId`) REFERENCES `apartment` (`aptId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tenant`
--
ALTER TABLE `tenant`
  ADD CONSTRAINT `tenant_ibfk_1` FOREIGN KEY (`email`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
