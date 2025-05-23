package com.example.HR.mapper;

import com.example.HR.dto.admin.BlockedUserResponses;
import com.example.HR.entity.models.BlockedUser;

public interface BlockedUserMapper {
    BlockedUserResponses toDto(BlockedUser blockedUser);
}
