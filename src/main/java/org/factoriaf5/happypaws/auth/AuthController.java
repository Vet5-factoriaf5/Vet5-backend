package org.factoriaf5.happypaws.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") // puedes parametrizarlo luego en application.properties
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<AuthDTOResponse> login() {

        // Obtenemos el contexto de seguridad
        SecurityContext contextHolder = SecurityContextHolder.getContext();
        Authentication auth = contextHolder.getAuthentication();

        // Creamos la respuesta con username y role
        AuthDTOResponse authResponse = new AuthDTOResponse(
            "Logged",
            auth.getName(),
            auth.getAuthorities().iterator().next().getAuthority()
        );

        return ResponseEntity.ok(authResponse);
    }
}
