package db_lab;

import db_lab.data.DAOException;
import db_lab.data.ProductPreview;
import db_lab.model.Model;
import java.util.Objects;

// The controller provides a holistic description of how the outside world can
// interact with our application: each public method is written as
//
//   subject + action + object (e.g. user + clicked + preview)
//
// So just by reading all the methods we know of all the possible interactions
// that can happen in our app. This makes it simpler to track all the possible
// actions that can take place as the application grows.
//
public final class Controller {

    // The controller holds a reference to the:
    //   - model: to have it load new data
    //   - view: to update it as new data is loaded
    //
    //    ┌────── updates ──────┐
    //    │                     │
    // ┌──▼┐                 ┌─┴────────┐ updates ┌──────┐
    // │view│                 │controller├─────────►model│
    // └──┬─┘                 └─▲───────┘         └──────┘
    //    │       notifies      │
    //    └────── of user's ────┘
    //            actions
    //
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

    

    public void createOrdini(String dipendente){
        /*deve visualizzare una pagina per poter creare l'ordine, in quella pagina poi verrà 
         * presa la query dal model
         */
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

    public void createCliente(String dipendente){

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

    }



    public void userClickedPreview(ProductPreview productPreview) {
        try {
            var product = this.model.find(productPreview.code);
            if (product.isPresent()) {
                this.view.productPage(product.get());
            } else {
                this.view.failedToLoadProduct(productPreview);
            }
        } catch (DAOException e) {
            this.view.failedToLoadProduct(productPreview);
        }
    }

    public void userClickedBack() {
        if (this.model.loadedPreviews()) {
            //this.view.previewPage(this.model.previews());
        } else {
            this.loadInitialPage();
        }
    }

    void loadInitialPage() {
        try {
            //this.view.previewPage(previews);
            this.view.loginPage();

        } catch (DAOException e) {
            e.printStackTrace();
            this.view.failedToLoadPreviews();
        }
    }
}
