package projet.client.ClientApp.Entity;


import jakarta.persistence.*;

import java.time.LocalDate;

enum TypeCompte {
    COURANT,
    EPARGNE
}

@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String numCompte;
    private TypeCompte typeCompte;
    private LocalDate dateCreation;
    private double solde;
    @ManyToOne
    private Client proprietere;

    public Compte() {
    }

    public Compte(String numCompte, TypeCompte typeCompte, LocalDate dateCreation, double solde, Client proprietere) {
        this.numCompte = numCompte;
        this.typeCompte = typeCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.proprietere = proprietere;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }
    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getProprietere() {
        return proprietere;
    }

    public void setProprietere(Client proprietere) {
        this.proprietere = proprietere;
    }


}
