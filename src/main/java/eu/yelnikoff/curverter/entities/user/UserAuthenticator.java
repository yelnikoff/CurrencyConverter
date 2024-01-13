package eu.yelnikoff.curverter.entities.user;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
@RequiredArgsConstructor
public class UserAuthenticator {

    private final AuthenticationManager authenticationManager;

    public boolean authenticate(SignInUserDto user, HttpServletRequest request, HttpServletResponse response) {
        SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(user.getEmail(), user.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();

            context.setAuthentication(authentication);

            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, request, response);

            return true;
        } catch (BadCredentialsException ignored) {}

        return false;
    }

}
