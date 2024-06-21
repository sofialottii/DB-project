package db_lab.data;

public final class Queries {
    public static final String TAGS_FOR_PRODUCT =
    //query che cambia ogni volta che la eseguiamo
        """
        select t.tag_name
        from TAGGED t
        where t.product_code = ?
        """;

    public static final String LIST_PRODUCTS =
    //query statica senza parametri
        """
        select p.code, p.name
        from PRODUCT p
        """;

    public static final String PRODUCT_COMPOSITION =
        """
        select m.code, m.description, c.percent
        from COMPOSITION c, MATERIAL m
        where c.product_code = ?
        and c.material_code = m.code
        """;

    public static final String FIND_PRODUCT =
        """
        select p.name, p.description
        from PRODUCT p
        where p.code = ?
        """;

    public static final String LOGIN_DIPENDENTE =
        """
        SELECT d.CF
        FROM DIPENDENTI d
        WHERE d.CF = ?
        AND d.password = ?
        AND d.dataLicenziamento IS NULL
        """;

    public static final String SHOW_TURNI = 
        """

        """;

    

    public static final String CREATE_ORDINI =
        """
        INSERT INTO ORDINI (CF, data, orario, importoTotale, Con_CF, Con_numeroTessera)
                    VALUES ( ?, CURDATE(), CURTIME(), ?, ?, ?);
        """;

    public static final String GUSTO_POPOLARE =
        """
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

    public static final String PRODOTTO_POPOLARE = 
        """
        SELECT codProdotto, SUM(quantita) AS totaleQuantita
        FROM composizioni
        GROUP BY codProdotto
        HAVING SUM(quantita) = (
            SELECT MAX(totaleQuantita)
            FROM (
                SELECT SUM(quantita) AS totaleQuantita
                FROM composizioni
                GROUP BY codProdotto
            ) AS Sottoquery
        );
        """;

        public static final String MESE_POPOLARE =
        """
        SELECT MONTH(data) AS mese, COUNT(*) AS numeroOrdini
        FROM ORDINI
        GROUP BY mese
        HAVING COUNT(*) = (
            SELECT MAX(numeroOrdini)
            FROM (
                SELECT COUNT(*) AS numeroOrdini
                FROM ORDINI
                GROUP BY MONTH(data)
            ) AS Sottoquery
        );
        """;
}
