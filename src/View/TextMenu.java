package View;

import Model.Commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> cmdmap;
    public TextMenu() {
        cmdmap = new HashMap<String, Command>();
    }

    public void addCommand(Command command) {
        this.cmdmap.put(command.getKey(), command);
    }

    private void printMenu(){
        for (Command command : cmdmap.values()) {
            String line = String.format("%s : %s",command.getKey(),command.getValue());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner sc = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.print("Enter command: ");
            String line = sc.nextLine();
            Command command = this.cmdmap.get(line);
            if(command == null){
                System.out.println("Command not found");
                continue;
            }
            command.execute();
        }
    }
}
