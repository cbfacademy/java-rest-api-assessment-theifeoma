package com.cbfacademy.apiassessment.repositories;

import com.cbfacademy.apiassessment.dto.ClientInternalContact;
import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.helpers.CSVDataConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.cbfacademy.apiassessment.constants.Const.*;

@Repository
public class ClientInternalContactRepository {

    private File jsonFile;
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(ClientInternalContactRepository.class);

    public ClientInternalContactRepository(@Value(INTERNAL_CONTACT_JSON_REPOSITORY) String filePath) {
        this.jsonFile = new File(filePath);
        this.objectMapper = new ObjectMapper();

        // Call the initialization method to perform CSV to Dto to JSON conversion
        initialiseInternalContactRepository();
    }

    private void initialiseInternalContactRepository() {
        // Check if the JSON file is empty
        if (jsonFile.length() == 0) {
            // If the JSON file is empty, perform the CSV to Dto to JSON conversion
            CSVDataConverter csvDataConverter = new CSVDataConverter();
            csvDataConverter.convertCSVToDtoToClientInternalJson(CLIENT_INTERNAL_CONTACT_CSV_DATA_FILES_LIST, INTERNAL_CONTACT_JSON_REPOSITORY);
        } else {
            // If the JSON file is not empty, log a message and continue using the existing JSON data
            log.info("Legal Details JSON file is not empty. Skipping CSV to JSON conversion.");
        }
    }

    public List<ClientInternalContact> getAll() throws IOException {
        List<ClientInternalContact> clientInternalContactList = objectMapper.readValue(jsonFile, new TypeReference<List<ClientInternalContact>>() {
        });
        return clientInternalContactList != null ? clientInternalContactList : new ArrayList<>();
    }

    //get by role

    //get by employeeId
}
