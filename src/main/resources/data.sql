-- =============================================================================
-- BANCOSOL LITE - CARGA DE DATOS DE PRUEBA COMPLETA
-- =============================================================================

-- 1. USUARIOS (admin123, manager123, user123 cifrados en BCrypt)
INSERT INTO users (id, username, email, password, role, enabled) VALUES
                                                                     (1, 'admin', 'admin@bancosol.com', '$2a$10$w8.4nS5JzB10Q0eZ1s3QeOGW2oYV32z4.P4t71T02qCkmg/Zf3e0.', 'ROLE_ADMIN', true),
                                                                     (2, 'manager', 'manager@bancosol.com', '$2a$10$eE0m9b4eG5Rk3oVlZ4TDeuU3M1o0K6V9Jz1X2w3Y4Z5A6B7C8D9E0', 'ROLE_MANAGER', true),
                                                                     (3, 'user', 'user@bancosol.com', '$2a$10$yF8J0d7gM6r7nS5Z1w3DeOzY4V32z4.P4t71T02qCkmg/Zf3e0.2', 'ROLE_USER', true)
    ON CONFLICT (id) DO NOTHING;

SELECT setval(pg_get_serial_sequence('users', 'id'), coalesce(max(id), 1)) FROM users;

-- 2. CAMPAÑAS (10 registros)
INSERT INTO campaigns (id, name, description, start_date, end_date, target_kilos, status) VALUES
                                                                                              (1, 'Operación Kilo Otoño', 'Recogida solidaria de legumbres y conservas', '2026-10-01', '2026-10-31', 5000.0, 'ACTIVE'),
                                                                                              (2, 'Gran Recogida de Navidad', 'Campaña especial para productos no perecederos e infantiles', '2026-12-01', '2026-12-24', 12000.0, 'PLANNING'),
                                                                                              (3, 'Campaña Primavera Solidaria', 'Abastecimiento de arroz, pasta y leche', '2026-03-15', '2026-04-15', 7500.0, 'FINISHED'),
                                                                                              (4, 'Operación Desayunos Infantiles', 'Leche entera, cereales y cacao soluble para familias', '2026-11-01', '2026-11-20', 4000.0, 'PLANNING'),
                                                                                              (5, 'Semana del Aceite y Conservas', 'Recogida focalizada en aceite de oliva y conservas de pescado', '2026-09-01', '2026-09-08', 3500.0, 'FINISHED'),
                                                                                              (6, 'Emergencia Alimentos Invierno', 'Campamento de abastecimiento invernal de alimentos calóricos', '2027-01-10', '2027-02-10', 9000.0, 'PLANNING'),
                                                                                              (7, 'Campaña Legumbre Nacional', 'Lentejas, garbanzos y alubias envasadas', '2026-10-10', '2026-11-10', 4500.0, 'ACTIVE'),
                                                                                              (8, 'Verano Nutritivo', 'Soporte estival para comedores comunitarios', '2026-06-01', '2026-06-30', 6000.0, 'FINISHED'),
                                                                                              (9, 'Vuelta al Cole Solidaria', 'Campaña coordinada con colegios para meriendas y zumos', '2026-09-10', '2026-09-25', 3000.0, 'FINISHED'),
                                                                                              (10, 'Campaña Alimentos Básicos 2027', 'Plan anual de reservas de harina y azúcar', '2027-03-01', '2027-03-31', 8000.0, 'PLANNING')
    ON CONFLICT (id) DO NOTHING;

-- 3. TIENDAS / PUNTOS DE RECOGIDA (10 registros)
INSERT INTO stores (id, name, address, city, active) VALUES
                                                         (1, 'Biedronka Centralna', 'ul. Marszałkowska 104', 'Warszawa', true),
                                                         (2, 'Carrefour Galeria Mokotów', 'ul. Wołoska 12', 'Mokotów', true),
                                                         (3, 'Lidl Puławska', 'ul. Puławska 45', 'Mokotów', true),
                                                         (4, 'Auchan Wola Park', 'ul. Górczewska 124', 'Wola', true),
                                                         (5, 'Biedronka Praga Północ', 'ul. Targowa 32', 'Praga', true),
                                                         (6, 'Kaufland Ursynów', 'ul. Ciszewskiego 15', 'Ursynów', true),
                                                         (7, 'Carrefour Express Śródmieście', 'ul. Nowy Świat 28', 'Warszawa', true),
                                                         (8, 'Lidl Ochota', 'ul. Grójecka 78', 'Ochota', true),
                                                         (9, 'Żabka Wilanów', 'al. Rzeczypospolitej 14', 'Wilanów', true),
                                                         (10, 'Biedronka Bemowo', 'ul. Powstańców Śląskich 26', 'Bemowo', false)
    ON CONFLICT (id) DO NOTHING;

SELECT setval(pg_get_serial_sequence('campaigns', 'id'), coalesce(max(id), 1)) FROM campaigns;
SELECT setval(pg_get_serial_sequence('stores', 'id'), coalesce(max(id), 1)) FROM stores;

-- 4. DONACIONES (10 registros vinculados a campaigns, stores y users)
INSERT INTO donations (id, food_category, quantity_kilos, donation_date, status, campaign_id, store_id, user_id) VALUES
                                                                                                                     (1, 'Legumbres', 120.5, '2026-10-05', 'VERIFIED', 1, 1, 3),
                                                                                                                     (2, 'Conservas de Pescado', 85.0, '2026-10-06', 'PENDING', 1, 2, 3),
                                                                                                                     (3, 'Arroz y Pasta', 250.0, '2026-10-07', 'VERIFIED', 1, 3, 3),
                                                                                                                     (4, 'Leche Entera', 90.0, '2026-10-08', 'PENDING', 1, 4, 3),
                                                                                                                     (5, 'Aceite de Girasol', 60.0, '2026-10-09', 'VERIFIED', 7, 5, 3),
                                                                                                                     (6, 'Alimentos Infantiles', 45.0, '2026-10-10', 'PENDING', 1, 6, 3),
                                                                                                                     (7, 'Harina y Azúcar', 110.0, '2026-10-11', 'VERIFIED', 7, 7, 3),
                                                                                                                     (8, 'Conservas Vegetales', 75.5, '2026-10-12', 'PENDING', 7, 8, 3),
                                                                                                                     (9, 'Galletas y Cereales', 130.0, '2026-10-13', 'VERIFIED', 1, 9, 3),
                                                                                                                     (10, 'Legumbres Secas', 95.0, '2026-10-14', 'PENDING', 1, 1, 3)
    ON CONFLICT (id) DO NOTHING;

SELECT setval(pg_get_serial_sequence('donations', 'id'), coalesce(max(id), 1)) FROM donations;