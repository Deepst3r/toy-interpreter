package Domain.Statements.SemaphoreStatements;

import Domain.PrgState;
import Domain.SemaphoreEntry;
import Domain.Statements.IStmt;
import Exceptions.VariableNotFoundException;

public class AcquireStmt implements IStmt {

    private String var;

    public AcquireStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) {

        if (state.getSymTable().checkExistence(var)) {
            int foundIndex = state.getSymTable().getValueForKey(var);
            if (state.getSemaphoreTable().checkExistence(foundIndex)) {
                SemaphoreEntry entry = state.getSemaphoreTable().getValueForKey(foundIndex);
                if (entry.getCounter() > entry.getList().size()) {
                    if (!entry.getList().contains(state.getId())) {
                        entry.getList().add(state.getId());
                    }
                } else {
                    state.getExeStack().push(this);
                }
            } else {
                throw new VariableNotFoundException();
            }
        } else {
            throw new VariableNotFoundException();
        }

        return null;
    }

    @Override
    public String toString() {
        return "acquire(" + var +") ";
    }
}
