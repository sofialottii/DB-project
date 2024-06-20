package db_lab.data;

import java.util.List;
import java.util.Objects;

public class Partecipazioni {
    
    private final String CF;
    private final String giornoSettimana;
    private final String fasciaOraria;

    public Partecipazioni(String CF, String giornoSettimana, String fasciaOraria) {
        this.CF = CF;
        this.giornoSettimana = giornoSettimana;
        this.fasciaOraria = fasciaOraria;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Partecipazioni) {
            var p = (Partecipazioni) other;
            return (p.CF.equals(this.CF) && 
                    p.giornoSettimana.equals(this.giornoSettimana) &&
                    p.fasciaOraria.equals(this.fasciaOraria));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.CF, this.giornoSettimana, this.fasciaOraria);
    }

    @Override
    public String toString() {
        return Printer.stringify(
            "Partecipazioni",
            List.of(
                Printer.field("CF", this.CF),
                Printer.field("giornoSettimana", this.giornoSettimana),
                Printer.field("fasciaOraria", this.fasciaOraria)
            )
        );
    }

    public final class DAO {

    }
}
