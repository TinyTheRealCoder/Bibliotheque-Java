/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

public enum PublicVise {

    ENFANT(3),
    ADOLESCENT(10),
    ADULTE(16); 

    private int ageMin;

    PublicVise(int ageMin){this.ageMin=ageMin;}   

    public int get_age_min(){return this.ageMin;}

}
