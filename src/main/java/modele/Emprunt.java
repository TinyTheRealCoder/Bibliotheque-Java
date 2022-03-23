/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.io.Serializable;
import java.time.LocalDate;
import vue.IHM;

/**
 *
 * @author benjt
 */
public class Emprunt implements Serializable{
    
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    private Exemplaire exemplaire;
    private Lecteur lecteur;
    private int nombr_jour_retour = 8;
    
    
    public Emprunt(Exemplaire ex, Lecteur lect, LocalDate dateEmprunt){
        
        this.dateEmprunt = dateEmprunt;
        this.set_date_retour(nombr_jour_retour);
        this.lecteur = lect;
        this.set_exemplaire(ex);
        //Benj 
        ex.set_emprunt(this);
        
    }
    
    /*
    Getter Setter
    */
    
    public LocalDate get_date_emprunt() {
        return this.dateEmprunt;
    }
    
    public LocalDate get_date_retour() {
        return this.dateRetour;
    }
    
    public IHM.InfosExemplaire get_infos_exemplaire() {
        return new IHM.InfosExemplaire(this.exemplaire.get_numero(), this.exemplaire.get_infos_ouvrage());
    }
    
    public IHM.InfosEmprunt get_infos_emprunt() {
        return new IHM.InfosEmprunt(this.lecteur.get_numero(), this.lecteur.get_nom(), this.lecteur.get_prenom(), this.get_date_emprunt(), this.get_date_retour());
    }
    
    public Lecteur get_lecteur() {
        return this.lecteur;
    }
    
    public Exemplaire get_exemplaire() {
        return this.exemplaire;
    }
    
    private void set_exemplaire(Exemplaire ex){
        this.exemplaire = ex;
    }
    
    private void set_date_retour(int nb_jour){
        this.dateRetour = this.dateEmprunt.plusDays(nb_jour);
    }
    
    public void set_lecteur(Lecteur lect){
        this.lecteur = lect;
    }
    
    /*
    Fonctions générales
    */
    
    public void detruire(){
        this.exemplaire.unset_emprunt();
        this.unset_exemplaire();
        this.unset_lecteur();
    }
    
    public void unset_exemplaire(){
        this.exemplaire = null;
    }
    
    public void unset_lecteur(){
        this.lecteur = null;
    }
}
