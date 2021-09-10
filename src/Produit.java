import java.util.ArrayList;

public class Produit implements Comparable {
    private String code;
    private String categorie;
    private String Nom;
    private double PrixUnitaire;
    private int Quantite;
    private String Description;

    static ArrayList<Produit> listProduit = new ArrayList<>();

    public Produit() {
    }

    public Produit(String code, String categorie, String nom, double prixUnitaire, int quantite, String description) {
        this.setCode(code);
        this.setCategorie(categorie);
        this.setNom(nom);
        this.setPrixUnitaire(prixUnitaire);
        this.setQuantite(quantite);
        this.setDescription(description);
    }

    public String getCode() {
        return code;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getNom() {
        return Nom;
    }

    public double getPrixUnitaire() {
        return PrixUnitaire;
    }

    public int getQuantite() {
        return Quantite;
    }

    public String getDescription() {
        return Description;
    }

    public ArrayList<Produit> getListProduit() {
        return listProduit;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setNom(String nom) {
        this.Nom = nom;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.PrixUnitaire = prixUnitaire;
    }

    public void setQuantite(int quantite) {
        this.Quantite = quantite;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setListProduit(ArrayList<Produit> listProduit) {
        this.listProduit = listProduit;
    }

    public void enregistrer(Produit produit) {
        listProduit.add(produit);
    }

    @Override
    public int compareTo(Object o) {
        String nom = ((Produit) o).getNom();
        return this.getCode().compareTo(nom);
    }

    @Override
    public String toString() {
        return "Produit{" +
                "code='" + code + '\'' +
                ", categorie='" + categorie + '\'' +
                ", Nom='" + Nom + '\'' +
                ", PrixUnitaire=" + PrixUnitaire +
                ", Quantite=" + Quantite +
                ", Description='" + Description + '\'' +
                ", listProduit=" + listProduit +
                '}';
    }
}
