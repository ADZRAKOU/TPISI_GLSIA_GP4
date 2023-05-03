package projet.client.ClientApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projet.client.ClientApp.Entity.Client;
import projet.client.ClientApp.Services.ClientService;

import java.util.List;


@RestController
@RequestMapping("api")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<Client> getAllClient(){
        return clientService.getAllClient();
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(value = "id") Long clientId){
           ;
            return clientService.getClientById(clientId);
    }
    @PostMapping("/clients")
    public Client createClient(@Validated @RequestBody Client client) {

        return clientService.createClient(client);
    }


    @DeleteMapping("/clients/{id}")
    public void deleteClient (@PathVariable(value = "id") Long Clientid)
    {
        clientService.deleteClient(Clientid);
    }

    @PutMapping("/clients/{id}")
    public  void updateClient(@Validated @RequestBody Client client, @PathVariable(value = "id") Long clientId)
    {
        //Client clt = clientService.getClientById(clientId).getBody();
        clientService.updateClient(client, clientId);
    }

}
