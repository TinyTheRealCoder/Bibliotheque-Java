package modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Lecteur implements Serializable {

    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private final Integer numeroLecteur;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String mail;
    private ArrayList<Emprunt> emprunts;

    public Lecteur(Integer num, String nom, String prenom, LocalDate dateNaiss, String email) {
        this.emprunts = new ArrayList<Emprunt>();

        this.numeroLecteur = num;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaiss;
        this.mail = email;
    }

    public Integer get_numero() {
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

    public ArrayList<Emprunt> get_emprunts(){
        return this.emprunts;
    }
    
    public PublicVise get_public_vise(){
        int age = this.get_age();
        
        if (age < 10){
            return PublicVise.ENFANT;
        } else if (age < 16){
            return PublicVise.ADOLESCENT;
        } else {
            return PublicVise.ADULTE;
        }
            
    }
    
    public boolean est_non_sature(){
        return this.emprunts.size() < 5;
    }
    
    public void detruire_emprunt(Emprunt emp){
        
        this.emprunts.remove(emp); // remove le 1er emprunt trouvé => ne traite pas le cas où il y'a deux emprunts identiques
        
    }
    
    public void creer_emprunt(Exemplaire ex, LocalDate dateEmprunt){
        Emprunt emp = new Emprunt(ex, this, dateEmprunt);
        this.add_emprunt(emp);
    }
    
    private void add_emprunt(Emprunt emp){
        if(this.emprunts == null){
            this.emprunts = new ArrayList<Emprunt>();
        }
        this.emprunts.add(emp);
    }

}
