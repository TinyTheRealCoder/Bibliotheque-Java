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
    private int derneierNumeroExemplaire;
    private PublicVise publicVise;
    
    Ouvrage(){
        
    }
    
}
