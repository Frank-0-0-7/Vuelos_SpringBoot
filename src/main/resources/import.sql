-- =========================================================================
-- 1. INSERTAR CIUDADES MAESTRAS
-- =========================================================================
INSERT INTO ciudades (nombre_ciudad) VALUES ('Mendoza');
INSERT INTO ciudades (nombre_ciudad) VALUES ('Buenos Aires');
INSERT INTO ciudades (nombre_ciudad) VALUES ('Cordoba');

-- =========================================================================
-- 2. INSERTAR AEROPUERTOS MAESTROS VINCULADOS
-- =========================================================================
INSERT INTO aeropuertos (nombre_aeropuerto, nombre_ciudad) VALUES ('Aeropuerto El Plumerillo', 'Mendoza');
INSERT INTO aeropuertos (nombre_aeropuerto, nombre_ciudad) VALUES ('Aeropuerto Internacional Ezeiza', 'Buenos Aires');
INSERT INTO aeropuertos (nombre_aeropuerto, nombre_ciudad) VALUES ('Aeroparque Jorge Newbery', 'Buenos Aires');
INSERT INTO aeropuertos (nombre_aeropuerto, nombre_ciudad) VALUES ('Aeropuerto Pajas Blancas', 'Cordoba');

-- =========================================================================
-- 3. INSERTAR AEROLÍNEAS MAESTRAS
-- =========================================================================
INSERT INTO aerolineas (nombre_aerolinea) VALUES ('Aerolineas Argentinas');
INSERT INTO aerolineas (nombre_aerolinea) VALUES ('Flybondi');

-- =========================================================================
-- 4. INSERTAR PERSONAS Y PILOTOS
-- =========================================================================
INSERT INTO personas (dni_persona, nombre_persona, apellido_persona) VALUES (20111222, 'Max', 'Mad');
INSERT INTO pilotos (dni_persona, numero_piloto) VALUES (20111222, 1);

-- =========================================================================
-- 5. INSERTAR AVIONES MAESTROS (5 Aviones en total)
-- =========================================================================
INSERT INTO aviones (numero_avion, tipo_avion, tipo_turbina) VALUES (101, 'Boeing 737', 'Turbofán');
INSERT INTO aviones (numero_avion, tipo_avion, tipo_turbina) VALUES (102, 'Airbus A320', 'Turbofán');
INSERT INTO aviones (numero_avion, tipo_avion, tipo_turbina) VALUES (103, 'Boeing 737', 'Turbofán');
INSERT INTO aviones (numero_avion, tipo_avion, tipo_turbina) VALUES (104, 'Embraer 190', 'Turborreactor');
INSERT INTO aviones (numero_avion, tipo_avion, tipo_turbina) VALUES (105, 'Airbus A330', 'Turbofán');

