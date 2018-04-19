package kz.adilka.springsecurity.app.dao;


import kz.adilka.springsecurity.app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

}
