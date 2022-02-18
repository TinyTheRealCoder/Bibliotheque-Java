/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tougmaa
 */
public class Ouvrage {
    
    private String numeroISBN;
    private String titre;
    private String editeur;
    private Date dateParution;
    private ArrayList<String> auteurs;
    private Integer dernierNumeroExemplaire;
    private PublicVise publicVise;
    
    Ouvrage(String numeroISBN, String titre, String editeur, Date dateParution, ArrayList<String> auteurs, PublicVise publicVise){
        this.numeroISBN = numeroISBN;
        this.titre = titre;
        this.editeur = editeur;
        this.dateParution = dateParution;
        this.auteurs = new ArrayList<String>();
        this.auteurs = auteurs.clone();
        this.dernierNumeroExemplaire = 0;
        this.publicVise = publicVise;
    }
    
    public void incrementer_dernier_numero_exemplaire(){
        this.dernierNumeroExemplaire = this.dernierNumeroExemplaire + 1;
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
    
    public Date get_date_parution() {
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
    
}
