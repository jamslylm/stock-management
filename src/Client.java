import java.util.ArrayList;

public class Client {
    private String Code;
    private String Nom;
    private String Prenom;
    private String Sex;
    private String Adresse;
    private int Tel;

    ArrayList<Client> listClient = new ArrayList<>();
    ArrayList<Produit> listProduit = new ArrayList<>();

    public Client() {
    }

    public Client(String code, String nom, String prenom, String sex, String adresse, int tel) {
        Code = code;
        Nom = nom;
        Prenom = prenom;
        Sex = sex;
        Adresse = adresse;
        Tel = tel;
    }

    public String getCode() {
        return Code;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getSex() {
        return Sex;
    }

    public String getAdresse() {
        return Adresse;
    }

    public int getTel() {
        return Tel;
    }

    public ArrayList<Client> getListClient() {
        return listClient;
    }

    public ArrayList<Produit> getListProduit() {
        return listProduit;
    }

    public void setCode(String code) {
        if ((code != null) && (!code.isEmpty())) {
            Code = code;
        }
    }

    public void setNom(String nom) {
        if ((nom != null) && (!nom.isEmpty())) {
            Nom = nom;
        }
    }

    public void setPrenom(String prenom) {
        if ((prenom != null) && (!prenom.isEmpty())) {
            Prenom = prenom;
        }
    }

    public void setSex(String sex) {
        if ((sex != null) && (!sex.isEmpty())) {
            Sex = sex;
        }
    }

    public void setAdresse(String adresse) {
        if ((adresse != null) && (!adresse.isEmpty())) {
            Adresse = adresse;
        }
    }

    public void setTel(int tel) {
        if (!(tel <= 0)) {
            Tel = tel;
        }
    }

    public void setListClient(ArrayList<Client> listClient) {
        this.listClient = listClient;
    }

    public void setListProduit(ArrayList<Produit> listProduit) {
        this.listProduit = listProduit;
    }

    public void enregistrer(Client client) {
        listClient.add(client);
    }

    public void enregistrerProd(Produit produit) {
        listProduit.add(produit);
    }

    @Override
    public String toString() {
        return "Client{" +
                "Code='" + Code + '\'' +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", Tel=" + Tel +
                ", listClient=" + listClient +
                '}';
    }
}
