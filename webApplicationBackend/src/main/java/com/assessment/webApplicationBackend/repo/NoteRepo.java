package com.assessment.webApplicationBackend.repo;

import com.assessment.webApplicationBackend.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepo extends JpaRepository<Note, UUID> {
    Optional<Note> findByNoteId(UUID noteId);
}
