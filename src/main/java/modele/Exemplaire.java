/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.io.Serializable;
import java.time.LocalDate;
import vue.IHM;
import vue.IHM.InfosOuvrage;
import vue.IHM.InfosEmprunt;

/**
 *
 * @author tougmaa
 */
public class Exemplaire implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private Integer numeroExemplaire;
    private LocalDate dateReception;
    private boolean empruntable;
    private Ouvrage ouvrage;
    private Emprunt emprunt;
    
    Exemplaire(Ouvrage ouvrage, Integer numExemplaire, LocalDate dateReception, boolean empruntable){
        this.emprunt = null;
        this.ouvrage = ouvrage;
        this.numeroExemplaire = numExemplaire;
        this.dateReception = dateReception;
        this.empruntable = empruntable;
    }
    
    /*
    Getter Setter
    */
    
    public Integer get_numero() {
        return this.numeroExemplaire;
    }
    
    public LocalDate get_date_reception() {
        return this.dateReception;
    }
    
    public boolean est_empruntable() {
        return this.empruntable;
    }
    
    public boolean est_emprunte(){
        return (this.emprunt != null);
    }
    
    public Emprunt get_emprunt(){
        return this.emprunt;
    }
    
    public Ouvrage get_ouvrage(){
        return this.ouvrage;
    }
    
    public InfosOuvrage get_infos_ouvrage(){
        return this.ouvrage.get_infos_ouvrage();
        //Benj return new IHM.InfosOuvrage(this.ouvrage.get_numero_ISBN(), this.ouvrage.get_titre());
    }
    
    public InfosEmprunt get_infos_emprunt(){
        return this.emprunt.get_infos_emprunt();
    }
    
    public void set_emprunt(Emprunt emprunt){
        this.emprunt = emprunt;
    }
    
    public void unset_emprunt(){
        this.emprunt = null;
    }
        
    /*
    Dans le constructeur
    public boolean set_ouvrage();
    */
}
