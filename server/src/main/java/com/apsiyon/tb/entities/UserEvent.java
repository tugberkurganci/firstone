package com.apsiyon.tb.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEvent extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    private String eventName;
    private String eventDescription;

    private LocalDateTime eventTime;

    @ManyToMany
    @JoinTable(
            name = "user_event_users",
            joinColumns = @JoinColumn(name = "user_event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

}
