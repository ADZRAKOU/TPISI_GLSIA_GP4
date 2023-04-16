package projet.client.ClientApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import projet.client.ClientApp.Entity.Client;
import projet.client.ClientApp.repository.ClientRepository;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    public List<Client> getAllClient(){
        return  clientRepository.findAll();
    }

    public ResponseEntity<Client> getClientById(Long clientId) throws ResourceNotFoundException {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found for this id :: " + clientId));
        return ResponseEntity.ok().body(client);
    }
    public Client createClient(Client client) {

        return clientRepository.save(client);
    }

    public void updateClient(Client client, Long id){
        clientRepository.save(client);
    }
    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

}
