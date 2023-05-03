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


}
