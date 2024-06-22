package db_lab.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

public final class Clienti {

    public final String CF;
    public final String nome;
    public final String cognome;
    public final String dataNascita;
    public final String email; // e_mail
    public final String dataIscrizione;
    public final String dataDisiscrizione;
    public final String regCF; // Reg_CF

    public Clienti(String CF, String nome, String cognome, String dataNascita, String email, String dataIscrizione,
            String dataDisiscrizione, String regCF) {
        this.CF = CF;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email == null ? "" : email;
        this.dataIscrizione = dataIscrizione;
        this.dataDisiscrizione = dataDisiscrizione;
        this.regCF = regCF;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof Clienti) {
            var p = (Clienti) other;
            return (p.CF.equals(this.CF) &&
                    p.nome.equals(this.nome) &&
                    p.cognome.equals(this.cognome) &&
                    p.dataNascita.equals(this.dataNascita) &&
                    p.email.equals(this.email) &&
                    p.dataIscrizione.equals(this.dataIscrizione) &&
                    p.dataDisiscrizione.equals(this.dataDisiscrizione) &&
                    p.regCF.equals(this.regCF));
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.CF, this.nome, this.cognome, this.dataNascita, this.email,
                this.dataIscrizione, this.dataDisiscrizione, this.regCF);
    }

    @Override
    public String toString() {
        return Printer.stringify(
                "Clienti",
                List.of(
                        Printer.field("CF", this.CF),
                        Printer.field("nome", this.nome),
                        Printer.field("cognome", this.cognome),
                        Printer.field("dataNascita", this.dataNascita),
                        Printer.field("email", this.email),
                        Printer.field("dataIscrizione", this.dataIscrizione),
                        Printer.field("dataDisiscrizione", this.dataDisiscrizione),
                        Printer.field("regCF", this.regCF)));
    }

    public final class DAO {
        public static void registraCliente(Connection connection, String dipendenteCF, String clienteCF, String nomeCliente,
        String cognomeCliente, String dataNascita, String email) {
            
            try (
                    
                    var statement = email.isEmpty() ?
                        DAOUtils.prepare(connection, Queries.CREA_CLIENTE, clienteCF, nomeCliente, cognomeCliente,
                        dataNascita, null, dipendenteCF) :
                        DAOUtils.prepare(connection, Queries.CREA_CLIENTE, clienteCF, nomeCliente, cognomeCliente,
                        dataNascita, email, dipendenteCF);
                    
                    var statement2 = DAOUtils.prepare(connection, Queries.CREA_PRIMA_TESSERA, clienteCF);
                    ) {
                    statement.executeUpdate();
                    statement2.executeUpdate();

                    statement.close();
                    statement2.close();
                
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static boolean clientePresente(Connection connection, String clienteCF){
            try (
                var statement = DAOUtils.prepare(connection, Queries.VISUALIZZA_ESISTENZA_CLIENTE, clienteCF);
                var resultSet = statement.executeQuery();
            ) { if (resultSet.next()) {
                    var count = resultSet.getInt(1); //usa l'indice della colonna
                    return count == 1;
                } else {
                    return false;
                }
            }catch( Exception e)
            {
            throw new DAOException(e);
            }
        }

        public static boolean verificaSePuoiCancellareCliente(Connection connection, String clienteCF) {
            try (
                    var statement = DAOUtils.prepare(connection, Queries.VISUALIZZA_DETERMINATO_CLIENTE, clienteCF);
                    var resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    var count = resultSet.getInt(1); //usa l'indice della colonna
                    return count == 1;
                } else {
                    return false;
                }
            } catch (Exception e) {
                throw new DAOException(e);
            }
        }

        public static void cancellaCliente(Connection connection, String clienteCF){
            try (
                    var statement = DAOUtils.prepare(connection, Queries.DISISCRIVI_CLIENTE, clienteCF);
            ) {
                statement.executeUpdate();
                statement.close();

            } catch (Exception e) {
                throw new DAOException(e);
            }
        }
    }

}
