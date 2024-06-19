package com.example.vaadindemo.repository;

import com.example.vaadindemo.model.NoteEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface NoteRepository {

    void save(NoteEntity userEntity);

    void deleteById(UUID noteId);

    Optional<NoteEntity> findById(UUID noteId);

    List<NoteEntity> findAll();
}
