-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: dbogg
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `a`
--

DROP TABLE IF EXISTS `a`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `a` (
  `cf` varchar(16) NOT NULL,
  `nome` varchar(20) DEFAULT NULL,
  `cognome` varchar(20) DEFAULT NULL,
  `luogodinascita` varchar(30) DEFAULT NULL,
  `indirizzodiresidenza` varchar(30) DEFAULT NULL,
  `telefonofisso` decimal(12,0) DEFAULT NULL,
  `telefonomobile` decimal(12,0) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `dataprimaiscrizione` date DEFAULT NULL,
  `professione` varchar(30) DEFAULT NULL,
  `eventualespecializzazione` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cf`),
  CONSTRAINT `a_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a`
--

LOCK TABLES `a` WRITE;
/*!40000 ALTER TABLE `a` DISABLE KEYS */;
/*!40000 ALTER TABLE `a` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `cf` varchar(16) NOT NULL,
  PRIMARY KEY (`cf`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attrezzo`
--

DROP TABLE IF EXISTS `attrezzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attrezzo` (
  `nvolowebattrezzo` varchar(10) NOT NULL,
  `nomeattrezzo` varchar(30) DEFAULT NULL,
  `targa` varchar(20) DEFAULT NULL,
  `locazione` varchar(30) DEFAULT NULL,
  `stato` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`nvolowebattrezzo`),
  UNIQUE KEY `targa` (`targa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attrezzo`
--

LOCK TABLES `attrezzo` WRITE;
/*!40000 ALTER TABLE `attrezzo` DISABLE KEYS */;
/*!40000 ALTER TABLE `attrezzo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b`
--

DROP TABLE IF EXISTS `b`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `b` (
  `cf` varchar(16) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `datascadenza` date NOT NULL,
  `dataacquisizione` date DEFAULT NULL,
  `entedirilascio` varchar(30) DEFAULT NULL,
  `n_documento` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cf`,`nome`,`datascadenza`),
  CONSTRAINT `b_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b`
--

LOCK TABLES `b` WRITE;
/*!40000 ALTER TABLE `b` DISABLE KEYS */;
/*!40000 ALTER TABLE `b` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `c`
--

DROP TABLE IF EXISTS `c`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `c` (
  `cf` varchar(16) NOT NULL,
  `nomedatore` varchar(16) DEFAULT NULL,
  `telefono` decimal(12,0) DEFAULT NULL,
  `faxdatore` decimal(12,0) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `numero_codice_postale` varchar(30) DEFAULT NULL,
  `iban` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cf`),
  CONSTRAINT `c_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c`
--

LOCK TABLES `c` WRITE;
/*!40000 ALTER TABLE `c` DISABLE KEYS */;
/*!40000 ALTER TABLE `c` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calderonecandidati`
--

DROP TABLE IF EXISTS `calderonecandidati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calderonecandidati` (
  `cf` varchar(16) NOT NULL,
  `flag` decimal(1,0) DEFAULT NULL,
  PRIMARY KEY (`cf`),
  CONSTRAINT `calderonecandidati_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calderonecandidati`
--

LOCK TABLES `calderonecandidati` WRITE;
/*!40000 ALTER TABLE `calderonecandidati` DISABLE KEYS */;
/*!40000 ALTER TABLE `calderonecandidati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compiti`
--

DROP TABLE IF EXISTS `compiti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compiti` (
  `cf` varchar(16) NOT NULL,
  `archivista` tinyint(1) DEFAULT NULL,
  `magazzino` tinyint(1) DEFAULT NULL,
  `comVolontari` tinyint(1) DEFAULT NULL,
  `formVolontari` tinyint(1) DEFAULT NULL,
  `parcoMezzi` tinyint(1) DEFAULT NULL,
  `parcoattrezzi` tinyint(1) DEFAULT NULL,
  `calendario` tinyint(1) DEFAULT NULL,
  `refinformatico` tinyint(1) DEFAULT NULL,
  `comunicazionigiunta` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compiti`
--

LOCK TABLES `compiti` WRITE;
/*!40000 ALTER TABLE `compiti` DISABLE KEYS */;
/*!40000 ALTER TABLE `compiti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d`
--

DROP TABLE IF EXISTS `d`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `d` (
  `cf` varchar(16) DEFAULT NULL,
  `grupposang` varchar(5) DEFAULT NULL,
  `tagliatesta` decimal(2,0) DEFAULT NULL,
  `tagliabusto` varchar(6) DEFAULT NULL,
  `tagliagiacca` decimal(2,0) DEFAULT NULL,
  `tagliamano` decimal(2,0) DEFAULT NULL,
  `tagliapantaloni` decimal(2,0) DEFAULT NULL,
  `tagliapantaloni2` varchar(6) DEFAULT NULL,
  `tagliascarpe` decimal(2,0) DEFAULT NULL,
  `abilita` varchar(40) DEFAULT NULL,
  KEY `cf` (`cf`),
  CONSTRAINT `d_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d`
--

LOCK TABLES `d` WRITE;
/*!40000 ALTER TABLE `d` DISABLE KEYS */;
/*!40000 ALTER TABLE `d` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flagvolontario`
--

DROP TABLE IF EXISTS `flagvolontario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flagvolontario` (
  `cf` varchar(16) NOT NULL,
  `flagdistato` decimal(2,0) DEFAULT NULL,
  `flagdiruolo` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`cf`),
  CONSTRAINT `flagvolontario_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flagvolontario`
--

LOCK TABLES `flagvolontario` WRITE;
/*!40000 ALTER TABLE `flagvolontario` DISABLE KEYS */;
/*!40000 ALTER TABLE `flagvolontario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `magazzino`
--

DROP TABLE IF EXISTS `magazzino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `magazzino` (
  `nomedivisa` varchar(30) NOT NULL,
  `taglia` varchar(10) NOT NULL,
  `quantita` decimal(4,0) DEFAULT NULL,
  PRIMARY KEY (`nomedivisa`,`taglia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magazzino`
--

LOCK TABLES `magazzino` WRITE;
/*!40000 ALTER TABLE `magazzino` DISABLE KEYS */;
/*!40000 ALTER TABLE `magazzino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `masterchief`
--

DROP TABLE IF EXISTS `masterchief`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `masterchief` (
  `cf` varchar(16) NOT NULL,
  PRIMARY KEY (`cf`),
  CONSTRAINT `masterchief_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `masterchief`
--

LOCK TABLES `masterchief` WRITE;
/*!40000 ALTER TABLE `masterchief` DISABLE KEYS */;
/*!40000 ALTER TABLE `masterchief` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mezzi`
--

DROP TABLE IF EXISTS `mezzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mezzi` (
  `nvolowebmezzo` varchar(10) NOT NULL,
  `nomemezzo` varchar(30) DEFAULT NULL,
  `statomezzo` varchar(30) DEFAULT NULL,
  `targa` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`nvolowebmezzo`),
  UNIQUE KEY `nomemezzo` (`nomemezzo`),
  UNIQUE KEY `targa` (`targa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mezzi`
--

LOCK TABLES `mezzi` WRITE;
/*!40000 ALTER TABLE `mezzi` DISABLE KEYS */;
/*!40000 ALTER TABLE `mezzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcheggio`
--

DROP TABLE IF EXISTS `parcheggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parcheggio` (
  `viaparco` varchar(30) DEFAULT NULL,
  `nvolowebmezzo` varchar(10) DEFAULT NULL,
  KEY `viaparco` (`viaparco`),
  KEY `nvolowebmezzo` (`nvolowebmezzo`),
  CONSTRAINT `parcheggio_ibfk_1` FOREIGN KEY (`viaparco`) REFERENCES `parcomezzi` (`viaparco`),
  CONSTRAINT `parcheggio_ibfk_2` FOREIGN KEY (`nvolowebmezzo`) REFERENCES `mezzi` (`nvolowebmezzo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcheggio`
--

LOCK TABLES `parcheggio` WRITE;
/*!40000 ALTER TABLE `parcheggio` DISABLE KEYS */;
/*!40000 ALTER TABLE `parcheggio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcomezzi`
--

DROP TABLE IF EXISTS `parcomezzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parcomezzi` (
  `viaparco` varchar(30) NOT NULL,
  PRIMARY KEY (`viaparco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcomezzi`
--

LOCK TABLES `parcomezzi` WRITE;
/*!40000 ALTER TABLE `parcomezzi` DISABLE KEYS */;
/*!40000 ALTER TABLE `parcomezzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pass`
--

DROP TABLE IF EXISTS `pass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pass` (
  `cf` varchar(16) NOT NULL,
  `pass` varchar(10) DEFAULT NULL,
  `user` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`cf`),
  UNIQUE KEY `user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pass`
--

LOCK TABLES `pass` WRITE;
/*!40000 ALTER TABLE `pass` DISABLE KEYS */;
/*!40000 ALTER TABLE `pass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `possessodivise`
--

DROP TABLE IF EXISTS `possessodivise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `possessodivise` (
  `cf` varchar(16) NOT NULL,
  `nomedivisa` varchar(30) NOT NULL,
  `taglia` varchar(10) NOT NULL,
  PRIMARY KEY (`cf`,`nomedivisa`,`taglia`),
  KEY `nomedivisa` (`nomedivisa`,`taglia`),
  CONSTRAINT `possessodivise_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`),
  CONSTRAINT `possessodivise_ibfk_2` FOREIGN KEY (`nomedivisa`, `taglia`) REFERENCES `magazzino` (`nomedivisa`, `taglia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `possessodivise`
--

LOCK TABLES `possessodivise` WRITE;
/*!40000 ALTER TABLE `possessodivise` DISABLE KEYS */;
/*!40000 ALTER TABLE `possessodivise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volontario_o_candidato`
--

DROP TABLE IF EXISTS `volontario_o_candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volontario_o_candidato` (
  `cf` varchar(16) NOT NULL,
  `vol_o_cand` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`cf`),
  CONSTRAINT `volontario_o_candidato_ibfk_1` FOREIGN KEY (`cf`) REFERENCES `pass` (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volontario_o_candidato`
--

LOCK TABLES `volontario_o_candidato` WRITE;
/*!40000 ALTER TABLE `volontario_o_candidato` DISABLE KEYS */;
/*!40000 ALTER TABLE `volontario_o_candidato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-06  9:51:42
