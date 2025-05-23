//package com.example.HR.config;
//
//import com.example.HR.exeptions.CustomException;
//import com.example.HR.repository.ApplicantRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class ApplicantApplicationConfig {
//    private ApplicantRepository applicantRepository;
//
//    public ApplicantApplicationConfig(ApplicantRepository applicantRepository) {
//        this.applicantRepository = applicantRepository;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return email-> (UserDetails) applicantRepository.findByEmail(email).
//                orElseThrow(()-> new CustomException("User not found", HttpStatus.NOT_FOUND));
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)  throws Exception {
//        return config.getAuthenticationManager();
//    }
//}
