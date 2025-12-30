package com.g6.Rental.seed;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.g6.Rental.entity.Role;
import com.g6.Rental.entity.User;
import com.g6.Rental.repository.RoleRepository;
import com.g6.Rental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SeedAdmin implements CommandLineRunner {
    @Value("${ADMIN_FULLNAME}")
    private String adminFullName;

    @Value("${ADMIN_USERNAME}")
    private String adminUsername;

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

     @Override
    public void run(String... args) {

        Role adminRole = roleRepository.findByName("admin")
        .orElseGet(() -> {
            Role role = new Role();
            role.setName("admin");
            return roleRepository.save(role);
        });

        Role userRole = roleRepository.findByName("user")
        .orElseGet(() -> {
            Role role = new Role();
            role.setName("user");
            return roleRepository.save(role);
        });


        // 2. Create admin user if not exist
        userRepository.findByEmail("admin@gmail.com").ifPresentOrElse(
                user -> {
                    System.out.println("Admin user already exists");
                    
                },
                () -> {
                    User adminUser = new User();
                    adminUser.setFullName(adminFullName);
                    adminUser.setUsername(adminUsername);
                    adminUser.setEmail(adminEmail);
                    adminUser.setPassword(passwordEncoder.encode(adminPassword));
                    adminUser.setRoles(List.of(adminRole, userRole));
                    userRepository.save(adminUser);
                });

    }
}