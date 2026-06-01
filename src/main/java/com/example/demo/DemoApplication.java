package com.example.demo;

import com.example.demo.model.Admin;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
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
    CommandLineRunner initData(
        AdminRepository adminRepository,
        CategoryRepository categoryRepository,
        ProductRepository productRepository
    ) {
        return args -> {
            if (!adminRepository.existsById("admin@test.com")) {
                Admin admin = new Admin();
                admin.setEmail("admin@test.com");
                admin.setPassword("123456");
                adminRepository.save(admin);
                System.out.println(
                    " Administrador de prueba creado: admin@test.com / 123456"
                );
            }

            if (categoryRepository.count() == 0) {
                Category cat1 = new Category("Celulares");
                Category cat2 = new Category("Marroquinería");
                categoryRepository.save(cat1);
                categoryRepository.save(cat2);
                System.out.println(" Categorías de prueba creadas");

                if (productRepository.count() == 0) {
                    Product p1 = new Product();
                    p1.setName("iPhone 13");
                    p1.setDescription("Smartphone Apple con 128GB");
                    p1.setPrice(800.0);
                    p1.setUrl("https://ejemplo.com/iphone.jpg");
                    p1.setCategory(cat1);

                    Product p2 = new Product();
                    p2.setName("Samsung S22");
                    p2.setDescription("Smartphone Android con 256GB");
                    p2.setPrice(750.0);
                    p2.setUrl("https://ejemplo.com/samsung.jpg");
                    p2.setCategory(cat1);

                    Product p3 = new Product();
                    p3.setName("Bolso de Cuero");
                    p3.setDescription("Bolso artesanal de cuero premium");
                    p3.setPrice(120.0);
                    p3.setUrl("https://ejemplo.com/bolso.jpg");
                    p3.setCategory(cat2);

                    productRepository.save(p1);
                    productRepository.save(p2);
                    productRepository.save(p3);
                    System.out.println(" Productos de prueba creados");
                }
            }
        };
    }
}
