package db_lab.data;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Composizioni {
    public final String CF;
    public final String data;
    public final String orario;
    public final String codProdotto;
    public final int quantita;

    public Composizioni(String CF, String data, String orario, String codProdotto, int quantita) {
        this.CF = CF;
        this.data = data;
        this.orario = orario;
        this.codProdotto = codProdotto;
        this.quantita = quantita;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Composizioni) {
            var p = (Composizioni) other;
            return (p.CF.equals(this.CF) &&
                    p.data.equals(this.data) &&
                    p.orario.equals(this.orario) &&
                    p.codProdotto.equals(this.codProdotto) &&
                    p.quantita == this.quantita);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.CF, this.data, this.orario, this.codProdotto, this.quantita);
    }

    @Override
    public String toString() {
        return Printer.stringify(
                "Composizioni",
                List.of(
                        Printer.field("CF", this.CF),
                        Printer.field("data", this.data),
                        Printer.field("orario", this.orario),
                        Printer.field("codProdotto", this.codProdotto),
                        Printer.field("quantita", this.quantita)));
    }

    public final class DAO {
        public static final Map<String, Integer> listProdottiPopolari(Connection connection) {
            try (
                    var statement = connection.prepareStatement(Queries.PRODOTTO_POPOLARE);
                    var resultSet = statement.executeQuery();) {
                var prodottiPopolari = new HashMap<String, Integer>();
                while (resultSet.next()) {
                    var codProdotto = resultSet.getString("p.codProdotto");
                    var tipoProdotto = resultSet.getString("p.tipoProdotto");
                    var tipoGelato = resultSet.getString("p.tipoGelato");
                    var numeroGusti = resultSet.getInt("p.numeroGusti");
                    var pesoVaschetta = resultSet.getFloat("p.pesoVaschetta");
                    var totaleQuantita = resultSet.getInt("totaleQuantita");

                    String stringa = codProdotto + " " + tipoProdotto;
                    stringa = tipoProdotto.equals("Gelato") ? stringa + " " + tipoGelato + ", " + numeroGusti + " gusti"
                            : stringa + " " + pesoVaschetta + " grammi";

                    prodottiPopolari.put(stringa, totaleQuantita);
                }
                return prodottiPopolari;
            } catch (Exception e) {
                throw new DAOException(e);
            }

        }

        public static void nuovaComposizione(Connection connection, String dipendenteCF, String data,
                String orario, String codProdotto, Integer quantita) {

            try (
                    var statement = DAOUtils.prepare(connection, Queries.NUOVA_COMPOSIZIONE, dipendenteCF,
                            data, orario, codProdotto, quantita);) {
                statement.executeUpdate();
                statement.close();

            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
