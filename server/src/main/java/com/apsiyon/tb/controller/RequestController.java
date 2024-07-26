package com.apsiyon.tb.controller;

import com.apsiyon.tb.dto.RequestRequest;
import com.apsiyon.tb.dto.RequestResponse;
import com.apsiyon.tb.entities.RequestStatus;
import com.apsiyon.tb.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public ResponseEntity<List<RequestResponse>> getAllRequests() {
        List<RequestResponse> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }



    @GetMapping("/{userId}")
    public List<RequestResponse>  getRequestByUserIdId(@PathVariable Long userId) {
        return requestService.getRequestByUserId(userId);

    }

    @GetMapping("/employee/{userId}")
    public List<RequestResponse>  getRequestByEmloyeesId(@PathVariable Long userId) {
        return requestService.getRequestByEmloyeesId(userId);

    }


    @PostMapping
    public ResponseEntity<RequestResponse> addRequest(@RequestBody RequestRequest request) {
        RequestResponse newRequest = requestService.createRequest(request);
        return ResponseEntity.ok(newRequest);
    }

    @PutMapping
    public ResponseEntity<RequestResponse> updateRequestStatus( @RequestBody RequestRequest request) {
        RequestResponse updatedRequest = requestService.updateRequestStatus( request);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteRequestByID(@PathVariable Long id) {
        return requestService.deleteRequestByID(id);

    }

}
