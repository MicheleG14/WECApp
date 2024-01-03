package com.unisa;
import java.sql.*;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci l'operazione che vuoi eseguire: ");
        int op = sc.nextInt();
        sc.nextLine();
        ResultSet set;
        String nomeEquipaggio;

        switch (op) {
            case 1:
                System.out.println("REGISTRAZIONE SCUDERIA");

                System.out.println("Inserisci il nome della scuderia: ");
                String nomeScuderia = sc.nextLine();

                System.out.println("Inserisci il luogo in cui è presente la sede principale: ");
                String luogoScuderia = sc.nextLine();

                set = db.execQuery("SELECT * FROM equipaggio");
                System.out.println("\nEQUIPAGGI");
                while (set.next()) {
                    System.out.println(set.getString("nome"));
                }

                System.out.println("Inserisci il nome dell'equipaggio corrispondente: ");
                nomeEquipaggio = sc.nextLine();

                db.insertQuery("INSERT INTO scuderia VALUES ('"+ nomeScuderia +"', '" + luogoScuderia + "', 0, '"+ nomeEquipaggio +"')");

                break;
            case 2:
                System.out.println("REGISTRAZIONE VETTURA");

                System.out.println("Inserisci il numero di gara: ");
                int numGara = sc.nextInt();
                sc.nextLine();

                System.out.println("Inserisci il modello: ");
                String modelloVettura = sc.nextLine();

                set = db.execQuery("SELECT * FROM equipaggio");
                System.out.println("\nEQUIPAGGI");
                while (set.next()) {
                    System.out.println(set.getString("nome"));
                }

                System.out.println("Inserisci il nome dell'equipaggio corrispondente: ");
                nomeEquipaggio = sc.nextLine();

                set = db.execQuery("SELECT * FROM telaio");
                System.out.println("\nTELAI");
                while (set.next()) {
                    System.out.println(set.getString("codice") + " " + set.getString("costo") + " " + set.getString("peso") + " " + set.getString("costruttore"));
                }

                set = db.execQuery("SELECT * FROM cambio");
                System.out.println("\nCAMBI");
                while (set.next()) {
                    System.out.println(set.getString("codice") + " " + set.getString("costo") + " " + set.getInt("num_marce") + " " + set.getString("costruttore"));
                }

                set = db.execQuery("SELECT * FROM motore");
                System.out.println("\nCAMBI");
                while (set.next()) {
                    System.out.println(set.getString("codice") + " " + set.getString("costo") + " " + set.getString("peso") + " " + set.getString("costruttore"));
                }

                db.insertQuery("INSERT INTO vettura VALUES ('"+ numGara + "', '"+ modelloVettura +"', '"+ nomeEquipaggio +"', 'TF01', 'CF01', 'MF01')");
                break;
            case 3:
                System.out.println("REGISTRAZIONE PILOTA");
                System.out.println("Inserisci il nome: ");
                String nomePilota = sc.nextLine();
                sc.nextLine();
                System.out.println("Inserisci il cognome: ");
                String cognomePilota = sc.nextLine();
                //numGara
                System.out.println("Inserisci la data di nascita (YYYY/MM/DD): ");
                String dataNascitaPilota = sc.nextLine();
                System.out.println("Inserisci la nazionalità: ");
                String nazionalitaPilota = sc.nextLine();
                System.out.println("Inserisci il tipo di pilota (Pro - 0, AM - 1, Gentleman Driver - 2): ");
                int tipoPilota = sc.nextInt();
                switch (tipoPilota) {
                    case 0:
                        System.out.println("Inserisci il numero di licenze possedute: ");
                        int numLicenzePilota = sc.nextInt();
                        db.insertQuery("INSERT INTO pilota_pro VALUES ('"+ nomePilota +"', '"+ cognomePilota +"', 16, '"+ dataNascitaPilota +"', '"+ nazionalitaPilota +"', "+ numLicenzePilota +", 'Ferrari')");
                        break;
                    case 1:
                        System.out.println("Inserisci la data di acquisizione della licenza: ");
                        String dataAcquisizioneLicenzaAM = sc.nextLine();
                        db.insertQuery("INSERT INTO pilota_am VALUES ('"+ nomePilota +"', '"+ cognomePilota +"', 55, '"+ dataNascitaPilota +"', '"+ nazionalitaPilota +"', '"+ dataAcquisizioneLicenzaAM +"', 'Ferrari')");
                        break;
                    case 2:
                        System.out.println("Inserisci la data di acquisizione della licenza: ");
                        String dataAcquisizioneLicenzaGD = sc.nextLine();
                        System.out.println("Inserisci la quota finanziata: ");
                        int quotaFinanziata = sc.nextInt();
                        db.insertQuery("INSERT INTO pilota_am VALUES ('"+ nomePilota +"', '"+ cognomePilota +"', 55, '"+ dataNascitaPilota +"', '"+ nazionalitaPilota +"', '"+ dataAcquisizioneLicenzaGD +"', 'Ferrari')");
                        break;
                }
                System.out.println("Inserisci il nome dell'equipaggio di cui fa parte il pilota: ");
                String equipaggioPilota = sc.nextLine();
                break;
            case 4:
                System.out.println("Inserisci cognome del Gentleman Driver: ");
                String cognomeGentlemanDriver = sc.nextLine();

                ResultSet set1 = db.execQuery("SELECT quota_finanziamenti, scuderia_finanziata FROM gentleman_driver WHERE cognome = '"+ cognomeGentlemanDriver +"'");
                float quotaFinanziamenti = set1.getFloat("quota_finanziamenti");
                String scuderiaFinanziata = set1.getString("scuderia_finanziata");

                System.out.println("Inserisci l'importo del finanziamento: ");
                float newQuotaFinanziamenti = quotaFinanziamenti + sc.nextFloat();
                db.insertQuery("UPDATE gentleman_driver SET quota_finanziamenti = "+ newQuotaFinanziamenti +" WHERE cognome = '"+ cognomeGentlemanDriver +"'");

                ResultSet set2 = db.execQuery("SELECT finanziamenti FROM scuderia WHERE nome = '"+ scuderiaFinanziata +"'");
                int numFinanziamenti = set2.getInt("finanziamenti") + 1;
                db.insertQuery("UPDATE scuderia SET finanziamenti = " + numFinanziamenti +" WHERE nome = '"+ scuderiaFinanziata +"'");
                break;
            case 5:
                System.out.println("ISCRIZIONE VETTURA ALLA GARA");
                System.out.println("Inserisci il numero di gara della vettura: ");
                int numGaraIscrizione = sc.nextInt();
                System.out.println("Inserisci il nome della gara: ");
                String garaIscrizione = sc.nextLine();
                System.out.println("Inserisci la data in cui si svolge la gara: ");
                String dataIscrizione = sc.nextLine();
                db.insertQuery("INSERT INTO iscrizione(vettura, gara, data_evento, punti, motivo_ritiro) VALUES ("+ numGaraIscrizione +", '"+ garaIscrizione +"', '"+ dataIscrizione +"', NULL, NULL)");
                break;
            case 6:
                //TO-DO
                break;
            case 7:
                System.out.println("VERIFICA MONTAGGIO COMPONENTE");
                System.out.println("Inserisci il tipo di componente che si vuole montare: ");
                String nomeComponente = sc.nextLine();
                //TO-DO
                break;
            case 8:
                //TO-DO
                break;
            case 9:

        }
    }
}