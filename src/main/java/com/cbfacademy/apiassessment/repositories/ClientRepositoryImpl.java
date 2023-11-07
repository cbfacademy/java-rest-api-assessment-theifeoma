package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.entities.Client;
import com.cbfacademy.apiassessment.helpers.Csv;

import java.util.List;

import static com.cbfacademy.apiassessment.constants.Const.CSV_FILE_PATH;

public class ClientRepositoryImpl implements ClientRepository{

    private Csv<Client> clientCsv;

    public ClientRepositoryImpl() {
        this.clientCsv = new Csv<>(CSV_FILE_PATH);
    }

    @Override
    public void save(Client client) {
        clientCsv.addNewClientToClientCsv(client);
    }

    @Override
    public Client findById(Long id) {
        List<Client> clients = clientCsv.readClientFromCsv();
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        List<Client> clients = clientCsv.readClientFromCsv();
        clients.removeIf(client -> client.getId().equals(id));
        clientCsv.writeToCsv(clients, Client::toString);
    }


}
