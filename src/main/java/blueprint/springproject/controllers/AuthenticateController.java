package blueprint.springproject.controllers;

import blueprint.springproject.models.dtos.JwtResponse;
import blueprint.springproject.models.dtos.Login;
import blueprint.springproject.security.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "Authentication")
public class AuthenticateController {

    private final AuthenticationService authenticationService;

    public AuthenticateController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody @Valid Login login) throws Exception {
        String token = authenticationService.login(login);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        return new ResponseEntity<>(new JwtResponse(token), headers, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody @Valid Login login) throws Exception {
        String token = authenticationService.register(login);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        return new ResponseEntity<>(new JwtResponse(token), headers, HttpStatus.CREATED);
    }
}
