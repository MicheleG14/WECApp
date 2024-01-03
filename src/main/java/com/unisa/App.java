package com.unisa;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci l'operazione che vuoi eseguire: ");
        int op = sc.nextInt();
        clearBuffer(sc);
        ResultSet set;
        String nomeEquipaggio;

        switch (op) {
            case 1:
                System.out.println("REGISTRAZIONE SCUDERIA");

                System.out.print("Inserisci il nome della scuderia: ");
                String nomeScuderia = sc.nextLine();

                System.out.print("Inserisci il luogo in cui è presente la sede principale: ");
                String luogoScuderia = sc.nextLine();

                set = db.execQuery("SELECT * FROM equipaggio");
                System.out.println("\nEQUIPAGGI");
                while (set.next()) {
                    System.out.println(set.getString("nome"));
                }

                System.out.print("Inserisci il nome dell'equipaggio corrispondente: ");
                nomeEquipaggio = sc.nextLine();

                db.insertQuery("INSERT INTO scuderia VALUES ('"+ nomeScuderia +"', '" + luogoScuderia + "', 0, '"+ nomeEquipaggio +"')");

                break;
            case 2:
                System.out.println("REGISTRAZIONE VETTURA");

                System.out.print("Inserisci il numero di gara: ");
                int numGara = sc.nextInt();
                clearBuffer(sc);

                System.out.print("Inserisci il modello: ");
                String modelloVettura = sc.nextLine();

                set = db.execQuery("SELECT * FROM equipaggio");
                System.out.println("\nEQUIPAGGI");
                while (set.next()) {
                    System.out.println(set.getString("nome"));
                }

                System.out.print("Inserisci il nome dell'equipaggio corrispondente: ");
                nomeEquipaggio = sc.nextLine();

                set = db.execQuery("SELECT * FROM telaio");
                System.out.println("\nTELAI");
                while (set.next()) {
                    System.out.println(set.getString("codice") + " "
                                        + set.getString("costo") + " "
                                        + set.getString("peso") + " "
                                        + set.getString("costruttore"));
                }

                System.out.print("Inserisci il telaio desiderato: ");
                String telaioVettura = sc.nextLine();

                set = db.execQuery("SELECT * FROM cambio");
                System.out.println("\nCAMBI");
                while (set.next()) {
                    System.out.println(set.getString("codice") + " "
                                        + set.getString("costo") + " "
                                        + set.getInt("num_marce") + " "
                                        + set.getString("costruttore"));
                }

                System.out.print("Inserisci il cambio desiderato: ");
                String cambioVettura = sc.nextLine();

                set = db.execQuery("SELECT * FROM motore");
                System.out.println("\nCAMBI");
                while (set.next()) {
                    System.out.println(set.getString("codice") + " "
                                        + set.getString("costo") + " "
                                        + set.getInt("num_cilindri") + " "
                                        + set.getInt("cilindrata") + " "
                                        + set.getString("tipo_motore") + " "
                                        + set.getString("costruttore"));
                }

                System.out.print("Inserisci il motore desiderato: ");
                String motoreVettura = sc.nextLine();

                db.insertQuery("INSERT INTO vettura VALUES ('"+ numGara + "', '"+ modelloVettura +"', '"
                                                            + nomeEquipaggio +"', '"+ telaioVettura +"', '"
                                                            + cambioVettura +"', '"+ motoreVettura +"', '"
                                                            + LocalDate.now() +"', '"+ LocalDate.now() +"', '"
                                                            + LocalDate.now() +"')");
                break;
            case 3:
                System.out.println("REGISTRAZIONE PILOTA");

                System.out.print("Inserisci il nome: ");
                String nomePilota = sc.nextLine();
                sc.nextLine();

                System.out.print("Inserisci il cognome: ");
                String cognomePilota = sc.nextLine();

                set = db.execQuery("SELECT * FROM vettura");
                while (set.next())
                {
                    System.out.println(set.getString("num_gara") + " " + set.getString("modello"));
                }

                System.out.print("Inserisci il numero di gara tra le vetture disponibili: ");
                int numGaraRegistrazione = sc.nextInt();
                clearBuffer(sc);

                System.out.print("Inserisci la data di nascita (YYYY-MM-DD): ");
                String dataNascitaPilota = sc.nextLine();

                System.out.print("Inserisci la nazionalità: ");
                String nazionalitaPilota = sc.nextLine();

                System.out.print("Inserisci il tipo di pilota (Pro - 0, AM - 1, Gentleman Driver - 2): ");
                int tipoPilota = sc.nextInt();
                clearBuffer(sc);

                switch (tipoPilota) {
                    case 0:
                        System.out.print("Inserisci il numero di licenze possedute: ");
                        int numLicenzePilota = sc.nextInt();
                        db.insertQuery("INSERT INTO pilota_pro VALUES ('"+ nomePilota +"', '"
                                        + cognomePilota +"', "+ numGaraRegistrazione +", '"
                                        + dataNascitaPilota +"', '"+ nazionalitaPilota +"', "
                                        + numLicenzePilota +", 'Ferrari')");
                        break;
                    case 1:
                        System.out.print("Inserisci la data di acquisizione della licenza (YYYY-MM-DD): ");
                        String dataAcquisizioneLicenzaAM = sc.nextLine();
                        db.insertQuery("INSERT INTO pilota_am VALUES ('"+ nomePilota +"', '"+ cognomePilota +"', "
                                        + numGaraRegistrazione +", '"+ dataNascitaPilota +"', '"+ nazionalitaPilota
                                        + "', '"+ dataAcquisizioneLicenzaAM +"', 'Ferrari')");
                        break;
                    case 2:
                        System.out.print("Inserisci la data di acquisizione della licenza (YYYY-MM-DD): ");
                        String dataAcquisizioneLicenzaGD = sc.nextLine();
                        System.out.print("Inserisci la quota finanziata: ");
                        int quotaFinanziata = sc.nextInt();
                        sc.nextLine();
                        db.insertQuery("INSERT INTO pilota_am VALUES ('"+ nomePilota +"', '"+ cognomePilota +"', "
                                        + numGaraRegistrazione +", '"+ dataNascitaPilota +"', '"
                                        + nazionalitaPilota +"', '"+ dataAcquisizioneLicenzaGD +"', 'Ferrari')");
                        break;
                }
                System.out.print("Inserisci il nome dell'equipaggio di cui fa parte il pilota: ");
                String equipaggioPilota = sc.nextLine();
                break;
            case 4:
                System.out.print("Inserisci cognome del Gentleman Driver: ");
                String cognomeGentlemanDriver = sc.nextLine();

                set = db.execQuery("SELECT quota_finanziamenti, scuderia_finanziata FROM gentleman_driver " +
                                                "WHERE cognome = '"+ cognomeGentlemanDriver +"'");

                float quotaFinanziamenti = set.getFloat("quota_finanziamenti");
                String scuderiaFinanziata = set.getString("scuderia_finanziata");

                System.out.print("Inserisci l'importo del finanziamento: ");
                float newQuotaFinanziamenti = quotaFinanziamenti + sc.nextFloat();
                clearBuffer(sc);

                db.insertQuery("UPDATE gentleman_driver SET quota_finanziamenti = "+ newQuotaFinanziamenti +
                                " WHERE cognome = '"+ cognomeGentlemanDriver +"'");

                set = db.execQuery("SELECT finanziamenti FROM scuderia WHERE nome = '"+ scuderiaFinanziata +"'");
                int numFinanziamenti = set.getInt("finanziamenti") + 1;
                db.insertQuery("UPDATE scuderia SET finanziamenti = " + numFinanziamenti
                                + " WHERE nome = '"+ scuderiaFinanziata +"'");
                break;
            case 5:
                System.out.println("ISCRIZIONE VETTURA ALLA GARA");
                System.out.print("Inserisci il numero di gara della vettura: ");
                int numGaraIscrizione = sc.nextInt();
                clearBuffer(sc);
                System.out.print("Inserisci il nome della gara: ");
                String garaIscrizione = sc.nextLine();
                System.out.print("Inserisci la data in cui si svolge la gara: ");
                String dataIscrizione = sc.nextLine();
                db.insertQuery("INSERT INTO iscrizione(vettura, gara, data_evento, punti, motivo_ritiro) VALUES ("
                                + numGaraIscrizione +", '"+ garaIscrizione +"', '"+ dataIscrizione +"', NULL, NULL)");
                break;
            case 6:
                System.out.println("INSERIMENTO RISULTATI DI UNA GARA");
                System.out.println("LISTA GARE");
                set = db.execQuery("SELECT * FROM gara");
                while(set.next()){
                    System.out.println(set.getString("nome") +" "
                                        + set.getDate("data_evento" + " ")
                                        + set.getInt("durata_ore" + " ")
                                        + set.getString("tipo_gara" + " ")
                                        + set.getString("circuito")
                    );
                }

                System.out.print("Seleziona una gara di cui inserire i risultati: ");
                String nomeGara = sc.nextLine();

                set = db.execQuery("SELECT * FROM iscrizione WHERE gara = '" + nomeGara + "'");

                while(set.next()){
                    System.out.print("Inserisci il numero di punti per la vettura "
                                    + set.getInt("vettura")+ ": ");
                    int punti = sc.nextInt();
                    clearBuffer(sc);
                    db.insertQuery("UPDATE iscrizione " +
                                    "SET punti = "+ punti +
                                    " WHERE id_iscrizione = " + set.getInt("id_iscrizione"));
                }

                break;
            case 7:
                System.out.println("VERIFICA MONTAGGIO COMPONENTE");
                System.out.println("LISTA VETTURE");

                set = db.execQuery("SELECT * FROM vettura");

                while (set.next()){
                    System.out.println(set.getInt("num_gara")+
                                        "\t"+ set.getString("modello"));
                }

                System.out.print("Inserisci il numero della vettura su cui " +
                                "verificare la possibilità di inserire un componente: ");

                int numeroVettura = sc.nextInt();
                clearBuffer(sc);

                set = db.execQuery("SELECT * FROM vettura WHERE num_gara = " + numeroVettura);

                while(set.next()){
                    if(set.getString("telaio") == null)
                        System.out.println("Il telaio può essere installato.");
                    else
                        System.out.println("Non è possibile installare un telaio.");

                    if(set.getString("cambio") == null)
                        System.out.println("Il cambio può essere installato.");
                    else
                        System.out.println("Non è possibile installare un cambio.");

                    if(set.getString("motore") == null)
                        System.out.println("Il motore può essere installato.");
                    else
                        System.out.println("Non è possibile installare un motore.");
                }
                break;
            case 8:
                System.out.println("SOMMA FINANZIAMENTI PER CIASCUNA SCUDERIA");

                set = db.execQuery("SELECT sum(quota_finanziamenti), gentleman_driver.scuderia_finanziata \n" +
                                    "FROM gentleman_driver \n" +
                                    "INNER JOIN scuderia ON gentleman_driver.scuderia_finanziata = scuderia.nome\n" +
                                    "WHERE scuderia_finanziata \n" +
                                    "IN (SELECT nome FROM scuderia) \n" +
                                    "GROUP BY gentleman_driver.scuderia_finanziata;");

                while (set.next()){
                    System.out.println(set.getInt("sum(quota_finanziamenti)") + " $\t" + set.getString("scuderia_finanziata"));
                }

                break;
            case 9:
                System.out.println("STAMPA DELLE SCUDERIE CHE HANNO PRESO PARTE AL CAMPIONATO");

                set = db.execQuery("SELECT * FROM scuderia");

                while (set.next()){
                    System.out.println(set.getString("nome")+"\t"
                                        + set.getString("sede_principale")+"\t"
                                        + set.getInt("finanziamenti")+"\t"
                                        + set.getString("equipaggio")
                    );
                }

                break;

        }
    }

    static void clearBuffer(Scanner sc){
        sc.nextLine();
    }
}