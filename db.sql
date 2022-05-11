CREATE DATABASE  IF NOT EXISTS `e-terroir` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `e-terroir`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: e-terroir
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `num_tele` varchar(255) DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `date_naissance` date NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c0r9atamxvbhjjvy5j8da1kam` (`email`),
  UNIQUE KEY `UK_b5hpapp4843xggr5rvtvmat4j` (`num_tele`),
  UNIQUE KEY `UK_92lovya5dg8fur25cps635skl` (`cin`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'anasboujra@email.com','$2a$10$/oArQ9FyKXG6ihqSK90.qeESe0wvPZHM4qC3afSaYYrLiGcW1uMyK','0604384177','Jk36429','1999-05-28','Boujra','Anas');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorie` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES (1,'alimentaire'),(2,'cosmetique');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `num_tele` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `date_naissance` date NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `panier_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bfgjs3fem0hmjhvih80158x29` (`email`),
  UNIQUE KEY `UK_8r4b34ntidw4qigx495rbfhfe` (`num_tele`),
  UNIQUE KEY `UK_o134qppytog4wbuhqpiuf0ued` (`cin`),
  KEY `FK890yab5k4krp0el6jvbm92rk8` (`panier_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'client1@email.com','$2a$10$UKjLt9elA24UovE7wzhfOelitnBnUEj5Cqr7pNMa.jYLHQlmapeYi','0628190382','Agadir Tikiouine bloc D','K28271','1988-12-01','nom1','prenom1',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commande` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `prix_total` bigint DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK79q1nginx2k3m83vi3bt3rlon` (`client_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande`
--

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cooperative`
--

DROP TABLE IF EXISTS `cooperative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cooperative` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `num_tele` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `secteur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dpnlrvqng44efodlvxdkjmos0` (`email`),
  UNIQUE KEY `UK_khsw8yria4gt1pse6nwiksxuo` (`num_tele`),
  UNIQUE KEY `UK_615tke83gps9jnrbtd2lmci78` (`nom`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooperative`
--

LOCK TABLES `cooperative` WRITE;
/*!40000 ALTER TABLE `cooperative` DISABLE KEYS */;
INSERT INTO `cooperative` VALUES (1,'Agdal@email.com','$2a$10$AeUrWSRdMjOj/AWZoSMUtexZ9TJKD9azeKdgHM8IxDQaiYt8m8EUu','0527194677','Agdal','Souss','cosmetique'),(2,'Tarjijt@email.com','$2a$10$eiJdYfHg4xU/L8l4GjMwF.bvx2xS/5/I7TrTiARUM88XXmhAcBzXu','0528782201','Tarjijt','Tafilalt','alimentaire');
/*!40000 ALTER TABLE `cooperative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ligne_commande`
--

DROP TABLE IF EXISTS `ligne_commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ligne_commande` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `prix_unitaire` double NOT NULL,
  `quantite` double NOT NULL,
  `commande_id` bigint DEFAULT NULL,
  `panier_id` bigint DEFAULT NULL,
  `produit_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaff2bjyreiuyi723relg10spm` (`commande_id`),
  KEY `FKhgk3sh7l2s5s153h2o952q0x2` (`panier_id`),
  KEY `FK5ykb96p8me6913jyiwbe8nyj5` (`produit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ligne_commande`
--

LOCK TABLES `ligne_commande` WRITE;
/*!40000 ALTER TABLE `ligne_commande` DISABLE KEYS */;
/*!40000 ALTER TABLE `ligne_commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matiere_premiere`
--

DROP TABLE IF EXISTS `matiere_premiere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matiere_premiere` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_id72db4wnh72iqapsoteobppd` (`nom`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matiere_premiere`
--

LOCK TABLES `matiere_premiere` WRITE;
/*!40000 ALTER TABLE `matiere_premiere` DISABLE KEYS */;
INSERT INTO `matiere_premiere` VALUES (1,'argan'),(2,'olive'),(3,'amande'),(4,'arachide'),(5,'miel');
/*!40000 ALTER TABLE `matiere_premiere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `origine`
--

DROP TABLE IF EXISTS `origine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `origine` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e096rhs51j2k0r8jh2h1oe2g2` (`nom`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `origine`
--

LOCK TABLES `origine` WRITE;
/*!40000 ALTER TABLE `origine` DISABLE KEYS */;
INSERT INTO `origine` VALUES (1,'Tiznit'),(2,'Ihahane'),(3,'Taroudant'),(4,'imintanout'),(5,'Agadir');
/*!40000 ALTER TABLE `origine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `panier`
--

DROP TABLE IF EXISTS `panier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `panier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjor7gwisog2wxwsgebfyuxqrv` (`client_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `panier`
--

LOCK TABLES `panier` WRITE;
/*!40000 ALTER TABLE `panier` DISABLE KEYS */;
/*!40000 ALTER TABLE `panier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prix` double NOT NULL,
  `quantite` double NOT NULL,
  `unite` varchar(255) DEFAULT NULL,
  `categorie_id` bigint DEFAULT NULL,
  `cooperative_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK52xhp55kbbl6u4rbluxm3g9hw` (`categorie_id`),
  KEY `FKghn62mhhmqurlji11oj55eq9v` (`cooperative_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produit`
--

LOCK TABLES `produit` WRITE;
/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
INSERT INTO `produit` VALUES (1,'naturel','Amlo',200,150,'1Kg',1,1),(2,'original','Huile d\'olive',80,250,'1L',1,1),(3,'organique','Huile d\'argan cosmetique',150,140,'100ml',2,2),(4,'naturel','Miel',700,60,'1Kg',1,2),(5,'naturel','Amlo',90,100,'1Kg',1,2);
/*!40000 ALTER TABLE `produit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produit_matiere_asso`
--

DROP TABLE IF EXISTS `produit_matiere_asso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produit_matiere_asso` (
  `produit_id` bigint NOT NULL,
  `matiere_premiere_id` bigint NOT NULL,
  `origine_id` bigint DEFAULT NULL,
  PRIMARY KEY (`matiere_premiere_id`,`produit_id`),
  KEY `FKl2a0oqepmhat5uklgl0dwc52r` (`origine_id`),
  KEY `FKs2jwrhjvff2aia7ke6hd9fx9g` (`produit_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produit_matiere_asso`
--

LOCK TABLES `produit_matiere_asso` WRITE;
/*!40000 ALTER TABLE `produit_matiere_asso` DISABLE KEYS */;
INSERT INTO `produit_matiere_asso` VALUES (1,1,2),(1,3,4),(2,2,4),(3,1,2),(4,5,3),(5,4,5),(5,1,2);
/*!40000 ALTER TABLE `produit_matiere_asso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-11  4:54:13
