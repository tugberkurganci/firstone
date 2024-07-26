package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
    UserEvent findByUsersId(Long userId);
    // Additional query methods if needed
}