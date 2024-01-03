DROP DATABASE IF EXISTS f1; 
CREATE DATABASE f1;
USE f1; 

CREATE TABLE equipaggio(
	nome VARCHAR(50) PRIMARY KEY
);

CREATE TABLE costruttore(
	nome VARCHAR(50) PRIMARY KEY,
    ragione_sociale VARCHAR(25) NOT NULL,
    sede_fabbrica VARCHAR(50) NOT NULL,
    num_componenti_forniti INT NOT NULL
);

CREATE TABLE telaio(
	codice CHAR(4) PRIMARY KEY,
    costo FLOAT NOT NULL,
    peso FLOAT NOT NULL,
    costruttore VARCHAR(50) NOT NULL,
    FOREIGN KEY (costruttore) REFERENCES costruttore(nome)
);

CREATE TABLE cambio(
	codice CHAR(4) PRIMARY KEY,
    costo FLOAT NOT NULL,
    num_marce INT NOT NULL,
    costruttore VARCHAR(50) NOT NULL,
    FOREIGN KEY (costruttore) REFERENCES costruttore(nome)
);

CREATE TABLE motore(
	codice CHAR(4) PRIMARY KEY,
    costo FLOAT NOT NULL,
    num_cilindri INT NOT NULL,
    cilindrata INT NOT NULL,
    tipo_motore ENUM('Turbo', 'Aspirato') NOT NULL,
    costruttore VARCHAR(50) NOT NULL,
    FOREIGN KEY (costruttore) REFERENCES costruttore(nome)
);

CREATE TABLE vettura(
	num_gara INT PRIMARY KEY,
    modello VARCHAR(30) NOT NULL,
    equipaggio VARCHAR(50) NOT NULL,
    telaio CHAR(4) NOT NULL,
    cambio CHAR(4) NOT NULL,
    motore CHAR(4) NOT NULL,
    data_installazione_telaio DATE,
    data_installazione_cambio DATE,
    data_installazione_vettura DATE,
    FOREIGN KEY (equipaggio) REFERENCES equipaggio(nome),
    FOREIGN KEY (telaio) REFERENCES telaio(codice),
    FOREIGN KEY (cambio) REFERENCES cambio(codice),
    FOREIGN KEY (motore) REFERENCES motore(codice)
);

CREATE TABLE circuito(
	nome VARCHAR(80) PRIMARY KEY,
    paese VARCHAR(20) NOT NULL,
    lunghezza_km FLOAT NOT NULL,
    num_curve INT NOT NULL
);

CREATE TABLE gara(
	nome VARCHAR(100),
    data_evento DATE,
    durata_ore INT NOT NULL,
    tipo_gara ENUM('Asciutta', 'Bagnata'),
    circuito VARCHAR(80) NOT NULL,
    PRIMARY KEY(nome, data_evento),
	FOREIGN KEY (circuito) REFERENCES circuito(nome)
);

CREATE TABLE iscrizione(
	id_iscrizione INT PRIMARY KEY AUTO_INCREMENT,
    vettura INT NOT NULL,
    gara VARCHAR(100) NOT NULL,
    data_evento DATE NOT NULL,
    punti INT,
    motivo_ritiro ENUM('Incidente', 'Guasto meccanico', 'Squalifica'),
    FOREIGN KEY (vettura) REFERENCES vettura(num_gara),
    FOREIGN KEY (gara, data_evento) REFERENCES gara(nome, data_evento)
);

CREATE TABLE scuderia(
	nome VARCHAR(40) PRIMARY KEY,
    sede_principale VARCHAR(50) NOT NULL,
    finanziamenti INT NOT NULL,
    equipaggio VARCHAR(50) NOT NULL,
    FOREIGN KEY (equipaggio) REFERENCES equipaggio(nome)
);

CREATE TABLE pilota_pro(
	nome VARCHAR(25),
    cognome VARCHAR(25),
    num_gara INT NOT NULL,
    data_nascita DATE,
    nazionalità VARCHAR(25) NOT NULL,
    num_licenze INT NOT NULL,
    equipaggio VARCHAR(50) NOT NULL,
    PRIMARY KEY(nome, cognome, data_nascita),
    FOREIGN KEY (equipaggio) REFERENCES equipaggio(nome),
    FOREIGN KEY (num_gara) REFERENCES vettura(num_gara)
);

CREATE TABLE pilota_am(
	nome VARCHAR(25),
    cognome VARCHAR(25),
    num_gara INT NOT NULL,
    data_nascita DATE NOT NULL,
    nazionalità VARCHAR(25) NOT NULL,
    data_acquisizione_licenza DATE NOT NULL,
    equipaggio VARCHAR(50) NOT NULL,
    PRIMARY KEY(nome, cognome, data_nascita),
    FOREIGN KEY (equipaggio) REFERENCES equipaggio(nome),
	FOREIGN KEY (num_gara) REFERENCES vettura(num_gara)
);

CREATE TABLE gentleman_driver(
	nome VARCHAR(25),
    cognome VARCHAR(25),
	num_gara INT NOT NULL,
    data_nascita DATE,
    nazionalità VARCHAR(25) NOT NULL,
    data_acquisizione_licenza DATE NOT NULL,
    quota_finanziamenti FLOAT,
    equipaggio VARCHAR(50) NOT NULL,
    scuderia_finanziata VARCHAR(40),
    PRIMARY KEY(nome, cognome, data_nascita),
    FOREIGN KEY (equipaggio) REFERENCES equipaggio(nome),
    FOREIGN KEY (scuderia_finanziata) REFERENCES scuderia(nome),
	FOREIGN KEY (num_gara) REFERENCES vettura(num_gara)
);

CREATE TABLE materiale(
	nome VARCHAR(40) PRIMARY KEY
);

CREATE TABLE assemblaggio(
	id_assemblaggio INT PRIMARY KEY AUTO_INCREMENT,
    telaio CHAR(5) NOT NULL,
    materiale VARCHAR(40) NOT NULL,
    FOREIGN KEY (telaio) REFERENCES telaio(codice),
    FOREIGN KEY (materiale) REFERENCES materiale(nome)
);
