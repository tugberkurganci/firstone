package com.apsiyon.tb.controller;

import com.apsiyon.tb.dto.ConciergeRequest;
import com.apsiyon.tb.dto.ConciergeResponse;
import com.apsiyon.tb.entities.Concierge;
import com.apsiyon.tb.services.ConciergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concierge-services")
public class ConciergeController {

    private final ConciergeService conciergeService;

    @Autowired
    public ConciergeController(ConciergeService conciergeService) {
        this.conciergeService = conciergeService;
    }

    @GetMapping
    public List<ConciergeResponse> getAllServices() {
        return conciergeService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConciergeResponse> getServiceById(@PathVariable Long id) {
        return conciergeService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ConciergeResponse addService(@RequestBody ConciergeRequest service) {
        return conciergeService.addService(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConciergeResponse> updateService(@PathVariable Long id, @RequestBody ConciergeRequest updatedService) {
        ConciergeResponse service = conciergeService.updateService(id, updatedService);
        if (service != null) {
            return ResponseEntity.ok(service);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        conciergeService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
