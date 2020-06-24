package midiforml;

import midilogging.MidiNoteLogger;

import javax.sound.midi.*;
import java.util.List;

public class MidiHandler
{
    MidiNoteLogger midiNoteLogger;

    public MidiHandler(MidiNoteLogger midiNoteLogger)
    {
        this.midiNoteLogger = midiNoteLogger;

        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
                device = MidiSystem.getMidiDevice(infos[i]);
                //does the device have any transmitters?
                //if it does, add it to the device list
                //System.out.println(infos[i]);

                //get all transmitters
                List<Transmitter> transmitters = device.getTransmitters();
                //and for each transmitter

                for(int j = 0; j<transmitters.size();j++) {
                    //create a new receiver
                    transmitters.get(j).setReceiver(
                            //using my own MidiInputReceiver
                            new MidiInputReceiver(device.getDeviceInfo().toString())
                    );
                }

                Transmitter trans1 = device.getTransmitter();
                //Use this one to log to text raw messages
                trans1.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));

                //open each device
                device.open();

                //if code gets this far without throwing an exception
                //print a success message
                System.out.println(device.getDeviceInfo() + " Was Opened");


            } catch (MidiUnavailableException e) {}
        }


    }
    //The send method handles an MidiEvents sent to it
    public class MidiInputReceiver implements Receiver {
        public String name;
        Synthesizer synthesizer = null;
        MidiChannel[] channels = null;
        public MidiInputReceiver(String name) throws MidiUnavailableException {
            this.name = name;
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channels = synthesizer.getChannels();
        }

        public void send(MidiMessage msg, long timeStamp) {

            byte[] message = msg.getMessage();
            System.out.println("midi received" + timeStamp + " " + ((int)message[0] == -112));

           if((int)message[0] == -112){
               midiNoteLogger.logNotePress(message[1], message[2]);
           }
            if((int)message[0] == -128){
                midiNoteLogger.logNoteRelease(message[1]);
            }

            if (msg instanceof ShortMessage) {
                ShortMessage shortMessage = (ShortMessage) msg;
                if (shortMessage.getCommand() == ShortMessage.NOTE_ON)
                    channels[shortMessage.getChannel()].noteOn(shortMessage.getData1(), shortMessage.getData2());
                else if (shortMessage.getCommand() == ShortMessage.NOTE_OFF) {
                    channels[shortMessage.getChannel()].noteOff(shortMessage.getData1(), shortMessage.getData2());
                }
            }

//            for (IKeyNote note : midiNoteLogger.getNotes()) {
//                System.out.println((ImmutableKeyNote) note);
//            }
        }
        public void close() {}
    }
}
