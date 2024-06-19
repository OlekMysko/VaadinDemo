package com.example.vaadindemo.repository;

import com.example.vaadindemo.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SqlRepo extends JpaRepository<NoteEntity, UUID> {
}