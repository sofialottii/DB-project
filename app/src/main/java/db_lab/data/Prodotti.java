package db_lab.data;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Prodotti {

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
        this.numeroGusti = numeroGusti == null ? Optional.empty() : numeroGusti;
        this.prezzoGelato = prezzoGelato == null ? Optional.empty() : prezzoGelato;
        this.pesoVaschetta = pesoVaschetta == null ? Optional.empty() : pesoVaschetta;
        this.prezzoVaschetta = prezzoVaschetta == null ? Optional.empty() : prezzoVaschetta;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Prodotti) {
            var p = (Prodotti) other;
            return (p.codProdotto.equals(this.codProdotto) &&
                    p.tipoProdotto.equals(this.tipoProdotto) &&
                    p.tipoGelato.equals(this.tipoGelato) &&
                    p.numeroGusti == this.numeroGusti &&
                    p.prezzoGelato == this.prezzoGelato &&
                    p.pesoVaschetta == this.prezzoVaschetta &&
                    p.prezzoVaschetta == this.prezzoVaschetta);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codProdotto, this.tipoProdotto, this.tipoGelato,
                this.numeroGusti, this.prezzoGelato,
                this.pesoVaschetta, this.prezzoVaschetta);
    }

    @Override
    public String toString() {
        return Printer.stringify(
                "Prodotti",
                List.of(
                        Printer.field("codProdotto", this.codProdotto),
                        Printer.field("tipoProdotto", this.tipoProdotto),
                        Printer.field("tipoGelato", this.tipoGelato),
                        Printer.field("numeroGusti", this.numeroGusti.orElse(null)),
                        Printer.field("prezzoGelato", this.prezzoGelato.orElse(null)),
                        Printer.field("pesoVaschetta", this.pesoVaschetta.orElse(null)),
                        Printer.field("prezzoVaschetta", this.prezzoVaschetta.orElse(null))));
    }

    public final class DAO {

    }

}
