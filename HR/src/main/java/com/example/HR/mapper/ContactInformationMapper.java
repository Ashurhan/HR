package com.example.HR.mapper;

import com.example.HR.dto.contactInformation.ContactInformationRequest;
import com.example.HR.dto.contactInformation.ContactInformationResponse;
import com.example.HR.entity.models.ContactInformation;

import java.util.List;

public interface ContactInformationMapper {
    ContactInformationResponse toDto(ContactInformation contactInformation);
    ContactInformationResponse requestToresponse(ContactInformationRequest contactInfromationRequest);
    List<ContactInformationResponse> toDto(List<ContactInformation> contactInformation);
    ContactInformation requestToEntity(ContactInformationRequest contactInformationRequest);

}
