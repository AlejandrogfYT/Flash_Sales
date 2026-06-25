package org.example.flash_sales.Services;

import org.example.flash_sales.DTOs.LoginRequest;
import org.example.flash_sales.DTOs.LoginResponse;
import org.example.flash_sales.DTOs.RegisterRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakService {

    // URL of the endpoint to access the token of the user that is currently authenticated
    // (Is named URI because is more efficient to treat it as a string)
    private final String tokenUri;
    // Endpoint of the admin user used for crating users, and privileged actions
    private final String adminUserURI;
    private final String clientId;
    private final String adminUsername;
    private final String adminPassword;
    // RestClient is used to interact with other APIs at a code level
    private final RestClient restClient;

    public KeycloakService(@Value("${KEYCLOAK_TOKEN_URI}") String tokenUri,
                           @Value("${KEYCLOAK_ADMIN_USERS_URI}") String adminUserURI,
                           @Value("${KEYCLOAK_CLIENT_ID}") String clientId,
                           @Value("${KEYCLOAK_ADMIN_USERNAME}") String adminUsername,
                           @Value("${KEYCLOAK_ADMIN_PASSWORD}") String adminPassword) {
        this.tokenUri = tokenUri;
        this.adminUserURI = adminUserURI;
        this.clientId = clientId;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.restClient = RestClient.create();
    }

    public LoginResponse login(LoginRequest request) {
        // We use a LinkedMultiValueMap because we need to send the request as form-url-encoded
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", clientId);
        body.add("username", request.username());
        body.add("password", request.password());

        return restClient.post()
                .uri(tokenUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .body(LoginResponse.class);
    }

    public void register(RegisterRequest request) {
        String adminToken = fetchAdminToken();

        // 1. Create a user without credentials (password)
        Map<String, Object> userPayload = new HashMap<>();
        userPayload.put("username", request.username());
        userPayload.put("email", request.email());
        userPayload.put("emailVerified", true);
        userPayload.put("requiredActions", List.of());
        userPayload.put("enabled", true);
        userPayload.put("firstName", request.firstName());
        userPayload.put("lastName", request.lastName());

        ResponseEntity<Void> response = restClient.post()
                .uri(adminUserURI)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userPayload)
                .retrieve()
                .toBodilessEntity();

        // 2. Extract userId from Location header
        String location = response.getHeaders().getLocation().toString();
        String userId = location.substring(location.lastIndexOf('/') + 1);

        // 3. Set password via reset-password endpoint
        String resetUri = adminUserURI + "/" + userId + "/reset-password";
        Map<String, Object> passwordPayload = Map.of(
                "type", "password",
                "value", request.password(),
                "temporary", false
        );

        restClient.put()
                .uri(resetUri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .body(passwordPayload)
                .retrieve()
                .toBodilessEntity();
    }

    private String fetchAdminToken() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", clientId);
        body.add("username", adminUsername);
        body.add("password", adminPassword);

        LoginResponse response = restClient.post()
                .uri(tokenUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .body(LoginResponse.class);

        assert response != null;
        return response.access_token();
    }
}
