package projet.client.ClientApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projet.client.ClientApp.Entity.Compte;
import projet.client.ClientApp.Services.CompteService;
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
    private CompteService compteService;

    @Autowired
    private ClientController clientController;

    public static String generateAccountNumber() {
        String randomChars = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        String currentYear = Year.now().toString();
        return randomChars + currentYear;
    }

    @GetMapping("/comptes")
    public List<Compte> ListCompte(){
        return compteService.getCompte();
    }

    @GetMapping("/comptes/{numCompte}")
    public Compte CompteById(@PathVariable(value = "numCompte") String numCompte) {
        return compteService.CompteById(numCompte);
    }

    @PostMapping("/comptes/{id}")
    public Compte createCompte(@Validated @RequestBody Compte compte,@PathVariable("id")Long clientId){
        return compteService.createCompte(compte, clientId);
    }

    @PutMapping("/comptes/{id}")
    public Compte updateCompte(@PathVariable(value = "id") String numCompte, @Validated @RequestBody Compte compte){
        return compteService.updateCompte(numCompte, compte);
    }

    @DeleteMapping("/comptes/{id}")
    public void deleteCompte(@PathVariable(value = "id") String numCompte){
        compteService.deleteCompte(numCompte);
    }


}
