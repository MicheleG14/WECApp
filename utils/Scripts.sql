use f1; 
-- OPERAZIONE 1

INSERT INTO scuderia VALUES ('Scuderia Ferrari', 'Maranello, Italy', 0, 'Ferrari');

-- OPERAZIONE 2

INSERT INTO telaio VALUES ('FT01', 1350000, 75, 'Ferrari');
INSERT INTO cambio VALUES ('FC01', 35000, 12, 'Ferrari');
INSERT INTO motore VALUES ('FM01', 2050000, 6, 5000, 'Aspirato', 'Ferrari');
INSERT INTO vettura VALUES (16, 'Ferrari SF-23', 'Ferrari', 'TF01', 'CF01', 'MF01');

-- OPERAZIONE 3

INSERT INTO pilota_pro VALUES
('Charles', 'Leclerc', 16, '1997-10-16', 'Principato di Monaco', 1, 'Ferrari');
INSERT INTO pilota_am VALUES
('Carlos', 'Sainz', 55, '1994-09-01', 'Spagna', '2015-03-15', 'Ferrari');
INSERT INTO gentleman_driver VALUES
('Guanyu', 'Zhou', 24, '1999-05-30', 'Cina', '2022-03-20', 1650000, 'Ferrari', 'Scuderia Ferrari');

-- OPERAZIONE 4

SELECT quota_finanziamento FROM gentleman_driver WHERE cognome = 'Zhou'; 
UPDATE gentleman_driver SET quota_finanziamento = 100  WHERE cognome = 'Zhou';
UPDATE scuderia SET finanziamenti = 1 WHERE nome = 'Scuderia Ferrari';

-- OPERAZIONE 5

INSERT INTO iscrizione(vettura, gara, data_evento, punti, motivo_ritiro) VALUES
(16, 'Formula 1 Pirelli Gran Premio d\'Italia', '2023-09-03', 12, NULL);

-- OPERAZIONE 6

SELECT DISTINCT gara FROM iscrizione; 
SELECT * FROM iscrizione WHERE gara = 'Formula 1 Pirelli Gran Premio d\'Italia';
UPDATE iscrizione SET punti = 25 WHERE vettura = 16 AND gara = 'Formula 1 Pirelli Gran Premio d\'Italia';

-- OPERAZIONE 7

SELECT DISTINCT * FROM vettura;

-- OPERAZIONE 8

SELECT sum(quota_finanziamenti), gentleman_driver.scuderia_finanziata 
FROM gentleman_driver 
INNER JOIN scuderia ON gentleman_driver.scuderia_finanziata = scuderia.nome
WHERE scuderia_finanziata 
IN (SELECT nome FROM scuderia) 
GROUP BY gentleman_driver.scuderia_finanziata;

-- OPERAZIONE 9

SELECT * FROM scuderia;

-- OPERAZIONE 10

SELECT * FROM pilota_am as p 
JOIN vettura AS v ON p.num_gara = v.num_gara 
WHERE p.nazionalità IN (
	SELECT paese 
    FROM circuito 
    INNER JOIN gara ON circuito.nome = gara.circuito 
    WHERE gara.nome IN (
		SELECT gara 
        FROM iscrizione
        WHERE punti = 25));
        
SELECT * FROM pilota_pro as p 
JOIN vettura AS v ON p.num_gara = v.num_gara 
WHERE p.nazionalità IN (
	SELECT paese 
    FROM circuito 
    INNER JOIN gara ON circuito.nome = gara.circuito 
    WHERE gara.nome IN (
		SELECT gara 
        FROM iscrizione
        WHERE punti = 25));
        
SELECT * FROM gentleman_driver as p 
JOIN vettura AS v ON p.num_gara = v.num_gara 
WHERE p.nazionalità IN (
	SELECT paese 
    FROM circuito 
    INNER JOIN gara ON circuito.nome = gara.circuito 
    WHERE gara.nome IN (
		SELECT gara 
        FROM iscrizione
        WHERE punti = 25));

-- OPERAZIONE 11

SELECT count(equipaggio), equipaggio FROM pilota_am GROUP BY equipaggio; 
SELECT count(equipaggio), equipaggio FROM pilota_pro GROUP BY equipaggio;
SELECT count(equipaggio), equipaggio FROM gentleman_driver GROUP BY equipaggio; 

-- OPERAZIONE 12

SELECT * FROM costruttore;

-- OPERAZIONE 13

SELECT * FROM iscrizione WHERE gara = 'Gran Premio degli Stati Uniti d\'America'; 

-- OPERAZIONE 14

SELECT
    m.tipo_motore,
    SUM(i.punti) AS punti_totali
FROM
    vettura v
JOIN
    motore m ON v.motore = m.codice
JOIN
    iscrizione i ON v.num_gara = i.vettura
GROUP BY
    m.tipo_motore
ORDER BY
    v.num_gara, punti_totali DESC;

-- OPERAZIONE 15

SELECT
    s.nome AS scuderia,
    AVG(i.punti / g.durata_ore * 60) AS rapporto_punti_minuti
FROM
    scuderia s
JOIN
    equipaggio e ON s.equipaggio = e.nome
JOIN
    pilota_pro p ON e.nome = p.equipaggio
JOIN
    vettura v ON p.num_gara = v.num_gara
JOIN
    gara g ON v.num_gara = g.nome
JOIN
    iscrizione i ON v.num_gara = i.vettura
GROUP BY
    s.nome
ORDER BY
    rapporto_punti_minuti DESC;

