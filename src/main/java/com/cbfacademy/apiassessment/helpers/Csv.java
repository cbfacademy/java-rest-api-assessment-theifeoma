package com.cbfacademy.apiassessment.helpers;

import com.cbfacademy.apiassessment.entities.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Csv<T> {
    private final String filePath;

    public Csv(String filePath) {
        this.filePath = filePath;
    }

//    public List<T> readAll() {
//        List<T> entities = new ArrayList<>();
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] data = line.split(",");
//                entities.add(mapToEntity.apply(data));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Handle the exception
//        }
//        return entities;
//    }

    public void writeToCsv(List<T> entities, Function<T, String> mapToCsv) {
        try (FileWriter writer = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(writer);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            for (T entity : entities) {
                printWriter.println(mapToCsv.apply(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }

    public List<Client> readClientFromCsv(){
        List<Client> clients = new ArrayList<>();
        String line;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while((line = br.readLine()) != null){
                String [] attributes = line.split(",");
                Long id = Long.parseLong(attributes[0]);
                String name = attributes[1];
                String contactPerson = attributes[2];
                String email = attributes[3];
                String phoneNumber = attributes[4];
                String address = attributes[5];

                Client client = new Client(id, name, contactPerson, email, phoneNumber, address);

                clients.add(client);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return clients;
    }

    public void addNewClientToClientCsv(Client client){
        String clients = convertSingleClientToStringForCsv(client);

        try{
            FileWriter fileWriter = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\n" + clients);
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String convertSingleClientToStringForCsv(Client client){
        return (client.getId() + "," + client.getName() + "," + client.getContactPerson() + "," + client.getEmail() + "," + client.getPhoneNumber() + "," + client.getAddress());
    }

    public List<String> convertEmployeeToStringForCSV(List<Client> clients) {
        List<String> clientStringArray = new ArrayList<>();
        for (Client client :clients) {
            String singleClient = convertSingleClientToStringForCsv(client);
            clientStringArray.add(singleClient);
        }
        //debug
        System.out.println(clientStringArray);
        return clientStringArray;
    }
}
