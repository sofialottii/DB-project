package db_lab;

import db_lab.data.Prodotti;
import db_lab.data.Turni;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import javax.swing.*;
import java.util.HashMap;

public final class View {

    private Optional<Controller> controller;
    private final JFrame mainFrame;

    // We take an action to run before closing the view so that one can gracefully
    // deal with open resources.
    public View(Runnable onClose) {
        this.controller = Optional.empty();
        this.mainFrame = this.setupMainFrame(onClose);
    }

    private JFrame setupMainFrame(Runnable onClose) {
        var frame = new JFrame("Gelateria");
        var padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        ((JComponent) frame.getContentPane()).setBorder(padding);
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    onClose.run();
                    System.exit(0);
                }
            }
        );

        return frame;
    }

    private Controller getController() {
        if (this.controller.isPresent()) {
            return this.controller.get();
        } else {
            throw new IllegalStateException(
                """
                The View's Controller is undefined, did you remember to call
                `setController` before starting the application?
                Remeber that `View` needs a reference to the controller in order
                to notify it of button clicks and other changes.
                """
            );
        }
    }

    public void setController(Controller controller) {
        Objects.requireNonNull(controller, "Set null controller in view");
        this.controller = Optional.of(controller);
    }

    public void loginPage() {

        freshPane(cp -> {
            cp.setLayout(new GridLayout(0, 1, 10, 10)); // GridLayout con spaziatura tra i componenti
            cp.setBackground(Color.lightGray); // Sfondo grigio chiaro

            // Titolo e campo Codice dipendente
            JLabel labelDipendente = new JLabel("Dipendente", SwingConstants.CENTER);
            labelDipendente.setFont(new Font("Arial", Font.BOLD, 18)); // Stile del titolo
            labelDipendente.setForeground(Color.BLUE); // Colore del testo
            cp.add(labelDipendente);
            final JTextField dipendenteCF = new JTextField("Codice dipendente", SwingConstants.CENTER);
            dipendenteCF.setForeground(Color.GRAY); // Colore del testo grigio
            cp.add(dipendenteCF);

            // FocusListener per gestire il testo predefinito
            dipendenteCF.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (dipendenteCF.getText().equals("Codice dipendente")) {
                        dipendenteCF.setText("");
                        dipendenteCF.setForeground(Color.BLACK); // Cambia il colore del testo quando viene selezionato
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (dipendenteCF.getText().isEmpty()) {
                        dipendenteCF.setText("Codice dipendente");
                        dipendenteCF.setForeground(Color.GRAY); // Ripristina il colore del testo grigio se vuoto
                    }
                }
            });

            // Spazio vuoto decorativo
            cp.add(Box.createRigidArea(new Dimension(0, 10))); // Spazio vuoto con altezza 10

            // Titolo e campo Password
            JLabel labelPassword = new JLabel("Password", SwingConstants.CENTER);
            labelPassword.setFont(new Font("Arial", Font.BOLD, 18)); // Stile del titolo
            labelPassword.setForeground(Color.BLUE); // Colore del testo
            cp.add(labelPassword);
            final JPasswordField password = new JPasswordField("Password dipendente", SwingConstants.CENTER);
            password.setForeground(Color.GRAY); // Colore del testo grigio
            cp.add(password);

            // FocusListener per gestire il testo predefinito
            password.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (new String(password.getPassword()).equals("Password dipendente")) {
                        password.setText("");
                        password.setForeground(Color.BLACK); // Cambia il colore del testo quando viene selezionato
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (new String(password.getPassword()).isEmpty()) {
                        password.setText("Password dipendente");
                        password.setForeground(Color.GRAY); // Ripristina il colore del testo grigio se vuoto
                    }
                }
            });

            // Pulsante di Login con colore di sfondo personalizzato
            JButton loginButton = button("Login", () -> this.getController().userClickedLogin(dipendenteCF.getText(), new String(password.getPassword())));
            loginButton.setBackground(Color.CYAN); // Colore di sfondo ciano
            loginButton.setForeground(Color.BLACK); // Colore del testo nero
            cp.add(loginButton);

            // Titolo per le funzioni aggregate
            JLabel labelFunzioni = new JLabel("FUNZIONI AGGREGATE", SwingConstants.CENTER);
            labelFunzioni.setFont(new Font("Arial", Font.BOLD, 18)); // Stile del titolo
            labelFunzioni.setForeground(Color.BLACK); // Colore del testo
            cp.add(labelFunzioni);

            // Pulsanti per le funzioni aggregate con colori personalizzati
            cp.add(button("Gusto piu popolare", () -> this.getController().showGustoPopolare()));
            cp.add(button("Prodotto piu popolare", () -> this.getController().showProdottoPopolare()));
            cp.add(button("Mese con piu alto numero di ordini", () -> this.getController().showBestMese()));
            cp.add(button("Ricavo mese attuale", () -> this.getController().showRicavoMensile()));
            cp.add(button("Fascia oraria piu affollata", () -> this.getController().showFasciaOraria()));

            // Aggiunta di margine per migliorare l'aspetto
            ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        });
    
    }

    //Buono
    public void privateArea(String dipendente) {
        
        freshPane(cp -> {
            cp.setLayout(new GridLayout(0, 1, 10, 10)); // GridLayout con una sola colonna e spaziatura di 10 pixel

            cp.add(new JLabel("Benvenuto "+dipendente, SwingConstants.CENTER));
            cp.add(button("Crea Ordini", () -> this.getController().createOrdine(dipendente)));
            cp.add(button("Visualizza i miei turni", () -> this.getController().showTurni(dipendente)));
            cp.add(button("Crea nuova dose gusto", () -> this.getController().createDoseGusto(dipendente)));
            cp.add(button("Iscrivi cliente", () -> this.createClientePage(dipendente)));
            cp.add(button("Disiscrivi cliente", () -> this.deleteClientePage(dipendente)));
            cp.add(button("Logout", () -> this.getController().userRequestedInitialPage()));
        });
    }

    /*visualizzazione turni */

    public void turniDipendentePage(List<Turni> turni, String dipendente) {
        freshPane(cp -> {
            this.esteticaAggregate(cp, "Turni dipendente "+dipendente);
            this.addTurni(cp, turni);
            
            JButton goBackButton = button("Go Back", () -> this.privateArea(dipendente));
            cp.add(goBackButton);

            ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


            /*this.addTurni(cp, turni);
            cp.add(button("Go Back", () -> this.privateArea(dipendente)));*/
        });
    }

    private void addTurni(Container cp, List<Turni> turni) {
        turni.forEach(turno -> {
            var label = "- " + turno.giornoSettimana + " [" + turno.fasciaOraria + "]";
            JLabel turnoLabel = new JLabel(label, SwingConstants.CENTER);
            cp.add(turnoLabel, SwingConstants.CENTER);

            //turnoLabel.setFont(new Font("Arial", Font.BOLD, 14));
            turnoLabel.setForeground(Color.BLACK); // Colore del testo
            cp.add(turnoLabel);
            
            //cp.add(new JLabel(label), SwingConstants.CENTER);
        });
    }

    /* creazione nuovo ordine */
    
    public void scegliProdotto(String dipendente, List<Prodotti> allProdotti){
        freshPane( cp -> {
            cp.setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout con due colonne e spaziatura
            var listaQuantita = new HashMap<String, JTextField>(); //codProdotto, quantita
            var listaPrezzi = new HashMap<String, Float>(); //codProdotto, prezzo
            allProdotti.forEach(prodotto -> {
                var label = " [ " + prodotto + " ] ";
                var quantita = new JTextField("0", SwingConstants.CENTER);
                quantita.setEditable(false);
                listaQuantita.put(prodotto.codProdotto, quantita);
                listaPrezzi.put(prodotto.codProdotto, prodotto.tipoProdotto.equals("Gelato") ?
                    prodotto.prezzoGelato.get() : prodotto.prezzoVaschetta.get());
                cp.add(clickableLabel(label, () -> {
                    //this.creaDoseGusto(dipendente, prodotto);
                    var i = Integer.valueOf(quantita.getText()) + 1;
                    quantita.setText(String.valueOf(i));
                }));
                cp.add(quantita);
            });
            JButton goBackButton = button("Go Back", () -> this.privateArea(dipendente));
            cp.add(goBackButton);
            JButton ordineSenzaTessera = button("Crea ordine SENZA Tessera", () -> {
                //funzione che prende il dipendente e le quantità dei prodottti scelti (nell'array gli indici sono in ordine come i prodotti)
                var codOrdine = this.getController().createOrdineSenzaTessera(dipendente, "", 0);
                
                float importoTotale = 0;
                for (String codProdotto : listaQuantita.keySet()) {
                    int quantita = Integer.valueOf(listaQuantita.get(codProdotto).getText());
                    if (quantita != 0) {
                        this.getController().createComposizione(dipendente, codOrdine.get(0), codOrdine.get(1),
                                codProdotto, quantita);
                    }
                    importoTotale += quantita * listaPrezzi.get(codProdotto);
                }

                this.getController().modifyImportoTotale(dipendente, codOrdine.get(0),codOrdine.get(1),importoTotale);
                
                this.privateArea(dipendente);
            });
            cp.add(ordineSenzaTessera);

            JButton ordineConTessera = button("Crea ordine CON Tessera", () -> {
                //List<Integer> quantita = new ArrayList<>();
                this.inserisciDatiTessera(dipendente, listaQuantita, listaPrezzi);
                
                /*var codOrdine = this.getController().createOrdineSenzaTessera(dipendente, "", 0);

                float importoTotale = 0;
                for (String codProdotto : listaQuantita.keySet()) {
                    int quantita = Integer.valueOf(listaQuantita.get(codProdotto).getText());
                    if (quantita != 0) {
                        this.getController().createComposizione(dipendente, codOrdine.get(0), codOrdine.get(1),
                                codProdotto, quantita);
                    }
                    importoTotale += quantita * listaPrezzi.get(codProdotto);
                }

                this.getController().modifyImportoTotale(dipendente, codOrdine.get(0), codOrdine.get(1), importoTotale);

                this.privateArea(dipendente);*/
            });
            cp.add(ordineConTessera);

            cp.add(Box.createGlue());

            ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margini esterni
        });
    }

    private void inserisciDatiTessera(String dipendente, Map<String, JTextField> listaQuantita, Map<String, Float> listaPrezzi){
        freshPane( cp -> {
            cp.setLayout(new GridLayout(0, 1, 10, 10)); // GridLayout con una sola colonna e spaziatura di 10 pixel

            JLabel cfLabel = new JLabel("codice fiscale cliente", SwingConstants.CENTER);
            cp.add(cfLabel);
            final JTextField clienteCF = new JTextField("", SwingConstants.CENTER);
            cp.add(clienteCF);
            cp.add(new JLabel(" "));

            cp.add(button("Crea Ordine", () -> {
                float nTessera = this.getController().associaOrdineACliente(clienteCF.getText());
                if (nTessera == 0) {
                    showErrorLabel(cp, cfLabel, "Dati non validi", "codice fiscale cliente");
                } else {
                    //creazione dell'ordine compreso di tessera

                    var codOrdine = this.getController().createOrdineSenzaTessera(dipendente, clienteCF.getText(), nTessera);

                    float importoTotale = 0;
                    for (String codProdotto : listaQuantita.keySet()) {
                        int quantita = Integer.valueOf(listaQuantita.get(codProdotto).getText());
                        if (quantita != 0) {
                            this.getController().createComposizione(dipendente, codOrdine.get(0), codOrdine.get(1),
                                    codProdotto, quantita);
                        }
                        importoTotale += quantita * listaPrezzi.get(codProdotto);
                    }

                    this.getController().modifyImportoTotale(dipendente, codOrdine.get(0), codOrdine.get(1),
                            importoTotale);

                    this.privateArea(dipendente);
                }
            }));

            JButton goBackButton = button("Go Back", () -> this.privateArea(dipendente));
            cp.add(goBackButton);

            ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margini esterni

        });
    }


    /*creazione dosi gusto */

    public void scegliGustoPerDose(String dipendente, List<String> allGusti) {
        freshPane(cp -> {
            cp.setLayout(new GridLayout(0, 2, 10, 10)); // GridLayout con due colonne e spaziatura

            allGusti.forEach(gusto -> {
                var label = " [ " + gusto + " ] ";
                cp.add(clickableLabel(label, () -> this.creaDoseGusto(dipendente, gusto)));
            });
            JButton goBackButton = button("Go Back", () -> this.privateArea(dipendente));
            cp.add(goBackButton);

            cp.add(Box.createGlue());

            ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margini esterni
        });
    }

    public void creaDoseGusto(String dipendente, String gusto){
        freshPane( cp -> {
            JLabel quantitaLabel = new JLabel("Selezionare quantita (in kg)", SwingConstants.CENTER); 
            cp.add(quantitaLabel);
            final JTextField quantita = new JTextField("", SwingConstants.CENTER);
            cp.add(quantita);

            cp.add(button("Crea dose", () -> {
                if (quantita.getText().isEmpty()){
                    showErrorLabel(cp, quantitaLabel, "Quantita non valida", "Selezionare quantita (in kg)");
                } else {
                    this.getController().createDoseEffettiva(dipendente, gusto, Float.valueOf(quantita.getText()));
                    this.privateArea(dipendente);
                }
            }));

            JButton goBackButton = button("Go Back", () -> this.privateArea(dipendente));
            cp.add(goBackButton);

        });
        
    }

    /* creazione di un nuovo cliente */

    public void createClientePage(String dipendente){
        freshPane(cp -> {
            JLabel cfLabel = new JLabel("codice fiscale cliente", SwingConstants.CENTER);
            cp.add(cfLabel);
            final JTextField clienteCF = new JTextField("", SwingConstants.CENTER);
            cp.add(clienteCF);
            cp.add(new JLabel(" "));
            cp.add(new JLabel("nome cliente", SwingConstants.CENTER));
            final JTextField nomeCliente = new JTextField("", SwingConstants.CENTER);
            cp.add(nomeCliente);
            cp.add(new JLabel(" "));
            cp.add(new JLabel("cognome cliente", SwingConstants.CENTER));
            final JTextField cognomeCliente = new JTextField("", SwingConstants.CENTER);
            cp.add(cognomeCliente);
            cp.add(new JLabel(" "));
            cp.add(new JLabel("Data di nascita (formato aaaa-mm-gg)", SwingConstants.CENTER));
            final JTextField dataNascita = new JTextField("", SwingConstants.CENTER);
            cp.add(dataNascita);
            cp.add(new JLabel(" "));
            cp.add(new JLabel("E-mail (opzionale)", SwingConstants.CENTER));
            final JTextField email = new JTextField("", SwingConstants.CENTER);
            cp.add(email);
            
            cp.add(button("Crea",
                    () -> {
                        boolean x = this.getController().createCliente(dipendente, clienteCF.getText(), nomeCliente.getText(),
                        cognomeCliente.getText(), dataNascita.getText(), email.getText());
                        if (x){
                            this.privateArea(dipendente);
                        } else {
                            showErrorLabel(cp, cfLabel, "Uno o piu campi errati o CF gia presente",
                            "codice fiscale cliente");
                        }
                    }));
            JButton goBackButton = button("Go Back", () -> this.privateArea(dipendente));
            cp.add(goBackButton);

        });
    }

    /* disiscrizione di un cliente a data corrente */

    public void deleteClientePage(String dipendente){
        freshPane(cp -> {
            JLabel cfLabel = new JLabel("codice fiscale cliente da disiscrivere", SwingConstants.CENTER);
            cp.add(cfLabel);

            final JTextField clienteCF = new JTextField("", SwingConstants.CENTER);
            cp.add(clienteCF);

            cp.add(button("Disiscrivi",
                    () -> {
                        boolean x = this.getController().deleteCliente(clienteCF.getText());
                        if (x){
                            this.privateArea(dipendente);
                        } else {
                            showErrorLabel(cp, cfLabel, "CF cliente inesistente o gia disiscritto", 
                                    "codice fiscale cliente da disiscrivere");
                        }
                        
                    }));
            JButton goBackButton = button("Go Back", () -> this.privateArea(dipendente));
            cp.add(goBackButton);
        });
    }

    private void showErrorLabel(Container cp, JLabel label, String newText, String oldText) {
        label.setText(newText);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                label.setText(oldText);
                timer.cancel();
            }
        }, 3000); // 3000 millisecondi = 3 secondi
    }


    /********FUNZIONI AGGREGATE**************************/

    
    public void gustoPopolarePage(Map<String, Float> result) {
        freshPane(cp -> {
            this.addGustiPopolari(cp, result);
            cp.add(button("Go back", () -> this.getController().userRequestedInitialPage()));
        });
    }

    public void mesiPopolariPage(Map<String, Integer> result) {
        freshPane(cp -> {
            this.addMesiPopolari(cp, result);
            cp.add(button("Go back", () -> this.getController().userRequestedInitialPage()));
        });
    }

    public void prodottiPopolariPage(Map<String, Integer> prodottiPopolari){
        freshPane(cp -> {
            this.addProdottiPopolari(cp, prodottiPopolari);
            cp.add(button("Go back", () -> this.getController().userRequestedInitialPage()));
        });
    }

    public void ricavoMensilePage(String ricavo){
        freshPane(cp -> {
            JLabel label = new JLabel(ricavo, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            cp.add(label);
            cp.add(button("Go back", () -> this.getController().userRequestedInitialPage()));
        });
    }

    public void fasceOrariePopolariPage(Map<String, Integer> fasceOrarie){
        freshPane(cp -> {
            this.addFascePopolari(cp, fasceOrarie);
            cp.add(button("Go back", () -> this.getController().userRequestedInitialPage()));
        });
    }

    private void addGustiPopolari(Container cp, Map<String, Float> gustiPopolari) {
        this.esteticaAggregate(cp, "Gusti popolari");

        gustiPopolari.keySet().stream()
                .sorted()
                .forEach(name -> {
                    Float value = gustiPopolari.get(name);
                    var label = name + " [" + value + " kg]";
                    JLabel gustiLabel = new JLabel(label, SwingConstants.CENTER);
                    cp.add(gustiLabel);
                });

        ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margini esterni
    }

    private void addMesiPopolari(Container cp, Map<String, Integer> mesiPopolari) {
        this.esteticaAggregate(cp, "Mesi popolari");
        mesiPopolari.keySet().stream()
                .sorted()
                .forEach(name -> {
                    Integer value = mesiPopolari.get(name);
                    var label = name + " [" + value + " ordini]";
                    JLabel mesiLabel = new JLabel(label, SwingConstants.CENTER);
                    cp.add(mesiLabel);
                });
        ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margini esterni
    }

    private void addProdottiPopolari(Container cp, Map<String, Integer> prodottiPopolari){
        this.esteticaAggregate(cp, "Prodotti popolari");
        prodottiPopolari.keySet().stream()
                .sorted()
                .forEach(name -> {
                    Integer value = prodottiPopolari.get(name);
                    var label = name + " [" + value + " vendite]";
                    JLabel prodottiLabel = new JLabel(label, SwingConstants.CENTER);
                    cp.add(prodottiLabel);
                });
        ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margini esterni
    }

    private void addFascePopolari(Container cp, Map<String, Integer> fasceOrarie){
        this.esteticaAggregate(cp, "Fasce orarie popolari");

        fasceOrarie.keySet().stream()
                .sorted()
                .forEach(name -> {
                    Integer value = fasceOrarie.get(name);
                    var label = name + " [" + value + " ordini]";
                    JLabel fasceLabel = new JLabel(label, SwingConstants.CENTER);
                    cp.add(fasceLabel);
                });
        ((JComponent) cp).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margini esterni
    }

    private void esteticaAggregate(Container cp, String text) {
        cp.setLayout(new GridLayout(0, 1, 10, 10));
        JLabel titleLabel = new JLabel(text, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        cp.add(titleLabel);
    }

    /* ******************************************** */


    private JButton button(String label, Runnable action) {
        var button = new JButton(label);
        button.addActionListener(event -> {
            button.setEnabled(false);
            SwingUtilities.invokeLater(() -> {
                action.run();
                button.setEnabled(true);
            });
        });
        return button;
    }

    private JLabel clickableLabel(String labelText, Runnable action) {
        var label = new JLabel(labelText);
        label.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SwingUtilities.invokeLater(() -> {
                        action.run();
                    });
                }
            }
        );
        return label;
    }

    private void freshPane(Consumer<Container> consumer) {
        var cp = this.mainFrame.getContentPane();
        cp.removeAll();
        consumer.accept(cp);
        cp.validate();
        cp.repaint();
        this.mainFrame.pack();
    }
}
