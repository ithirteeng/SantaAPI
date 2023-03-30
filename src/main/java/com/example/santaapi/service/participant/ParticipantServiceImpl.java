package com.example.santaapi.service.participant;

import com.example.santaapi.dto.participant.CreateUpdateParticipantDto;
import com.example.santaapi.dto.participant.FullParticipantDto;
import com.example.santaapi.entity.ParticipantEntity;
import com.example.santaapi.exception.NotFoundException;
import com.example.santaapi.mapper.ParticipantMapper;
import com.example.santaapi.repository.GroupRepository;
import com.example.santaapi.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final GroupRepository groupRepository;

    @Transactional
    @Override
    public Long addParticipantToGroup(CreateUpdateParticipantDto dto, Long groupId) throws NotFoundException {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException("Группы с таким id не существует"));

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
    public void deleteParticipantFromGroup(Long groupId, Long participantId) throws NotFoundException {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException("Группы с таким id не существует"));

        var participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new NotFoundException("Участника с таким id не существует"));

        if (!groupId.equals(participant.getGroup().getId())) {
            throw new NotFoundException("Участника с таким id не существует");
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
    public List<FullParticipantDto> tossParticipants(Long groupId) {
        return null;
    }
}
