package db_lab;

import db_lab.data.Prodotti;
import db_lab.data.Product;
import db_lab.data.ProductPreview;
import db_lab.data.Turni;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;

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
/*Tutto ciò che è sopra va bene */
    public void productPage(Product product) {
        freshPane(cp -> {
            cp.add(new JLabel(product.name));
            cp.add(new JLabel(" "));
            cp.add(new JLabel(product.description));
            cp.add(new JLabel(" "));
            product.composition
                .entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(entry -> {
                    var percent = Math.round(entry.getValue() * 100) + "%";
                    return "- " + entry.getKey().description + " " + percent;
                })
                .forEach(entry -> cp.add(new JLabel(entry)));

            cp.add(new JLabel(" "));
            cp.add(button("Go back", () -> this.getController().userClickedBack()));
        });
    }

    public void failedToLoadProduct(ProductPreview productPreview) {
        freshPane(cp -> {
            cp.add(new JLabel("I couldn't load the page for product", SwingConstants.CENTER));
            cp.add(new JLabel(productPreview.name, SwingConstants.CENTER));
            cp.add(new JLabel(" "));
            cp.add(button("Retry", () -> this.getController().userClickedPreview(productPreview)));
            cp.add(button("Go back", () -> this.getController().userClickedBack()));
        });
    }

    //Buono
    public void loginPage() {
        freshPane(cp -> {
            cp.add(new JLabel("Dipendente", SwingConstants.CENTER));
            final JTextField dipendenteCF = new JTextField("Codice dipendente", SwingConstants.CENTER);
            cp.add(dipendenteCF);
            cp.add(new JLabel(" "));
            cp.add(new JLabel("Password", SwingConstants.CENTER));
            final JTextField password = new JTextField("Password dipendente", SwingConstants.CENTER);
            cp.add(password);
            cp.add(new JLabel(" "));
            cp.add(button("Login", () -> this.getController().userClickedLogin(dipendenteCF.getText(), password.getText())));

            cp.add(new JLabel("FUNZIONI AGGREGATE", SwingConstants.CENTER));
            cp.add(button("Gusto più popolare", () -> this.getController().showGustoPopolare()));
            cp.add(button("Prodotto più popolare", () -> this.getController().showProdottoPopolare()));
            cp.add(button("Mese con più alto n di ordini", () -> this.getController().showBestMese()));
            cp.add(button("Ricavo mese attuale", () -> this.getController().showRicavoMensile()));
            cp.add(button("Fascia oraria più affolata", () -> this.getController().showFasciaOraria()));
            //cp.add(button("Visualizza calorie totali di un gusto", () -> this.getController().ShowGustoCalorie(?)));
        });
    }

    //Buono
    public void privateArea(String dipendente) {
        
        System.out.println("ciao");
        freshPane(cp -> {
            cp.add(new JLabel("Benvenuto "+dipendente, SwingConstants.CENTER));
            cp.add(button("Crea Ordini", () -> this.getController().createOrdini(dipendente)));
            cp.add(button("Visualizza i miei turni", () -> this.getController().showTurni(dipendente)));
            cp.add(button("Crea nuova dose gusto", () -> this.getController().createDoseGusto(dipendente)));
            cp.add(button("Iscrivi cliente", () -> this.getController().createCliente(dipendente)));
            cp.add(button("Logout", () -> this.getController().userRequestedInitialPage()));
        });
    }

    public void turniDipendentePage(List<Turni> turni, String dipendente) {
        freshPane(cp -> {
            this.addTurni(cp, turni);
            // cp.add(new JLabel(, SwingConstants.CENTER));
            cp.add(button("Go Back", () -> this.privateArea(dipendente)));
        });
    }

    private void addTurni(Container cp, List<Turni> turni) {
        Collections.sort(turni, Comparator.comparing(t -> t.giornoSettimana));
        turni.forEach(turno -> {
            var label = "- " + turno.giornoSettimana + " [" + turno.fasciaOraria + "]";
            cp.add(new JLabel(label), SwingConstants.CENTER);
        });
    }


    //FUNZIONI AGGREGATE

    public void gustoPopolarePage(Map<String, Float> result) {
        freshPane(cp -> {
            this.addGustiPopolari(cp, result);
            // cp.add(new JLabel(, SwingConstants.CENTER));
            cp.add(button("Logout", () -> this.getController().userRequestedInitialPage()));
        });
    }

    public void mesiPopolariPage(Map<String, Integer> result) {
        freshPane(cp -> {
            this.addMesiPopolari(cp, result);
            // cp.add(new JLabel(, SwingConstants.CENTER));
            cp.add(button("Logout", () -> this.getController().userRequestedInitialPage()));
        });
    }

    public void prodottiPopolariPage(Map<String, Integer> prodottiPopolari){
        freshPane(cp -> {
            this.addProdottiPopolari(cp, prodottiPopolari);
            // cp.add(new JLabel(, SwingConstants.CENTER));
            cp.add(button("Logout", () -> this.getController().userRequestedInitialPage()));
        });
    }

    public void ricavoMensilePage(String ricavo){
        freshPane(cp -> {
            cp.add(new JLabel(ricavo));
            // cp.add(new JLabel(, SwingConstants.CENTER));
            cp.add(button("Logout", () -> this.getController().userRequestedInitialPage()));
        });
    }

    private void addGustiPopolari(Container cp, Map<String, Float> gustiPopolari) {
        gustiPopolari.keySet().stream()
                .sorted()
                .forEach(name -> {
                    Float value = gustiPopolari.get(name);
                    var label = "- " + name + " [" + value + " kg]";
                    cp.add(new JLabel(label), SwingConstants.CENTER);
                });
    }

    private void addMesiPopolari(Container cp, Map<String, Integer> mesiPopolari) {
        mesiPopolari.keySet().stream()
                .sorted()
                .forEach(name -> {
                    Integer value = mesiPopolari.get(name);
                    var label = "- " + name + " [" + value + " ordini]";
                    cp.add(new JLabel(label), SwingConstants.CENTER);
                });
    }

    private void addProdottiPopolari(Container cp, Map<String, Integer> prodottiPopolari){
        prodottiPopolari.keySet().stream()
                .sorted()
                .forEach(name -> {
                    System.out.println(name);
                    Integer value = prodottiPopolari.get(name);
                    System.out.println("value: "+value+"   Name: "+name.toString());
                    var label = "- " + name + " [" + value + "]";
                    cp.add(new JLabel(label), SwingConstants.CENTER);
                });
    }
    
    private void addPreviews(Container cp, List<ProductPreview> productPreviews) {
        productPreviews.forEach(preview -> {
            var tags = preview.tags
                .stream()
                .map(tag -> tag.name)
                .sorted((tag1, tag2) -> tag1.compareTo(tag2))
                .collect(Collectors.joining(","));
            var label = "- " + preview.name + " [" + tags + "]";
            cp.add(clickableLabel(label, () -> this.getController().userClickedPreview(preview)));
        });
    }


    public void failedToLoadPreviews() {
        freshPane(cp -> {
            cp.add(new JLabel("I couldn't load the previews", SwingConstants.CENTER));
            //cp.add(button("Retry", () -> this.getController().userClickedReloadPreviews()));
        });
    }

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
