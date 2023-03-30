package com.example.santaapi.repository;

import com.example.santaapi.entity.GroupEntity;
import com.example.santaapi.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    List<ParticipantEntity> findAllByGroup_Id(Long groupId);
}
