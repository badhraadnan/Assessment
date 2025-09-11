package com.assessment.webApplicationBackend.service;

import com.assessment.webApplicationBackend.dto.noteDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    ResponseEntity<noteDto> saveNotes(noteDto dto);
    ResponseEntity<List<noteDto>> getAllNotes();
    ResponseEntity<noteDto> updateNote(noteDto dto);
    ResponseEntity<noteDto> getNoteById(UUID noteId);
    ResponseEntity<Void> deleteNote(UUID noteId);
}
