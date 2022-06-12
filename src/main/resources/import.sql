/* Populate tabla encargados */
INSERT INTO encargados (nombre, apellido, dni, contratacion) VALUES('Juan', 'Perez', '54545454J', '1998-01-09');
INSERT INTO encargados (nombre, apellido, dni, contratacion) VALUES('María', 'Mtez', '67676767B', '2002-11-30');
INSERT INTO encargados (nombre, apellido, dni, contratacion) VALUES('Pepe', 'Fdez', '12121212X', '2005-07-07');
INSERT INTO encargados (nombre, apellido, dni, contratacion) VALUES('Ana', 'Sanz', '89898989F', '2020-03-12');
INSERT INTO encargados (nombre, apellido, dni, contratacion) VALUES('Pedro', 'Jiménz', '15151515L', '2008-01-10');
INSERT INTO encargados (nombre, apellido, dni, contratacion) VALUES('Laura', 'Robles', '37373737N', '2014-11-22');
INSERT INTO encargados (nombre, apellido, dni, contratacion) VALUES('Antonio', 'Ortiz', '94949494M', '2019-07-05');
INSERT INTO encargados (nombre, apellido, dni, contratacion) VALUES('Beatriz', 'Herrera', '26262626D', '2020-03-17');

/* Populate tabla obras */
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(1, 'Centro comercial Plaza Rio', 'Calle Goya', 'Madrid');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(2, 'Hospital Ramón y Cajal', 'Calle Rosales', 'Valencia');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(3, 'Parque Central', 'Calle Gran Vía', 'Bilbao');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(4, 'Parking Paseo de la Libertad', 'Paseo de la Libertad', 'Barcelona');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(5, 'Ikea centro', 'Calle Goya', 'Murcia');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(6, 'Paseo marítimo', 'Paseo marítio', 'Cádiz');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(7, 'Centro comercial central', 'Avenida de la Consitución', 'Sevilla');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(8, 'Parking Europa', 'Calle Europa', 'Ciudad Real');

/* Populate tabla empleados */
INSERT INTO empleados (nombre, apellido, dni, contratacion) VALUES('Fran', 'Jimenez', '54545454J', '2021-12-01');
INSERT INTO empleados (nombre, apellido, dni, contratacion) VALUES('Laura', 'López', '67676767B', '2022-03-24');
INSERT INTO empleados (nombre, apellido, dni, contratacion) VALUES('Antonio', 'Rodriguez', '12121212X', '2019-11-11');
INSERT INTO empleados (nombre, apellido, dni, contratacion) VALUES('Eva', 'Montenegro', '89898989F', '2020-02-28');

/* Creamos algunos usuarios con sus roles */
INSERT INTO `usuarios` (username, password, enabled) VALUES ('admin','$2a$10$BnCAjlhm9JAegDZxh5CwUOP6JQpfkJO.b.vyQ7UVnLmoq2/mE4eJ.',1);
INSERT INTO `usuarios` (username, password, enabled) VALUES ('encargado','$2a$10$GKsGL1EtkzbAUNNn9B9s.OyZdruQGA0WVqgOK9yOEjyxuMw6IAEUS',1);
INSERT INTO `usuarios` (username, password, enabled) VALUES ('empleado','$2a$10$qzxhL47xLubDoN8R6pNbpePfqc1tpSM/EBA5kNrvqZYFev0FDh5Nu',1);

INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ENCARGADO');
INSERT INTO `roles` (nombre) VALUES ('ROLE_EMPLEADO');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (3, 3);

/* Populate tabla empresas */
INSERT INTO empresas (nombre, cif, email, text, create_at) VALUES('IKEA', 'F09090909', 'ikea@mail.es', 'texto plano p','2020-03-17');


/* Populate tabla empresas */
INSERT INTO aplicantes (nombre, dni, email, text, create_at) VALUES('Jose', '43434343A', 'jose43@mail.es', 'texto plano aplicante','2022-03-12');

