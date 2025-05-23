package com.example.HR;

import com.example.HR.dto.UserResponse;
import com.example.HR.entity.enums.UserRole;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LombokTest {
    
    @Test
    public void testLombokFunctionality() {
        // Test Builder pattern
        UserResponse user = UserResponse.builder()
            .id(1L)
            .firstname("John")
            .lastname("Doe")
            .email("john@example.com")
            .role(UserRole.APPLICANT)
            .isOnline(true)
            .build();
            
        // Test getters
        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstname());
        assertEquals("Doe", user.getLastname());
        assertEquals("john@example.com", user.getEmail());
        assertEquals(UserRole.APPLICANT, user.getRole());
        assertTrue(user.isOnline());
        
        // Test setters
        user.setFirstname("Jane");
        user.setLastname("Smith");
        assertEquals("Jane", user.getFirstname());
        assertEquals("Smith", user.getLastname());
        
        // Test toString
        String toString = user.toString();
        assertTrue(toString.contains("John"));
        assertTrue(toString.contains("Doe"));
        assertTrue(toString.contains("john@example.com"));
        
        // Test equals and hashCode
        UserResponse user2 = UserResponse.builder()
            .id(1L)
            .firstname("John")
            .lastname("Doe")
            .email("john@example.com")
            .role(UserRole.APPLICANT)
            .isOnline(true)
            .build();
            
        assertEquals(user, user2);
        assertEquals(user.hashCode(), user2.hashCode());
    }
} 