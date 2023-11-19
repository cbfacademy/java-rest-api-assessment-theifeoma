package com.cbfacademy.apiassessment.mappers;

import com.cbfacademy.apiassessment.dto.*;
import com.cbfacademy.apiassessment.entities.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "clientDetails.clientId", target = "clientId")
    ClientDto mapToClientDto(ClientDetails clientDetails, ClientAddress clientAddress);

    @Mapping(source = "addressHouseNumber", target = "addressHouseNumber")
    ClientAddress maptoClientAddress(ClientDto clientDto);

    @Mapping(source = "clientId", target = "clientId")
    ClientDetails mapClientDetails(ClientDto clientDto);

    @Mapping(source = "clientDetails.clientId", target = "clientId")
    @Mapping(target = "listOfClientTrades", source = "tradeDetails", qualifiedByName = "mapTradeDetailsList")
    ClientTradeDetails mapToClientTradeDetails(ClientDetails clientDetails, List<TradeDetails> tradeDetails);

    @Mapping(target = "timeOfExecution", source = "timeOfExecution", qualifiedByName = "mapToLocalDate")
    TradeDetailsDto mapToTradeDetailsDto(TradeDetails tradeDetails);

    @Mapping(source = "clientDetails.clientId", target = "clientId")
    ClientLegalDetails mapToClientLegalDetails(ClientDetails clientDetails, LegalDetails legalDetails);

    @Mapping(source = "employeeDetails.firstName", target = "firstName")
    @Mapping(source = "employeeDetails.middleName", target = "middleName")
    @Mapping(source = "employeeDetails.familyName", target = "familyName")
    @Mapping(source = "employeeDetails.role", target = "role")
    @Mapping(source = "employeeDetails.email", target = "email")
    @Mapping(source = "employeeDetails.telephoneNumber", target = "telephoneNumber")
    ClientInternalContact mapToClientInternalContact(ClientDetails clientDetails, EmployeeDetails employeeDetails);

    @Named("mapToLocalDate")
    default LocalDate mapToLocalDate(String timeOfExecution) {
        try {
            //conversion logic from String to LocalDate
            return LocalDate.parse(timeOfExecution, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            //log the error and return a default LocalDate of now
            System.err.println("Error parsing date: " + e.getMessage());
            return LocalDate.now(); // or throw a custom exception
        }
    }

    @Named("mapTradeDetailsList")
    default List<TradeDetailsDto> mapTradeDetailsList(List<TradeDetails> tradeDetailsList) {
        return tradeDetailsList.stream()
                .map(this::mapToTradeDetailsDto)
                .collect(Collectors.toList());
    }

}
