package midilogging;


import java.util.LinkedList;

public class MidiNoteLogger {

    LinkedList<KeyNote> listOfNotes;

    public MidiNoteLogger(){
       listOfNotes = new LinkedList<>();
    }

    public void logNotePress(int noteValue, int dur){
        KeyNote foundKeyNote = keyNoteExists(noteValue);
        if(foundKeyNote == null || (foundKeyNote != null && !foundKeyNote.isPressed())) {
            listOfNotes.add(new KeyNote(noteValue, dur));
        }
    }

    public void logNoteRelease(int noteValue){
        KeyNote foundKeyNote = keyNoteExists(noteValue);
        if(foundKeyNote != null && foundKeyNote.isPressed()) {
            foundKeyNote.setReleased();
        }
    }

    private KeyNote keyNoteExists(int noteValue){
        for (KeyNote note : listOfNotes) {
            if(note.getNoteValue() == noteValue){
                return note;
            }
        }
        return null;
    }

    public LinkedList<IKeyNote> getNotes(){
        LinkedList<IKeyNote> listOfImmutableNotes = new LinkedList<>();
        for (KeyNote notes : listOfNotes) {
            listOfImmutableNotes.add(new ImmutableKeyNote(notes.getNoteValue(), notes.getTimeStamp()));
        }
        return listOfImmutableNotes;
    }

}
