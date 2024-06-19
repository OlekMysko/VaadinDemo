package com.example.vaadindemo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NOTE")
public class NoteEntity {
    @Id
    @GeneratedValue
    @Column(name = "note_id", nullable = false)
    private UUID noteId;
    @Column(name = "note_title")
    private String title;
    @Column(name = "note_content")
    private String content;


    public NoteEntity(String title) {
        this.title = title;
    }
}
