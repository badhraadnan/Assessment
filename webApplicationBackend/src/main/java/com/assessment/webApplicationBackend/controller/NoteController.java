package com.assessment.webApplicationBackend.controller;

import com.assessment.webApplicationBackend.dto.noteDto;
import com.assessment.webApplicationBackend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/add")
    public ResponseEntity<noteDto> addCustomer(@RequestBody noteDto dto){
        return noteService.saveNotes(dto);
    }

    @GetMapping("/all-notes")
    public ResponseEntity<List<noteDto>> getAllCustomer(){
        return noteService.getAllNotes();
    }

    @PutMapping("/update-notes")
    public ResponseEntity<noteDto> updateCustomer(@RequestBody noteDto dto){
        return noteService.updateNote(dto);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<noteDto> getCustomerById(@PathVariable UUID noteId){
        return noteService.getNoteById(noteId);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID noteId){
        return noteService.deleteNote(noteId);
    }
}
