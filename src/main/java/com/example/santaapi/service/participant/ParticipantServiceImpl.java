package com.example.santaapi.service.participant;

import com.example.santaapi.dto.participant.CreateUpdateParticipantDto;
import com.example.santaapi.dto.participant.FullParticipantDto;
import com.example.santaapi.dto.participant.WithoutRecipientParticipantDto;
import com.example.santaapi.entity.GroupEntity;
import com.example.santaapi.entity.ParticipantEntity;
import com.example.santaapi.exception.BadRequestException;
import com.example.santaapi.exception.ConflictException;
import com.example.santaapi.exception.NotFoundException;
import com.example.santaapi.mapper.ParticipantMapper;
import com.example.santaapi.repository.GroupRepository;
import com.example.santaapi.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final GroupRepository groupRepository;

    @Transactional
    @Override
    public Long addParticipantToGroup(CreateUpdateParticipantDto dto, Long groupId) throws BadRequestException {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new BadRequestException("Группы с таким id не существует"));

        var entity = ParticipantMapper.newParticipantToEntity(dto, group);

        participantRepository.save(entity);

        var participants = group.getParticipants();
        participants.add(entity);

        group.setParticipants(participants);

        groupRepository.save(group);

        return entity.getId();
    }

    @Transactional
    @Override
    public void deleteParticipantFromGroup(Long groupId, Long participantId) throws BadRequestException {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new BadRequestException("Группы с таким id не существует"));

        var participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new BadRequestException("Участника с таким id не существует"));

        if (!groupId.equals(participant.getGroup().getId())) {
            throw new BadRequestException("Участника с таким id не существует");
        } else {
            var list = group.getParticipants();

            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                if (Objects.equals(list.get(i).getId(), participantId)) {
                    index = i;
                    break;
                }
            }
            list.remove(index);

            group.setParticipants(list);
            groupRepository.save(group);

            participantRepository.deleteById(participantId);
        }

    }

    @Transactional
    @Override
    public List<FullParticipantDto> tossParticipants(Long groupId) throws ConflictException, BadRequestException {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new BadRequestException("Группы с таким id не существует"));

        var participants = group.getParticipants();

        if (canBeTossed(group)) {


            for (int i = 0; i < participants.size(); i++) {
                addParticipantRecipient(participants, i);
            }

            var list = participantRepository.findAllByGroup_Id(groupId);

            group.setParticipants(list);
            groupRepository.save(group);

            List<FullParticipantDto> result = new ArrayList<>();
            for (ParticipantEntity entity : list) {
                result.add(ParticipantMapper.entityToFullDto(entity));
            }
            return result;
        } else {
            throw new ConflictException("Группа с id: " + groupId + " слишком мала");

        }
    }

    @Transactional
    @Override
    public WithoutRecipientParticipantDto getParticipantRecipient(Long groupId, Long participantId) throws BadRequestException {
        groupRepository.findById(groupId)
                .orElseThrow(() -> new BadRequestException("Группы с таким id не существует"));

        var participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new BadRequestException("Участника с таким id не существует"));

        if (!groupId.equals(participant.getGroup().getId())) {
            throw new BadRequestException("Участника с таким id не существует");
        } else {
            return ParticipantMapper.entityToSmallDto(participant.getRecipient());
        }
    }

    @Transactional
    void addParticipantRecipient(List<ParticipantEntity> participants, int index) {

        if (index <= participants.size() - 2) {
            participants.get(index).setRecipient(participants.get(index + 1));
        } else {
            participants.get(index).setRecipient(participants.get(0));
        }

        participantRepository.save(participants.get(index));
    }

    private Boolean checkIfParticipantHasRecipient(ParticipantEntity entity) {
        return entity.getRecipient() != null;
    }

    private Boolean canBeTossed(GroupEntity group) {
        return group.getParticipants().size() >= 3;
    }
}
