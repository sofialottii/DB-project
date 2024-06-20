package db_lab.data;

import java.util.List;
import java.util.Objects;

public class Gusti {

    private final String nomeGusto;
    private final String ricetta;
    private final float calorieTotali;
    private final String tipoGusto;
    private final String disponibilita;
    private final String periodoDisponibilita;

    public Gusti(String nomeGusto, String ricetta, float calorieTotali, String tipoGusto, 
                 String disponibilita, String periodoDisponibilita) {
        this.nomeGusto = nomeGusto;
        this.ricetta = ricetta;
        this.calorieTotali = calorieTotali;
        this.tipoGusto = tipoGusto;
        this.disponibilita = disponibilita == null ? "" : disponibilita;
        this.periodoDisponibilita = periodoDisponibilita == null ? "" : periodoDisponibilita;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Gusti) {
            var g = (Gusti) other;
            return g.nomeGusto.equals(this.nomeGusto) &&
                   g.ricetta.equals(this.ricetta) &&
                   g.calorieTotali == this.calorieTotali &&
                   g.tipoGusto.equals(this.tipoGusto) &&
                   g.disponibilita.equals(this.disponibilita) &&
                   g.periodoDisponibilita.equals(this.periodoDisponibilita);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nomeGusto, this.ricetta, this.calorieTotali, this.tipoGusto, 
                            this.disponibilita, this.periodoDisponibilita);
    }

    @Override
    public String toString() {
        return Printer.stringify(
            "Gusti",
            List.of(
                Printer.field("nomeGusto", this.nomeGusto),
                Printer.field("ricetta", this.ricetta),
                Printer.field("calorieTotali", this.calorieTotali),
                Printer.field("tipoGusto", this.tipoGusto),
                Printer.field("disponibilita", this.disponibilita),
                Printer.field("periodoDisponibilita", this.periodoDisponibilita)
            )
        );
    }

    public final class DAO {

    }

    
}
