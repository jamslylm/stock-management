import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TraitementProduits implements ProduitContract {
    Produit produit = new Produit();
    Scanner scanner;

    ArrayList<Produit> produits = new ArrayList<>();

    @Override
    public void Enregistrer() throws IOException {
        Produit produit1 = new Produit();
        scanner = new Scanner(System.in);

        do {
            System.out.print("Entrer le code : ");
            produit1.setCode(scanner.nextLine());
        } while (produit1.getCode().isEmpty());
        do {
            System.out.print("Entrer la categorie : ");
            produit1.setCategorie(scanner.nextLine());
        } while (produit1.getCategorie().isEmpty());
        do {
            System.out.print("Entrer le nom : ");
            produit1.setNom(scanner.nextLine());
        } while (produit1.getNom().isEmpty());
        do {
            System.out.print("Entrer le prix : ");
            produit1.setPrixUnitaire(scanner.nextDouble());
        } while (produit1.getPrixUnitaire() <= 0);
        do {
            System.out.print("Entrer la quantite : ");
            produit1.setQuantite(scanner.nextInt());
        } while (produit1.getQuantite() <= 0);
        do {
            scanner = new Scanner(System.in);
            System.out.print("Entrer la description : ");
            produit1.setDescription(scanner.nextLine());
        } while (produit1.getDescription().isEmpty());

        produit.enregistrer(produit1);
        sauvegarderProduit(produit1);
    }

    @Override
    public void Modifier_un_produit() {
        String code = "";
        scanner = new Scanner(System.in);
        do {
            System.out.print("Entrer le code du produit : ");
            code = scanner.nextLine();
        } while (code.isEmpty());

        int indexProd = rechercherProduit(code);
        showProd(indexProd);

        int choix;
        scanner = new Scanner(System.in);

        do {
            System.out.println("--------------Modifier Produit--------------"
                    + "\nModifier : "
                    + "\n1- Categorie\n2- Nom\n3- Prix\n4- Quantite\n5- Description");
            choix = scanner.nextInt();

            scanner = new Scanner(System.in);
            switch (choix) {
                case 1:
                    System.out.print("Entrer la categorie : ");
                    produits.get(indexProd).setCategorie(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Entrer la nom : ");
                    produits.get(indexProd).setNom(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Entrer le prix : ");
                    produits.get(indexProd).setPrixUnitaire(scanner.nextInt());
                    break;
                case 4:
                    System.out.print("Entrer la quantite : ");
                    produits.get(indexProd).setQuantite(scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Entrer le Description : ");
                    produits.get(indexProd).setDescription(scanner.nextLine());
                    break;
                default:
                    System.out.println("Mauvaix choix!");
            }
        } while (choix <= 5);
    }

    @Override
    public void Afficher_les_produit() {
        produits = produit.getListProduit();

        System.out.println("-------------Les Produits-------------");

        if (produits.size() > 0) {
            showProd(-1);
        } else {
            System.out.println("Aucun produit a afficher!");
        }
    }

    @Override
    public void Supprimer_un_produit() {

    }

    @Override
    public void Afficher_la_liste_produit_OrdreCroissant() {

    }

    @Override
    public void Afficher_la_liste_produit_OrdreDecroissant() {

    }

    @Override
    public void Restaurer_un_produit_supprime() {

    }

    @Override
    public void Restaurer_tous_les_produits_supprime() {

    }

    /**
     * This method returns the index of the product for the given code
     *
     * @param codeProduit
     * @return
     */
    public int rechercherProduit(String codeProduit) {
        produits = produit.getListProduit();

        if (produits.size() > 0) {
            for (int i = 0; i < produits.size(); i++) {
                if (produits.get(i).getCode().equalsIgnoreCase(codeProduit)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * This method allows us to display a given product if we specify the index
     * or display all products if no parameter has given
     *
     * @param index
     */
    public void showProd(int index) {
        if (index == -1) {
            produits = produit.getListProduit();
            for (int i = 0; i < produits.size(); i++) {
                System.out.println("Code                   : " + produits.get(i).getCode()
                        + "\nCategorie              : " + produits.get(i).getCategorie()
                        + "\nNom                    : " + produits.get(i).getNom()
                        + "\nPrix                   : " + produits.get(i).getPrixUnitaire()
                        + "\nQuantite               : " + produits.get(i).getQuantite()
                        + "\nDescription            : " + produits.get(i).getDescription()
                        + "\n------------------------------------");
            }
        } else {
            System.out.println("Code                   : " + produits.get(index).getCode()
                    + "\nCategorie              : " + produits.get(index).getCategorie()
                    + "\nNom                    : " + produits.get(index).getNom()
                    + "\nPrix                   : " + produits.get(index).getPrixUnitaire()
                    + "\nQuantite               : " + produits.get(index).getQuantite()
                    + "\nDescription            : " + produits.get(index).getDescription()
                    + "\n------------------------------------");
        }
    }

    /**
     * This method allows us to store in a single file as a database every
     * product we register
     *
     * @param produit1
     * @throws IOException
     */
    public void sauvegarderProduit(Produit produit1) throws IOException {
        FileOutputStream file = new FileOutputStream("produits.txt", true);
        BufferedOutputStream buffer = new BufferedOutputStream(file);

        String line = produit1.getCode() + ";" + produit1.getCategorie() + ";" + produit1.getNom() + ";"
                + produit1.getPrixUnitaire() + ";" + produit1.getQuantite() + ";" + produit1.getDescription() + ";\n";

        byte[] bytes = line.getBytes();
        buffer.write(bytes);

        buffer.flush();
        buffer.close();
    }

    /**
     * This method allows us to display a menu to manage the product field
     *
     * @throws IOException
     */
    public void menuProduit() throws IOException {
        int choix = 0;
        scanner = new Scanner(System.in);

        do {
            System.out.println("______ Menu Produits_______"
                    + "\nFaites un choix"
                    + "\n1.- Enregistrer un Produits"
                    + "\n2.- Modifier un produit par son code"
                    + "\n3.- Afficher tous les produits"
                    + "\n4.- Supprimer un produit"
                    + "\n5.- Afficher la liste des produits en ordre croissant"
                    + "\n6.- Afficher la liste des produits en ordre decroissant"
                    + "\n7.- Restaurer un produit supprime par son code"
                    + "\n8.- Restaurer tous les produits supprimes");

            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    Enregistrer();
                    break;
                case 2:
                    Modifier_un_produit();
                    break;
                case 3:
                    Afficher_les_produit();
                    break;
                case 4:
                    Supprimer_un_produit();
                    break;
                case 5:
                    Afficher_la_liste_produit_OrdreCroissant();
                    break;
                case 6:
                    Afficher_la_liste_produit_OrdreDecroissant();
                    break;
                case 7:
                    Restaurer_un_produit_supprime();
                    break;
                case 8:
                    Restaurer_tous_les_produits_supprime();
                    break;
                default:
                    System.out.println("Mauvais choix !");
            }
        } while (choix <= 8);
    }
}
