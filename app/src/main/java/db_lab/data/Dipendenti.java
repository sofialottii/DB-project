package db_lab.data;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Dipendenti {
    public final String CF;
    public final String nome;
    public final String cognome;
    public final String dataNascita;
    public final String password;
    public final String IBAN;
    public final Optional<Float> stipendio;
    public final String dataAssunzione;
    public final String dataLicenziamento;

    public Dipendenti(String CF, String nome, String cognome, String dataNascita, String password, String IBAN,
            Optional<Float> stipendio, String dataAssunzione, String dataLicenziamento) {
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.password = password;
        this.IBAN = IBAN;
        this.stipendio = stipendio == null ? Optional.empty() : stipendio;
        this.dataAssunzione = dataAssunzione;
        this.dataLicenziamento = dataLicenziamento == null ? "" : dataLicenziamento;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Dipendenti) {
            var p = (Dipendenti) other;
            return (p.CF.equals(this.CF) &&
                    p.nome.equals(this.nome) &&
                    p.cognome.equals(this.cognome) &&
                    p.dataNascita.equals(this.dataNascita) &&
                    p.password.equals(this.password) &&
                    p.IBAN.equals(this.IBAN) &&
                    p.stipendio.equals(this.stipendio) &&
                    p.dataAssunzione.equals(this.dataAssunzione) &&
                    p.dataLicenziamento.equals(this.dataLicenziamento));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.CF, this.nome, this.cognome, this.dataNascita, this.password,
                this.IBAN, this.stipendio, this.dataAssunzione, this.dataLicenziamento);
    }

    @Override
    public String toString() {
        return Printer.stringify(
                "Dipendenti",
                List.of(
                        Printer.field("CF", this.CF),
                        Printer.field("nome", this.nome),
                        Printer.field("cognome", this.cognome),
                        Printer.field("dataNascita", this.dataNascita),
                        Printer.field("password", this.password),
                        Printer.field("IBAN", this.IBAN),
                        Printer.field("stipendio", this.stipendio.orElse(null)),
                        Printer.field("dataAssunzione", this.dataAssunzione),
                        Printer.field("dataLicenziamento", this.dataLicenziamento)));
    }

    public final class DAO {

    }
}
