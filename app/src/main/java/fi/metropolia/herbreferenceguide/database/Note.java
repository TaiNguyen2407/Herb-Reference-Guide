package fi.metropolia.herbreferenceguide.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * This class defines Note variables that can be fetched from database
 */
@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private final int noteId;
    private final String noteTitle;
    private final String noteDescription;

    /**
     * Constructor for Note class
     * @param noteId defines note Id
     * @param noteTitle defines note Title
     * @param noteDescription defines note Description
     */
    public Note(int noteId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    /**
     * Getter and setter of note variables
     */
    public int getNoteId() {
        return noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

}
