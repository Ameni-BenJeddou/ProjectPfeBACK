package com.example.MyComar_Back.SpringSecurity.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
        private String token;
        private String type = "Bearer ";
        private Long id;
        private String email;
        private
        List<String> role;
        public JwtResponse(String accessToken, Long id,String email, List<String> roles) {
                this.token = accessToken;
                this.id = id;
                this.email = email;
                this.role = roles;
        }
}
