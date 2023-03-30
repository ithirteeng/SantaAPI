package com.example.santaapi.service.group;

import com.example.santaapi.dto.group.CreateUpdateGroupDto;
import com.example.santaapi.dto.group.FullGroupDto;
import com.example.santaapi.dto.group.WithoutParticipantGroupDto;
import com.example.santaapi.entity.GroupEntity;
import com.example.santaapi.exception.NotFoundException;
import com.example.santaapi.mapper.GroupMapper;
import com.example.santaapi.repository.GroupRepository;
import com.example.santaapi.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;

    @Transactional
    @Override
    public FullGroupDto findGroupById(Long id) throws NotFoundException {
        var entity = groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такой группы не существует"));

//        var participants = participantRepository.findAllByGroup_Id(id);
//        entity.setParticipants(participants);

        return GroupMapper.entityToFullDto(entity);
    }

    @Transactional
    @Override
    public void changeGroup(CreateUpdateGroupDto dto, Long id) throws NotFoundException {
        var entity = groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такой группы не существует"));
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        groupRepository.save(entity);
    }

    @Transactional
    @Override
    public void createGroup(CreateUpdateGroupDto dto) {
        var entity = GroupMapper.newGroupToEntity(dto);
        groupRepository.save(entity);
    }

    @Transactional
    @Override
    public List<WithoutParticipantGroupDto> getGroups() {
        var listOfGroups = groupRepository.findAll();
        List<WithoutParticipantGroupDto> list = new ArrayList<>();
        for (GroupEntity entity : listOfGroups) {
            list.add(GroupMapper.entityToSmallDto(entity));
        }
        return list;
    }

    @Transactional
    @Override
    public void deleteGroup(Long id) throws NotFoundException {
        var entity = groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Такой группы не существует"));
        groupRepository.delete(entity);
    }
}
