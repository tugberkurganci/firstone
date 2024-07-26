package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.Request;
import com.apsiyon.tb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserEventsId(Long eventId);
}