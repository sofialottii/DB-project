package db_lab.data;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.sql.Connection;


public final class Partecipazioni {
    
    public final String CF;
    public final String giornoSettimana;
    public final String fasciaOraria;

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
        public static List<Turni> listTurniDipendente(Connection connection, String dipendenteCF) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.SHOW_TURNI, dipendenteCF);
                    var resultSet = statement.executeQuery();) {
                List<Turni> turni = new ArrayList<>();
                while (resultSet.next()) {
                    var giornoSettimana = resultSet.getString("p.giornoSettimana");
                    var fasciaOraria = resultSet.getString("p.fasciaOraria");
                    Turni turno = new Turni(giornoSettimana, fasciaOraria);
                    turni.add(turno);
                }
                return turni;

            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }
}
