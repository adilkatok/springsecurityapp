package kz.adilka.springsecurity.app.service;

import kz.adilka.springsecurity.app.dao.RoleDao;
import kz.adilka.springsecurity.app.dao.UserDao;
import kz.adilka.springsecurity.app.model.Role;
import kz.adilka.springsecurity.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserService} interface.
 *
 * @author Adilka Tokushev
 * @version 1.0
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles  = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
