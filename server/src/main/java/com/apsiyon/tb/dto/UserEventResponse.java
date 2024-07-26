package com.apsiyon.tb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEventResponse {
    private Long id;
    private Long organizerId;
    private String eventName;
    private String eventDescription;
    private LocalDateTime eventTime;
    private List<Long> userIds;
    private List<String> userNames;
}