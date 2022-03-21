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
    private Integer dernierNumeroLecteur;

    public Bibliotheque() {
        this.lecteurs = new HashMap<>();
        this.ouvrages = new HashMap<>();
        this.dernierNumeroLecteur = 0;
    }

    
    /* GETTER */
    
    public Map<Integer, Lecteur> get_lecteurs() {
        return this.lecteurs;
    }
    
    private Lecteur get_lecteur(Integer numeroLecteur){
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
    
    private void incrementer_numero_lecteur(){
        this.dernierNumeroLecteur += 1;
    }
    
    /* Cas d'utilisation */
    
    public void nouveau_lecteur(IHM ihm) {
        this.incrementer_numero_lecteur();
        IHM.InfosLecteur infosLecteur = ihm.saisir_lecteur(this.dernierNumeroLecteur);
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
        ArrayList<String> numerosISBN = this.get_numeros_ISBN();
        IHM.InfosOuvrage infosOuvrage = ihm.saisir_ouvrage(numerosISBN);
        if(infosOuvrage != null){
            Ouvrage ouvrage = new Ouvrage(infosOuvrage.numeroISBN, infosOuvrage.titre, infosOuvrage.editeur, infosOuvrage.dateParution, infosOuvrage.auteurs, infosOuvrage.publicVise);
            this.add_ouvrage(ouvrage, ouvrage.get_numero_ISBN());
            ihm.informer_utilisateur("ouvrage créé", true);
        }
        else{
            ihm.informer_utilisateur("l'ouvrage existe déjà dans la base", false);
        }
    }
    
    public void nouvel_exemplaire(IHM ihm){
        ArrayList<String> numerosISBN = this.get_numeros_ISBN();
        IHM.InfosExemplaire infosExemplaire = ihm.saisir_exemplaire(numerosISBN);
        if(infosExemplaire == null){
            ihm.informer_utilisateur("cet ouvrage n'existe pas dans la base", false);
        }
        else{
            Ouvrage o = ouvrages.get(infosExemplaire.numeroISBN);
            o.add_exemplaire(infosExemplaire.quantiteExemplaire, infosExemplaire.quantiteEmpruntable, infosExemplaire.dateReception);
            ihm.informer_utilisateur("exemplaires ajoutés", true);
        }
    }
        

    public void consulter_lecteur(IHM ihm){
        ArrayList<Integer> numerosLecteur = this.get_numeros_lecteur();
        Integer numeroLecteur = ihm.saisir_numero_lecteur(numerosLecteur);
        if(numeroLecteur == null){
            ihm.informer_utilisateur("ce numéro de lecteur n'existe pas dans la base", false);
        }
        else{
            Lecteur lecteur = this.get_lecteur(numeroLecteur);
            String nom = lecteur.get_nom();
            String prenom = lecteur.get_prenom();
            LocalDate date = lecteur.get_date_naissance();
            String mail = lecteur.get_mail();
            Integer age = lecteur.get_age();
            ihm.afficher_lecteur( nom, prenom, date, age, mail, numeroLecteur);
        }
    }
    
    public void consulter_ouvrage(IHM ihm){
        ArrayList<String> numerosISBN = this.get_numeros_ISBN();
        String numeroISBN = ihm.saisir_numero_ouvrage(numerosISBN);
        if(numeroISBN == null){
            ihm.informer_utilisateur("cet ouvrage n'existe pas dans la base", false);
        }
        else{
            Ouvrage ouvrage = get_ouvrage(numeroISBN);
            ihm.afficher_ouvrage(ouvrage.get_titre(), numeroISBN, ouvrage.get_editeur(), ouvrage.get_date_parution(), ouvrage.get_auteurs(), ouvrage.get_public_vise());
        }
    }
    
    public void consulter_exemplaire_ouvrage(IHM ihm){
        ArrayList<String> numerosISBN = this.get_numeros_ISBN();
        String numeroISBN = ihm.saisir_numero_ouvrage(numerosISBN);
        if(numeroISBN == null){
            ihm.informer_utilisateur("cet ouvrage n'existe pas dans la base", false);
        }
        else{
            Ouvrage o = get_ouvrage(numeroISBN);
            String titre = o.get_titre();
            ihm.afficher_ouvrage(titre, numeroISBN);
            ArrayList<Exemplaire> exemplaires = o.get_exemplaires();
            int numeroExemplaire;
            for(Exemplaire ex : exemplaires){
                numeroExemplaire = ex.get_numero_exemplaire();
                ihm.afficher_numero_exemplaire(numeroExemplaire);
            }
        }
    }
    
    public void emprunter_exemplaire(IHM ihm){
        ArrayList<String> numerosISBN = this.get_numeros_ISBN();
        ArrayList<Integer> numerosLecteur = this.get_numeros_lecteur();
        IHM.InfosExemplaire infosExemplaire = ihm.saisir_numero_ouvrage(numerosISBN);
        Integer numeroLecteur = ihm.saisir_numero_lecteur(numerosLecteur);
        if(infosExemplaire.numeroISBN == null){
            ihm.informer_utilisateur("cet ouvrage n'existe pas dans la base", false);
        }
        else if(numeroLecteur == null){
            ihm.informer_utilisateur("ce numéro de lecteur n'existe pas dans la base", false);
        }
        else{
            Lecteur lect = this.get_lecteur(numeroLecteur);
            Ouvrage ouvr = this.get_ouvrage(numeroISBN);
            Exemplaire ex = ouvr.get_exemplaire(infosExemplaire.numeroExemplaire);
            if(ex == null){
                ihm.informer_utilisateur("ce numéro d'exemplaire n'existe pas dans la base", false);
            }
            else if(!ex.est_empruntable()){
                ihm.informer_utilisateur("L'exemplaire n'est pas empruntable", false);
            }
            else if(lect.get_public_vise < ouvr.get_public_vise()){
                ihm.informer_utilisateur("Le lecteur est trop jeune pour cet ouvrage", false);
            }
            else{
                LocalDate dateEmprunt = ihm.saisir_date_emprunt();
                lect.creer_emprunt(ex, dateEmprunt);
                ihm.informer_utilisateur("Emprunt enregistré", true);
            }
        }
    }
    
    public void rendre_exemplaire(IHM ihm){
        ArrayList<String> numerosISBN = this.get_numeros_ISBN();
        IHM.InfosExemplaire infosExemplaire = ihm.saisir_numero_ouvrage(numerosISBN);
        if(infosExemplaire.numeroISBN == null){
            ihm.informer_utilisateur("cet ouvrage n'existe pas dans la base", false);
        }
        else{
            Ouvrage ouvr = this.get_ouvrage(infosExemplaire.numeroISBN);
            Exemplaire ex = ouvr.get_exemplaire(infosExemplaire.numeroExemplaire);
            if(ex == null){
                ihm.informer_utilisateur("ce numéro d'exemplaire n'existe pas dans la base", false);
            }
            else if(!ex.est_emprunte()){
                ihm.informer_utilisateur("cet exemplaire n'est pas emprunté, il n'est pas possible de le rendre", false);
            }
            else {
                Emprunt emp = ex.get_emprunt();
                if(emp == null){
                    ihm.informer_utilisateur("l'emprunt n'existe pas", false);
                }
                else{
                    Lecteur lect = emp.get_lecteur();
                    if(lect == null){
                        ihm.informer_utilisateur("Aucun lecteur n'est associé à l'emprunt", false);
                    }
                    else{
                        lect.detruire_emprunt(emp);
                        ihm.informer_utilisateur("Exemplaire rendu", true);
                    }
                }
            }
        }
    }
    
    public void consulter_emprunts_lecteur(IHM ihm){
        ArrayList<Integer> numerosLecteur = this.get_numeros_lecteur();
        Integer numeroLecteur = ihm.saisir_numero_lecteur(numerosLecteur);
        if(numeroLecteur == null){
            ihm.informer_utilisateur("ce numéro de lecteur n'existe pas dans la base", false);
        }
        else{
            Lecteur lect = this.get_lecteur(numeroLecteur);
            ihm.afficher_lecteur(numeroLecteur, lect.get_nom(), lect.get_prenom());
            ArrayList<Emprunt> emprunts = lect.get_emprunts();
            for(Emprunt emp : emprunts){
                IHM.InfosExemplaire infosExemplaire = emp.get_infos_exemplaire();
                ihm.afficher_emprunt(infosExemplaire, emp.get_date_emprunt(), emp.get_date_retour());
            }
        }
    }
    
    public void relancer_lecteur(IHM ihm){
        for(Lecteur lect : this.lecteurs.values()){
            ArrayList<Emprunt> emprunts = lect.get_emprunts();
            for(Emprunt emp : emprunts){
                if(LocalDate.now().isAfter(emp.get_date_retour().plusDays(15))){
                    IHM.InfosExemplaire infosExemplaire = emp.get_infos_exemplaire();
                    ihm.afficher_emprunt(infosExemplaire, emp.get_date_emprunt(), emp.get_date_retour());
                }
            }
        }
    }
}
