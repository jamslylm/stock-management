import java.io.IOException;

public interface ClientContract {
    void Enregistrer() throws IOException;

    void Afficher_La_ListeClient();

    void Modifier_Adresse();

    void Afficher_produits_Achetes();
}
