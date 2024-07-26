package com.apsiyon.tb.controller;

import com.apsiyon.tb.dto.UserEventRequest;
import com.apsiyon.tb.dto.UserEventResponse;
import com.apsiyon.tb.services.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user-events")
public class UserEventController {
    @Autowired
    private UserEventService userEventService;

    @PostMapping
    public UserEventResponse createUserEvent(@RequestBody UserEventRequest userEventRequest) {
        return userEventService.createUserEvent(userEventRequest);
    }

    @GetMapping
    public List<UserEventResponse> getAllUserEvents() {
        return userEventService.getAllUserEvents();
    }

    @GetMapping("/{id}")
    public UserEventResponse getUserEventById(@PathVariable Long id) {
        return userEventService.getUserEventById(id);
    }

    @PostMapping("/{eventId}/users/{userId}")
    public UserEventResponse addUserToEvent(@PathVariable Long eventId, @PathVariable Long userId) {
        return userEventService.addUserToEvent(eventId, userId);
    }


}

