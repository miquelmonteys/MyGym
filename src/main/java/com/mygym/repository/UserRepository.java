package com.mygym.repository;

import com.mygym.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndHasAccess(String username, Boolean hasAccess);

    Optional<User> findByEmail(String email);


    Optional<User> findByRestorePasswordAndRestorePasswordDateAfter(String restorePassword,
                                                                    ZonedDateTime restorePasswordDate);
}