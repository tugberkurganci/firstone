package com.apsiyon.tb.repositories;

import com.apsiyon.tb.entities.Request;
import com.apsiyon.tb.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}