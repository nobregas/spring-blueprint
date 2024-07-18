package blueprint.springproject.security;

import blueprint.springproject.models.dtos.Login;
import blueprint.springproject.models.entities.User;
import blueprint.springproject.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;

    private final AuthenticationConfiguration authenticationConfiguration;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    public AuthenticationService(
            JwtService jwtService, AuthenticationConfiguration authenticationConfiguration,
            PasswordEncoder passwordEncoder,
            UserService userService
    ) {
        this.jwtService = jwtService;
        this.authenticationConfiguration = authenticationConfiguration;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public String login(Login login) throws Exception {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                login.username(),
                login.password()
        );

        Authentication authentication = authenticationConfiguration
                .getAuthenticationManager().authenticate(authenticationToken);

        return authenticate(authentication);
    }

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication.getName());
    }

    public String register(Login login) throws Exception {
        var user = new User(login);

        user.setPassword(passwordEncoder.encode(login.password()));
        userService.save(user);

        return login(login);
    }
}
