import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        TraitementProduits traitementProduits = new TraitementProduits();
        TraitementClients traitementClients = new TraitementClients();

        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        do {
            System.out.println("$-$-$-$-$ TK_Store $-$-$-$-$ "
                    + "\n______ Menu Principale_______"
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
                case 4:
                    System.out.println("Au revoir 👋!");
            }

        } while (choix != 4);
    }
}
