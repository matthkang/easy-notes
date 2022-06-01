package com.example.easynotes.controller;

import com.example.easynotes.model.Note;
import com.example.easynotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
REST APIs for creating, retrieving, updating and deleting a Note
 */

/*
RestController is a combination of Controller and ResponseBody
@Controller - define a controller
@ResponseBody - the return value of a method should be used as the response body of the request.
 */
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteService noteService;

    // Get All Notes
    // Retrieve all the notes from the database and returns the entire list
    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/notesDesc")
    public List<Note> getNotesDesc(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "100") Integer pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy) {
        return noteService.getNotesDesc(pageNo, pageSize, sortBy);
    }

    // Create a new Note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteService.createNote(note);
    }

    // Get a Single Note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteService.getNoteById(noteId);
    }

    // Update a Note
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Note noteDetails) {
        return noteService.updateNote(noteId, noteDetails);
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        return noteService.deleteNote(noteId);
    }
}
