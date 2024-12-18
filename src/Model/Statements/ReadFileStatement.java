package Model.Statements;

import Exceptions.Temp;
import Model.Expressions.IExpressions;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Utils.ProgramState;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement{

    private IExpressions expression;
    private String variable;

    public ReadFileStatement(IExpressions expression, String variable){
        this.expression = expression;
        this.variable = variable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp,IOException {
        var symTable = state.getSymTable();
//        for (var elem : symTable.getAll().values()){
//            System.out.println(elem + "\n");
//        }
        if (!symTable.contains(variable)){
            throw new Temp(variable + " is not defined");
        }
        if (!symTable.getValue(variable).getType().equals(new IntType())){
            throw new Temp(variable + "Type is incorrect");
        }

        var result = expression.evaluate(symTable, state.getHeap());

        if(!result.getType().equals(new StringType())){
            throw new Temp(variable + "Type is not string");
        }

        //var elem = state.getSymTable().getAll().values().iterator().next();
        //HERE IS THE ISSUE, READER IS NULL
        BufferedReader reader = state.getFileTable().getValue((StringValue) result);
        if (reader == null) {
            throw new Temp("File is not opened: " + ((StringValue)result).getValue());
        }
        String readLineContent = reader.readLine();
        if (readLineContent.isEmpty()){
            readLineContent = "0";
        }

        int parser;
        parser = Integer.parseInt(readLineContent);
        symTable.insert(variable,new IntValue(parser));
        return state;
    }

    @Override
    public String toString(){
        return "readFile(" + expression.toString()+ "," + variable + ")";
    }
}
