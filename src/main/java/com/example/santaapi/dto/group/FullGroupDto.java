package com.example.santaapi.dto.group;

import com.example.santaapi.dto.participant.FullParticipantDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullGroupDto {
    private Long id;

    private String name;

    private String description;

    private List<FullParticipantDto> participants;
}
