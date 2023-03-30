package com.example.santaapi.controller;

import com.example.santaapi.dto.group.CreateUpdateGroupDto;
import com.example.santaapi.dto.participant.CreateUpdateParticipantDto;
import com.example.santaapi.service.participant.ParticipantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService service;

    @PostMapping("/group/{id}/participant")
    public ResponseEntity<String> createParticipantInGroup(@Valid @RequestBody CreateUpdateParticipantDto dto, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.addParticipantToGroup(dto, id).toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/group/{groupId}/participant/{participantId}")
    public ResponseEntity<String> deleteParticipantFromGroup(@PathVariable Long groupId, @PathVariable Long participantId) {
        try {
            service.deleteParticipantFromGroup(groupId, participantId);
            return ResponseEntity.ok("Participant deleted from group" + groupId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
