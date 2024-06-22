package db_lab.data;

public final class Queries {
        

        public static final String LIST_PRODUCTS =
                        // query statica senza parametri
                        """
                                        select p.code, p.name
                                        from PRODUCT p
                                        """;

        public static final String PRODUCT_COMPOSITION = """
                        select m.code, m.description, c.percent
                        from COMPOSITION c, MATERIAL m
                        where c.product_code = ?
                        and c.material_code = m.code
                        """;

        public static final String FIND_PRODUCT = """
                        select p.name, p.description
                        from PRODUCT p
                        where p.code = ?
                        """;

        public static final String LOGIN_DIPENDENTE = """
                        SELECT d.CF
                        FROM DIPENDENTI d
                        WHERE d.CF = ?
                        AND d.password = ?
                        AND d.dataLicenziamento IS NULL
                        """;

        public static final String SHOW_TURNI = """
                        SELECT p.giornoSettimana, p.fasciaOraria
                        FROM partecipazioni p
                        WHERE p.CF = ?;
                        """;

        public static final String CREATE_ORDINI = """
                        INSERT INTO ORDINI (CF, data, orario, importoTotale, Con_CF, Con_numeroTessera)
                                    VALUES ( ?, CURDATE(), CURTIME(), ?, ?, ?);
                        """;

        public static final String GUSTO_POPOLARE = """
                        SELECT nomeGusto, SUM(quantita) AS totaleQuantita
                        FROM DOSI_GUSTO
                        GROUP BY nomeGusto
                        HAVING SUM(quantita) = (
                            SELECT MAX(totaleQuantita)
                            FROM (
                                SELECT SUM(quantita) AS totaleQuantita
                                FROM DOSI_GUSTO
                                GROUP BY nomeGusto
                            ) AS Sottoquery
                        );
                        """;

        public static final String PRODOTTO_POPOLARE = """
                        SELECT p.codProdotto, p.tipoProdotto, p.tipoGelato, p.numeroGusti, p.pesoVaschetta, SUM(c.quantita) AS totaleQuantita
                        FROM composizioni c
                        JOIN PRODOTTI p ON c.codProdotto = p.codProdotto
                        GROUP BY p.codProdotto, p.tipoProdotto, p.numeroGusti, p.pesoVaschetta
                        HAVING SUM(c.quantita) = (
                            SELECT MAX(totaleQuantita)
                            FROM (
                                SELECT SUM(quantita) AS totaleQuantita
                                FROM composizioni
                                GROUP BY codProdotto
                            ) AS Sottoquery
                        );
                        """;

        public static final String MESE_POPOLARE = """
                        SELECT DATE_FORMAT(data, '%M') AS mese, COUNT(*) AS numeroOrdini
                        FROM ORDINI
                        GROUP BY mese
                        HAVING COUNT(*) = (
                            SELECT MAX(numeroOrdini)
                            FROM (
                                SELECT COUNT(*) AS numeroOrdini
                                FROM ORDINI
                                GROUP BY DATE_FORMAT(data, '%M')
                            ) AS Sottoquery
                        );
                        """;

        public static final String RICAVO_MENSILE = """
                        SELECT DATE_FORMAT(data, '%M') AS mese, SUM(importoTotale) AS ricavoMensile
                        FROM ORDINI
                        WHERE MONTH(data) = MONTH(CURDATE()) AND YEAR(data) = YEAR(CURDATE())
                        GROUP BY mese;
                        """;

        public static final String FASCIA_AFFOLLATA =
        """
        SELECT fasciaOraria, COUNT(*) AS numeroPartecipazioni
        FROM partecipazioni
        GROUP BY fasciaOraria
        HAVING COUNT(*) = (
            SELECT MAX(numeroPartecipazioni)
            FROM (
                SELECT COUNT(*) AS numeroPartecipazioni
                FROM partecipazioni
                GROUP BY fasciaOraria
            ) AS sottoquery
        );
        
        """;

