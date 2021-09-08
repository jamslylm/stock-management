import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        TraitementProduits traitementProduits = new TraitementProduits();
        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        do {
            System.out.println("$-$-$-$-$ TK_Store $-$-$-$-$ "
                    + "\n______ Menu Principale_______"
                    + "\n1.- Menu Produits"
                    + "\n2.- Menu Clients"
                    + "\n3.- Menu Ventes"
                    + "\n4.- Quitter le Menu Principale");

            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    traitementProduits.menuProduit();
                    break;
            }

        } while (choix <= 3);
    }
}
