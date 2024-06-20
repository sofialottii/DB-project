package db_lab.data;

import java.util.List;
import java.util.Objects;

public class Tessere {

    public final String CF;
    public final float numeroTessera;
    public final int numeroAcquisti;

    public Tessere(String CF, float numeroTessera, int numeroAcquisti) {
        this.CF = CF;
        this.numeroTessera = numeroTessera;
        this.numeroAcquisti = numeroAcquisti;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Tessere) {
            var p = (Tessere) other;
            return (p.CF.equals(this.CF) && 
                    p.numeroTessera == this.numeroTessera &&
                    p.numeroAcquisti == this.numeroAcquisti);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.CF, this.numeroTessera, this.numeroAcquisti);
    }

    @Override
    public String toString() {
        return Printer.stringify(
            "numeroAcquisti",
            List.of(
                Printer.field("CF",this.CF),
                Printer.field("giornoSettimana", this.numeroTessera),
                Printer.field("fasciaOraria", this.numeroAcquisti)
            )
        );
    }

    public final class DAO {

    }

}
