import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TraitementClients implements ClientContract {
    Client client = new Client();
    Scanner scanner;

    static ArrayList<Client> clients = new ArrayList<>();

    @Override
    public void Enregistrer() throws IOException {
        Client client1 = new Client();
        scanner = new Scanner(System.in);

        System.out.println("---------------Enregistrement Client---------------");

        do {
            System.out.print("Entrer le code client : ");
            client1.setCode(scanner.nextLine());
        } while (client1.getCode().isEmpty());
        do {
            System.out.print("Entrer le nom : ");
            client1.setNom(scanner.nextLine());
        } while (client1.getNom().isEmpty());
        do {
            System.out.print("Entrer le prenom : ");
            client1.setPrenom(scanner.nextLine());
        } while (client1.getPrenom().isEmpty());
        do {
            System.out.print("Entrer le sexe : ");
            client1.setSex(scanner.nextLine());
        } while (client1.getSex().isEmpty());
        do {
            System.out.print("Enter l\'adresse : ");
            client1.setAdresse(scanner.nextLine());
        } while (client1.getAdresse().isEmpty());
        do {
            System.out.print("Entrer le telephone : ");
            client1.setTel(scanner.nextInt());
        } while (client1.getTel() <= 0);

        client.enregistrer(client1);
        sauvegarderClient(client1, "Clients");
    }

    @Override
    public void Afficher_La_ListeClient() {
        clients = client.getListClient();

        System.out.println("---------------Les Clients---------------");

        if (clients.size() > 0) {
            showClient(-1);
        } else {
            System.out.println("---------------Message---------------" +
                    "\nAucun client a afficher!" +
                    "\n---------------------------------------------");
        }
    }

    @Override
    public void Modifier_Adresse() {
        scanner = new Scanner(System.in);
        String code = "";

        do {
            System.out.print("Entrer le code du client : ");
            code = scanner.nextLine();
        } while (code.isEmpty());

        int indexCl = rechercherClient(code);
        String adresse;

        if (indexCl == -1) {
            System.out.println("---------------Message---------------" +
                    "\nAucune correspondance, veuillez re-essayez svp!" +
                    "\n---------------------------------------------");
            ;
        } else {
            showClient(indexCl);
            do {
                System.out.print("Entrer la nouvelle adresse : ");
                adresse = scanner.nextLine();
            } while (adresse.isEmpty());
            clients.get(indexCl).setAdresse(adresse);
            System.out.println("Adresse modifie!");
        }

    }

    @Override
    public void Afficher_produits_Achetes() {
        scanner = new Scanner(System.in);
        String code = "";
        Vente vente = new Vente();
        TraitementVentes tv = new TraitementVentes();
        TraitementProduits tp = new TraitementProduits();
        ArrayList<Vente> ventes = new ArrayList<>();

        do {
            System.out.print("Entrer le code du client : ");
            code = scanner.nextLine();
        } while (code.isEmpty());

        int sClient = rechercherClient(code);
        if (sClient == -1) {
            System.out.println("---------------Message---------------" +
                    "\nAucune correspondance, veuillez re-essayez svp!" +
                    "\n---------------------------------------------");
        }

        if (sClient == -2) {
            System.out.println("---------------Message---------------" +
                    "\nAucun client enregistre, veuillez re-essayez svp!" +
                    "\n---------------------------------------------");
        }

        ventes = vente.getListVente();

        if (ventes.size() > 0) {
            System.out.println("---------------Produits achetes---------------");
            for (int i = 0; i < ventes.size(); i++) {
                if (ventes.get(i).getCodeClient().equalsIgnoreCase(code)) {
                    tp.showProd(i);
                }
            }
        } else {
            System.out.println("---------------Message---------------" +
                    "\nAucun produit a afficher!" +
                    "\n---------------------------------------------");
        }
    }

    public int rechercherClient(String codeCLient) {
        clients = client.getListClient();

        if (clients.size() > 0) {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).getCode().equalsIgnoreCase(codeCLient)) {
                    return i;
                }
            }
        } else {
            return -2;
        }
        return -1;
    }

    public void showClient(int value) {
        if (value == -1) {
            clients = client.getListClient();
            for (int i = 0; i < clients.size(); i++) {
                System.out.println("Code               : " + clients.get(i).getCode()
                        + "\nNom                : " + clients.get(i).getNom()
                        + "\nPrenom             : " + clients.get(i).getPrenom()
                        + "\nSexe               : " + clients.get(i).getSex()
                        + "\nAdresse            : " + clients.get(i).getAdresse()
                        + "\nTelephone          : " + clients.get(i).getTel()
                        + "\n---------------------------------------------");
            }
        } else {
            System.out.println("Code               : " + clients.get(value).getCode()
                    + "\nNom                : " + clients.get(value).getNom()
                    + "\nPrenom             : " + clients.get(value).getPrenom()
                    + "\nSexe               : " + clients.get(value).getSex()
                    + "\nAdresse            : " + clients.get(value).getAdresse()
                    + "\nTelephone          : " + clients.get(value).getTel()
                    + "\n---------------------------------------------");
        }
    }

    public void sauvegarderClient(Client client1, String filename) throws IOException {
        FileOutputStream file = new FileOutputStream(filename + ".txt", true);
        BufferedOutputStream buffer = new BufferedOutputStream(file);

        String line = client1.getCode() + ";" + client1.getNom() + ";" + client1.getPrenom() + ";"
                + client1.getSex() + ";" + client1.getAdresse() + ";" + client1.getTel() + ";\n";

        byte[] bytes = line.getBytes();
        buffer.write(bytes);

        buffer.flush();
        buffer.close();
    }

    public void menuClient() throws IOException {
        int choix = 0;
        scanner = new Scanner(System.in);

        do {
            System.out.println("---------------Menu Clients---------------"
                    + "\nFaites un choix : "
                    + "\n1.- Enregistrer un client"
                    + "\n2.- Afficher les clients"
                    + "\n3.- Modifier l\'adresse d\'un client"
                    + "\n4.- Afficher les produits acheter par un client"
                    + "\n0.- Retourner dans le menu avant");

            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    Enregistrer();
                    break;
                case 2:
                    Afficher_La_ListeClient();
                    break;
                case 3:
                    Modifier_Adresse();
                    break;
                case 4:
                    Afficher_produits_Achetes();
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
