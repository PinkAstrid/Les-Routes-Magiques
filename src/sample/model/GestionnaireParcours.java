package sample.model;

import sample.Parcours;

import java.util.List;

public class GestionnaireParcours {
    List<Parcours> listeParcours;
    List<Parcours> listeParcoursRecherches;

    public void ajouterParcours(){
        Parcours p = new Parcours();
        listeParcours.add(p);
    }

    public void supprimerParcours(){}

    public void afficherParcours(){}
}
