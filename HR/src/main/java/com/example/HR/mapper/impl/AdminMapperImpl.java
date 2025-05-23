package com.example.HR.mapper.impl;

import com.example.HR.dto.admin.ResponsesForAdmin;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.Employer;
import com.example.HR.entity.models.User;
import com.example.HR.mapper.AdminMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public ResponsesForAdmin toDto(User user) {
        if(user == null){
            return  null;
        }
        ResponsesForAdmin forAdmin= new ResponsesForAdmin();
        forAdmin.setUserId(user.getId());
        if(user.getEmployer()!=null){
            forAdmin.setUserName(user.getEmployer().getNameOfCompany());
        }else if (user.getApplicant()!=null){
            forAdmin.setUserName(user.getApplicant().getFirstName());
        }
        forAdmin.setUserRole(user.getRole().name());
        forAdmin.setUserEmail(user.getEmail());
        forAdmin.setLastVisit(user.getLastVisit());
        return forAdmin;
    }

    @Override
    public List<ResponsesForAdmin> toDtos(List<User> users) {
        List<ResponsesForAdmin> forAdmins= new ArrayList<>();
        for(User user:users){
            forAdmins.add(toDto(user));
        }
        return forAdmins;
    }

    @Override
    public ResponsesForAdmin toDtoEmployer(Employer employer) {
        if(employer ==null){
            return  null;
        }
        ResponsesForAdmin forAdminEmployer = new ResponsesForAdmin();
        forAdminEmployer.setUserId(employer.getId());
        forAdminEmployer.setUserName(employer.getNameOfCompany());
        forAdminEmployer.setUserRole(employer.getUser().getRole().name());
        forAdminEmployer.setUserEmail(employer.getEmail());
        forAdminEmployer.setLastVisit(employer.getUser().getLastVisit());
        return forAdminEmployer;

    }

    @Override
    public List<ResponsesForAdmin> toDtosEmployer(List<Employer> employers) {
        List<ResponsesForAdmin> forAdmins = new ArrayList<>();
        for(Employer employer:employers){
            forAdmins.add(toDtoEmployer(employer));
        }
        return forAdmins;
    }

    @Override
    public ResponsesForAdmin toDtoApplicant(Applicant applicant) {
        if (applicant == null) {
            return null;
        }
        ResponsesForAdmin forAdmin= new ResponsesForAdmin();
        forAdmin.setUserId(applicant.getId());
        forAdmin.setUserName(applicant.getFirstName());
        forAdmin.setUserRole(applicant.getUser().getRole().name());
        forAdmin.setLastVisit(applicant.getUser().getLastVisit());
        forAdmin.setUserEmail(applicant.getEmail());
        return forAdmin;
    }

    @Override
    public List<ResponsesForAdmin> toDtosApplicant(List<Applicant> applicants) {
        List<ResponsesForAdmin> forAdmins = new ArrayList<>();
        for(Applicant applicant: applicants){
            forAdmins.add(toDtoApplicant(applicant));
        }
        return forAdmins;
    }
}
