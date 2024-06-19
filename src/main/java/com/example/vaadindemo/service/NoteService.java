package com.example.vaadindemo.service;

import com.example.vaadindemo.model.NoteEntity;
import com.example.vaadindemo.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteEntity> notes() {
        return noteRepository.findAll();
    }

    public void createNote(String title) {
        noteRepository.save(new NoteEntity(title));
    }

    public Optional<NoteEntity> note(UUID noteId) {
        return noteRepository.findById(noteId);
    }

    public void save(NoteEntity note) {
        noteRepository.save(note);
    }

    public void deleteById(UUID noteId) {
        noteRepository.deleteById(noteId);
    }
}
