package com.example.santaapi.service.group;

import com.example.santaapi.dto.group.CreateUpdateGroupDto;
import com.example.santaapi.dto.group.FullGroupDto;
import com.example.santaapi.dto.group.WithoutParticipantGroupDto;
import com.example.santaapi.exception.NotFoundException;

import java.util.List;

public interface GroupService {

    FullGroupDto findGroupById(Long id) throws NotFoundException;

    void changeGroup(CreateUpdateGroupDto dto, Long id) throws NotFoundException;

    void createGroup(CreateUpdateGroupDto dto);

    List<WithoutParticipantGroupDto> getGroups();

    void deleteGroup(Long id) throws NotFoundException;

}
