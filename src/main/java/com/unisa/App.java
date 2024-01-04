package com.unisa;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        Database db = new Database();
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci l'operazione che vuoi eseguire: ");
        int op = sc.nextInt();
        clearBuffer(sc);
        ResultSet set = null;
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

            case 10:

                break;

            case 11:
                System.out.println("PERCENTUALE GENTLEMAN DRIVER PER OGNI SCUDERIA");

                HashMap<String, Integer> piloti = new HashMap<String, Integer>();
                HashMap<String, Integer> gentlemanDriver = new HashMap<String, Integer>();

                set = db.execQuery("SELECT count(equipaggio), equipaggio FROM pilota_am GROUP BY equipaggio");
                while (set.next()){
                    piloti.put(set.getString("equipaggio"), set.getInt("count(equipaggio)"));
                }

                set = db.execQuery("SELECT count(equipaggio), equipaggio FROM pilota_pro GROUP BY equipaggio");
                while (set.next()){
                    if(piloti.containsKey(set.getString("equipaggio"))){
                        piloti.put(set.getString("equipaggio"),
                                piloti.get(set.getString("equipaggio")) + set.getInt("count(equipaggio)"));
                    }
                    else {
                        piloti.put(set.getString("equipaggio"), set.getInt("count(equipaggio)"));
                    }
                }

                set = db.execQuery("SELECT count(equipaggio), equipaggio FROM gentleman_driver GROUP BY equipaggio");
                while (set.next()){
                    gentlemanDriver.put(set.getString("equipaggio"), set.getInt("count(equipaggio)"));
                }

                for (Map.Entry<String, Integer> entry : piloti.entrySet()) {
                    String scuderia = entry.getKey();
                    int numPiloti = entry.getValue();
                    int numGentlemanDriver = gentlemanDriver.getOrDefault(scuderia, 0);

                    // Evita una divisione per zero
                    if (numGentlemanDriver != 0) {
                        double percentualeGentlemanDriver = (double) numGentlemanDriver / (numPiloti + numGentlemanDriver) * 100;
                        System.out.println("Scuderia: " + scuderia + "\t percentuale gentleman driver: " + percentualeGentlemanDriver);
                    } else {
                        System.out.println("Scuderia: " + scuderia + "\t percentuale gentleman driver: 0.0");
                    }
                }
                break;

            case 12:
                System.out.println("STAMPA DEI COSTRUTTORI E DEL NUMERO DI COMPONENTI FORNITI");

                set = db.execQuery("SELECT * FROM costruttore");

                while (set.next()){
                    System.out.println(set.getString("nome") + "\t"
                                        + set.getString("ragione_sociale") + "\t"
                                        + set.getString("sede_fabbrica") + "\t"
                                        + set.getInt("num_componenti_forniti"));
                }

                break;
            case 13:
                System.out.println("STAMPA DELLA CLASSIFICA FINALE DEI PUNTI CONSEGUITI DA TUTTE LE VETTURA");

                set = db.execQuery("SELECT\n" +
                        "    v.num_gara AS num_gara,\n" +
                        "    v.modello as modello,\n" +
                        "    SUM(COALESCE(i.punti, 0)) AS punti_totali\n" +
                        "FROM\n" +
                        "    vettura v\n" +
                        "        LEFT JOIN iscrizione i ON v.num_gara = i.vettura\n" +
                        "GROUP BY\n" +
                        "\n" +
                        "    v.num_gara\n" +
                        "ORDER BY\n" +
                        "    punti_totali DESC");

                while (set.next()){
                    System.out.printf("Numero gara: %d\tModello: %s\tPunti totali: %d\n",
                                        set.getInt("num_gara"),
                                        set.getString("modello"),
                                        set.getInt("punti_totali"));
                }


                break;

            case 14:
                System.out.println("STAMPA DELLE CLASSIFICHE FINALI PER TIPO DI MOTORE");
                set = db.execQuery("SELECT\n" +
                        "    m.tipo_motore,\n" +
                        "    SUM(i.punti) AS punti_totali\n" +
                        "FROM\n" +
                        "    vettura v\n" +
                        "JOIN\n" +
                        "    motore m ON v.motore = m.codice\n" +
                        "JOIN\n" +
                        "    iscrizione i ON v.num_gara = i.vettura\n" +
                        "GROUP BY\n" +
                        "    m.tipo_motore\n" +
                        "ORDER BY\n" +
                        "    punti_totali DESC;\n" +
                        "\n");

                while (set.next()){
                    System.out.printf("Tipo motore: %s\tPunti totali: %d\n", set.getString("tipo_motore"), set.getInt("punti_totali"));
                }
                break;

            case 15:

                //DA TESTARE
                System.out.println("STAMPA DEL REPORT CHE ELENCHI CIASCUNA SCUDERIA SULLA BASE DEL RAPPORTO" +
                        "PUNTI/MINUTI DI GARA, MEDIANDO TRA LE MACCHINE APPARTENENTI A CIASCUNA SCUDERIA");

                set = db.execQuery("SELECT\n" +
                        "    s.nome AS scuderia,\n" +
                        "    AVG(i.punti / g.durata_ore * 60) AS rapporto_punti_minuti\n" +
                        "FROM\n" +
                        "    scuderia s\n" +
                        "JOIN\n" +
                        "    equipaggio e ON s.equipaggio = e.nome\n" +
                        "JOIN\n" +
                        "    pilota_pro p ON e.nome = p.equipaggio\n" +
                        "JOIN\n" +
                        "    vettura v ON p.num_gara = v.num_gara\n" +
                        "JOIN\n" +
                        "    iscrizione i ON v.num_gara = i.vettura\n" +
                        "JOIN\n" +
                        "    gara g ON i.gara = g.nome\n" +
                        "\n" +
                        "GROUP BY\n" +
                        "    s.nome\n" +
                        "ORDER BY\n" +
                        "    rapporto_punti_minuti DESC;");

                while (set.next()){
                    System.out.println(set.getString("scuderia") + "\t" + set.getFloat("rapporto_punti_minuti"));
                }
                break;
        }
        set.close();
        sc.close();
    }

    static void clearBuffer(Scanner sc){
        sc.nextLine();
    }
}