package com.example.vaadindemo.Views;

import com.example.vaadindemo.model.NoteEntity;
import com.example.vaadindemo.service.NoteService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final NoteService noteService;
    private final Grid<NoteEntity> grid;
    private final TextField newNoteTitle;

    public MainView(NoteService noteService) {
        this.noteService = noteService;

        H2 header = new H2("Notes");
        header.getStyle().set("margin-bottom", "10px");

        grid = new Grid<>(NoteEntity.class);
        grid.setColumns("title");
        grid.setItems(noteService.notes());
        grid.getStyle().set("margin-bottom", "10px");

        grid.addColumn(new ComponentRenderer<>(note -> {
            Button removeButton = new Button("Remove", e -> removeNote(note));
            removeButton.getStyle().set("margin-left", "10px");
            return removeButton;
        })).setHeader("Actions");

        newNoteTitle = new TextField("New Note Title");
        newNoteTitle.setPlaceholder("Enter note title");

        Button addButton = new Button("Add Note", e -> addNote());

        VerticalLayout inputLayout = new VerticalLayout(newNoteTitle, addButton);
        inputLayout.setPadding(false);
        inputLayout.setSpacing(false);
        inputLayout.setAlignItems(Alignment.START);

        setPadding(true);
        setSpacing(false);
        setAlignItems(Alignment.START);

        add(header, inputLayout, grid);

        grid.asSingleSelect().addValueChangeListener(event -> {
            NoteEntity selectedNote = event.getValue();
            if (selectedNote != null) {//niech sprawdza serwis
                getUI().ifPresent(ui -> ui.navigate(NoteDetailView.class, selectedNote.getNoteId().toString()));
            }
        });
    }

    private void addNote() {
        String title = newNoteTitle.getValue();
        if (!title.isEmpty()) {//do serwisu
            noteService.createNote(title);
            grid.setItems(noteService.notes());
            newNoteTitle.clear();
        }
    }

    private void removeNote(NoteEntity note) {
        noteService.deleteById(note.getNoteId());
        grid.setItems(noteService.notes());
        Notification.show("Note removed");
    }
}
