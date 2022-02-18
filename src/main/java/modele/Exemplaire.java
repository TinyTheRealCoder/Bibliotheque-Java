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
    
    Exemplaire(){
        
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
