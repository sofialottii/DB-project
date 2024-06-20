package db_lab.data;

import java.util.List;
import java.util.Objects;

public class Ingredienti {

    private final String codIngrediente;
    private final float calorie;
    private final String codFornitore;
    private final String nomeIngrediente;

    public Ingredienti(String codIngrediente, float calorie, String codFornitore, String nomeIngrediente) {
        this.codIngrediente = codIngrediente;
        this.calorie = calorie;
        this.codFornitore = codFornitore;
        this.nomeIngrediente = nomeIngrediente;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Ingredienti) {
            var i = (Ingredienti) other;
            return i.codIngrediente.equals(this.codIngrediente) &&
                   i.calorie == this.calorie &&
                   i.codFornitore.equals(this.codFornitore) &&
                   i.nomeIngrediente.equals(this.nomeIngrediente);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.codIngrediente, this.calorie, this.codFornitore, this.nomeIngrediente);
    }

    @Override
    public String toString() {
        return Printer.stringify(
            "Ingredienti",
            List.of(
                Printer.field("codIngrediente", this.codIngrediente),
                Printer.field("calorie", this.calorie),
                Printer.field("codFornitore", this.codFornitore),
                Printer.field("nomeIngrediente", this.nomeIngrediente)
            )
        );
    }

    public final class DAO {

    }
    
}
