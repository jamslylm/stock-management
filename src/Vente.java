import java.util.ArrayList;

public class Vente {
    private String code;
    private String codeProduit;
    private String codeClient;
    private int quantiteAchete;
    private String DateVente;
    private String etat;

    ArrayList<Vente> listVente = new ArrayList<>();

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
        if ((code != null) && (!code.isEmpty())) {
            this.code = code;
        }
    }

    public void setCodeProduit(String codeProduit) {
        if ((codeProduit != null) && (!codeProduit.isEmpty())) {
            this.codeProduit = codeProduit;
        }
    }

    public void setCodeClient(String codeClient) {
        if ((codeClient != null) && (!codeClient.isEmpty())) {
            this.codeClient = codeClient;
        }
    }

    public void setQuantiteAchete(int quantiteAchete) {
        if (!(quantiteAchete <= 0)) {
            this.quantiteAchete = quantiteAchete;
        }
    }

    public void setDateVente(String dateVente) {
        if ((dateVente != null) && (!dateVente.isEmpty())) {
            DateVente = dateVente;
        }
    }

    public void setEtat(String etat) {
        if ((etat != null) && (!etat.isEmpty())) {
            this.etat = etat;
        }
    }

    public void setListVente(ArrayList<Vente> listVente) {
        this.listVente = listVente;
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
