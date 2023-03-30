package com.example.santaapi.controller;

import com.example.santaapi.dto.group.CreateUpdateGroupDto;
import com.example.santaapi.service.group.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/group")
    public ResponseEntity<String> createGroup(@Valid @RequestBody CreateUpdateGroupDto dto) {
        try {
            groupService.createGroup(dto);
            return ResponseEntity.ok("Group created!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/group/{id}")
    public ResponseEntity<String> changeGroup(@Valid @RequestBody CreateUpdateGroupDto dto, @PathVariable Long id) {
        try {
            groupService.changeGroup(dto, id);
            return ResponseEntity.ok("Group changed!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/groups")
    public ResponseEntity<?> getAllGroups() {
        try {
            return ResponseEntity.ok(groupService.getGroups());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<?> getGroup(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(groupService.findGroupById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        try {
            groupService.deleteGroup(id);
            return ResponseEntity.ok("Group deleted!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
