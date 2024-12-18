package Repo;

import Exceptions.Temp;
import Model.Utils.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    List<ProgramState> getAllProgramStates();
    void addState(ProgramState programState);
    void logProgramState(ProgramState programState) throws Temp;
    void setState(List<ProgramState> programStateList);
}
