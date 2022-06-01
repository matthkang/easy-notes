package com.example.easynotes.service;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
REST APIs for creating, retrieving, updating and deleting a Note
 */

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    // Get All Notes
    // Retrieve all the notes from the database and returns the entire list
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> getNotesDesc(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Note> resultPage = noteRepository.findAll(paging);
        if (resultPage.hasContent()){
            return resultPage.getContent();
        }
        else {
            return new ArrayList<Note>();
        }
    }

    // Create a new Note
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    // Get a Single Note
    public Note getNoteById(Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    // Update a Note
    public Note updateNote(Long noteId, Note noteDetails) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    // Delete a Note
    public ResponseEntity<?> deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
