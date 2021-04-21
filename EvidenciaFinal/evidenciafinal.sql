-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-04-2021 a las 20:38:50
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `evidenciafinal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camion`
--

CREATE TABLE `camion` (
  `matricula` varchar(7) COLLATE utf8_spanish_ci NOT NULL,
  `modelo` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `potencia` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `camion`
--

INSERT INTO `camion` (`matricula`, `modelo`, `potencia`) VALUES
('1234AAA', 'Mitsubishi Fuso Canter', 111),
('1234GFG', 'Mercedes', 1000),
('1234GJL', 'Prueba', 323);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camionero`
--

CREATE TABLE `camionero` (
  `dni` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `telefono` int(9) NOT NULL,
  `direccion` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `salario` int(11) NOT NULL,
  `poblacion` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `dni_destinatario` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `fecha_nacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `camionero`
--

INSERT INTO `camionero` (`dni`, `nombre`, `apellidos`, `telefono`, `direccion`, `salario`, `poblacion`, `dni_destinatario`, `fecha_nacimiento`) VALUES
('12345678D', 'Jesús', 'Martinez', 633746584, 'Direccion', 1234, 'Gijon', '12345678A', '1998-12-10'),
('53646509H', 'David', 'Pociño', 633454545, 'Calle de prueba', 1000, 'Gijon', '12345678A', '1997-12-31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conduce`
--

CREATE TABLE `conduce` (
  `dni_camionero` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `matricula_camion` varchar(7) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `conduce`
--

INSERT INTO `conduce` (`dni_camionero`, `matricula_camion`) VALUES
('12345678D', '1234AAA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinatario`
--

CREATE TABLE `destinatario` (
  `dni` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `cp` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `destinatario`
--

INSERT INTO `destinatario` (`dni`, `nombre`, `apellidos`, `direccion`, `cp`) VALUES
('12345678A', 'Ash', 'Ketchum', 'Un entrenador de pueblo paleta', 33212),
('54535453D', 'David', 'Pociño', 'Calle de prueba', 33609);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquete`
--

CREATE TABLE `paquete` (
  `codigo` int(11) NOT NULL,
  `descripcion` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `peso` int(11) NOT NULL,
  `dni_destinatario_fk` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `codigo_provincia_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `paquete`
--

INSERT INTO `paquete` (`codigo`, `descripcion`, `peso`, `dni_destinatario_fk`, `codigo_provincia_fk`) VALUES
(1, 'Google Echo', 1, '12345678A', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

CREATE TABLE `provincia` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `provincia`
--

INSERT INTO `provincia` (`codigo`, `nombre`) VALUES
(1, 'Asturias'),
(2, 'Madrid'),
(3, 'Cataluña'),
(4, 'Comunidad Valenciana'),
(5, 'Galicia'),
(6, 'Andalucia'),
(7, 'Pais Vasco'),
(8, 'Castilla y Leon'),
(9, 'Murcia');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `camion`
--
ALTER TABLE `camion`
  ADD PRIMARY KEY (`matricula`);

--
-- Indices de la tabla `camionero`
--
ALTER TABLE `camionero`
  ADD PRIMARY KEY (`dni`),
  ADD KEY `dni_destinatario` (`dni_destinatario`);

--
-- Indices de la tabla `conduce`
--
ALTER TABLE `conduce`
  ADD KEY `dni_camionero` (`dni_camionero`,`matricula_camion`),
  ADD KEY `matricula_camion` (`matricula_camion`);

--
-- Indices de la tabla `destinatario`
--
ALTER TABLE `destinatario`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `paquete`
--
ALTER TABLE `paquete`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `dni_destinatario_fk` (`dni_destinatario_fk`),
  ADD KEY `codigo_provincia_fk` (`codigo_provincia_fk`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`codigo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `camionero`
--
ALTER TABLE `camionero`
  ADD CONSTRAINT `camionero_ibfk_1` FOREIGN KEY (`dni_destinatario`) REFERENCES `destinatario` (`dni`);

--
-- Filtros para la tabla `conduce`
--
ALTER TABLE `conduce`
  ADD CONSTRAINT `conduce_ibfk_1` FOREIGN KEY (`dni_camionero`) REFERENCES `camionero` (`dni`),
  ADD CONSTRAINT `conduce_ibfk_2` FOREIGN KEY (`matricula_camion`) REFERENCES `camion` (`matricula`);

--
-- Filtros para la tabla `paquete`
--
ALTER TABLE `paquete`
  ADD CONSTRAINT `paquete_ibfk_1` FOREIGN KEY (`dni_destinatario_fk`) REFERENCES `destinatario` (`dni`),
  ADD CONSTRAINT `paquete_ibfk_2` FOREIGN KEY (`codigo_provincia_fk`) REFERENCES `provincia` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
