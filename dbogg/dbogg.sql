-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: dbogg
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
  `datadinascita` date DEFAULT NULL,
  PRIMARY KEY (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `a`
--

LOCK TABLES `a` WRITE;
/*!40000 ALTER TABLE `a` DISABLE KEYS */;
INSERT INTO `a` VALUES ('28032017ALOIUYTR','Lorenzo','Medici','Torino','Via Torino 4',986543234,987654321,'','2017-03-28','','','1985-05-07'),('FFFFFFFFFFFFFFFF','Federico','Bianchi','Ancona','Via del Po 5',987654321,3335678907,'','2017-04-01','','','1934-04-06'),('NNNNNNNNNNNNNNNN','Nicola','Rossi','Roma','Via Tagliavento 2',987678965,3207654321,'NicolaRossi@libero.it','2017-04-01','Dottore','','1960-04-07'),('PROVAGENERALE123','Giuseppe','Cannata','San Giovanni Rotondo','Via Tagliavento 3',987654321,3440987654,'','2017-03-18','','','1995-07-08'),('ZZZZZZZZZZZZZZZZ','Giovanni Edoardo','Arcangeli','Ancona','Via del Tevere 3',987654321,987654321,'','2017-03-29','','','1995-08-26');
/*!40000 ALTER TABLE `a` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `abilitazioni`
--

DROP TABLE IF EXISTS `abilitazioni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abilitazioni` (
  `cf` varchar(16) NOT NULL,
  `nome` varchar(30) DEFAULT NULL,
  `datascadenza` varchar(15) DEFAULT NULL,
  `dataacquisizione` varchar(15) DEFAULT NULL,
  `entedirilascio` varchar(30) DEFAULT NULL,
  `n_documento` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abilitazioni`
--

LOCK TABLES `abilitazioni` WRITE;
/*!40000 ALTER TABLE `abilitazioni` DISABLE KEYS */;
INSERT INTO `abilitazioni` VALUES ('PROVAGENERALE123','cinofilo','2017-01-01','1950-06-06','C','H'),('28032017ALOIUYTR','utilizzo piatt elev','2021-01-01','1950-01-01','Comune','790'),('ZZZZZZZZZZZZZZZZ','anti incendio','2020-06-04','2012-07-04','Comune','1'),('ZZZZZZZZZZZZZZZZ','bls','2017-01-01','1950-01-01','T','5'),('NNNNNNNNNNNNNNNN','utilizzo piatt elev','2020-04-06','1957-05-05','Comune','1234'),('NNNNNNNNNNNNNNNN','bls','2022-03-07','1950-04-02','Comune','125'),('FFFFFFFFFFFFFFFF','utilizzo piatt elev','2022-06-06','1981-01-01','Comune','789');
/*!40000 ALTER TABLE `abilitazioni` ENABLE KEYS */;
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
  PRIMARY KEY (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `c`
--

LOCK TABLES `c` WRITE;
/*!40000 ALTER TABLE `c` DISABLE KEYS */;
INSERT INTO `c` VALUES ('28032017ALOIUYTR','Rizzuto',9876543212,9876542135,'','444','4444'),('FFFFFFFFFFFFFFFF','Rizzulli',876543257899,466,'','78','797'),('NNNNNNNNNNNNNNNN','Rizzuto',9875432145,876,'','4325','65432'),('PROVAGENERALE123','Rizzuto',987654321,456,'','456','456'),('ZZZZZZZZZZZZZZZZ','Rizzuto',987654321,8986,'','90786','754');
/*!40000 ALTER TABLE `c` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compiti`
--

DROP TABLE IF EXISTS `compiti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compiti` (
  `cf` varchar(16) DEFAULT NULL,
  `Archivista` varchar(2) DEFAULT NULL,
  `Add_Giunta` varchar(2) DEFAULT NULL,
  `Referente_Informatico` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compiti`
--

LOCK TABLES `compiti` WRITE;
/*!40000 ALTER TABLE `compiti` DISABLE KEYS */;
INSERT INTO `compiti` VALUES ('PROVAGENERALE123','si','si','si'),('28032017ALOIUYTR','no','si','si'),('ZZZZZZZZZZZZZZZZ','si','no','no'),('NNNNNNNNNNNNNNNN','no','no','no');
/*!40000 ALTER TABLE `compiti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corsi`
--

DROP TABLE IF EXISTS `corsi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `corsi` (
  `cf` varchar(16) NOT NULL,
  `nome` varchar(30) DEFAULT NULL,
  `datascadenza` varchar(15) DEFAULT NULL,
  `dataacquisizione` varchar(15) DEFAULT NULL,
  `entedirilascio` varchar(30) DEFAULT NULL,
  `n_documento` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corsi`
--

LOCK TABLES `corsi` WRITE;
/*!40000 ALTER TABLE `corsi` DISABLE KEYS */;
INSERT INTO `corsi` VALUES ('28032017ALOIUYTR','lingua inglese','2017-01-01','1950-01-01','Comune','87765'),('28032017ALOIUYTR','istruttore guida','2017-01-01','1950-01-01','Comune','75'),('ZZZZZZZZZZZZZZZZ','logistica da campo','2017-01-01','1950-01-01','A','1'),('NNNNNNNNNNNNNNNN','rischio sanitari','2022-04-01','1999-04-02','Comune','098'),('FFFFFFFFFFFFFFFF','base protezione civil','2023-05-05','1950-06-06','Comune','897');
/*!40000 ALTER TABLE `corsi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d`
--

DROP TABLE IF EXISTS `d`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `d` (
  `cf` varchar(16) DEFAULT NULL,
  `grupposang` varchar(7) DEFAULT NULL,
  `tagliatesta` varchar(5) DEFAULT NULL,
  `tagliabusto` varchar(5) DEFAULT NULL,
  `tagliamano` varchar(5) DEFAULT NULL,
  `tagliapantaloni` varchar(5) DEFAULT NULL,
  `tagliascarpe` varchar(5) DEFAULT NULL,
  `abilita` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d`
--

LOCK TABLES `d` WRITE;
/*!40000 ALTER TABLE `d` DISABLE KEYS */;
INSERT INTO `d` VALUES ('PROVAGENERALE123','0+','5','5','6','7','8',''),('28032017ALOIUYTR','0+','1','1','1','1','1',''),('ZZZZZZZZZZZZZZZZ','0+','11','1','1','1','1',''),('OVAULTIMA3031017','0+','1','1','1','1','1','ddd'),('NNNNNNNNNNNNNNNN','A','1','1','1','1','1','');
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
  `stato` varchar(20) DEFAULT NULL,
  `ruolo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flagvolontario`
--

LOCK TABLES `flagvolontario` WRITE;
/*!40000 ALTER TABLE `flagvolontario` DISABLE KEYS */;
INSERT INTO `flagvolontario` VALUES ('28032017ALOIUYTR','Attivo','Cordinatore'),('NNNNNNNNNNNNNNNN','Attivo','Volontario_semplice'),('PROVAGENERALE123','Attivo','Admin'),('ZZZZZZZZZZZZZZZZ','Attivo','Vicecordinatore');
/*!40000 ALTER TABLE `flagvolontario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messaggi`
--

DROP TABLE IF EXISTS `messaggi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messaggi` (
  `cf` varchar(30) DEFAULT NULL,
  `Mittente` varchar(30) DEFAULT NULL,
  `messaggio` varchar(200) DEFAULT NULL,
  `letto` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaggi`
--

LOCK TABLES `messaggi` WRITE;
/*!40000 ALTER TABLE `messaggi` DISABLE KEYS */;
/*!40000 ALTER TABLE `messaggi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pass`
--

DROP TABLE IF EXISTS `pass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pass` (
  `cf` varchar(16) NOT NULL,
  `pass` varchar(20) DEFAULT NULL,
  `user` varchar(20) DEFAULT NULL,
  `vol_o_cand` tinyint(1) DEFAULT NULL,
  `conf_giunta` tinyint(1) DEFAULT NULL,
  `primoaccesso` varchar(3) DEFAULT NULL,
  `Conf_Archivista` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`cf`),
  UNIQUE KEY `user` (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pass`
--

LOCK TABLES `pass` WRITE;
/*!40000 ALTER TABLE `pass` DISABLE KEYS */;
INSERT INTO `pass` VALUES ('28032017ALOIUYTR','0000','Lorenzo',1,1,'no',1),('FFFFFFFFFFFFFFFF','0000','Federico',0,0,'si',0),('NNNNNNNNNNNNNNNN','0000','Nicola',1,1,'no',1),('PROVAGENERALE123','0000','AKIRA',1,1,'no',1),('ZZZZZZZZZZZZZZZZ','0000','Giovanni',1,1,'no',1);
/*!40000 ALTER TABLE `pass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patenti`
--

DROP TABLE IF EXISTS `patenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patenti` (
  `cf` varchar(16) NOT NULL,
  `nome` varchar(30) DEFAULT NULL,
  `datascadenza` varchar(15) DEFAULT NULL,
  `dataacquisizione` varchar(15) DEFAULT NULL,
  `entedirilascio` varchar(30) DEFAULT NULL,
  `n_documento` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patenti`
--

LOCK TABLES `patenti` WRITE;
/*!40000 ALTER TABLE `patenti` DISABLE KEYS */;
INSERT INTO `patenti` VALUES ('28032017ALOIUYTR','guida d','2021-06-04','1954-05-04','Comune','7865785'),('28032017ALOIUYTR','guida b','2017-01-01','1950-05-04','Comune','8978879'),('ZZZZZZZZZZZZZZZZ','guida b','2017-01-01','1950-01-01','AAAA','9'),('NNNNNNNNNNNNNNNN','guida d','2021-01-01','1953-02-02','Comune','0987');
/*!40000 ALTER TABLE `patenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-01 17:27:46
