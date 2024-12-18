package Repo;

import Exceptions.Temp;
import Model.Utils.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private String logFilePath;

    public Repository(ProgramState initial, String filename) {
        this.logFilePath = filename;
        programStates = new ArrayList<ProgramState>();
        this.programStates.add(initial);
    }

    @Override
    public List<ProgramState> getAllProgramStates() {
        return this.programStates;
    }

    @Override
    public void addState(ProgramState programState) {
        this.programStates.add(programState);
    }

    @Override
    public void logProgramState(ProgramState programState) throws Temp {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)));
            logFile.println(programState.toString());
            logFile.close();
        }
        catch (IOException e) {
            throw new Temp("File does not exist");
        }
    }

    @Override
    public void setState(List<ProgramState> programStateList) {
        this.programStates = programStateList;
    }
}
