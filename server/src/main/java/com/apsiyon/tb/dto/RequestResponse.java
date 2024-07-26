package com.apsiyon.tb.dto;

import com.apsiyon.tb.entities.RequestStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;



@Data
public class RequestResponse {
    private Long id;
    private String serviceName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean started;
    private List<String> employeeNames;
    private RequestStatus status;
    private String username;
    private Long userId;
}

