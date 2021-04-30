package ru.gb.market.happy.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.happy.auth.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}