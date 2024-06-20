package db_lab.data;

import java.util.List;
import java.util.Objects;

public final class Fornitori {
    
    private final String codFornitore;
    private final String nomeAzienda;
    private final String recapito;

    public Fornitori(String codFornitore, String nomeAzienda, String recapito) {
        this.codFornitore = codFornitore;
        this.nomeAzienda = nomeAzienda;
        this.recapito = recapito;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Fornitori) {
            var f = (Fornitori) other;
            return f.codFornitore.equals(this.codFornitore) &&
                   f.nomeAzienda.equals(this.nomeAzienda) &&
                   f.recapito.equals(this.recapito);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codFornitore, this.nomeAzienda, this.recapito);
    }

    @Override
    public String toString() {
        return Printer.stringify(
            "Fornitori",
            List.of(
                Printer.field("codFornitore", this.codFornitore),
                Printer.field("nomeAzienda", this.nomeAzienda),
                Printer.field("recapito", this.recapito)
            )
        );
    }

    public final class DAO {

    }
}
