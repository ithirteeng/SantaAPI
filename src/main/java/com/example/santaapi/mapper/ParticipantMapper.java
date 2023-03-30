package com.example.santaapi.mapper;

import com.example.santaapi.dto.participant.CreateUpdateParticipantDto;
import com.example.santaapi.dto.participant.FullParticipantDto;
import com.example.santaapi.dto.participant.WithoutRecipientParticipantDto;
import com.example.santaapi.entity.GroupEntity;
import com.example.santaapi.entity.ParticipantEntity;

public class ParticipantMapper {

    public static ParticipantEntity newParticipantToEntity(CreateUpdateParticipantDto newDto, GroupEntity group) {
        return ParticipantEntity.builder()
                .name(newDto.getName())
                .wish(newDto.getWish())
                .group(group)
                .build();
    }

    public static WithoutRecipientParticipantDto entityToSmallDto(ParticipantEntity entity) {
        if (entity != null) {
            return WithoutRecipientParticipantDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .wish(entity.getWish())
                    .build();
        }
        return null;
    }

    public static FullParticipantDto entityToFullDto(ParticipantEntity entity) {
        return FullParticipantDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .wish(entity.getWish())
                .recipient(entityToSmallDto(entity.getRecipient()))
                .build();
    }


}
