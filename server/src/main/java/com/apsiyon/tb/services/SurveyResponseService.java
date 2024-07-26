package com.apsiyon.tb.services;

import com.apsiyon.tb.dto.SurveyResponseRequestDto;
import com.apsiyon.tb.dto.SurveyResponseResponseDto;
import com.apsiyon.tb.entities.Survey;
import com.apsiyon.tb.entities.SurveyResponse;
import com.apsiyon.tb.entities.User;
import com.apsiyon.tb.repositories.SurveyRepository;
import com.apsiyon.tb.repositories.SurveyResponseRepository;
import com.apsiyon.tb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyResponseService {

    @Autowired
    private SurveyResponseRepository surveyResponseRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    public SurveyResponseResponseDto createSurveyResponse(SurveyResponseRequestDto surveyResponseRequestDto) {
        SurveyResponse surveyResponse = convertToEntity(surveyResponseRequestDto);
        SurveyResponse savedResponse = surveyResponseRepository.save(surveyResponse);
        return convertToResponseDto(savedResponse);
    }

    public List<SurveyResponseResponseDto> getAllResponses() {
        return surveyResponseRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<SurveyResponseResponseDto> getResponseById(Long id) {
        return surveyResponseRepository.findBySurveyId(id)
                .stream().map(this::convertToResponseDto).toList();
    }

    public void deleteResponse(Long id) {
        surveyResponseRepository.deleteById(id);
    }

    private SurveyResponse convertToEntity(SurveyResponseRequestDto surveyResponseRequestDto) {

        Optional<Survey> survey=surveyRepository.findById(surveyResponseRequestDto.getSurveyId());
        Optional<User> user=userRepository.findById(surveyResponseRequestDto.getUserId());

       return new SurveyResponse(
                survey.get(),
               user.get(), // Handle Survey mapping here if neede// Handle User mapping here if needed
                surveyResponseRequestDto.getComment(),
                surveyResponseRequestDto.isResponse()
        );
    }

    private SurveyResponseResponseDto convertToResponseDto(SurveyResponse surveyResponse) {
        return new SurveyResponseResponseDto(
                surveyResponse.getId(),
                surveyResponse.getSurvey().getId(),
                surveyResponse.getCreator().getId(),
                surveyResponse.getComment(),
                surveyResponse.getResponse()
        );
    }
}
