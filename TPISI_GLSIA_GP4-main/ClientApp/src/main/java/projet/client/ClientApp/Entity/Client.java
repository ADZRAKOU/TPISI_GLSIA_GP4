package projet.client.ClientApp.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenoms;
    private Date dateNaiss;
    private String sexe;
    private String adresse;
    private String telephone;

    public Client() {
    }

    public Client(long id, String nom, String prenoms, Date dateNaiss, String sexe, String adresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenoms = prenoms;
        this.dateNaiss = dateNaiss;
        this.sexe = sexe;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
