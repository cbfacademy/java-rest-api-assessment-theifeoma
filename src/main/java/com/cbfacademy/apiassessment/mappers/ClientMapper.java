package com.cbfacademy.apiassessment.mappers;

import com.cbfacademy.apiassessment.dto.ClientDto;
import com.cbfacademy.apiassessment.dto.ClientLegalDetails;
import com.cbfacademy.apiassessment.dto.ClientTradeDetails;
import com.cbfacademy.apiassessment.entities.ClientAddress;
import com.cbfacademy.apiassessment.entities.ClientDetails;
import com.cbfacademy.apiassessment.entities.LegalDetails;
import com.cbfacademy.apiassessment.entities.TradeDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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
    @Mapping(source = "tradeDetails.revenue", target = "revenue")
    @Mapping(source = "tradeDetails.subAccountNumber", target = "subAccountNumber")
    ClientTradeDetails mapToClientTradeDetails(ClientDetails clientDetails, TradeDetails tradeDetails);

    @Mapping(source = "clientDetails.clientId", target = "clientId")
    ClientLegalDetails mapToClientLegalDetails(ClientDetails clientDetails, LegalDetails legalDetails);
}
