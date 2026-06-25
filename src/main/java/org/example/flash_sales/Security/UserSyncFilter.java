package org.example.flash_sales.Security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.flash_sales.Enums.UserType;
import org.example.flash_sales.Models.User;
import org.example.flash_sales.Repositories.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// We extend from the OncePerRequestFilter class so that this filter is only executed once
public class UserSyncFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public UserSyncFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        // We extract the authentication from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If the user is authenticated and the authentication principal is an instance of JWT
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {

            String keycloakId = jwt.getSubject(); // UUID of Keycloak
            String email = jwt.getClaimAsString("email");
            String username = jwt.getClaimAsString("name");

            // We verify if the user exists by the keycloak ID
            if (!userRepository.existsUserById(keycloakId)) {
                User shadowUser = new User();
                shadowUser.setId(keycloakId);
                shadowUser.setEmail(email);
                shadowUser.setUsername(username);
                shadowUser.setType(UserType.PUBLIC);
                userRepository.save(shadowUser);
            }
        }
        // Continue to the controller
        filterChain.doFilter(request, response);
    }
}