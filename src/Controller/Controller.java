package Controller;

import Exceptions.Temp;
import Model.Statements.IStatement;
import Model.Utils.MyIStack;
import Model.Utils.ProgramState;
import Model.Values.IValue;
import Model.Values.RefValue;
import Repo.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepository repo;

    public Controller(IRepository r) {
        this.repo = r;
    }

    Map<Integer, IValue> safeGarbageCollector(List<Integer> symTabbleAddr, Map<Integer,IValue> heap) {
        return heap.entrySet().stream().filter(e->symTabbleAddr.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues,Collection<IValue> heapAddresses) {
        var addresses = symTableValues.stream().filter(e -> e instanceof RefValue).map(v->{RefValue v1 = (RefValue)v;return v1.getAddress();});
        var addresses2 = heapAddresses.stream().filter(e -> e instanceof RefValue).map(v->{RefValue v1 = (RefValue)v;return v1.getAddress();});
        return Stream.concat(addresses, addresses2).collect(Collectors.toList());
    }

    public void allStep() throws Temp, IOException {
        ProgramState programState = repo.getCurrentProgramState();
        while (!programState.getStack().isEmpty()) {
            oneStep(programState);
            programState.getHeap().setContent(safeGarbageCollector(
                    getAddrFromSymTable(
                            programState.getSymTable().getAll().values(),programState.getHeap().getContent().values()),
                    programState.getHeap().getContent()));
            repo.logProgramState();
        }
    }

}