        public static final String CALORIE_TOTALI = """
                        SELECT g.nomeGusto, SUM(i.calorie) AS calorieTotali
                        FROM GUSTI g
                        JOIN costituzioni c ON g.nomeGusto = c.nomeGusto
                        JOIN INGREDIENTI i ON c.codIngrediente = i.codIngrediente
                        WHERE g.nomeGusto = '<nomeGusto>'
                        GROUP BY g.nomeGusto;
                        """;

        public static final String VISUALIZZA_ALLGUSTI =
        """
        SELECT nomeGusto
        FROM GUSTI
        WHERE disponibilita = 'Si' OR disponibilita IS NULL;
        """;

        public static final String CREA_DOSE =
        """
        INSERT INTO DOSI_GUSTO (CF, data, orario, quantita, nomeGusto)
        VALUES (?, CURDATE(), CURTIME(), ?, ?);

        """;

        public static final String VISUALIZZA_ESISTENZA_CLIENTE =
        """
        SELECT COUNT(*)
        FROM CLIENTI
        WHERE CF = ?;      
        """;

        public static final String CREA_CLIENTE =
        """
        INSERT INTO CLIENTI (CF, nome, cognome, dataNascita, e_mail, dataIscrizione, dataDisiscrizione, Reg_CF)
        VALUES (?, ?, ?, ?, ?, CURDATE(), null, ?);
        
        """;

        public static final String CREA_PRIMA_TESSERA =
        """
        INSERT INTO TESSERE (CF, numeroTessera, numeroAcquisti)
        VALUES (?, 1, 0);
        """;
        //automatico avviene subito dopo CREA_CLIENTE

        public static final String CREA_NUOVA_TESSERA =
        """
        INSERT INTO TESSERE (CF, numeroTessera, numeroAcquisti)
        VALUES (?, ?, 0);
        """;

        public static final String VISUALIZZA_DETERMINATO_CLIENTE =
        """
        SELECT COUNT(*)
        FROM CLIENTI
        WHERE CF = ? AND dataDisiscrizione IS NULL;
        """;
        //se il count>=1 allora il cliente c'è ed è attualmente attivo (non disiscritto)

        public static final String DISISCRIVI_CLIENTE =
        """
        UPDATE CLIENTI
        SET dataDisiscrizione = CURDATE()
        WHERE CF = ?;
        """;

        public static final String VISUALIZZA_ALLPRODOTTI =
        """
        SELECT *
        FROM PRODOTTI;
        """;

        public static final String NUOVO_ORDINE_SENZA_TESSERA =
        """
        INSERT INTO ORDINI (CF, data, orario, importoTotale, Con_CF, Con_numeroTessera)
        VALUES (?, ?, ?, 0, ?, ?);
        """;

        public static final String NUOVA_COMPOSIZIONE =
        """
        INSERT INTO COMPOSIZIONI (CF, data, orario, codProdotto, quantita)
        VALUES (?, ?, ?, ?, ?);        
        """;

        public static final String CAMBIA_IMPORTO_TOTALE =
        """
        UPDATE ORDINI
        SET importoTotale = ?
        WHERE CF = ? AND data = ? AND orario = ?;       
        """;

        public static final String TROVA_ULTIMA_TESSERA =
        """
        SELECT MAX(numeroTessera) AS numeroTesseraMax
        FROM TESSERE
        WHERE CF = ?;
        """;

        public static final String TROVA_NUMERO_ACQUISTI_TESSERA =
        """
        SELECT numeroAcquisti
        FROM TESSERE
        WHERE CF = ? AND numeroTessera = ?;        
        """;

        public static final String INCREMENTA_NUMERO_ACQUISTI =
        """
        UPDATE TESSERE
        SET numeroAcquisti = ?
        WHERE CF = ? AND numeroTessera = ?;
        """;
        
        

}
