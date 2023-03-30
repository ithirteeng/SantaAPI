package com.example.santaapi.service.participant;

import com.example.santaapi.dto.participant.CreateUpdateParticipantDto;
import com.example.santaapi.dto.participant.FullParticipantDto;
import com.example.santaapi.dto.participant.WithoutRecipientParticipantDto;
import com.example.santaapi.exception.BadRequestException;
import com.example.santaapi.exception.ConflictException;
import com.example.santaapi.exception.NotFoundException;

import java.util.List;

public interface ParticipantService {

    Long addParticipantToGroup(CreateUpdateParticipantDto dto, Long groupId) throws NotFoundException, BadRequestException;

    void deleteParticipantFromGroup(Long groupId, Long participantId) throws NotFoundException, BadRequestException;

    List<FullParticipantDto> tossParticipants(Long groupId) throws NotFoundException, ConflictException, BadRequestException;

    WithoutRecipientParticipantDto getParticipantRecipient(Long groupId, Long participantId) throws NotFoundException, BadRequestException;

}
