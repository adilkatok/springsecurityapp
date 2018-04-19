package kz.adilka.springsecurity.app.service;

import kz.adilka.springsecurity.app.model.User;

/**
 * Service class for {@link kz.adilka.springsecurity.app.model.User}
 *
 * @author Adilka Tokushev
 * @version 1.0
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
