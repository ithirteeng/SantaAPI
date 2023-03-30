package com.example.santaapi.mapper;

import com.example.santaapi.dto.group.CreateUpdateGroupDto;
import com.example.santaapi.dto.group.FullGroupDto;
import com.example.santaapi.dto.group.WithoutParticipantGroupDto;
import com.example.santaapi.dto.participant.FullParticipantDto;
import com.example.santaapi.entity.GroupEntity;
import com.example.santaapi.entity.ParticipantEntity;

import java.util.ArrayList;
import java.util.List;

public class GroupMapper {
    public static GroupEntity newGroupToEntity(CreateUpdateGroupDto newDto) {
        return GroupEntity.builder()
                .name(newDto.getName())
                .description(newDto.getDescription())
                .build();
    }

    public static WithoutParticipantGroupDto entityToSmallDto(GroupEntity entity) {
        return WithoutParticipantGroupDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .name(entity.getName())
                .build();
    }

    public static FullGroupDto entityToFullDto(GroupEntity entity) {
        return FullGroupDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .name(entity.getName())
                .participants(toDto(entity.getParticipants()))
                .build();
    }

    private static List<FullParticipantDto> toDto(List<ParticipantEntity> participants) {
        List<FullParticipantDto> list = new ArrayList<>();
        for (ParticipantEntity entity : participants) {
            list.add(ParticipantMapper.entityToFullDto(entity));
        }
        return list;
    }
}
