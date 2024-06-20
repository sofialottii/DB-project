package db_lab.data;

import java.util.List;
import java.util.Objects;

public final class Turni {

    public final String giornoSettimana;
    public final String fasciaOraria;

    public Turni(String giornoSettimana, String fasciaOraria) {
        this.giornoSettimana = giornoSettimana;
        this.fasciaOraria = fasciaOraria;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Turni) {
            var p = (Turni) other;
            return (
                p.giornoSettimana.equals(this.giornoSettimana) &&
                p.fasciaOraria.equals(this.fasciaOraria)
            );
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.giornoSettimana, this.fasciaOraria);
    }

    @Override
    public String toString() {
        return Printer.stringify(
            "Turni",
            List.of(
                Printer.field("giornoSettimana", this.giornoSettimana),
                Printer.field("fasciaOraria", this.fasciaOraria)
            )
        );
    }

    public final class DAO {

    }


    
}
