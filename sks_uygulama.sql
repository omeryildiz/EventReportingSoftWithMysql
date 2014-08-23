-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Anamakine: localhost
-- Üretim Zamanı: 15 Şub 2012, 21:21:57
-- Sunucu sürümü: 5.5.16
-- PHP Sürümü: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Veritabanı: `sks_uygulama`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `sks_tablo`
--

CREATE TABLE IF NOT EXISTS `sks_tablo` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `isim` varchar(150) NOT NULL,
  `aciklama` varchar(600) NOT NULL,
  `yer` varchar(25) NOT NULL,
  `faaliyet` int(11) NOT NULL,
  `katilimci` varchar(35) NOT NULL,
  `konusmaci` varchar(35) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin5 AUTO_INCREMENT=27 ;

--
-- Tablo döküm verisi `sks_tablo`
--

INSERT INTO `sks_tablo` (`ID`, `isim`, `aciklama`, `yer`, `faaliyet`, `katilimci`, `konusmaci`) VALUES
(16, 'Yeni Dünya Düzeninde Türkiyenin Yeri efefe', 'Çok önemli dfe', 'bekta? aç?kgöz', 1, 'özellik', 'omer yildiz efef'),
(24, 'Eski Dünya Düzeninde Türklerin Yeri', 'Eski bir topluluk olmalar?n?n yan? s?ra ayr?ca yeni bir toplumdur da.', 'bekta? aç?kgöz', 2, 'özel', 'omer yildiz'),
(25, 'ömer ', 'y?ld?z', 'sanane', 2, 'resmi', 'yaz?l?m geli?tiri ömer y?ld?z'),
(26, 'ömer ', 'y?ld?z', 'sanane', 3, 'resmi', 'yaz?l?m geli?tiri ömer y?ld?z');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
