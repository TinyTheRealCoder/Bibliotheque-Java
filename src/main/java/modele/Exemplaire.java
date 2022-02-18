/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.Date;

/**
 *
 * @author tougmaa
 */
public class Exemplaire {
    
    private Integer numeroExemplaire;
    private Date dateReception;
    private boolean empruntable;
    private Ouvrage ouvrage;
    
    Exemplaire(Ouvrage ouvrage, Date dateReception, boolean empruntable){
        this.ouvrage = ouvrage;
        this.ouvrage.incrementer_dernier_numero_exemplaire();
        this.numeroExemplaire = this.ouvrage.get_dernier_numero_exemplaire();
        this.dateReception = dateReception;
        this.empruntable = empruntable;
    }
    
    /*
    Getter Setter
    */
    
    public Integer get_numero_exemplaire() {
        return this.numeroExemplaire;
    }
    
    public Date get_date_reception() {
        return this.dateReception;
    }
    
    public boolean get_empruntable() {
        return this.empruntable;
    }
    
}
