package vue;

import modele.*;
import util.* ;
import java.util.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.time.LocalDate;

/**
* La classe IHM est responsable des interactions avec l'utilisateur/trice en
* mode texte. C'est une classe qui n'est associée à aucun état : elle ne contient aucun
* attribut d'instance. Aucune méthode de cette classe n'est pas censée modifier ses paramètres,
* c'est pourquoi les paramètres des méthodes sont tous marqués comme `final`.
*/

public class IHM  {

    private final Bibliotheque bibliotheque;

    public IHM(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    //-----  affichage menu et saisie des commandes par l'utilisateur  -------------------------------------------------

    /**
    * afficherInterface permet l'affichage du menu et le choix d'une commande
    *par l'utilisateur (dialogueSaisirCommande) puis l'invocation de la méthode
    *de la classe Bibliotheque réalisant l'action  (gererDialogue)
    */

    public void afficher_interface() {
        Commande cmd;
        do {
            cmd = this.dialogue_saisir_commande();
            this.gerer_dialogue(cmd);
        } while (cmd != Commande.QUITTER);
    }

    private Commande dialogue_saisir_commande() {
        ES.afficher_titre("===== Bibliotheque =====");
        ES.afficher_libelle(Commande.synopsis_commandes());
        ES.afficher_libelle("===============================================");
        ES.afficher_libelle("Saisir l'identifiant de l'action choisie :");
        return Commande.lire_commande();
    }

    private void gerer_dialogue(Commande cmd) {
        switch (cmd) {
            case QUITTER:
                break;
            case CREER_LECTEUR:
                bibliotheque.nouveau_lecteur(this);
                break;
            case CONSULTER_LECTEURS:
                bibliotheque.consulter_lecteur(this);
                break;
            case CREER_OUVRAGE:
                bibliotheque.nouvel_ouvrage(this);
                break;
            case CONSULTER_OUVRAGE:
                bibliotheque.consulter_ouvrage(this);
                break;
            case CREER_EXEMPLAIRES:
                bibliotheque.nouvel_exemplaire(this);
                break;
            case CONSULTER_EXEMPLAIRES:
                bibliotheque.consulter_exemplaire_ouvrage(this);
                break;
            case EMPRUNTER_EXEMPLAIRE:
                bibliotheque.emprunter_exemplaire(this);
                break;
            case RENDRE_EXEMPLAIRE:
                bibliotheque.rendre_exemplaire(this);
                break;
            case CONSULTER_EMPRUNTS_LECTEUR:
                bibliotheque.consulter_emprunts_lecteur(this);
                break;
            case RELANCER_LECTEUR:
                bibliotheque.relancer_lecteur(this);
                break;
            default:
                assert false : "Commande inconnue.";
        }
    }

    //-----  Classes conteneurs et éléments de dialogue pour le lecteur -------------------------------------------------

    /** Classe conteneur pour les informations saisies pour la création d'un
    * lecteur. Tous les attributs sont `public` par commodité d'accès.
    * Tous les attributs sont `final` pour ne pas être modifiables.
    */

    public static class InfosLecteur {
        public final Integer num;
        public final String nom;
        public final String prenom;
        public final String adresse;
        public final LocalDate dateNaiss;
        public final String email;

        public InfosLecteur(final Integer num, final String nom, final String prenom, final String adresse, final LocalDate dateNaiss, final String email) {
            this.num = num;
            this.nom = nom;
            this.prenom = prenom;
            this.adresse = adresse ;
            this.dateNaiss = dateNaiss;
            this.email = email;
        }
    }
    public static class InfosOuvrage{
        public final String titre;
        public final String editeur;
        public final LocalDate dateParution;
        public final ArrayList<String> auteurs;
        public final String numeroISBN;
        public final PublicVise publicVise;

        public InfosOuvrage(final String titre, final String editeur, final LocalDate dateParution, final ArrayList<String> auteurs, final String numeroISBN, final PublicVise publicVise) {
            this.titre = titre;
            this.editeur = editeur;
            this.dateParution = dateParution;
            this.auteurs = auteurs;
            this.numeroISBN = numeroISBN;
            this.publicVise = publicVise;
        }
        
        public InfosOuvrage(final String numero, final String titre){
            this.numeroISBN = numero;
            this.titre = titre;
            
            this.auteurs = null;
            this.dateParution = null;
            this.editeur = null;
            this.publicVise = null;
        }
    }
    
    public static class InfosExemplaire{
        
        public final String numeroISBN;
        public final LocalDate dateReception;
        public final Integer quantiteExemplaire, quantiteEmpruntable;
        
        public final int numeroExemplaire;
        public final InfosOuvrage infosOuvrage;
        
        public InfosExemplaire(final LocalDate dateReception, final Integer quantiteExemplaire, final Integer quantiteEmpruntable){
            this.numeroISBN = null;
            this.dateReception = dateReception;
            this.quantiteExemplaire = quantiteExemplaire;
            this.quantiteEmpruntable = quantiteEmpruntable;
            this.numeroExemplaire = 0;
            this.infosOuvrage = null;
        }
        
        public InfosExemplaire(final int numExemplaire,final InfosOuvrage infoOuvrage){
            this.numeroISBN = null;
            this.dateReception = null;
            this.quantiteExemplaire = null;
            this.quantiteEmpruntable = null;
            this.numeroExemplaire = numExemplaire;
            this.infosOuvrage = infoOuvrage;
        }
    }
    
    public static class InfosEmprunt{
        public final int numeroLecteur;
        public final String nom, prenom;
        public final LocalDate dateEmprunt, dateRetour;
        
        public InfosEmprunt(final int numeroLecteur, final String nom,final String prenom,final LocalDate dateEmprunt,final LocalDate dateRetour){
            this.numeroLecteur = numeroLecteur;
            this.nom = nom;
            this.prenom = prenom;
            this.dateEmprunt = dateEmprunt;
            this.dateRetour = dateRetour;
        }
    }
    
    public InfosExemplaire saisir_exemplaire(ArrayList<String> numerosISBN, LocalDate dateParution){
        LocalDate dateReception;
        Integer quantiteExemplaire, quantiteEmpruntable;
        
        ES.afficher_titre("== Saisie des informations de l'exemlaire ==");
        dateReception = ES.lire_date("Saisir la date de reception de l'exemplaire", dateParution);
        quantiteExemplaire = ES.lire_entier("Saisir la quantité d'exemplaire à ajouter", 0);
        quantiteEmpruntable = ES.lire_entier("Combien sont empruntables ?", 0, quantiteExemplaire);
        
        return new InfosExemplaire(dateReception, quantiteExemplaire, quantiteEmpruntable);
    }
    
    public InfosOuvrage saisir_ouvrage(ArrayList<String> numerosISBN){
        String numeroISBN, titre, editeur;
        LocalDate dateParution;
        ArrayList<String> auteurs;
        PublicVise publicVise;
        
        ES.afficher_titre("== Saisie des informations de l'ouvrage ==");
        numeroISBN = ES.lire_numero_ISBN(numerosISBN, false);
        if(numeroISBN == null){
            return null;
        }
        titre = ES.lire_chaine("Saisir le titre de l'ouvrage : ");
        editeur = ES.lire_chaine("Saisir le nom de l'éditeur : ");
        dateParution = ES.lire_date("Saisir la date de parution de l'ouvrage");
        auteurs = ES.lire_auteurs();
        
        publicVise = ES.lire_public();
        
        
        return new InfosOuvrage(titre, editeur, dateParution, auteurs, numeroISBN, publicVise);
    }
    
    public Integer saisir_numero_exemplaire(){
        Integer num;
        
        ES.afficher_titre("== Saisie du numéro d'exemplaire ==");
        
        num = ES.lire_entier("Veuillez saisir un numéro d'exemplaire : ", 0);
        
        return num;   
    }
    
    public InfosLecteur saisir_lecteur(Integer num) {
        String nom, prenom, adresse, email;
        LocalDate dateNaiss;

        ES.afficher_titre("== Saisie d'un lecteur ==");
        nom = ES.lire_chaine("Saisir le nom du lecteur :");
        prenom = ES.lire_chaine("Saisir le prénom du lecteur :");
        adresse = ES.lire_chaine("Saisir l'adresse du lecteur :");
        dateNaiss = ES.lire_date("Saisir la date de naissance du lecteur :");
        email = ES.lire_email("Saisir l'email du lecteur :");

        return new InfosLecteur(num, nom, prenom, adresse, dateNaiss, email);
    }
    
    public Integer saisir_numero_lecteur(ArrayList<Integer> numerosLecteur){
        Integer numero;
        
        ES.afficher_titre("== Saisie d'un numéro lecteur ==");
        
        numero = ES.lire_entier("Veuillez saisir un numéro de lecteur : ");
        
        if(!numerosLecteur.contains(numero)){
            numero = null;
        }
        
        return numero;
    }
       
    public String saisir_numero_ouvrage(ArrayList<String> numerosISBN){
        String numero;
        
        ES.afficher_titre("== Saisie d'un numéro ISBN ==");
        
        numero = ES.lire_numero_ISBN(numerosISBN, true);
        
        return numero;        
    }
    
    public LocalDate saisir_date_emprunt(LocalDate dateRecept){
        LocalDate dateEmprunt;
        
        ES.afficher_titre("== Saisie d'une date d'emprunt ==");
        
        dateEmprunt = ES.lire_date("Veuillez saisir la date de l'emprunt :", dateRecept);
        
        return dateEmprunt;        
    }

    public void afficher_lecteur(final String nom, final String prenom, final LocalDate dateNaiss, final int age,final String email, final Integer num){
        ES.afficher_titre("== Affichage du lecteur N°" + num + " == " );
        ES.afficher_libelle("Nom | Prénom | Mail du lecteur : " + nom + " | " + prenom + " | " + email);
        ES.afficher_libelle("Date de naissance et age du lecteur : " + dateNaiss + ", " + age + "ans");
    }
    
    public void afficher_lecteur(final int num, final String nom, final String prenom){
        ES.afficher_libelle("N° | Nom | Prénom du lecteur : " + num + " | " + nom + " | " + prenom);
    }
    
    public void afficher_ouvrage(String titre, String numeroISBN){
        ES.afficher_titre("== Affichage de l'ouvrage ==");
        ES.afficher_libelle("N° " + numeroISBN);
        ES.afficher_libelle("Titre : " + titre);        
    }
    
    public void afficher_ouvrage(String titre, String numeroISBN, String editeur, LocalDate date, ArrayList<String> auteurs, PublicVise pub){
        ES.afficher_titre("== Affichage de l'ouvrage ==");
        ES.afficher_libelle("N° " + numeroISBN);
        ES.afficher_libelle("Titre : " + titre);
        ES.afficher_libelle("Date de parution : "+ date);
        ES.afficher_libelle("Editeur : " + editeur);
        if(auteurs.size()>1){
            ES.afficher_auteurs("Auteurs : ", auteurs);
        }
        else{
            ES.afficher_auteurs("Auteur : ", auteurs);
        } 
        ES.afficher_titre("Public : " + pub);
    }
    
    public void afficher_numero_exemplaire(int numeroExemplaire){
        ES.afficher_titre("== Affichage de l'exemplaire N°" + numeroExemplaire + " ==");
    }
    
    public void afficher_emprunt(InfosEmprunt infosEmprunt){
        if(infosEmprunt != null){
            ES.afficher_libelle("Exemplaire emprunté par le lecteur N°" + infosEmprunt.numeroLecteur);
            ES.afficher_libelle("Nom, prénom du lecteur : " + infosEmprunt.nom + ", " + infosEmprunt.prenom);   
            ES.afficher_libelle("Periode d'emprunt : " + infosEmprunt.dateEmprunt + " au " + infosEmprunt.dateRetour);  
        }
        else{
            ES.afficher_libelle("Exemplaire non emprunté");
        }
    }
    
    public void afficher_emprunt(InfosExemplaire infosExemplaire, LocalDate dateEmprunt, LocalDate dateRetour){
        ES.afficher_titre("== Affichage de l'emprunt =="); 
        ES.afficher_libelle("Date d'emprunt : " + dateEmprunt + " au " + dateRetour);   
        ES.afficher_libelle("Ouvrage N° " + infosExemplaire.infosOuvrage.numeroISBN); 
        ES.afficher_libelle("Exemplaire N° " + infosExemplaire.numeroExemplaire); 
        ES.afficher_libelle("Titre : " + infosExemplaire.infosOuvrage.titre); 
        
    }

    //-----  Primitives d'affichage  -----------------------------------------------
    public void informer_utilisateur(final String msg, final boolean succes) {
        ES.afficher_libelle((succes ? "[OK]" : "[KO]") + " " + msg);
    }

    public void informer_utilisateur(final String msg) {
        ES.afficher_libelle(msg);
    }

}





