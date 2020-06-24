package midiforml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import midilogging.MidiNoteLogger;
import midilogging.NoteDataUtils;

import java.util.HashMap;

public class Controller {

    MidiNoteLogger midiNoteLogger = new MidiNoteLogger();

    @FXML
    BorderPane borderPane1;

    @FXML
    Button button;

    @FXML
    TextArea textArea1;

    //Used for creating:
    public Controller(){
        System.out.println("Controller Constructor Called");
        //button = new Button("TEST");
    }

    //Used for Initializing, you let the FXML do the constructing, called after constructor:
    public void initialize(){
        MidiHandler midiHandler = new MidiHandler(midiNoteLogger);

        System.out.println("Initializer Called");
        button.setText("Refresh");

        HashMap<String, Button> classButtonMap = new HashMap<>();
        VBox buttonBox = new VBox();
        buttonBox.setSpacing(10);
        for (String s : NoteDataUtils.classArray) {
            Button button = new Button(s);
            button.getStyleClass().addAll("btn","btn-secondary");
            classButtonMap.put(s, button);
            buttonBox.getChildren().add(classButtonMap.get(s));
        }
        borderPane1.setLeft(buttonBox);
        //NORMAL with anonymous class
//        button.setOnAction(new EventHandler(){
//            @Override
//            public void handle(Event event) {
//                System.out.println("Hi");
//            }
//        });

        //LAMBDA
        //https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
//        button.setOnAction((event) -> {
//            System.out.println("HI");
//        });

        //Method Reference Operator:
        //https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
        button.setOnAction(this::handleButtonAction);
    }

    private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println("Button Pressed");
        textArea1.setText(NoteDataUtils.triadLog(midiNoteLogger));
    }

}
