/* Populate tabla encargados */
INSERT INTO encargados (id, nombre, apellido, dni, contratacion) VALUES(5, 'Juan', 'Perez', '54545454J', '1998-01-09');
INSERT INTO encargados (id, nombre, apellido, dni, contratacion) VALUES(6, 'María', 'Mtez', '67676767B', '2002-11-30');
INSERT INTO encargados (id, nombre, apellido, dni, contratacion) VALUES(7, 'Pepe', 'Fdez', '12121212X', '2005-07-07');
INSERT INTO encargados (id, nombre, apellido, dni, contratacion) VALUES(8, 'Ana', 'Sanz', '89898989F', '2020-03-12');

/* Populate tabla obras */
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(6, 'Comercial', 'Calle Goya', 'Madrid');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(5, 'Hospital', 'Calle Rosales', 'Valencia');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(8, 'Parque', 'Calle Gran Vía', 'Bilbao');
INSERT INTO obras (encargado_id, denominacion, direccion, ciudad) VALUES(7, 'Parking', 'Calle Centro', 'Barcelona');

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

