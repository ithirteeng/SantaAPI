package com.example.santaapi.dto.participant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullParticipantDto {

    private Long id;

    private String name;

    private String wish;

    private WithoutRecipientParticipantDto recipient;
}
