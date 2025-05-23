package com.example.HR.mapper.impl;

import com.example.HR.dto.contactInformation.ContactInformationRequest;
import com.example.HR.dto.contactInformation.ContactInformationResponse;
import com.example.HR.entity.models.ContactInformation;
import com.example.HR.mapper.ContactInformationMapper;

import java.util.ArrayList;
import java.util.List;

public class ContactInformationImpl implements ContactInformationMapper {
    @Override
    public ContactInformationResponse toDto(ContactInformation contactInformation) {
        ContactInformationResponse contactInformationResponse = new ContactInformationResponse();
        contactInformationResponse.setId(contactInformation.getId());
        contactInformationResponse.setCity(contactInformation.getCity());
        contactInformationResponse.setCountry(contactInformation.getCountry());
        contactInformationResponse.setAdress(contactInformation.getAddress());
        contactInformationResponse.setPhone(contactInformation.getPhone());
        return contactInformationResponse;
    }

    @Override
    public ContactInformationResponse requestToresponse(ContactInformationRequest contactInfromationRequest) {
        ContactInformationResponse response = new ContactInformationResponse();
        response.setCity(contactInfromationRequest.getCity());
        response.setCountry(contactInfromationRequest.getCountry());
        response.setAdress(contactInfromationRequest.getAddress());
        response.setPhone(contactInfromationRequest.getPhone());
        return response;
    }

    @Override
    public List<ContactInformationResponse> toDto(List<ContactInformation> contactInformation) {
        List<ContactInformationResponse> contactInformationResponses = new ArrayList<>();
        for(ContactInformation contactInformation1 : contactInformation) {
            contactInformationResponses.add(toDto(contactInformation1));
        }
        return contactInformationResponses;
    }

    @Override
    public ContactInformation requestToEntity(ContactInformationRequest contactInformationRequest) {
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setCity(contactInformationRequest.getCity());
        contactInformation.setCountry(contactInformationRequest.getCountry());
        contactInformation.setAddress(contactInformationRequest.getAddress());
        contactInformation.setPhone(contactInformationRequest.getPhone());
        return contactInformation;
    }
}
