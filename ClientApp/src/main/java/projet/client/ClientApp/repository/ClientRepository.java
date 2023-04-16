package projet.client.ClientApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projet.client.ClientApp.Entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
