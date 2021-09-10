import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TraitementVentes implements VenteContract {
    Vente vente = new Vente();
    TraitementClients tc = new TraitementClients();
    TraitementProduits tp = new TraitementProduits();
    Scanner scanner;

    static ArrayList<Vente> listVentes = new ArrayList<>();
    static ArrayList<Produit> listProduits = new ArrayList<>();

    @Override
    public void Enregistrer() throws IOException {
        Vente vente1 = new Vente();
        Produit p = new Produit();
        listProduits = p.getListProduit();
        scanner = new Scanner(System.in);

        System.out.println("---------------Enregistrement de ventes---------------");

        doBlock:
        {
            do {
                System.out.print("Entrer le code de la vente : ");
                vente1.setCode(scanner.nextLine());
            } while (vente1.getCode().isEmpty());
            do {
                System.out.print("Entrer le code du client : ");
                vente1.setCodeClient(scanner.nextLine());

                int client = tc.rechercherClient(vente1.getCodeClient());
                if (client == -1) {
                    System.out.println("---------------Message---------------" +
                            "\nAucune correspondance, veuillez re-essayez svp!" +
                            "\n---------------------------------------------");
                    vente1.setCodeClient("");
                }

                if (client == -2) {
                    System.out.println("---------------Message---------------" +
                            "\nAucun client enregiste, veuillez re-essayez svp!" +
                            "\n---------------------------------------------");
                    break doBlock;
                }

            } while (vente1.getCodeClient().isEmpty());
            do {
                System.out.print("Entrer le code du produit : ");
                vente1.setCodeProduit(scanner.nextLine());

                int produit = tp.rechercherProduit(vente1.getCodeProduit());
                if (produit == -1) {
                    System.out.println("---------------Message---------------" +
                            "\nAucune correspondance, veuillez re-essayez svp!" +
                            "\n---------------------------------------------");
                    vente1.setCodeProduit("");
                }

                if (produit == -2) {
                    System.out.println("---------------Message---------------" +
                            "\nAucun produit enregistre, veuillez re-essayez svp!" +
                            "\n---------------------------------------------");
                    break doBlock;
                }

            } while (vente1.getCodeProduit().isEmpty());
            do {
                System.out.print("Entrer la quantite : ");
                vente1.setQuantiteAchete(scanner.nextInt());
                int index = tp.rechercherProduit(vente1.getCodeProduit());
                listProduits.get(index).setQuantite(listProduits.get(index).getQuantite() - vente1.getQuantiteAchete());
            } while (vente1.getQuantiteAchete() <= 0);
            do {
                scanner = new Scanner(System.in);
                System.out.print("Entrer l\'etat de vente : ");
                vente1.setEtat(scanner.nextLine());
            } while (vente1.getEtat().isEmpty());
        }

        LocalDate date = LocalDate.now();
        vente1.setDateVente(date.toString());

        vente.enregistrer(vente1);
        sauvegarderVente(vente1, "Ventes");

    }

    @Override
    public void Afficher_la_liste_ventes() {
        listVentes = vente.getListVente();

        System.out.println("---------------Les Ventes---------------");

        if (listVentes.size() > 0) {
            showVente(-1);
        } else {
            System.out.println("Aucune vente a afficher");
        }
    }

    @Override
    public void modifier_la_quantite_produit() {
        String codeVente = "";
        scanner = new Scanner(System.in);

        do {
            System.out.print("Entrer le code de la vente : ");
            codeVente = scanner.nextLine();
        } while (codeVente.isEmpty());

        int indexVt = rechercherVente(codeVente);
        int quantiteProd;

        if (indexVt == -1) {
            System.out.println("Aucune correspondance!");
        } else {
            showVente(indexVt);
            do {
                System.out.print("Entrer la nouvelle quantite : ");
                quantiteProd = scanner.nextInt();
            } while (quantiteProd <= 0);
            listVentes.get(indexVt).setQuantiteAchete(quantiteProd);
            System.out.println("Quantite modifie!");
        }
    }

    public int rechercherVente(String codeVente) {
        listVentes = vente.getListVente();

        if (listVentes.size() > 0) {
            for (int i = 0; i < listVentes.size(); i++) {
                if (listVentes.get(i).getCode().equalsIgnoreCase(codeVente)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int rechercherVenteParCodeClient(String codeClient) {
        listVentes = vente.getListVente();

        if (listVentes.size() > 0) {
            for (int i = 0; i < listVentes.size(); i++) {
                if (listVentes.get(i).getCodeClient().equalsIgnoreCase(codeClient)) {
                    return i;
                }
            }
        } else {
            return -2;
        }

        return -1;
    }

    public void showVente(int value) {
        if (value == -1) {
            listVentes = vente.getListVente();
            for (int i = 0; i < listVentes.size(); i++) {
                System.out.println("Code Vente                 : " + listVentes.get(i).getCode()
                        + "\nCode Client                : " + listVentes.get(i).getCodeClient()
                        + "\nCode Produit               : " + listVentes.get(i).getCodeProduit()
                        + "\nQuantite                   : " + listVentes.get(i).getQuantiteAchete()
                        + "\nDate de vente              : " + listVentes.get(i).getDateVente()
                        + "\nEtat                       : " + listVentes.get(i).getEtat()
                        + "\n---------------------------------------------");
            }
        } else {
            System.out.println("Code Vente                 : " + listVentes.get(value).getCode()
                    + "\nCode Client                : " + listVentes.get(value).getCodeClient()
                    + "\nCode Produit               : " + listVentes.get(value).getCodeProduit()
                    + "\nQuantite                   : " + listVentes.get(value).getQuantiteAchete()
                    + "\nDate de vente              : " + listVentes.get(value).getDateVente()
                    + "\nEtat                       : " + listVentes.get(value).getEtat()
                    + "\n------------------------------------------------");
        }
    }

    public void sauvegarderVente(Vente vente1, String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename + ".txt", true);
        BufferedOutputStream buffer = new BufferedOutputStream(file);

        String line = vente1.getCode() + ";" + vente1.getCodeClient() + ";" + vente1.getCodeProduit() + ";"
                + vente1.getQuantiteAchete() + ";" + vente1.getDateVente() + ";" + vente1.getEtat() + ";\n";

        byte[] bytes = line.getBytes();
        buffer.write(bytes);

        buffer.flush();
        buffer.close();
    }

    public void menuVente() throws IOException {
        int choix = 0;
        scanner = new Scanner(System.in);

        do {
            System.out.println("---------------Menu de Ventes---------------" +
                    "\n1.- Enregister vente" +
                    "\n2.- Afficher les ventes" +
                    "\n3.- Modifier la quantite de produit" +
                    "\n0.- Retournez dans le menu avant");

            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    Enregistrer();
                    break;
                case 2:
                    Afficher_la_liste_ventes();
                    break;
                case 3:
                    modifier_la_quantite_produit();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("---------------Message---------------" +
                            "\nMauvais choix!" +
                            "\n---------------------------------------------");
                    break;
            }
        } while (choix != 0);
    }
}
