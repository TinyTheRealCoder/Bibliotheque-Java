package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.apache.commons.validator.routines.EmailValidator;

/**
* Classe de gestion de primitives d'EntréesSorties (affichages et saisies)

*/
public class ES {

private static final long serialVersionUID = 1L;
private static BufferedReader entree = new BufferedReader(new InputStreamReader(System.in));

/**
* Constructeur. Aucun traitement requis.
*/
public ES() {

}

//	---------------------------------------------------------------------------------------------------------------------
//	Gestion des chaines de caractère 
/**
* permet la saisie d'une chaine. Pas de contrôle du format saisi.
*
* @return chaine saisie par l'utilisREJECTED_NONFASTFORWARDateur
*/

public static String lire_chaine() {
try {
    return entree.readLine();
} catch (IOException e) {
    System.out.println(" ERREUR d'E/S : IO.LIRE_CHAINE");
    return "";
}
}  // Fin lireChaine

/**
* Affiche un libellé et permet la saisie d'une chaine.
*
* @param libelle : libellé à afficher en début pour indiquer quelle saisie
* effectuer
* @return String : chaine saisie par l'utilisateur
*/
public static String lire_chaine(String libelle) {
ES.afficher_libelle(libelle);
return lire_chaine();
}  // Fin lireChaine


public static String lire_email() {
boolean OK = false;
String email = lire_chaine();
EmailValidator validator = EmailValidator.getInstance(false, false);        
do { 
if (validator.isValid(email)){
    OK = true;   
} 
else {
System.out.println(" l'adresse mail n'est pas valide. Recommencez.");
email = lire_chaine();
}   
} while (!OK);
return  email;
}   // Fin lireEmail

public static String lire_email(String libelle) {
ES.afficher_libelle(libelle);
return lire_email();
}  // Fin de lireEmail

//	---------------------------------------------------------------------------------------------------------------------
//	Gestion des entiers
/**
* permet la saisie d'un entier. Si l'utilisateur saisit une valeur
* non-entière, affichage d'une erreur et demande d'une autre saisie.
*
* @return int : l'entier saisi.
*
*/
public static int lire_entier() {
boolean ok = false;
int valentiere = 0;

try {
    do {
        try {
            valentiere = Integer.parseInt(entree.readLine());
            ok = true;
        } catch (NumberFormatException e) {
            {System.out.println("Non un entier. Recommencez.");}
        }
    } while (!ok);

    return valentiere;
} catch (IOException e) {
     System.out.println(" ERREUR d'E/S : IO.LIRE_ENTIER");
    return 0;
}
} // Fin de lireEntier

/**
* Affiche un libellé puis permet la saisie d'un entier. Si l'utilisateur
* saisit une valeur non-entière, affichage d'une erreur et demande d'une
* autre saisie.
*
* @param libelle : libellé à afficher en début pour indiquer quelle saisie
* effectuer
* @return int : l'entier saisi.
*
*/
public static int lire_entier(String libelle) {
ES.afficher_libelle(libelle);
return lire_entier();
}  // Fin de lireEntier

//	---------------------------------------------------------------------------------------------------------------------
//	Gestion des flottants
/**
* permet la saisie d'un réel. Si l'utilisateur saisit une valeur
* non-réelle, affichage d'une erreur et demande d'une autre saisie.
*
* @return float : la valeur flottante saisie.
*/
public static float lire_flottant() {
boolean ok = false;
float valflottant = 0;
Float flot;
try {
    do {
        try {
            flot = Float.valueOf(entree.readLine());
            valflottant = flot;
            ok = true;
        } catch (NumberFormatException e) {
            {System.out.println("Non un flottant. Recommencez.");}
        }
    } while (!ok);
    return valflottant;
} catch (IOException e) {
    System.out.println(" ERREUR d'E/S : IO.LIRE_ENTIER");
    return 0;
}
} // Fin de lireFlottant

/**
* Affiche un libellé puis permet la saisie d'un réel. Si l'utilisateur
* saisit une valeur non-réelle, affichage d'une erreur et demande d'une
* autre saisie.
*
* @param libelle : libellé à afficher en début pour indiquer quelle saisie
* effectuer
* @return float : la valeur flottante saisie.
*/
public static float lire_flottant(String libelle) {
ES.afficher_libelle(libelle);
return lire_flottant();
}  // Fin de lireFlottant

//	---------------------------------------------------------------------------------------------------------------------
//	Gestion des dates
/**
* permet la saisie d'une date au format AAAA-MM-JJ. Si l'utilisateur saisit une valeur
* non conforme, affichage d'une erreur et demande d'une autre saisie.
* @return DateLocal : la date est saisie.
*/

public static LocalDate lire_date () {
Boolean OK = false;
LocalDate date = null;
do {
  try {
     String dateSaisie = lire_chaine();
     date = LocalDate.parse(dateSaisie);
     OK = true;
    } catch (DateTimeParseException ignored) {
        {System.out.println ("La date saisie n'est pas valide, Recommencez au format YYYY-MM-JJ");}
}        
}while (!OK);
return date;

} // Fin de lireDate

public static LocalDate lire_date(String libelle) {
ES.afficher_libelle(libelle);
return lire_date();
}   // Fin de lireDate

// Traitements internes
/**
* @return un entier représentant l'année courante.
*/
private static int annee_courante() {
// Instancie une variable date
LocalDate dateCourante = LocalDate.now ();
// Extrait l'année de cette variable et la renvoie
return dateCourante.getYear();
}

/**
* @return un entier représentant le mois courant.
*/
private static int mois_courant() {
// Instancie une variable date
LocalDate dateCourante = LocalDate.now ();
// Extrait le mois de cette variable et la renvoie
return dateCourante.getMonthValue();
}

//	---------------------------------------------------------------------------------------------------------------------
//	Affichage d'informations
/**
* Affichage d'un titre. On fait précéder et suivre le texte reçu par une
* ligne blanche.
*
* @param titre titre à afficher
*/
public static void afficher_titre(String titre) {
System.out.println();
System.out.println(titre);
System.out.println();
System.out.flush();
}

/**
* Affichage d'un message. On revient à la ligne après le message.
*
* @param libelle libelle à afficher.
*/

public static void afficher_libelle(String libelle) {
System.out.println(libelle);
System.out.flush();
}

} // Fin de classe ES
