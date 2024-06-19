package com.example.vaadindemo.Views;

import com.example.vaadindemo.model.NoteEntity;
import com.example.vaadindemo.service.NoteService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.util.UUID;

@Route("note")
public class NoteDetailView extends Div implements HasUrlParameter<String> {

    private final NoteService noteService;
    private NoteEntity note;
    private final TextArea content;

    public NoteDetailView(NoteService noteService) {
        this.noteService = noteService;

        content = new TextArea("Content");
        Button saveButton = new Button("Save", e -> saveNote());

        add(content, saveButton);
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        UUID noteId = UUID.fromString(parameter);
        noteService.note(noteId).ifPresent(noteEntity -> {
            note = noteEntity;
            content.setValue(note.getContent());
        });
    }

    private void saveNote() {
        if (note != null) {
            note.setContent(content.getValue());
            noteService.save(note);
        }
    }
}
