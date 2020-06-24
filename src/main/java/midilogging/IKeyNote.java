package midilogging;

public interface IKeyNote {
    int noteValue = 0;
    int vel = 0;
    int getTimeStamp();
    int getNoteValue();
    @Override
    String toString();
}
