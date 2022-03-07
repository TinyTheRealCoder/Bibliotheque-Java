/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.time.LocalDate;

/**
 *
 * @author tougmaa
 */
public class Exemplaire {
    
    private Integer numeroExemplaire;
    private LocalDate dateReception;
    private boolean empruntable;
    private Ouvrage ouvrage;
    private Lecteur lecteur;
    
    Exemplaire(Ouvrage ouvrage, Integer numExemplaire, LocalDate dateReception, boolean empruntable){
        this.ouvrage = ouvrage;
        this.numeroExemplaire = numExemplaire;
        this.dateReception = dateReception;
        this.empruntable = empruntable;
    }
    
    /*
    Getter Setter
    */
    
    public Integer get_numero_exemplaire() {
        return this.numeroExemplaire;
    }
    
    public LocalDate get_date_reception() {
        return this.dateReception;
    }
    
    public boolean get_empruntable() {
        return this.empruntable;
    }
    
    /*
    Dans le constructeur
    public boolean set_ouvrage();
    */
}
