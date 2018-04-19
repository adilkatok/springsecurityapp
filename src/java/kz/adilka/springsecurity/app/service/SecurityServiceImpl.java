package kz.adilka.springsecurity.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


/**
 * Implementation of {@link SecurityService} interface
 *
 * @author Adilka Tokushev
 * @version 1.0
 *
 */

@Service
public class SecurityServiceImpl implements SecurityService{

    //private static final Logger logger = (Logger) LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInUsername() {
        Object userDaetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDaetails instanceof UserDetails) {
            return ((UserDetails) userDaetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

//            logger.debug(String)
        }
    }
}
