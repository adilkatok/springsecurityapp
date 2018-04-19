package kz.adilka.springsecurity.app.dao;

import kz.adilka.springsecurity.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
