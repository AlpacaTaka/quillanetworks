USE redesunificadas;

-- Insertar roles
INSERT INTO rol (nombre) VALUES ('admin');
SET @rol_admin_id = LAST_INSERT_ID();

INSERT INTO rol (nombre) VALUES ('cliente');
SET @rol_cliente_id = LAST_INSERT_ID();

-- Insertar usuario administrador con rol 'admin'
INSERT INTO usuario 
(nombre, estado, eliminado, fecha_creacion, rol_id) 
VALUES 
('Administrador', 1, 0, NOW(), @rol_admin_id);

SET @admin_id = LAST_INSERT_ID();

-- Credencial (password: Admin123)
INSERT INTO credencial 
(correo, password, usuario_id) 
VALUES 
('admin@example.com', '$2a$12$5M3/7XeW9Y1O2dQb8vzJ1eBpZ6nVcR7sT8uK9lL2mN3oP4qR5S6D', @admin_id);