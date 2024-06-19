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
public class NoteEntity {
    @Id
    @GeneratedValue
    private UUID noteId;
    private String title;
    private String content;


    public NoteEntity(String title) {
        this.title = title;
    }
}
