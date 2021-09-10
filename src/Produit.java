import java.util.ArrayList;

public class Produit implements Comparable {
    private String code;
    private String categorie;
    private String Nom;
    private double PrixUnitaire;
    private int Quantite;
    private String Description;

    ArrayList<Produit> listProduit = new ArrayList<>();

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
        if ((code != null) && (!code.isEmpty())) {
            this.code = code;
        }
    }

    public void setCategorie(String categorie) {
        if ((categorie != null) && (!categorie.isEmpty())) {
            this.categorie = categorie;
        }
    }

    public void setNom(String nom) {
        if ((nom != null) && (!nom.isEmpty())) {
            this.Nom = nom;
        }
    }

    public void setPrixUnitaire(double prixUnitaire) {
        if (!(prixUnitaire <= 0)) {
            this.PrixUnitaire = prixUnitaire;
        }
    }

    public void setQuantite(int quantite) {
        if (!(quantite <= 0)) {
            this.Quantite = quantite;
        }
    }

    public void setDescription(String description) {
        if ((description != null) && (!description.isEmpty())) {
            this.Description = description;
        }
    }

    public void setListProduit(ArrayList<Produit> listProduit) {
        if (listProduit != null) {
            this.listProduit = listProduit;
        }
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
