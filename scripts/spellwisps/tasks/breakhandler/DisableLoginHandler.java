package scripts.spellwisps.tasks.breakhandler;

import framework.taskscript.rt6.Task;

import org.powerbot.script.rt6.ClientContext;

public class DisableLoginHandler extends Task<ClientContext> {
    public DisableLoginHandler(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return !ctx.widgets.component(596, 22).visible() && !ctx.properties.getProperty("login.disable").equals("true");
    }

    @Override
    public void execute() {
        ctx.properties.setProperty("login.disable", "true");
    }
}