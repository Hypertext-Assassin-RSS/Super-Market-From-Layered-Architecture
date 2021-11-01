drop database if exists supermarket;
create database if not exists supermarket;
use supermarket;
CREATE TABLE `Customer` (
  `id` varchar(30) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `Supplier` (
  `id` varchar(30) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `Item` (
  `code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `qtyOnHand` int(10) DEFAULT NULL,
  `unitPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`code`)
);
CREATE TABLE `Orders` (
  `oid` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  `customerID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `customerID` (`customerID`),
  CONSTRAINT `Orders` FOREIGN KEY (`customerID`) REFERENCES `Customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `OrderDetails` (
  `oid` varchar(255) NOT NULL,
  `itemCode` varchar(255) NOT NULL,
  `qty` int(10) DEFAULT NULL,
  `unitPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`oid`,`itemCode`),
  KEY `itemCode` (`itemCode`),
  CONSTRAINT `OrderDetails` FOREIGN KEY (`oid`) REFERENCES `Orders` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `OrderDetails` FOREIGN KEY (`itemCode`) REFERENCES `Item` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
);