package db_lab.model;

import db_lab.data.Clienti;
import db_lab.data.Composizioni;
import db_lab.data.Dipendenti;
import db_lab.data.DosiGusto;
import db_lab.data.Gusti;
import db_lab.data.Ordini;
import db_lab.data.Partecipazioni;
import db_lab.data.Prodotti;
import db_lab.data.Tessere;
import db_lab.data.Turni;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// This is the real model implementation that uses the DAOs we've defined to
// actually load data from the underlying database.
//
// As you can see this model doesn't do too much except loading data from the
// database and keeping a cache of the loaded previews.
// A real model might be doing much more, but for the sake of the example we're
// keeping it simple.
//
public final class DBModel implements Model {

    private final Connection connection;
    //private Optional<List<ProductPreview>> previews;

    public DBModel(Connection connection) {
        Objects.requireNonNull(connection, "Model created with null connection");
        this.connection = connection;
        //this.previews = Optional.empty();
    }

    @Override
    public String findDipendente(String dipendenteCF, String password) {
        return Dipendenti.DAO.findDipendente(connection, dipendenteCF, password);
    }

    @Override
    public Map<String, Float> listGustiPopolari() {
        return DosiGusto.DAO.listGustiPopolari(connection);
    }

    @Override
    public Map<String, Integer> listMesiPopolari() {
        return Ordini.DAO.listMesiPopolari(connection);
    }

    @Override
    public Map<String, Integer> listProdottiPopolari() {
        return Composizioni.DAO.listProdottiPopolari(connection);
    }

    @Override
    public String calculateRicavoMensile() {
        return Ordini.DAO.calculateRicavoMensile(connection);
    }

    @Override
    public List<Turni> listTurniDipendente(String dipendente) {
        return Partecipazioni.DAO.listTurniDipendente(connection, dipendente);
    }

    @Override
    public List<String> listAllGusti() {
        return Gusti.DAO.listAllGusti(connection);
    }

    @Override
    public void registraDose(String dipendente, String gusto, Float quantita){
        DosiGusto.DAO.registraDose(connection, dipendente, gusto, quantita);
    }

    @Override
    public void registraCliente(String dipendenteCF, String clienteCF, String nomeCliente, String cognomeCliente,
            String dataNascita, String email) {
        Clienti.DAO.registraCliente(connection, dipendenteCF, clienteCF, nomeCliente, cognomeCliente, dataNascita, email);
    }

    @Override
    public Map<String, Integer> listFasceOrarie() {
        return Partecipazioni.DAO.listFasceOrarie(connection);
    }

    @Override
    public boolean verificaSePuoiCancellareCliente(String clienteCF) {
        return Clienti.DAO.verificaSePuoiCancellareCliente(connection, clienteCF);
    }

    @Override
    public void cancellaCliente(String clienteCF) {
        Clienti.DAO.cancellaCliente(connection, clienteCF);
    }

    @Override
    public boolean clientePresente(String clienteCF) {
        return Clienti.DAO.clientePresente(connection, clienteCF);
    }

    @Override
    public List<Prodotti> listAllProdotti() {
        return Prodotti.DAO.listAllProdotti(connection);
    }

    @Override
    public List<String> nuovoOrdine(String dipendente, String clienteCF, float nTessera) {
        return Ordini.DAO.nuovoOrdine(connection, dipendente, clienteCF, nTessera);
    }

    @Override
    public void nuovaComposizione(String dipendente, String data, String orario, String codProdotto, Integer quantita) {
        Composizioni.DAO.nuovaComposizione(connection, dipendente, data, orario, codProdotto, quantita);
    }

    @Override
    public void cambiaImportoTotale(String dipendente, String data, String orario, Float importoTotale) {
        Ordini.DAO.cambiaImportoTotale(connection, dipendente, data, orario, importoTotale);
    }

    @Override
    public float trovaUltimaTesseraCliente(String cliente) {
        float nTessera = Tessere.DAO.trovaUltimaTesseraCliente(connection, cliente);

        int nAcquisti = Tessere.DAO.trovaNumeroAcquistiTessera(connection, cliente, nTessera);

        if (nAcquisti >= 7){
            //creare nuova tessera da associare a cliente, con numero di tessera = nTessera++
            Tessere.DAO.creaNuovaTessera(connection, cliente, nTessera+1);
        }
        Tessere.DAO.aumentaNAcquisti(connection, cliente, nTessera, nAcquisti+1);

        return nTessera;
    }

}
