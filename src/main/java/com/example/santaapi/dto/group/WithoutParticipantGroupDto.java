package com.example.santaapi.dto.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithoutParticipantGroupDto {
    private Long id;

    private String name;

    private String description;
}
