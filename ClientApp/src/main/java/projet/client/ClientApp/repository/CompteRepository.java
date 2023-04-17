package projet.client.ClientApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.client.ClientApp.Entity.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
