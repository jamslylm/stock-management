import java.io.IOException;

public interface VenteContract {
    void Enregistrer() throws IOException;

    void Afficher_la_liste_ventes();

    void modifier_la_quantite_produit();
}
