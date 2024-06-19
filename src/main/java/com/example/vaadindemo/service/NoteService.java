package com.example.vaadindemo.service;

import com.example.vaadindemo.model.NoteEntity;
import com.example.vaadindemo.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NoteService {

    private NoteRepository noteRepository;
    private static final Duration BUFFER_DURATION = Duration.ofMillis(500);
    private final Sinks.Many<NoteEntity> sink = Sinks.many().multicast().directBestEffort();

    public List<NoteEntity> notes() {
        return noteRepository.findAll();
    }

    public void createNote(String title) {
        noteRepository.save(new NoteEntity(title));
    }

    public Optional<NoteEntity> note(UUID noteId) {
        return noteRepository.findById(noteId);
    }

    public Flux<List<NoteEntity>> liveMessages(UUID noteId)  {

        return sink.asFlux().filter(m -> m.getNoteId().equals(noteId)).buffer(BUFFER_DURATION);
    }
}
