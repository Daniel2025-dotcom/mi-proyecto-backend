package com.example.demo.controller;

import com.example.demo.DTOs.request.LoginResquestDTO;
import com.example.demo.DTOs.response.LoginResponseDTO;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/validator")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> validator(@RequestBody LoginResquestDTO dto) {

        if (loginService.validatorLogin(dto)) {
            String token = loginService.generateToken(dto);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } else {
            return ResponseEntity
                    .status(401)
                    .body(java.util.Map.of("error", "Credenciales incorrectas"));
        }
    }
}