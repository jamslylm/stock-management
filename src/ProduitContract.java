import java.io.FileNotFoundException;
import java.io.IOException;

public interface ProduitContract {
    void Enregistrer() throws IOException;

    void Modifier_un_produit();

    void Afficher_les_produit();

    void Supprimer_un_produit();

    void Afficher_la_liste_produit_OrdreCroissant();

    void Afficher_la_liste_produit_OrdreDecroissant();

    void Restaurer_un_produit_supprime();

    void Restaurer_tous_les_produits_supprime();
}
