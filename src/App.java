import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        TraitementProduits traitementProduits = new TraitementProduits();
        TraitementClients traitementClients = new TraitementClients();
        TraitementVentes traitementVentes = new TraitementVentes();

        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        do {
            System.out.println("$-$-$-$-$ TK_Store $-$-$-$-$ "
                    + "\n---------------Menu Principale---------------"
                    + "\n1.- Menu Produits"
                    + "\n2.- Menu Clients"
                    + "\n3.- Menu Ventes"
                    + "\n4.- Quitter le Menu Programme");

            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    traitementProduits.menuProduit();
                    break;
                case 2:
                    traitementClients.menuClient();
                    break;
                case 3:
                    traitementVentes.menuVente();
                    break;
                case 4:
                    System.out.println("Au revoir 👋!");
                    break;
                default:
                    System.out.println("Mauvais choix");
                    break;
            }

        } while (choix != 4);
    }
}
