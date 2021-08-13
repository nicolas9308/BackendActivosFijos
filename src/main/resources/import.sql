
INSERT INTO activos_fijos (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, estado) VALUES(1, current_date(), 101, current_date(), 101, 'Bienes inmuebles', null, 'activo');
INSERT INTO activos_fijos (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, estado) VALUES(2, current_date(), 101, current_date(), 101, 'maquinaria', null, 'activo');
INSERT INTO activos_fijos (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, estado) VALUES(3, current_date(), 101, current_date(), 101, 'material de oficina', null, 'activo');

INSERT INTO areas (id, creado, creado_por, actualizado, actualizado_por, nombre, estado) VALUES(1, current_date(), 101, current_date(), 101, 'Explotacion minera', 'activo');

INSERT INTO personas (id, creado, creado_por, actualizado, actualizado_por, nombre, cargo, identificacion, telefono, estado, email) VALUES (1, current_date(), 101, current_date(), 101, 'Carlos joaquin peña quintero', 'conductor maquinaria pesada', '1024542755', '3174944581', 'activo', 'brayannicolas1993@hotmail.com');

INSERT INTO tipos_activos (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, serial, numero_inventario, peso, alto, ancho, largo , valor_compra, fecha_compra, fecha_baja, estado, color, activo_fijo_id, area_id, persona_id) VALUES (1, current_date(), 101, current_date(), 101, 'Retroexcavadora', 'Trabajo de excavación', '112358569', '001', 150.00, 2.00, 180.00, 180.00, 150000000.00, '2018-01-01', null, 'activo', 'amarillo', 2, 1, 1);

INSERT INTO aut_personas (id, creado, creado_por, actualizado, actualizado_por, usuario, clave, estado) VALUES (1, current_date(), 101, current_date(), 101, "admin", '$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq', 'activo');
INSERT INTO aut_personas (id, creado, creado_por, actualizado, actualizado_por, usuario, clave, estado) VALUES (2, current_date(), 101, current_date(), 101, "nicolas", '$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK', 'activo');

INSERT INTO roles (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, estado) VALUES (1, current_date(), 101, current_date(), 101, 'ROLE_ADMIN', null, 'activo');
INSERT INTO roles (id, creado, creado_por, actualizado, actualizado_por, nombre, descripcion, estado) VALUES (2, current_date(), 101, current_date(), 101, 'ROLE_USER', null, 'activo');

INSERT INTO aut_personas_roles (aut_personas_id, rol_id) VALUES (1, 1);
INSERT INTO aut_personas_roles (aut_personas_id, rol_id) VALUES (2, 2);
INSERT INTO aut_personas_roles (aut_personas_id, rol_id) VALUES (2, 1);