package com.example.hibernatetest.demo;

import com.example.hibernatetest.demo.ResourceNotFoundException;
import com.example.hibernatetest.demo.Note;
import com.example.hibernatetest.demo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    // Get All Notes
    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Create a new Note

    // Get a Single Note

    // Update a Note

    // Delete a Note
}