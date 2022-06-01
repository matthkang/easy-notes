package com.example.easynotes.repository;

import com.example.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Access Note's data from the repository
Able to use JpaRepository’s methods: save(), findOne(), findAll(), count(), delete()
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
