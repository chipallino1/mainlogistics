package com.example.logistics.controllers;

import com.example.logistics.db.Route;
import com.example.logistics.repositories.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    Repositories routeRepository;

    // Get All Notes
    @GetMapping("/notes")
    public List<Route> getAllNotes() {
        return routeRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/notes")
    public Route createNote(@Valid @RequestBody Route note) {
        return routeRepository.save(note);
    }
    // Get a Single Note

    @GetMapping("/notes/{id}")
    public Route getNoteById(@PathVariable(value = "id") Long noteId) {
        return routeRepository.findById(noteId);
    }
    // Update a Note
    @PutMapping("/notes/{id}")
    public Route updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Route noteDetails) {

        Route note = routeRepository.findById(noteId)
                .orElseThrow(() -> new Exception()));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }
    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}