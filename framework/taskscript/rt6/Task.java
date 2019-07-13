package framework.taskscript.rt6;

import org.powerbot.script.rt6.ClientAccessor;
import org.powerbot.script.rt6.ClientContext;

public abstract class Task<C extends ClientContext> extends ClientAccessor {
    public Task(C ctx) {
        super(ctx);
    }

    public abstract boolean validate();
    public abstract void execute();
}
