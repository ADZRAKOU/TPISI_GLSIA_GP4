package projet.client.ClientApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projet.client.ClientApp.Entity.Compte;
import projet.client.ClientApp.repository.CompteRepository;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.UUID;
//import projet.client.ClientApp.Services.CompteService;

@RestController
@RequestMapping("api")
public class CompteController {
    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ClientController clientController;

    public static String generateAccountNumber() {
        String randomChars = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        String currentYear = Year.now().toString();
        return randomChars + currentYear;
    }

    @GetMapping("/comptes")
    public List<Compte> getCompte(){
        return compteRepository.findAll();
    }

    @GetMapping("/comptes/{id}")
    public Compte CompteById(@PathVariable(value = "id") Long compteId) {
        return compteRepository.findById(compteId).get();
    }

    @PostMapping("/comptes/{id}")
    public Compte createCompte(@Validated @RequestBody Compte compte,@PathVariable("id")Long clientId){
        compte.setNumCompte(generateAccountNumber());
        compte.setDateCreation(LocalDate.now());
        compte.setProprietere(clientController.getClientById(clientId).getBody());
        return compteRepository.save(compte);
    }

    @PutMapping("/comptes/{id}")
    public Compte updateCompte(@PathVariable(value = "id") Long compteId, @Validated @RequestBody Compte compte){
        Compte cpt = CompteById(compteId);
        cpt.setTypeCompte(compte.getTypeCompte());
        cpt.setSolde(compte.getSolde());
        return compteRepository.save(cpt);
    }

    @DeleteMapping("/comptes/{id}")
    public void deleteCompte(@PathVariable(value = "id") Long compteId){
        compteRepository.deleteById(compteId);
    }

}
