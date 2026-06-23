package com.example.demo;
import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(AdminRepository adminRepository) {
        return args -> {
            if (!adminRepository.existsById("admin@test.com")) {
                Admin admin = new Admin();
                admin.setName("Juan Daniel");
                admin.setEmail("admin@test.com");
                admin.setPassword("123456");
                adminRepository.save(admin);
            }
        };
    }
}