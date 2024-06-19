package com.example.vaadindemo.repository;

import com.example.vaadindemo.model.NoteEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SqlNoteRepository implements NoteRepository {

    SqlRepo repository;

    @Override
    public void save(NoteEntity userEntity) {
        repository.save(userEntity);
    }

    @Override
    public void deleteById(UUID noteId) {
        repository.deleteById(noteId);
    }

    @Override
    public Optional<NoteEntity> findById(UUID noteId) {
        return repository.findById(noteId);
    }

    @Override
    public List<NoteEntity> findAll() {
        return repository.findAll();
    }
}
