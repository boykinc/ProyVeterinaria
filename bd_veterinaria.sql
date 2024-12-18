-- Base de datos: `bd_veterinaria`
--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE DATABASE bd_veterinaria;

USE bd_veterinaria;

CREATE TABLE IF NOT EXISTS `tipo_usuario` (
  `id_tipo_usu` int(11) NOT NULL AUTO_INCREMENT,
  `descrip_tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tipo_usu`)
);

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id_tipo_usu`, `descrip_tipo`) VALUES
(1, 'USER'),
(2, 'ADMIN'),
(3, 'MEDICO');

--
-- Estructura de tabla para la tabla `tb_usuario`
--

CREATE TABLE IF NOT EXISTS `tb_usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nom_usu` varchar(50) NOT NULL,
  `ape_usu` varchar(50) NOT NULL,
  `email_usu` varchar(50) NOT NULL,
  `contra_usu` varchar(50) NOT NULL,
  `fono_usu` char(9) NOT NULL,
  `direc_usu` varchar(50) NOT NULL,
  `id_tipo_usu` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email_usu` (`email_usu`),
  KEY `id_tipo_usu` (`id_tipo_usu`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `tb_usuario`
--

INSERT INTO `tb_usuario` (`id_usuario`, `nom_usu`, `ape_usu`, `email_usu`, `contra_usu`, `fono_usu`, `direc_usu`, `id_tipo_usu`) VALUES
(1, 'Mariana', 'Wisman', 'Mariana@gmail.com', 'holamundo123', '123456789', 'Ate', 2),
(2, 'Katerina', 'Garay', 'kate@gmail.com', '123', '987456123', 'San Miguel', 1),
(3, 'Piero', 'Caro', 'piero@gmail.com', 'Piero123', '999888777', 'Rimac', 3),
(4, 'Favio', 'Condor', 'favio@gmail.com', 'Ah123', '999999999', 'San Martin de Porres', 2),
(5, 'Roberto', 'Salcedo', 'roberto@gmail.com', 'Roberto123', '999444999', 'Surquillo', 3);
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tb_especie`
--

CREATE TABLE IF NOT EXISTS `tb_especie` (
  `id_especie` int(11) NOT NULL AUTO_INCREMENT,
  `descrip_espe` varchar(100) NOT NULL,
  PRIMARY KEY (`id_especie`)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_mascota`
--

CREATE TABLE IF NOT EXISTS `tb_mascota` (
  `id_mascota` int(11) NOT NULL AUTO_INCREMENT,
  `nom_masco` varchar(50) NOT NULL,
  `id_especie` int(11) NOT NULL,
  `raza` varchar(40) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `fec_nac` datetime NOT NULL,
  `peso_masco` decimal(10,2) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_mascota`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_especie` (`id_especie`)
);

-- --------------------------------------------------------

-- Estructura de tabla para la tabla `tb_especialidad`
--

CREATE TABLE IF NOT EXISTS `tb_especialidad` (
  `id_especialidad` int(11) NOT NULL AUTO_INCREMENT,
  `des_especialidad` varchar(100) NOT NULL,
  PRIMARY KEY (`id_especialidad`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `tb_especialidad`
--

INSERT INTO `tb_especialidad` (`id_especialidad`, `des_especialidad`) VALUES
(1, 'Oftalmologia'),
(2, 'Quimioterapia'),
(3, 'Radiologia'),
(4, 'Traumatologia');

--
-- Estructura de tabla para la tabla `tb_veterinario`
--

CREATE TABLE IF NOT EXISTS `tb_veterinario` (
  `id_veterinario` int(11) NOT NULL AUTO_INCREMENT,
  `id_especialidad` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `estado` char(1) NOT NULL,
  PRIMARY KEY (`id_veterinario`),
  KEY `id_especialidad` (`id_especialidad`),
  KEY `id_usuario` (`id_usuario`)
);

--
-- Volcado de datos para la tabla `tb_veterinario`
--

INSERT INTO `tb_veterinario` (`id_especialidad`, `id_usuario`, `estado`) VALUES
(2, 3, 'A');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_mascota`
--
ALTER TABLE `tb_mascota`
  ADD CONSTRAINT `tb_mascota_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`),
  ADD CONSTRAINT `tb_mascota_ibfk_2` FOREIGN KEY (`id_especie`) REFERENCES `tb_especie` (`id_especie`);

--
-- Filtros para la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD CONSTRAINT `tb_usuario_ibfk_1` FOREIGN KEY (`id_tipo_usu`) REFERENCES `tipo_usuario` (`id_tipo_usu`);

--
-- Filtros para la tabla `tb_veterinario`
--
ALTER TABLE `tb_veterinario`
  ADD CONSTRAINT `tb_veterinario_ibfk_1` FOREIGN KEY (`id_especialidad`) REFERENCES `tb_especialidad` (`id_especialidad`),
  ADD CONSTRAINT `tb_veterinario_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`);
