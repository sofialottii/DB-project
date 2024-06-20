package db_lab.data;

import java.util.List;
import java.util.Objects;

public final class DosiGusto {
    
    private final String CF;
    private final String data;
    private final String orario;
    private final float quantita;
    private final String nomeGusto;

    public DosiGusto(String CF, String data, String orario, float quantita, String nomeGusto) {
        this.CF = CF;
        this.data = data;
        this.orario = orario;
        this.quantita = quantita;
        this.nomeGusto = nomeGusto;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof DosiGusto) {
            var d = (DosiGusto) other;
            return d.CF.equals(this.CF) &&
                   d.data.equals(this.data) &&
                   d.orario.equals(this.orario) &&
                   d.quantita == this.quantita &&
                   d.nomeGusto.equals(this.nomeGusto);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.CF, this.data, this.orario, this.quantita, this.nomeGusto);
    }

    @Override
    public String toString() {
        return Printer.stringify(
            "DosiGusto",
            List.of(
                Printer.field("CF", this.CF),
                Printer.field("data", this.data),
                Printer.field("orario", this.orario),
                Printer.field("quantita", this.quantita),
                Printer.field("nomeGusto", this.nomeGusto)
            )
        );
    }

    public final class DAO {

    }

}
