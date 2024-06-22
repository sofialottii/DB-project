package db_lab;

import db_lab.data.DAOException;
import db_lab.model.Model;
import java.util.Objects;
import java.util.List;

public final class Controller {

    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        Objects.requireNonNull(model, "Controller created with null model");
        Objects.requireNonNull(view, "Controller created with null view");
        this.view = view;
        this.model = model;
    }

    public void userRequestedInitialPage() {
        this.loadInitialPage();
    }

    public void userClickedLogin(String dipendenteCF, String password) {
        String dipendente = this.model.findDipendente(dipendenteCF, password);
        if(dipendente != "") {
            this.view.privateArea(dipendente);
        } else {
            this.loadInitialPage();
            //dovrà anche far vedere che dipendente e password non sono validi
        }
    }

    

    public void createOrdine(String dipendente){
        var prodotti = this.model.listAllProdotti();
        this.view.scegliProdotto(dipendente, prodotti);
    }

    public List<String> createOrdineSenzaTessera(String dipendente, String clienteCF, float nTessera) {
        return this.model.nuovoOrdineSenzaTessera(dipendente, clienteCF, nTessera);
    }

    public void createComposizione(String dipendente, String data, String orario, String codProdotto, Integer quantita){
        this.model.nuovaComposizione(dipendente, data, orario, codProdotto, quantita);
    }

    public void modifyImportoTotale(String dipendente, String data, String orario, Float importoTotale){
        this.model.cambiaImportoTotale(dipendente, data, orario, importoTotale);
    }

    public float associaOrdineACliente(String clienteCF){
        boolean esiste = this.model.verificaSePuoiCancellareCliente(clienteCF); //se è true il cliente esiste e non è disiscritto
        if (esiste){
            var nTessera = this.model.trovaUltimaTesseraCliente(clienteCF);
            
            
            return nTessera;
        } else {
            return 0; //sarebbe il "falso"
        }
    }

    public void showTurni(String dipendente) {
        var turni = this.model.listTurniDipendente(dipendente);
        this.view.turniDipendentePage(turni, dipendente);
    }

    public void createDoseGusto(String dipendente){
        var gusti = this.model.listAllGusti();
        this.view.scegliGustoPerDose(dipendente, gusti);
    }

    public void createDoseEffettiva(String dipendente, String gusto, Float quantita){
        this.model.registraDose(dipendente, gusto, quantita);
    }

    public boolean createCliente(String dipendenteCF, String clienteCF, String nomeCliente, String cognomeCliente, String dataNascita,
        String email){
        /*controllo che il CF non ci sia*/
        if (this.model.clientePresente(clienteCF) || clienteCF.isEmpty() || nomeCliente.isEmpty() || cognomeCliente.isEmpty()
        || !(dataNascita.length() == 10) ){
            return false;

        } else {
            this.model.registraCliente(dipendenteCF, clienteCF, nomeCliente, cognomeCliente, dataNascita, email);
            return true;
        }
    }

    public boolean deleteCliente(String clienteCF){
        /*controllo che ci sia il CF e non sia già stato Rimosso */
        boolean esiste = this.model.verificaSePuoiCancellareCliente(clienteCF);

        if (esiste){
            this.model.cancellaCliente(clienteCF);
        } 
        return esiste;
    }

    //FUNZIONALITA AGGREGATE

    public void showGustoPopolare() {
        var result = this.model.listGustiPopolari();
        this.view.gustoPopolarePage(result);
    }

    public void showBestMese() {
        var mesi = this.model.listMesiPopolari();
        this.view.mesiPopolariPage(mesi);
    }


    public void showProdottoPopolare(){
        var prodotti = this.model.listProdottiPopolari();
        this.view.prodottiPopolariPage(prodotti);

    }

    public void showRicavoMensile(){
        var ricavo = this.model.calculateRicavoMensile();
        this.view.ricavoMensilePage(ricavo);
    }

    public void showFasciaOraria(){
        var fasceOrarie = this.model.listFasceOrarie();
        this.view.fasceOrariePopolariPage(fasceOrarie);
    }


    void loadInitialPage() {
        try {
            //this.view.previewPage(previews);
            this.view.loginPage();

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
