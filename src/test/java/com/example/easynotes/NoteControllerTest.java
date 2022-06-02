package com.example.easynotes;

import com.example.easynotes.controller.NoteController;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;


    Note note1 = new Note(1L, "title1", "content1");

    @Test
    public void getAllNotes_success() throws Exception {
        List<Note> notes = new ArrayList<>(Arrays.asList(note1));

        Mockito.when(noteService.getAllNotes()).thenReturn(notes);

        mvc.perform(get("/api/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(note1.getTitle())));
    }

    @Test
    public void createNoteWithEmptyTitle() throws Exception {
        mvc.perform(post("/api/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"\", \"content\": \"sample content\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createNoteWithEmptyContent() throws Exception {
        mvc.perform(post("/api/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"title\", \"content\": \"\"}"))
                .andExpect(status().isBadRequest());
    }

    /*@Test
    public void getNonexistentNote() throws Exception {
        mvc.perform(get("/api/notes/{id}", -1L))
                .andExpect(status().isNotFound());
    }*/
}
