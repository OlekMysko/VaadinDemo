package com.example.vaadindemo.Views;

import com.example.vaadindemo.model.NoteEntity;
import com.example.vaadindemo.service.NoteService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final NoteService noteService;
    private final Grid<NoteEntity> grid;
    private final TextField newNoteTitle;

    public MainView(NoteService noteService) {
        this.noteService = noteService;

        grid = new Grid<>(NoteEntity.class);
        grid.setColumns("title");
        grid.setItems(noteService.notes());

        newNoteTitle = new TextField("New Note Title");
        Button addButton = new Button("Add Note", e -> addNote());

        grid.asSingleSelect().addValueChangeListener(event -> {
            NoteEntity selectedNote = event.getValue();
            if (selectedNote != null) {
                getUI().ifPresent(ui -> ui.navigate(NoteDetailView.class, selectedNote.getNoteId().toString()));
            }
        });

        add(newNoteTitle, addButton, grid);
    }

    private void addNote() {
        String title = newNoteTitle.getValue();
        if (!title.isEmpty()) {
            noteService.createNote(title);
            grid.setItems(noteService.notes());
            newNoteTitle.clear();
        }
    }
}
