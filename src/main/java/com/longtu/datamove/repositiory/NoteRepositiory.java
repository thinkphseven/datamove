package com.longtu.datamove.repositiory;

import com.longtu.datamove.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteRepositiory extends JpaRepository<Note, Long> {
}