-- =========================================================================
-- 6. INSERTAR ASIENTOS MAESTROS (5 asientos por cada avión = 25 en total)
-- =========================================================================
-- Avión 101
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (1, 101, 1, 'A', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (2, 101, 1, 'B', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (3, 101, 2, 'A', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (4, 101, 2, 'B', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (5, 101, 3, 'C', 'ECONOMY');

-- Avión 102
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (6, 102, 1, 'A', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (7, 102, 1, 'B', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (8, 102, 2, 'A', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (9, 102, 2, 'B', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (10, 102, 3, 'C', 'ECONOMY');

-- Avión 103
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (11, 103, 1, 'A', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (12, 103, 1, 'B', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (13, 103, 2, 'A', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (14, 103, 2, 'B', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (15, 103, 3, 'C', 'ECONOMY');

-- Avión 104
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (16, 104, 1, 'A', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (17, 104, 1, 'B', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (18, 104, 2, 'A', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (19, 104, 2, 'B', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (20, 104, 3, 'C', 'ECONOMY');

-- Avión 105
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (21, 105, 1, 'A', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (22, 105, 1, 'B', 'BUSINESS');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (23, 105, 2, 'A', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (24, 105, 2, 'B', 'TURISTA');
INSERT INTO asientos (id_asiento, numero_avion, fila_asiento, letra_asiento, clase_asiento) VALUES (25, 105, 3, 'C', 'ECONOMY');

-- =========================================================================
-- 7. INSERTAR LOS 5 VUELOS CATÁLOGOS
-- =========================================================================
INSERT INTO vuelos (numero_vuelo, numero_avion, aeropuerto_origen, aeropuerto_destino, nombre_aerolinea, numero_piloto, fecha_salida, fecha_llegada) VALUES (1, 101, 'Aeropuerto El Plumerillo', 'Aeropuerto Internacional Ezeiza', 'Aerolineas Argentinas', 20111222, '2026-06-01 08:00:00', '2026-06-01 10:00:00');
INSERT INTO vuelos (numero_vuelo, numero_avion, aeropuerto_origen, aeropuerto_destino, nombre_aerolinea, numero_piloto, fecha_salida, fecha_llegada) VALUES (2, 102, 'Aeroparque Jorge Newbery', 'Aeropuerto Pajas Blancas', 'Flybondi', 20111222, '2026-06-02 14:30:00', '2026-06-02 15:45:00');
INSERT INTO vuelos (numero_vuelo, numero_avion, aeropuerto_origen, aeropuerto_destino, nombre_aerolinea, numero_piloto, fecha_salida, fecha_llegada) VALUES (3, 103, 'Aeropuerto Pajas Blancas', 'Aeropuerto El Plumerillo', 'Aerolineas Argentinas', 20111222, '2026-06-03 11:15:00', '2026-06-03 12:30:00');
INSERT INTO vuelos (numero_vuelo, numero_avion, aeropuerto_origen, aeropuerto_destino, nombre_aerolinea, numero_piloto, fecha_salida, fecha_llegada) VALUES (4, 104, 'Aeropuerto El Plumerillo', 'Aeroparque Jorge Newbery', 'Flybondi', 20111222, '2026-06-04 19:00:00', '2026-06-04 21:00:00');
INSERT INTO vuelos (numero_vuelo, numero_avion, aeropuerto_origen, aeropuerto_destino, nombre_aerolinea, numero_piloto, fecha_salida, fecha_llegada) VALUES (5, 105, 'Aeropuerto Internacional Ezeiza', 'Aeropuerto Pajas Blancas', 'Aerolineas Argentinas', 20111222, '2026-06-05 06:00:00', '2026-06-05 08:15:00');

-- =========================================================================
-- 8. INSERTAR TARIFAS ASOCIADAS A LOS VUELOS
-- =========================================================================
INSERT INTO tarifas (numero_tarifa, numero_vuelo, precio_tarifa, impuesto_tarifa, clase_tarifa) VALUES (1, 1, 150000.00, 35000.00, 'BUSINESS');
INSERT INTO tarifas (numero_tarifa, numero_vuelo, precio_tarifa, impuesto_tarifa, clase_tarifa) VALUES (2, 2, 80000.00, 15000.00, 'TURISTA');
INSERT INTO tarifas (numero_tarifa, numero_vuelo, precio_tarifa, impuesto_tarifa, clase_tarifa) VALUES (3, 3, 95000.00, 20000.00, 'ECONOMY');
INSERT INTO tarifas (numero_tarifa, numero_vuelo, precio_tarifa, impuesto_tarifa, clase_tarifa) VALUES (4, 4, 78000.00, 14000.00, 'TURISTA');
INSERT INTO tarifas (numero_tarifa, numero_vuelo, precio_tarifa, impuesto_tarifa, clase_tarifa) VALUES (5, 5, 165000.00, 40000.00, 'BUSINESS');

-- =========================================================================
-- 9. INSERTAR PERSONAS HISTÓRICAS QUE YA TIENEN RESERVA REALIZADA
-- =========================================================================
-- Pasajero histórico 1: Carlos Gomez
INSERT INTO personas (dni_persona, nombre_persona, apellido_persona) VALUES (11111111, 'Carlos', 'Gomez');
INSERT INTO usuarios (dni_persona, numero_usuario, contrasenia_usuario, correo_electronico_usuario) VALUES (11111111, 11, 'carlos123', 'carlos@mail.com');
-- CORRECCIÓN: Pasamos el DNI (11111111) en lugar del numero_usuario (11)
INSERT INTO tarjetas (numero_usuario, numero_tarjeta, tipo_tarjeta) VALUES (11111111, '1111222233334444', 'CREDITO');

-- Pasajero histórico 2: Ana Milano
INSERT INTO personas (dni_persona, nombre_persona, apellido_persona) VALUES (22222222, 'Ana', 'Milano');
INSERT INTO usuarios (dni_persona, numero_usuario, contrasenia_usuario, correo_electronico_usuario) VALUES (22222222, 22, 'ana123', 'ana@mail.com');
-- CORRECCIÓN: Pasamos el DNI (22222222) en lugar del numero_usuario (22)
INSERT INTO tarjetas (numero_usuario, numero_tarjeta, tipo_tarjeta) VALUES (22222222, '5555666677778888', 'DEBITO');

-- =========================================================================
-- 10. SIMULACIÓN DE 2 RESERVAS HISTÓRICAS YA PROCESADAS EN LA BD
-- =========================================================================
-- Reserva 1: Carlos Gomez (DNI 11111111) compra el Asiento 1 del Vuelo 1
INSERT INTO reservas (numero_reserva, numero_usuario, numero_vuelo, id_asiento) VALUES (1, 11111111, 1, 1);
INSERT INTO pagos (numero_pago, numero_reserva, numero_tarjeta, cantidad_pago) VALUES (1, 1, '1111222233334444', 185000.00);

-- Reserva 2: Ana Milano (DNI 22222222) compra el Asiento 6 del Vuelo 2
INSERT INTO reservas (numero_reserva, numero_usuario, numero_vuelo, id_asiento) VALUES (2, 22222222, 2, 6);
INSERT INTO pagos (numero_pago, numero_reserva, numero_tarjeta, cantidad_pago) VALUES (2, 2, '5555666677778888', 95000.00);
