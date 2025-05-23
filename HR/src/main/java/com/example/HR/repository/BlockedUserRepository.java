package com.example.HR.repository;

import com.example.HR.entity.models.BlockedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedUserRepository extends JpaRepository<BlockedUser ,Long> {

}
