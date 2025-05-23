package com.example.HR.mapper.impl;

import com.example.HR.dto.admin.BlockedUserResponses;
import com.example.HR.entity.models.BlockedUser;
import com.example.HR.mapper.BlockedUserMapper;
import org.springframework.stereotype.Component;

@Component
public class BlockedUserImpl implements BlockedUserMapper {

    @Override
    public BlockedUserResponses toDto(BlockedUser blockedUser) {
        BlockedUserResponses blockedUserResponses = new BlockedUserResponses();
        blockedUserResponses.setUserId(blockedUser.getId());
        blockedUserResponses.setViewingCandidateData(blockedUser.getViewingCandidateData());
        blockedUserResponses.setVacancyAndHiringManagement(blockedUser.getVacancyAndHiringManagement());
        blockedUserResponses.setViewTheStatusOfResponded(blockedUser.getViewTheStatusOfResponded());
        blockedUserResponses.setCommunicationWithEmployers(blockedUser.getCommunicationWithEmployers());
        blockedUserResponses.setCommunicationWithJobSeekers(blockedUser.getCommunicationWithJobSeekers());
        blockedUserResponses.setViewingAndSearchingForVacancies(blockedUser.getViewingAndSearchingForVacancies());
        blockedUserResponses.setViewingCompanyData(blockedUser.getViewingCompanyData());
        return blockedUserResponses;
    }
}
