package kz.adilka.springsecurity.app.service;

/**
 * Service for Security
 *
 * @author Adilka Tokushev
 * @version 1.0
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
