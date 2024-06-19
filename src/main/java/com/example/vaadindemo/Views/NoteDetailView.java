package com.example.vaadindemo.Views;

import com.example.vaadindemo.model.NoteEntity;
import com.example.vaadindemo.service.NoteService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Route("note")
public class NoteDetailView extends VerticalLayout implements HasUrlParameter<String> {

    private final NoteService noteService;
    private NoteEntity note;
    private final TextArea content;

    public NoteDetailView(NoteService noteService) {
        this.noteService = noteService;

        H2 header = new H2("Note Details");
        header.getStyle().set("margin-bottom", "10px");

        content = new TextArea("Content");
        content.setWidthFull();
        content.getStyle().set("margin-bottom", "10px");

        Button saveButton = new Button("Save", e -> saveNote());
        Button backButton = new Button("Back", e -> getUI().ifPresent(ui -> ui.navigate("")));

        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, backButton);
        buttonLayout.setPadding(false);
        buttonLayout.setSpacing(true);

        setPadding(true);
        setSpacing(false);
        setAlignItems(Alignment.START);

        add(header, content, buttonLayout);
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        UUID noteId = UUID.fromString(parameter);
        noteService.note(noteId).ifPresent(noteEntity -> {
            note = noteEntity;
            content.setValue(note.getContent() != null ? note.getContent() : "");
        });
    }

    private void saveNote() {
        if (note != null) {
            note.setContent(content.getValue());
            noteService.save(note);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = now.format(formatter);
            Notification.show("Note saved at " + formattedTime);
        }
    }
}
