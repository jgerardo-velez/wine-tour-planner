CREATE TABLE `wine-tour-planner`.`regions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO regions (name, code, country) VALUES ('Valle de Guadalupe', 'MEXICO_VALLE_GUADALUPE', 'Mexico');


CREATE TABLE `wine-tour-planner`.`wineries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(100),
  `phone` varchar(45),
  `website` varchar(100) NOT NULL,
  `region_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_region_id
  FOREIGN KEY (region_id) 
  REFERENCES regions(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO wineries (name, address, phone, website, region_id)
VALUES('Bodegas de Santo Tomás', 'Miramar 666, Zona Centro, 22800 Ensenada, B.C.', '646 178 3333', 'http://santo-tomas.com/', 1);

INSERT INTO wineries (name, address, phone, website, region_id)
VALUES('Chateau Camou', 'Av 16 de Septiembre 832, Ulbrich, 22830 Ensenada, B.C.', '646 177 2221', 'https://www.chateau-camou.com.mx/', 1);

INSERT INTO wineries (name, address, phone, website, region_id)
VALUES('L.A. Cetto', 'San Marino, 22870 Ensenada, B.C.', '646 175 2363', 'https://lacetto.mx/boutique/cavas-y-boutique', 1);

INSERT INTO wineries (name, address, phone, website, region_id)
VALUES('El Cielo', 'El Porvenir, 22755 Valle de Guadalupe, B.C.', '646 978 0011', 'http://elcielovalledeguadalupe.com/', 1);

INSERT INTO wineries (name, address, phone, website, region_id)
VALUES('Monte Xanic', 'Francisco Zarco S/N, Col, 22750 Valle de Guadalupe, B.C.', '646 155 2080', 'https://montexanic.com.mx/', 1);


CREATE TABLE `wine-tour-planner`.`products` (
  `sku` int(11) NOT NULL,
  `winery_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` DECIMAL(6,2) DEFAULT NULL,
  `currency` varchar(10) DEFAULT NULL,
  `image` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`sku`),
  CONSTRAINT fk_winery_id
  FOREIGN KEY (winery_id) REFERENCES wineries(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Santo Tomas
INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(10001, 1, 'ST Tinto Cabernet Sauvignon', 254.80, 'MXN $', '10001.jpg'); 

INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(10002, 1, 'Vino Blanco Santo Tomás Chenin Blanc', 278.00, 'MXN $', '10002.jpg');


-- Chateau Camou
INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(20001, 2, 'Vino Tinto Umbral Ensamble Tinto 2014', 343.00, 'MXN $', '20001.jpg');

INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(20002, 2, 'Vino Rosado Chateau Camou Rose', 333.00, 'MXN $', '20002.jpg');

INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(20003, 2, 'Vino Blanco Chateau Camou Chardonnay', 333.00, 'MXN $', '20003.jpg');


-- LA Cetto
INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(30001, 3, 'Vino Tinto L.A. Cetto Nebbiolo Reserva Privada', 271.70, 'MXN $', '30001.jpg');

INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(30002, 3, 'Vino Tinto L.A. Cetto Cabernet Sauvignon Reserva Privada', 475.67, 'MXN $', '30002.jpg');

INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(30003, 3, 'Vino Blanco L.A. Cetto Blanc De Blancs', 190.00, 'MXN $', '30003.jpg');


-- El Cielo
INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(40001, 4, 'Vino Tinto Hubble 2015', 499.00, 'MXN $', '40001.jpg');

INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(40002, 4, 'Vino Blanco Halley', 385.00, 'MXN $', '40002.jpg');

INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(40003, 4, 'Vino Tinto Kepler 2017', 499.00, 'MXN $', '40003.jpg');

INSERT INTO products (sku, winery_id, name, price, currency, image)
VALUES(40004, 4, 'Vino Blanco Andromeda 2018', 460.00, 'MXN $', '40004.jpg');


-- Add Winery id to the users table
ALTER TABLE users 
  ADD COLUMN winery_id INT(11) DEFAULT NULL AFTER role;


-- Replace the winery@winetourplanner.com to elcielo@winetourplanner.com
UPDATE users SET email = 'elcielo@winetourplanner.com', winery_id = 4 WHERE id = 3;  

-- Inser a new Vintner for LA Cetto
INSERT INTO users (email, name, role, winery_id, active, password)
VALUES('lacetto@winetourplanner.com', 'Jesus Lopez', 'ROLE_VINTNER', '3', true, '$2a$10$y5lUhRNeDNeq46Gc26g3MuoFbtCM/ZQbxUOV4sGkCTPWjK75ySr7m');


CREATE TABLE `wine-tour-planner`.`users_tokens` (
  `token` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `expiration_time` Datetime not null,
  `active` tinyint(4) NOT NULL,
  PRIMARY KEY (`token`),
  FOREIGN KEY (user_id) 
  REFERENCES users(id)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;



