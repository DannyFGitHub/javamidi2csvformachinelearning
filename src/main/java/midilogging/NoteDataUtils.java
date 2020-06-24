package midilogging;

public class NoteDataUtils {

    final public static String[] classArray = new String[]{"Harmony","Dissonant","Neutral"};

    static public String triadLog(MidiNoteLogger midiNoteLogger){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("N1-1,N1-2,N1-3,N2-1,N2-2,N2-3,Class\n");
        int i = 0;
        for (IKeyNote keyNote: midiNoteLogger.getNotes()) {
            if(i == 6){
                i = 0;
                stringBuilder.append(getClassFromUser());
//                stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
                stringBuilder.append("\n");
            }
            stringBuilder.append(keyNote.getNoteValue());
            stringBuilder.append(',');
            i++;
        }
        return stringBuilder.toString();
    }

    static public String getClassFromUser(){
        String classFromUser = "placeholderclass";
        return classFromUser;
    }
}
