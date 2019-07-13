package framework.taskscript.rt4;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public abstract class Task<C extends ClientContext> extends ClientAccessor {
    public Task(C ctx) {
        super(ctx);
    }

    public abstract boolean validate();
    public abstract void execute();
}
