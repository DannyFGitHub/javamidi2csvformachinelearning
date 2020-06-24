package midilogging;

public class KeyNote implements IKeyNote {

    private boolean isPressed;
    private boolean isReleased = false;
    private int noteValue;
    private int timeStamp;

    public KeyNote(int noteValue, int timeStamp){
        this.noteValue = noteValue;
        this.timeStamp = timeStamp;
        this.isPressed = true;
        this.isReleased = false;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public boolean isReleased() {
        return this.isReleased;
    }

    public int getNoteValue() {
        return this.noteValue;
    }

    public int getTimeStamp(){
        return this.timeStamp;
    }

    public ImmutableKeyNote setReleased() {
        this.isReleased = true;
        this.isPressed = false;
        return new ImmutableKeyNote(this.noteValue, this.timeStamp);
    }

    @Override
    public String toString(){
        return "" + noteValue;
    }
}
