package com.bookmybus.tms.database.repository;

import com.bookmybus.tms.database.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}