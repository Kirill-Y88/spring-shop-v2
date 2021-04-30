package ru.gb.market.happy.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.happy.auth.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}