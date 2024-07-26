package com.apsiyon.tb.services;
import com.apsiyon.tb.dto.SurveyRequestDto;
import com.apsiyon.tb.dto.SurveyResponseDto;
import com.apsiyon.tb.entities.Survey;
import com.apsiyon.tb.entities.User;
import com.apsiyon.tb.repositories.SurveyRepository;
import com.apsiyon.tb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userService;



    public SurveyResponseDto createSurvey(SurveyRequestDto surveyRequestDto) {
        Survey survey = convertToEntity(surveyRequestDto);
        survey.setCreationTime(LocalDateTime.now());
        Survey savedSurvey = surveyRepository.save(survey);
        return convertToDto(savedSurvey);
    }

    public List<SurveyResponseDto> getAllSurveys() {
        return surveyRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<SurveyResponseDto> getSurveyById(Long id) {
        return surveyRepository.findById(id)
                .map(this::convertToDto);
    }

    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }


    private Survey convertToEntity(SurveyRequestDto surveyRequestDto) {

        Optional<User> user=userService.findById(surveyRequestDto.getCreatorId());
        return new Survey(
                user.get(),
                surveyRequestDto.getSurveyTitle(),
                surveyRequestDto.getSurveyDescription(),
                surveyRequestDto.getCreationTime(),
                null // Handle responses mapping here if needed
        );
    }


    private SurveyResponseDto convertToDto(Survey survey) {
        return new SurveyResponseDto(
                survey.getId(),
                survey.getSurveyTitle(),
                survey.getSurveyDescription(),
                survey.getCreationTime(),
                survey.getCreator().getId()
        );
    }
}
