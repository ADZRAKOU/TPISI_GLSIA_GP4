package projet.client.ClientApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import projet.client.ClientApp.Entity.Compte;
import projet.client.ClientApp.repository.CompteRepository;

import java.time.Year;
import java.util.List;
import java.util.UUID;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;
    public static String generateAccountNumber() {
        String randomChars = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        String currentYear = Year.now().toString();
        return randomChars + currentYear;
    }
    public List<Compte> getCompte(){
        return compteRepository.findAll();
    }
    public ResponseEntity<Compte> getCompteById(Long compteId) throws ResourceNotFoundException {
        Compte compte = compteRepository.findById(compteId)
                .orElseThrow(() -> new ResourceNotFoundException("Compte not found for this id :: " + compteId));
        return ResponseEntity.ok().body(compte);
    }

    public Compte createCompte(Compte compte){
        Compte cpt = new Compte();
        cpt.setNumCompte(generateAccountNumber());
        return compteRepository.save(compte);
    }
    public void updateCompte(Compte compte, Long comptId){
        compteRepository.save(compte);
    }
    public void  deleteCompte(Long compteId){
        compteRepository.deleteById(compteId);
    }


}
