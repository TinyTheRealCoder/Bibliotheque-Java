package modele;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;

public class Lecteur implements Serializable {

private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
private final Integer numeroLecteur;
private String nom;
private String prenom;
private LocalDate dateNaissance;
private String mail;
private ArrayList<Exemplaire> exemplaires;

public Lecteur(Integer num, String nom, String prenom, LocalDate dateNaiss, String email) {
    this.numeroLecteur = num;
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = dateNaiss;
    this.mail = email;
}

public Integer get_num() {
    return numeroLecteur;
}

public String get_nom() {
    return nom;
}

public String get_prenom() {
    return prenom;
}

public LocalDate get_date_naissance() {
    return dateNaissance;
}

public String get_mail() {
    return this.mail;
}

public Integer get_age() {
    int age;
    LocalDate dateNaissComp;
    LocalDate dateActuelle = LocalDate.now();
    dateNaissComp = LocalDate.of(dateActuelle.getYear(), dateNaissance.getMonthValue(), dateNaissance.getDayOfMonth());
    if (dateNaissComp.isBefore(dateActuelle)) {
        age = dateActuelle.getYear() - dateNaissance.getYear();
    } else {
        age = dateActuelle.getYear() - dateNaissance.getYear() - 1;
    }
    return age;
}
}
