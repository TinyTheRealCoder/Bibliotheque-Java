package modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import util.ES;
import vue.*;
import vue.IHM.InfosLecteur;

public class Bibliotheque implements Serializable {

private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
private Map<Integer, Lecteur> lecteurs;  // association qualifiée par le num

/*
roberben
*/
private int dernierNumeroLecteur;

public Bibliotheque() {
        this.lecteurs = new HashMap<>();
}

public void nouveau_lecteur(IHM ihm) {
    IHM.InfosLecteur infosLecteur = ihm.saisir_lecteur();
    Lecteur l = lecteurs.get(infosLecteur.num);
    if (l == null) {
        l = new Lecteur(infosLecteur.num, infosLecteur.nom, infosLecteur.prenom, infosLecteur.dateNaiss, infosLecteur.email);
        lier_lecteur(l, infosLecteur.num);
        ihm.informer_utilisateur("création du lecteur de numéro : " + infosLecteur.num, true);

    } else {
        ihm.informer_utilisateur("numéro de lecteur existant", false);
    }
}

public Map<Integer, Lecteur> get_lecteurs() {
    return this.lecteurs;
}

private void lier_lecteur(Lecteur l, Integer num) {
    this.lecteurs.put(num, l);
}

public void consulter_exemplaire_ouvrage(){
    
}

public void consulter_exemplaires_ouvrage(){
    
}

}
