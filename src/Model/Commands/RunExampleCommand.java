package Model.Commands;

import Controller.Controller;
import Exceptions.Temp;

import java.io.IOException;

public class RunExampleCommand extends Command {
   private Controller controller;
    public RunExampleCommand(String key, String value, Controller controller) {
        super(key,value);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            this.controller.allStep();
        }catch (Temp e){System.out.println(e.getMessage());} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
