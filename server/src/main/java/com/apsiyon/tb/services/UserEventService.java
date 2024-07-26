package com.apsiyon.tb.services;

import com.apsiyon.tb.dto.UserEventRequest;
import com.apsiyon.tb.dto.UserEventResponse;
import com.apsiyon.tb.entities.User;
import com.apsiyon.tb.entities.UserEvent;
import com.apsiyon.tb.repositories.UserEventRepository;
import com.apsiyon.tb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserEventService {
    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private UserRepository userRepository;

    public UserEventResponse createUserEvent(UserEventRequest userEventRequest) {
        User organizer = userRepository.findById(userEventRequest.getOrganizerId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));
        List<User> users = new ArrayList<>();

        UserEvent userEvent = new UserEvent();
        userEvent.setOrganizer(organizer);
        userEvent.setEventName(userEventRequest.getEventName());
        userEvent.setEventDescription(userEventRequest.getEventDescription());
        userEvent.setEventTime(userEventRequest.getEventTime());
        userEvent.setUsers(users);

        UserEvent savedUserEvent = userEventRepository.save(userEvent);
        return mapToResponse(savedUserEvent);
    }

    public List<UserEventResponse> getAllUserEvents() {
        return userEventRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserEventResponse getUserEventById(Long id) {
        UserEvent userEvent = userEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserEvent not found"));
        return mapToResponse(userEvent);
    }

    public UserEventResponse addUserToEvent(Long eventId, Long userId) {
        UserEvent userEvent = userEventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("UserEvent not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
User user3=userRepository.findByUserEventsId(eventId);
        if( user3!= null)throw new RuntimeException("participant is  already exist");

        userEvent.getUsers().add(user);
        userEventRepository.save(userEvent);

        return mapToResponse(userEvent);
    }

    private UserEventResponse mapToResponse(UserEvent userEvent) {
        List<Long> userIds = userEvent.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList());
        List<String> userNames = userEvent.getUsers().stream()
                .map(User::getFullName)
                .collect(Collectors.toList());

        return new UserEventResponse(
                userEvent.getId(),
                userEvent.getOrganizer().getId(),
                userEvent.getEventName(),
                userEvent.getEventDescription(),
                userEvent.getEventTime(),
                userIds,userNames
        );
    }
}
