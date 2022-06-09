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
