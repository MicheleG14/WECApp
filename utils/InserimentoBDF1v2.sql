USE f1;

INSERT INTO equipaggio VALUES ('Ferrari'), ('Mercedes-AMG'), ('Red Bull Racing');

INSERT INTO costruttore VALUES
('Ferrari', 'Ferrari S.p.A.', 'Maranello, Italy', 100),
('Mercedes-AMG', 'Mercedes-Benz AG', 'Stuttgart, Germany', 120),
('Red Bull Racing', 'Red Bull Racing Ltd.', 'Milton Keynes, UK', 90);

INSERT INTO telaio VALUES
('FT01', 1350000, 75, 'Ferrari'),
('RT01', 1400000, 72, 'Red Bull Racing'),
('MT01', 1300000, 78, 'Mercedes-AMG');

INSERT INTO cambio VALUES
('FC01', 35000, 12, 'Ferrari'),
('RC01', 40000, 16, 'Red Bull Racing'),
('MC01', 34500, 13, 'Mercedes-AMG');

INSERT INTO motore VALUES
('FM01', 2050000, 6, 5000, 'Aspirato', 'Ferrari'),
('RM01', 2600000, 6, 5000, 'Turbo', 'Red Bull Racing'),
('MM01', 3050000, 6, 5000, 'Aspirato', 'Mercedes-AMG');

INSERT INTO vettura VALUES
(16, 'SF-23', 'Ferrari', 'FT01', 'FC01', 'FM01','2024-01-03','2024-01-03','2024-01-03'),
(55, 'SF-23', 'Ferrari', 'FT01', 'FC01', 'FM01','2024-01-03','2024-01-03','2024-01-03'),
(24, 'SF-23', 'Ferrari', 'FT01', 'FC01', 'FM01','2024-01-03','2024-01-03','2024-01-03'),
(44, 'W14', 'Mercedes-AMG', 'MT01', 'MC01', 'MM01','2024-01-03','2024-01-03','2024-01-03'),
(2, 'W14', 'Mercedes-AMG', 'MT01', 'MC01', 'MM01','2024-01-03','2024-01-03','2024-01-03'),
(1, 'RB19', 'Red Bull Racing', 'RT01', 'RC01', 'RM01','2024-01-03','2024-01-03','2024-01-03'),
(11, 'RB19', 'Red Bull Racing', 'RT01', 'RC01', 'RM01','2024-01-03','2024-01-03','2024-01-03');

INSERT INTO circuito VALUES
('Autodromo Nazionale di Monza', 'Italia', 5.7, 11),
('RedBull Ring', 'Austria', 4.3, 10),
('Circuit of the Americas', 'Stati Uniti', 5.5, 20);

INSERT INTO gara VALUES
('Gran Premio d\'Austria', '2023-07-02', 2, 'Asciutta','RedBull Ring'),
('Formula 1 Pirelli Gran Premio d\'Italia', '2023-09-03', 2,'Asciutta' ,'Autodromo Nazionale di Monza'),
('Gran Premio degli Stati Uniti d\'America', '2023-10-22', 2, 'Bagnata' ,'Circuit of the Americas');

INSERT INTO iscrizione(vettura, gara, data_evento, punti, motivo_ritiro) VALUES 
(1, 'Gran Premio d\'Austria', '2023-07-02', 25, NULL),
(16, 'Formula 1 Pirelli Gran Premio d\'Italia', '2023-09-03', 12, NULL),
(44, 'Gran Premio degli Stati Uniti d\'America', '2023-10-22', 12, NULL);

INSERT INTO scuderia VALUE
('Scuderia Ferrari', 'Maranello', 1, 'Ferrari'),
('Mercedes-AMG PETRONAS F1 Team', 'Brackley', 0, 'Mercedes-AMG'),
('Oracle Red Bull Racing', 'Milton Keynes', 1, 'Red Bull Racing');

INSERT INTO pilota_pro VALUES
('Charles', 'Leclerc', 16, '1997-10-16', 'Principato di Monaco', 1, 'Ferrari'),
('Max', 'Verstappen', 1, '1997-10-30', 'Olanda', 1, 'Red Bull Racing'),
('Lewis', 'Hamilton', 44, '1985-01-07', 'Inghilterra', 1, 'Mercedes-AMG');

INSERT INTO pilota_am VALUES
('Carlos', 'Sainz', 55, '1994-09-01', 'Spagna', '2015-03-15', 'Ferrari'),
('Logan', 'Sargeant', 2, '1999-07-20', 'Stati Uniti', '2021-03-15', 'Mercedes-AMG');

INSERT INTO gentleman_driver VALUES
('Guanyu', 'Zhou', 24,  '1999-05-30', 'Cina', '2022-03-20', 1650000, 'Ferrari', 'Scuderia Ferrari'),
('Sergio', 'Perez', 11, '1990-05-26', 'Messico', '2011-03-27', 2300000, 'Red Bull Racing', 'Oracle Red Bull Racing');

INSERT INTO materiale VALUES
('Fibra di carbonio'), ('Alluminio'), ('Acciaio');

INSERT INTO assemblaggio(telaio, materiale) VALUES ('FT01', 'Fibra di carbonio');
