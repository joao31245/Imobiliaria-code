package com.jotace.imob.repository.user;

import com.jotace.imob.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findUserByEmail(String email);

    User findUserByName(String name);
}
