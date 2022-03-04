package modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import util.ES;
import vue.*;
import vue.IHM.InfosLecteur;

public class Bibliotheque implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private Map<Integer, Lecteur> lecteurs;  // association qualifiée par le num
    private HashMap<String, Ouvrage> ouvrages;

    /*
    roberben
    */
    private int dernierNumeroLecteur;

    public Bibliotheque() {
        this.lecteurs = new HashMap<>();
    }

    
    /* GETTER */
    
    public Map<Integer, Lecteur> get_lecteurs() {
        return this.lecteurs;
    }
    
    public Lecteur get_lecteur(Integer numeroLecteur){
        return lecteurs.get(numeroLecteur);
    }
    
    private Ouvrage get_ouvrage(String numeroISBN){
        return this.ouvrages.get(numeroISBN);
    }
    
    private ArrayList<String> get_numeros_ISBN(){
        ArrayList<String> numerosISBN = new ArrayList<>();
        this.ouvrages.forEach((n, o) -> {
            numerosISBN.add(o.get_numero_ISBN());
        });
        return numerosISBN;
    }

    private ArrayList<Integer> get_numeros_lecteur(){
        ArrayList<Integer> numerosLecteur = new ArrayList<>();
        this.lecteurs.forEach((n, l) -> {
            numerosLecteur.add(l.get_num());
        });
        return numerosLecteur;
    }
    
    /* ADDER*/
    
    private void add_lecteur(Lecteur l, Integer num) {
        this.lecteurs.put(num, l);
    }
    
    private void add_ouvrage(Ouvrage ouvrage, String numeroISBN){
        this.ouvrages.put(numeroISBN, ouvrage);
    }
    
    /* Cas d'utilisation */
    
    public void nouveau_lecteur(IHM ihm) {
        IHM.InfosLecteur infosLecteur = ihm.saisir_lecteur();
        Lecteur l = lecteurs.get(infosLecteur.num);
        if (l == null) {
            l = new Lecteur(infosLecteur.num, infosLecteur.nom, infosLecteur.prenom, infosLecteur.dateNaiss, infosLecteur.email);
            add_lecteur(l, infosLecteur.num);
            ihm.informer_utilisateur("création du lecteur de numéro : " + infosLecteur.num, true);

        } else {
            ihm.informer_utilisateur("numéro de lecteur existant", false);
        }
    }
    
    public void nouvel_ouvrage(IHM ihm){
        ArrayList<String> numerosISBN = new ArrayList<>();
        numerosISBN = this.get_numeros_ISBN();
        IHM.InfosOuvrage infosOuvrage = ihm.saisir_ouvrage(numerosISBN);
        Ouvrage ouvrage = new Ouvrage(infosOuvrage.numeroISBN, infosOuvrage.titre, infosOuvrage.editeur, infosOuvrage.datePerution, infosOuvrage.auteurs, infosOuvrage.publicVise);
        this.add_ouvrage(ouvrage, ouvrage.get_numero_ISBN());
        ihm.informer_utilisateur("Ouvrage créé", true);
    }
    
    public void nouvel_exemplaire(IHM ihm){
        ArrayList<String> numerosISBN = new ArrayList<>();
        numerosISBN = this.get_numeros_ISBN();
        IHM.InfosExemplaire infosExemplaire = ihm.saisir_exemplaire(numerosISBN);
        Ouvrage ouvrage = this.get_ouvrage(infosExemplaire.numeroISBN);
        ouvrage.add_exemplaire(infosExemplaire.quantiteExemplaire, infosExemplaire.quantiteEmpruntable, infosExemplaire.dateReception);
        ihm.informer_utilisateur("Exemplaires ajoutés", true);
    }


    public void consulter_exemplaire_ouvrage(IHM ihm){
        ArrayList<String> numerosISBN = new ArrayList<>();
        numerosISBN = this.get_numeros_ISBN();
        String numeroISBN = ihm.saisir_numero_ouvrage(numerosISBN);
        Ouvrage ouvrage = get_ouvrage(numeroISBN);
        String titre = ouvrage.get_titre();
        ihm.afficher_ouvrage(titre, numeroISBN);
        ArrayList<Exemplaire> exemplaires = new ArrayList<>();
        exemplaires = ouvrage.get_exemplaires();
        int numeroExemplaire;
        for(Exemplaire ex : exemplaires){
            numeroExemplaire = ex.get_numero_exemplaire();
            ihm.afficher_numero_exemplaire(numeroExemplaire);
        }
    }

    public void consulter_lecteur(IHM ihm){
        ArrayList<Integer> numerosLecteur = new ArrayList<>();
        numerosLecteur = this.get_numeros_lecteur();
        Integer numeroLecteur = ihm.saisir_numero_lecteur(numerosLecteur);
        Lecteur lecteur = this.get_lecteur(numeroLecteur);
        String nom = lecteur.get_nom();
        String prenom = lecteur.get_prenom();
        LocalDate date = lecteur.get_date_naissance();
        String mail = lecteur.get_mail();
        Integer age = lecteur.get_age();
        ihm.afficher_lecteur(numeroLecteur, nom, prenom, date, age, mail);
    }
    
    
    public void consulter_ouvrage(IHM ihm){
        ArrayList<String> numerosISBN = new ArrayList<>();
        numerosISBN = this.get_numeros_ISBN();
        String numeroISBN = ihm.saisir_numero_ouvrage(numerosISBN);
        Ouvrage ouvrage = get_ouvrage(numeroISBN);
        String titre = ouvrage.get_titre();
        ihm.afficher_ouvrage(titre, numeroISBN);
    }
    
}
