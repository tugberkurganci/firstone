package com.apsiyon.tb.dto;


import com.apsiyon.tb.entities.RequestStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestRequest {
    private Long id;
    private Long userId;
    private Long serviceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean started;
    private RequestStatus status;
}

