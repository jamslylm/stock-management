import java.io.*;
import java.util.*;

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
        sauvegarderProduit(produit1, "produits");
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
        if (indexProd == -1) {
            System.out.println("Aucun Produit enregistrer avec ce code, re-essayez svp!");
        } else {
            showProd(indexProd);
            int choix;
            scanner = new Scanner(System.in);

            do {
                System.out.println("--------------Modifier Produit--------------"
                        + "\nModifier : "
                        + "\n1- Categorie\n2- Nom\n3- Prix\n4- Quantite\n5- Description\n0- Retourner dans le menu avant");
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
                    case 0:
                        break;
                    default:
                        System.out.println("Mauvaix choix!");
                }
            } while (choix != 0);
        }

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
    public void Supprimer_un_produit() throws IOException {
        String code = "";
        produits = produit.getListProduit();
        scanner = new Scanner(System.in);

        do {
            System.out.print("Entrer le code du produit : ");
            code = scanner.nextLine();
        } while (code.isEmpty());

        int indexPro = rechercherProduit(code);

        if (indexPro == -1) {
            System.out.println("Aucun produit enregistrer avec ce code, re-essayez svp!");
        } else {
            Produit produit1 = new Produit(produits.get(indexPro).getCode(), produits.get(indexPro).getCategorie(),
                    produits.get(indexPro).getNom(), produits.get(indexPro).getPrixUnitaire(),
                    produits.get(indexPro).getQuantite(), produits.get(indexPro).getDescription());
            sauvegarderProduit(produit1, "Produits-supprimes");
            produits.remove(indexPro);
            System.out.println("Produit supprime avec succes!");
        }
    }

    @Override
    public void Afficher_la_liste_produit_OrdreCroissant() {
        produits = produit.getListProduit();
        Collections.sort(produits);

        System.out.println("---------------Les Produits -> (^A-Z)---------------");

        if (produits.size() > 0) {
            showProd(-1);
        } else {
            System.out.println("Aucun produit a afficher!");
        }

    }

    @Override
    public void Afficher_la_liste_produit_OrdreDecroissant() {
        produits = produit.getListProduit();
        Collections.sort(produits, Collections.reverseOrder());

        System.out.println("---------------Les Produits -> (^Z-A)---------------");

        if (produits.size() > 0) {
            showProd(-1);
        } else {
            System.out.println("Aucun produit a afficher!");
        }
    }

    @Override
    public void Restaurer_un_produit_supprime() throws FileNotFoundException {
        String code = "";
        scanner = new Scanner(System.in);

        do {
            System.out.print("Entrer le code du produit : ");
            code = scanner.nextLine();
        } while (code.isEmpty());

        restore(code);

    }

    @Override
    public void Restaurer_tous_les_produits_supprime() throws FileNotFoundException {
        restore("");
    }

    public void restore(String code) throws FileNotFoundException {
        Produit produit1 = new Produit();
        File file = new File("Produits-supprimes.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String[] abc = new String[0];
        boolean found = false;
        if (!code.isEmpty()) {
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    abc = line.split(";");
                    if (abc[0].equals(code)) {
                        found = true;

                        produit1.setCode(abc[0]);
                        produit1.setCategorie(abc[1]);
                        produit1.setNom(abc[2]);
                        produit1.setPrixUnitaire(Double.parseDouble(abc[3]));
                        produit1.setQuantite(Integer.parseInt(abc[4]));
                        produit1.setDescription(abc[5]);

                        produit.enregistrer(produit1);

                    } else {
                        found = false;
                    }

                }

                if (!found) {
                    System.out.println("Aucune correspondance");
                } else {
                    System.out.println("produit restaure!");
                }
                fileReader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    abc = line.split(";");
                    produit1.setCode(abc[0]);
                    produit1.setCategorie(abc[1]);
                    produit1.setNom(abc[2]);
                    produit1.setPrixUnitaire(Double.parseDouble(abc[3]));
                    produit1.setQuantite(Integer.parseInt(abc[4]));
                    produit1.setDescription(abc[5]);

                    produit.enregistrer(produit1);
                }

                fileReader.close();
                System.out.println("produits restaures!");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
     * @param value
     */
    public void showProd(int value) {
        if (value == -1) {
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
            System.out.println("Code                   : " + produits.get(value).getCode()
                    + "\nCategorie              : " + produits.get(value).getCategorie()
                    + "\nNom                    : " + produits.get(value).getNom()
                    + "\nPrix                   : " + produits.get(value).getPrixUnitaire()
                    + "\nQuantite               : " + produits.get(value).getQuantite()
                    + "\nDescription            : " + produits.get(value).getDescription()
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
    public void sauvegarderProduit(Produit produit1, String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename + ".txt", true);
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
                    + "\n8.- Restaurer tous les produits supprimes"
                    + "\n0.- Retourner dans le menu avant");

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
                case 0:
                    break;
                default:
                    System.out.println("Mauvais choix !");
                    break;
            }
        } while (choix != 0);
    }
}
