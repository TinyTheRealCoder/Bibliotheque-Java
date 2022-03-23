/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import vue.IHM;

/**
 *
 * @author tougmaa
 */
public class Ouvrage implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private String numeroISBN;
    private String titre;
    private String editeur;
    private LocalDate dateParution;
    private ArrayList<String> auteurs;
    private Integer dernierNumeroExemplaire;
    private PublicVise publicVise;
    private ArrayList<Exemplaire> exemplaires;
    
    Ouvrage(String numeroISBN, String titre, String editeur, LocalDate dateParution, ArrayList<String> auteurs, PublicVise publicVise){
        this.numeroISBN = numeroISBN;
        this.titre = titre;
        this.editeur = editeur;
        this.dateParution = dateParution;
        this.auteurs = new ArrayList<>();
        this.auteurs = (ArrayList<String>) auteurs.clone();
        this.dernierNumeroExemplaire = 0;
        this.publicVise = publicVise;
        this.exemplaires = new ArrayList<>();
    }
    
    /*
    Methodes Générales
    */
    
    private void incrementer_dernier_numero_exemplaire(){
        this.dernierNumeroExemplaire = this.dernierNumeroExemplaire + 1;
    }
    
    /*
    Methodes Add
    */
    
    public void add_exemplaire(Integer quantiteExemplaire, Integer quantiteEmpruntable, LocalDate dateReception){
        boolean empruntable;
        Exemplaire ex;
        
        for(int i = 1; i <= quantiteExemplaire; i++){
            
            empruntable = (i <= quantiteEmpruntable);
            
            this.incrementer_dernier_numero_exemplaire();
                        
            ex = new Exemplaire(this,this.get_dernier_numero_exemplaire(), dateReception, empruntable);
            
            //Methode private add_exemplaire du diag de sequence simplifié 
            this.exemplaires.add(ex);
            
        }
    }
    
    public void add_exemplaire(Exemplaire ex){
        this.incrementer_dernier_numero_exemplaire();
        this.exemplaires.add(ex);
    }
    
    /*
    * Getter Setter
    */
    
    public String get_numero_ISBN() {
        return this.numeroISBN;
    }
    
    public String get_titre() {
        return this.titre;
    }
    
    public String get_editeur() {
        return this.editeur;
    }
    
    public LocalDate get_date_parution() {
        return this.dateParution;
    }
    
    public ArrayList<String> get_auteurs() {
        return this.auteurs;
    }
    
    public Integer get_dernier_numero_exemplaire() {
        return this.dernierNumeroExemplaire;
    }
    
    public PublicVise get_public_vise() {
        return this.publicVise;
    }
    
    public ArrayList<Exemplaire> get_exemplaires() {
        return this.exemplaires;
    }
    
    public Exemplaire get_exemplaire(int numero){
        
        for (Exemplaire ex : this.exemplaires){
            if (ex.get_numero() == numero){
                return ex;
            }
        }
        
        return null;
    }
    
    //Benj Modif
    public IHM.InfosOuvrage get_infos_ouvrage(){
        return new IHM.InfosOuvrage(this.titre, this.editeur, this.dateParution, this.auteurs, this.numeroISBN, this.publicVise);
    }
    
    public void set_ouvrage(Ouvrage ouvrage){
        
    }
    
}
