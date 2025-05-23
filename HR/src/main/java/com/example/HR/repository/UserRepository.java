package com.example.HR.repository;

import com.example.HR.entity.enums.UserRole;
import com.example.HR.entity.models.Applicant;
import com.example.HR.entity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User getUserById(Long id);

    Optional<User> findByGoogleId(String googleId);


    @Query("SELECT u FROM User u WHERE u.role IN : roles ")
    List<User> findAllByRoleAndRole(@Param("roles") List<UserRole> roles);

    List<User> findAllByRole(UserRole role);
    @Query("SELECT u FROM User u WHERE u.role IN :roles")
    List<User> findEmployersAndApplicants(@Param("roles") List<UserRole> roles);



    User findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN u.applicant js " +
            "LEFT JOIN u.employer emp " +
            "WHERE lower(js.firstName) LIKE lower(concat('%', :name, '%')) " +
            "OR lower(js.lastName) LIKE lower(concat('%', :name, '%')) " +
            "OR lower(emp.nameOfCompany) LIKE lower(concat('%', :name, '%'))")
    List<User> searchByFirstNameLastNameAndCompanyName(@Param("name") String name);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN u.applicant js " +
            "LEFT JOIN u.employer emp " +
            "WHERE (lower(js.firstName) LIKE lower(concat('%', :name, '%')) " +
            "OR lower(js.lastName) LIKE lower(concat('%', :name, '%')) " +
            "OR lower(emp.nameOfCompany) LIKE lower(concat('%', :name, '%'))) " +
            "AND u.role = :role " +
            "ORDER BY u.firstName ASC, u.role ASC")
    List<User> searchByNameAndRoleSortedByNameAndRole(
            @Param("name") String name,
            @Param("role") UserRole role
    );


    Optional<User> findByVerificationCode(String code);
}
