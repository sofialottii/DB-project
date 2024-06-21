package db_lab.model;

import db_lab.data.Gusti;
import db_lab.data.Prodotti;
import db_lab.data.Product;
import db_lab.data.ProductPreview;
import db_lab.data.Turni;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Model {
    public Optional<Product> find(int productCode);

    public List<ProductPreview> previews();

    public boolean loadedPreviews();

    public List<ProductPreview> loadPreviews();

    public String findDipendente(String dipendenteCF, String password);

    public Map<String,Float> listGustiPopolari();

    public Map<String,Integer> listProdottiPopolari();

    public Map<String,Integer> listMesiPopolari();

    public String calculateRicavoMensile();

    public List<Turni> listTurniDipendente(String dipendente);

    public List<String> listAllGusti();

    // Create a model that connects to a database using the given connection.
    //
    public static Model fromConnection(Connection connection) {
        return new DBModel(connection);
    }
}
