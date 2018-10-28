package sample;

//masking ip will require external command prompt
//it will execute needed commands to hide the IP address

import java.io.IOException;

public class MaskIP {

    static Runtime cmdMaskRT = Runtime.getRuntime();
    // the /K here is the one that starts the command
    static String hidingCommand = "cmd /c start cmd.exe /K \"ipconfig/release\"";

    static String unhideCommand = "cmd /c start cmd.exe /K \"ipconfig/renew\"";

    public static void masks() throws IOException {
        cmdMaskRT.exec(hidingCommand);
    }

    public static void unmasks() throws IOException{
        cmdMaskRT.exec(unhideCommand);
    }
}
