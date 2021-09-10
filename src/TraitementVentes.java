import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TraitementVentes implements VenteContract {
    Vente vente = new Vente();
    TraitementClients tc = new TraitementClients();
    Scanner scanner;

    ArrayList<Vente> listVentes = new ArrayList<>();


    @Override
    public void Enregistrer() {
        Vente vente1 = new Vente();
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
            } while (vente1.getCodeClient().isEmpty());
            do {
                System.out.print("Entrer le code du produit : ");
                vente1.setCodeProduit(scanner.nextLine());
            } while (vente1.getCodeProduit().isEmpty());
            do {
                System.out.print("Entrer la quantite : ");
                vente1.setQuantiteAchete(scanner.nextInt());
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
        String code = "";
        scanner = new Scanner(System.in);

        do {
            System.out.print("Entrer le code de la vente : ");
            code = scanner.nextLine();
        } while (code.isEmpty());

        int indexVt = rechercherVente(code);
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

    public void showVente(int value) {
        if (value == -1) {
            listVentes = vente.getListVente();
            for (int i = 0; i < listVentes.size(); i++) {
                System.out.println("Code Vente                 : " + listVentes.get(i).getCode()
                        + "\nCode Client                : " + listVentes.get(i).getCodeClient()
                        + "\nCode Produit               : " + listVentes.get(i).getCodeClient()
                        + "\nQuantite                   : " + listVentes.get(i).getQuantiteAchete()
                        + "\nDate de vente              : " + listVentes.get(i).getDateVente()
                        + "\nEtat                       : " + listVentes.get(i).getEtat()
                        + "\n---------------------------------------------");
            }
        } else {
            System.out.println("Code Vente                 : " + listVentes.get(value).getCode()
                    + "\nCode Client                : " + listVentes.get(value).getCodeClient()
                    + "\nCode Produit               : " + listVentes.get(value).getCodeClient()
                    + "\nQuantite                   : " + listVentes.get(value).getQuantiteAchete()
                    + "\nDate de vente              : " + listVentes.get(value).getDateVente()
                    + "\nEtat                       : " + listVentes.get(value).getEtat()
                    + "\n------------------------------------------------");
        }
    }

    public void menuVente() {
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
                    System.out.println("Mauvais choix");
                    break;
            }
        } while (choix != 0);
    }
}
