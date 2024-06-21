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
        SELECT *
        FROM DIPENDENTI
        WHERE CF = ?
        AND password = ?
        """;
}
