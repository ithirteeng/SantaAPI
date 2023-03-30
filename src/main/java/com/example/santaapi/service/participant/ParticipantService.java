package com.example.santaapi.service.participant;

import com.example.santaapi.dto.participant.CreateUpdateParticipantDto;
import com.example.santaapi.dto.participant.FullParticipantDto;
import com.example.santaapi.exception.NotFoundException;

import java.util.List;

public interface ParticipantService {

    Long addParticipantToGroup(CreateUpdateParticipantDto dto, Long groupId) throws NotFoundException;

    void deleteParticipantFromGroup(Long groupId, Long participantId) throws NotFoundException;

    List<FullParticipantDto> tossParticipants(Long groupId);

}
