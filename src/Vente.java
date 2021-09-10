import java.util.ArrayList;

public class Vente {
    private String code;
    private String codeProduit;
    private String codeClient;
    private int quantiteAchete;
    private String DateVente;
    private String etat;

    static ArrayList<Vente> listVente = new ArrayList<>();

    public Vente() {
    }

    public Vente(String code, String codeProduit, String codeClient, int quantiteAchete, String dateVente, String etat) {
        this.setCode(code);
        this.setCodeProduit(codeProduit);
        this.setCodeClient(codeClient);
        this.setQuantiteAchete(quantiteAchete);
        this.setDateVente(dateVente);
        this.setEtat(etat);
    }

    public String getCode() {
        return code;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public int getQuantiteAchete() {
        return quantiteAchete;
    }

    public String getDateVente() {
        return DateVente;
    }

    public String getEtat() {
        return etat;
    }

    public ArrayList<Vente> getListVente() {
        return listVente;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public void setQuantiteAchete(int quantiteAchete) {
        this.quantiteAchete = quantiteAchete;
    }

    public void setDateVente(String dateVente) {
        DateVente = dateVente;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setListVente(ArrayList<Vente> listVente) {
        this.listVente = listVente;
    }

    public void enregistrer(Vente vente) {
        listVente.add(vente);
    }

    @Override
    public String toString() {
        return "Vente{" +
                "code='" + code + '\'' +
                ", codeProduit='" + codeProduit + '\'' +
                ", codeClient='" + codeClient + '\'' +
                ", quantiteAchete=" + quantiteAchete +
                ", DateVente='" + DateVente + '\'' +
                ", etat='" + etat + '\'' +
                ", listVente=" + listVente +
                '}';
    }
}
