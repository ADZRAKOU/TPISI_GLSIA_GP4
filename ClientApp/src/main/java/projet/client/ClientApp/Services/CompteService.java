package projet.client.ClientApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import projet.client.ClientApp.Entity.Compte;
import projet.client.ClientApp.controller.ClientController;
import projet.client.ClientApp.repository.CompteRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ClientController clientController;

    public static String generateAccountNumber() {
        String randomChars = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        String currentYear = Year.now().toString();
        return randomChars + currentYear;
    }

    public List<Compte> getCompte(){
        return compteRepository.findAll();
    }

    public Compte CompteById(String numCompte) {
        return compteRepository.findById(numCompte).get();
    }

    public Compte createCompte(@Validated @RequestBody Compte compte, @PathVariable("id")Long clientId){
        compte.setNumCompte(generateAccountNumber());
        compte.setDateCreation(LocalDate.now());
        compte.setProprietere(clientController.getClientById(clientId).getBody());
        return compteRepository.save(compte);
    }

    public Compte updateCompte(@PathVariable(value = "id") String numCompte, @Validated @RequestBody Compte compte){
        Compte cpt = CompteById(numCompte);
        cpt.setTypeCompte(compte.getTypeCompte());
        cpt.setSolde(compte.getSolde());
        return compteRepository.save(cpt);
    }
    public void deleteCompte(@PathVariable(value = "id") String numCompte){
        compteRepository.deleteById(numCompte);
    }


    // a.	Faire un versement sur son compte ;
    public Compte FaireVirement(Compte compte, String numCompte){
        Compte cpt = compteRepository.findById(numCompte).get();
        if (compte.getSolde() <= 0){
            throw new RuntimeException("Montant entré est inférieur à zéro(0)");
        }else{
            double montant = cpt.getSolde() + compte.getSolde();
            cpt.setSolde(montant);
            return compteRepository.save(cpt);
        }
    }

    //b.	Faire un retrait sur son compte si le solde le permet ;
    public Compte Faireretrait(String numCompte, Compte compte) {

        try {
            Compte cpt = compteRepository.findById(numCompte).get();
            double montant = cpt.getSolde() - compte.getSolde();
            if(montant <= 0){
                throw new RuntimeException("Montant entré est inférieur à zéro(0)");
            } else{
                cpt.setSolde(montant);
                return  compteRepository.save(cpt);
            }
        }catch (NullPointerException e){
            throw e;
        }
    }

    //c.	Faire un virement d’un compte à un autre
    public Compte Transfer(String numCptP, Compte compte){
        Compte cpt1 = compteRepository.findById(numCptP).get();
        double montant = cpt1.getSolde() - compte.getSolde();
        if(montant <= 0){
            throw new RuntimeException("Montant entré est inférieur à zéro(0)");
        } else{
            Compte cpt2 = compteRepository.findById(compte.getNumCompte()).get();
            double argent = cpt2.getSolde() + compte.getSolde();
            cpt1.setSolde(montant);
            cpt2.setSolde(argent);
            compteRepository.save(cpt1);
            return  compteRepository.save(cpt2);
        }
    }
}
