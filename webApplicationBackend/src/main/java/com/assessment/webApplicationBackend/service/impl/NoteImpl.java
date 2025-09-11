package com.assessment.webApplicationBackend.service.impl;

import com.assessment.webApplicationBackend.dto.noteDto;
import com.assessment.webApplicationBackend.entity.Note;
import com.assessment.webApplicationBackend.exception.NotesNotFound;
import com.assessment.webApplicationBackend.repo.NoteRepo;
import com.assessment.webApplicationBackend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteImpl implements NoteService {

    @Autowired
    private NoteRepo noteRepo;

    @Override
    public ResponseEntity<noteDto> saveNotes(noteDto dto) {
        Optional<Note> existNote = noteRepo.findByNoteId(dto.getNoteId());
        if (existNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Note note = noteDto.convertToEntity(dto);
        Note saveNote = noteRepo.save(note);

        return ResponseEntity.ok(noteDto.convertToDto(saveNote));

    }

    @Override
    public ResponseEntity<List<noteDto>> getAllNotes() {
        List<Note> notes = noteRepo.findAll();

        List<noteDto> dto = notes.stream()
                .map(noteDto::convertToDto)
                .toList();
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<noteDto> updateNote(noteDto dto) {
        Note existNote = noteRepo.findByNoteId(dto.getNoteId())
                .orElseThrow(() -> new NotesNotFound("Notes Not Found"));

        noteDto.updateNote(dto, existNote);
        Note saveNote = noteRepo.save(existNote);

        return ResponseEntity.ok(noteDto.convertToDto(saveNote));
    }

    @Override
    public ResponseEntity<noteDto> getNoteById(UUID noteId) {
        Note existNote = noteRepo.findByNoteId(noteId)
                .orElseThrow(() -> new NotesNotFound("Notes Not Found"));

        return ResponseEntity.ok(noteDto.convertToDto(existNote));
    }

    @Override
    public ResponseEntity<Void> deleteNote(UUID noteId) {
        Note existNote = noteRepo.findByNoteId(noteId)
                .orElseThrow(() -> new NotesNotFound("Notes Not Found"));

        noteRepo.deleteById(noteId);
        return ResponseEntity.ok().build();
    }
}
