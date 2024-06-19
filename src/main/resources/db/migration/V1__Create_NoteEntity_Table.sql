CREATE TABLE NoteEntity
(
    noteId  BINARY(16) PRIMARY KEY,
    title   VARCHAR(255) NOT NULL,
    content TEXT
);
