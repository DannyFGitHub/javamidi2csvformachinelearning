package midilogging;

public class ImmutableKeyNote implements IKeyNote {
    private final int noteValue;
    private final int vel;

    public ImmutableKeyNote(int noteValue, int vel) {
        this.noteValue = noteValue;
        this.vel = vel;
    }

    public int getNoteValue() {
        return noteValue;
    }

    public int getTimeStamp() {
        return vel;
    }

    @Override
    public String toString(){
        return "" + noteValue;
    }
}
