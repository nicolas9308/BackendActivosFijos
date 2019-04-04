/*Insert activos fijos */

INSERT INTO activos_fijos (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, estado) VALUES(1, '2018-01-01 11:33:52.12315', 101, '2018-01-01 11:33:52.12315', 101, 'bienes inmuebles', null, 'activo');
INSERT INTO activos_fijos (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, estado) VALUES(2, '2018-01-01 11:33:52.12315', 101, '2018-01-01 11:33:52.12315', 101, 'maquinaria', null, 'activo');
INSERT INTO activos_fijos (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, estado) VALUES(3, '2018-01-01 11:33:52.12315', 101, '2018-01-01 11:33:52.12315', 101, 'material de oficina', null, 'activo');

/*Insert area*/
INSERT INTO areas (id, creado, creado_por, actualizado, actualizado_por, nombre, estado) VALUES(1, '2018-01-01 11:33:52.12315', 101, '2018-01-01 11:33:52.12315', 101, 'Explotacion minera', 'activo');

/*Insert persona*/
INSERT INTO personas (id, creado, creado_por, actualizado, actualizado_por, nombre, cargo, identificacion, telefono, estado, email) VALUES (1, '2018-01-01 11:33:52.12315', 101, '2018-01-01 11:33:52.12315', 101, 'Carlos joaquin peña quintero', 'conductor maquinaria pesada', '1024542755', '3174944581', 'activo', 'brayannicolas1993@hotmail.com');

/*Insert tipos de activos fijos*/
INSERT INTO tipos_activos (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, serial, numero_inventario, peso, alto, ancho, largo , valor_compra, fecha_compra, fecha_baja, estado, color, activo_fijo_id, area_id, persona_id) VALUES (1, '2018-01-01 11:33:52.12315', 101, '2018-01-01 11:33:52.12315', 101, 'Retroexcavadora', 'Trabajo de excavación', '112358569', '001', 150.00, 2.00, 180.00, 180.00, 150000000.00, '2018-01-01', null, 'activo', 'amarillo', 2, 1, 1);