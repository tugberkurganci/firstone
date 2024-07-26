package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.Request;
import com.apsiyon.tb.entities.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {
    List<SurveyResponse>findBySurveyId(Long id);
}