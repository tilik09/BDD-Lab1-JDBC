-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: bdd__lab1jdbc
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `idAddress` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_idCustomer` int(11) NOT NULL,
  `Address` varchar(70) NOT NULL,
  `City` varchar(45) NOT NULL,
  PRIMARY KEY (`idAddress`),
  KEY `fk_Address_Customer1_idx` (`Customer_idCustomer`),
  CONSTRAINT `fk_Address_Customer1` FOREIGN KEY (`Customer_idCustomer`) REFERENCES `customer` (`idcustomer`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,1,'Jepilor 36 Bl. A34','Brasov'),(2,1,'Ionescu Crum Nr.1','Brasov'),(3,2,'Ionescu Crum Nr.1','Brasov'),(4,3,'Ionescu Crum Nr.1','Brasov'),(5,4,'Ionescu Crum Nr.1','Brasov'),(6,1,'Aleea Uzineri Nr. 7','Zarnesti'),(7,2,'Branduselor Nr. 20','Brasov'),(8,2,'Lalelelor Nr. 4','Bacau'),(9,3,'Saturn Nr. 5','Bucuresti'),(10,1,'Aleea Uzinei Nr. 7','Rasnov'),(11,5,'Florilor Nr. 15','Cristian');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `idCustomer` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idCustomer`),
  UNIQUE KEY `idCustomer_UNIQUE` (`idCustomer`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  UNIQUE KEY `Phone_UNIQUE` (`Phone`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Gavril Andrei','gavril.andrei1447@gmail.com','0742869869'),(2,'Roman Bogdan','rbogdan@yahoo.com','0742888888'),(3,'Costea Andreea','andreacostea@gmail.com','0742888889'),(4,'Radu Ciubotaru','radu91@gmail.com','0745555555'),(5,'Andrei Test','andrei.test@gmail.com','0752869869');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order` (
  `idOrder` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_idCustomer` int(11) DEFAULT NULL,
  `Address_idAddress` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Status_idStatus` int(11) NOT NULL,
  PRIMARY KEY (`idOrder`),
  KEY `fk_Order_Address1_idx` (`Address_idAddress`) /*!80000 INVISIBLE */,
  KEY `fk_Order_Customer1_idx` (`Customer_idCustomer`),
  KEY `fk_Order_Status1_idx` (`Status_idStatus`),
  CONSTRAINT `fk_Order_Address1` FOREIGN KEY (`Address_idAddress`) REFERENCES `address` (`idaddress`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_Order_Customer1` FOREIGN KEY (`Customer_idCustomer`) REFERENCES `customer` (`idcustomer`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_Order_Status1` FOREIGN KEY (`Status_idStatus`) REFERENCES `status` (`idstatus`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (3,1,1,'2020-03-20',2),(4,2,1,'2020-03-20',1),(5,2,2,'2021-03-20',2),(6,2,3,'2023-03-20',3),(7,3,1,'2020-03-20',1),(14,5,11,'2002-04-20',5),(15,5,11,'2002-04-20',5),(19,4,5,'2002-04-20',5),(20,5,11,'2002-04-20',5);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order item`
--

DROP TABLE IF EXISTS `order item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order item` (
  `idOrderItem` int(11) NOT NULL AUTO_INCREMENT,
  `Product_idProduct` int(11) NOT NULL,
  `Order_idOrder` int(11) NOT NULL,
  `Quantity` varchar(45) NOT NULL,
  PRIMARY KEY (`idOrderItem`),
  KEY `fk_Order Item_Order1_idx` (`Order_idOrder`),
  KEY `fk_Order Item_Product1_idx` (`Product_idProduct`),
  CONSTRAINT `fk_Order Item_Order1` FOREIGN KEY (`Order_idOrder`) REFERENCES `order` (`idorder`),
  CONSTRAINT `fk_Order Item_Product1` FOREIGN KEY (`Product_idProduct`) REFERENCES `product` (`idproduct`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order item`
--

LOCK TABLES `order item` WRITE;
/*!40000 ALTER TABLE `order item` DISABLE KEYS */;
INSERT INTO `order item` VALUES (1,3,3,'1'),(2,1,4,'1'),(3,2,4,'1'),(4,3,4,'1'),(5,11,3,'1'),(6,12,3,'1'),(7,13,3,'1'),(8,14,3,'1'),(9,5,5,'5'),(10,6,5,'5'),(11,7,6,'1'),(12,7,7,'2'),(13,1,15,'1'),(14,1,14,'1'),(15,1,19,'3'),(16,2,19,'5'),(17,6,20,'2'),(18,11,20,'1'),(19,14,20,'1');
/*!40000 ALTER TABLE `order item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `idProduct` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Price` double NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Supplier_idSupplier` int(11) NOT NULL,
  PRIMARY KEY (`idProduct`),
  UNIQUE KEY `idProduct_UNIQUE` (`idProduct`),
  KEY `fk_Product_Supplier1_idx` (`Supplier_idSupplier`),
  CONSTRAINT `fk_Product_Supplier1` FOREIGN KEY (`Supplier_idSupplier`) REFERENCES `supplier` (`idsupplier`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Xbox One X',300,40,1),(2,'Play Station 4',350,40,2),(3,'CPU Ryzen 7 1800x',260,20,3),(4,'CPU Ryzen 7 1700x',230,25,3),(5,'CPU Ryzen 7 1700',200,30,3),(6,'CPU Ryzen 5 1600x',168,30,3),(7,'CPU Intel I7 7700k',180,10,4),(11,'Carcasa NZXT H440',600,20,5),(12,'PCU CX750M',550,20,6),(13,'Memorie RAM Vengeance 2x8GB',494,20,6),(14,'Placa de baza Gigabyte GA-AX370M-Gaming 3',600,20,7);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `status` (
  `idStatus` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(20) NOT NULL,
  PRIMARY KEY (`idStatus`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Awaiting'),(2,'Delivered'),(3,'Cancelled'),(4,'Returned'),(5,'Opened');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `supplier` (
  `idSupplier` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  PRIMARY KEY (`idSupplier`),
  UNIQUE KEY `idSupplier_UNIQUE` (`idSupplier`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Microsoft','800566'),(2,'Sony','800588'),(3,'Amd','238846'),(4,'Intel','238666'),(5,'NZXT','232323'),(6,'Corsair','456666'),(7,'Gigabyte','455666'),(10,'MSI','8005666');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-03 11:55:05
