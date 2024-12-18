package Model.Commands;

public class ExitCommand extends Command {

    public ExitCommand(String key, String value) {
        super(key, value);
    }
    @Override
    public void execute() {
        System.exit(0);
    }
}
