USE redesunificadas;

-- Usuario administrador
INSERT INTO usuario 
(nombre, rol, estado, eliminado, fecha_creacion) 
VALUES 
('Administrador', 'admin', 1, 0, NOW());

SET @admin_id = LAST_INSERT_ID();

-- Credencial (password: Admin123)
INSERT INTO credencial 
(correo, password, usuario_id) 
VALUES 
('admin@example.com', '$2a$12$5M3/7XeW9Y1O2dQb8vzJ1eBpZ6nVcR7sT8uK9lL2mN3oP4qR5S6D', @admin_id);