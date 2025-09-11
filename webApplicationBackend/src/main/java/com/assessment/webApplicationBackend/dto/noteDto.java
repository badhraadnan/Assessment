package com.assessment.webApplicationBackend.dto;

import com.assessment.webApplicationBackend.entity.Note;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class noteDto {

    private UUID noteId;
    private String title;
    private String content;
    private LocalDateTime createdTime;

    public static Note convertToEntity(noteDto dto) {
        return Note.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }

    public static noteDto convertToDto(Note note) {
        return noteDto.builder()
                .noteId(note.getNoteId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdTime(note.getCreatedTime())
                .build();
    }

    public static void updateNote(noteDto dto, Note note) {
        if (dto.getTitle() != null) {
            note.setTitle(dto.getTitle());
        }
        if (dto.getContent() != null) {
            note.setContent(dto.getContent());
        }
    }
}
