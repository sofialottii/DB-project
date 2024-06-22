package db_lab.data;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.sql.Connection;

public final class Prodotti {

    public final String codProdotto;
    public final String tipoProdotto;
    public final String tipoGelato;
    public final Optional<Integer> numeroGusti;
    public final Optional<Float> prezzoGelato;
    public final Optional<Float> pesoVaschetta;
    public final Optional<Float> prezzoVaschetta;

    public Prodotti(String codProdotto, String tipoProdotto, String tipoGelato, Optional<Integer> numeroGusti,
            Optional<Float> prezzoGelato,
            Optional<Float> pesoVaschetta, Optional<Float> prezzoVaschetta) {
        this.codProdotto = codProdotto;
        this.tipoProdotto = tipoProdotto;
        this.tipoGelato = tipoGelato == null ? "" : tipoGelato;
        this.numeroGusti = numeroGusti != null ? numeroGusti : Optional.empty();
        this.prezzoGelato = prezzoGelato != null ? prezzoGelato : Optional.empty();
        this.pesoVaschetta = pesoVaschetta != null ? pesoVaschetta : Optional.empty();
        this.prezzoVaschetta = prezzoVaschetta != null ? prezzoVaschetta : Optional.empty();
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof Prodotti)) {
            return false;
        } else {
            Prodotti p = (Prodotti) other;
            return Objects.equals(this.codProdotto, p.codProdotto)
                    && Objects.equals(this.tipoProdotto, p.tipoProdotto)
                    && Objects.equals(this.tipoGelato, p.tipoGelato)
                    && Objects.equals(this.numeroGusti, p.numeroGusti)
                    && Objects.equals(this.prezzoGelato, p.prezzoGelato)
                    && Objects.equals(this.pesoVaschetta, p.pesoVaschetta)
                    && Objects.equals(this.prezzoVaschetta, p.prezzoVaschetta);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codProdotto, this.tipoProdotto, this.tipoGelato,
                this.numeroGusti, this.prezzoGelato,
                this.pesoVaschetta, this.prezzoVaschetta);
    }

    /*@Override
    public String toString() {
        return Printer.stringify(
                "Prodotti",
                List.of(
                        Printer.field("codProdotto", this.codProdotto),
                        Printer.field("tipoProdotto", this.tipoProdotto),
                        Printer.field("tipoGelato", this.tipoGelato),
                        Printer.field("numeroGusti", this.numeroGusti),
                        Printer.field("prezzoGelato", this.prezzoGelato),
                        Printer.field("pesoVaschetta", this.pesoVaschetta),
                        Printer.field("prezzoVaschetta", this.prezzoVaschetta)));
    }*/

    @Override
    public String toString() {
        String str = this.codProdotto +": "+ this.tipoProdotto + " ";
        if(this.tipoGelato.equals("")) {
            str = str +" "+ this.pesoVaschetta.get() +" gr, "+ this.prezzoVaschetta.get() + " euro";
        } else {
            str = str +" "+ this.tipoGelato +" "+ this.numeroGusti.get() +" gusti, "+ this.prezzoGelato.get() + " euro";
        }
        return str;
    }

    public final class DAO {
        public static List<Prodotti> listAllProdotti(Connection connection) {
            try (
                    var statement = connection.prepareStatement(Queries.VISUALIZZA_ALLPRODOTTI); //uso prepareStatement e non il metodo di Utility prepare
                var resultSet = statement.executeQuery();                           //perchè non ho dei parametri nella query
            )
            {
                List<Prodotti> prodotti = new ArrayList<>();
                while (resultSet.next()) {
                    var codProdotto = resultSet.getString("codProdotto");
                    var tipoProdotto = resultSet.getString("tipoProdotto");
                    var tipoGelato = resultSet.getString("tipoGelato");
                    var numeroGusti = Optional.ofNullable(resultSet.getInt("numeroGusti"));
                    var prezzoGelato = Optional.ofNullable(resultSet.getFloat("prezzoGelato"));
                    var pesoVaschetta = Optional.ofNullable(resultSet.getFloat("pesoVaschetta"));
                    var prezzoVaschetta = Optional.ofNullable(resultSet.getFloat("prezzoVaschetta"));
                    Prodotti prodotto = new Prodotti(codProdotto, tipoProdotto, tipoGelato, 
                        numeroGusti, prezzoGelato, pesoVaschetta, prezzoVaschetta);
                    prodotti.add(prodotto);
                }
                return prodotti;

            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }

}
