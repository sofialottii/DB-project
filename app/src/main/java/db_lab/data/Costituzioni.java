package db_lab.data;

import java.util.List;
import java.util.Objects;

public final class Costituzioni {
    public final String nomeGusto;
    public final String codIngrediente;

    public Costituzioni(String nomeGusto, String codIngrediente) {
        this.nomeGusto = nomeGusto;
        this.codIngrediente = codIngrediente;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Costituzioni) {
            var p = (Costituzioni) other;
            return (p.nomeGusto.equals(this.nomeGusto) &&
                    p.codIngrediente.equals(this.codIngrediente));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nomeGusto, this.codIngrediente);
    }

    @Override
    public String toString() {
        return Printer.stringify(
                "Costituzioni",
                List.of(
                        Printer.field("nomeGusto", this.nomeGusto),
                        Printer.field("codIngrediente", this.codIngrediente)));
    }

    public final class DAO {
        // Metodi DAO per l'interazione con il database possono essere definiti qui
    }
}
