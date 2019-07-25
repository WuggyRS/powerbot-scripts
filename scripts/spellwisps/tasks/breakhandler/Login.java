package scripts.spellwisps.tasks.breakhandler;

import framework.taskscript.rt6.Task;

import org.powerbot.script.rt6.ClientContext;

public class Login extends Task<ClientContext> {
    public Login(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean validate() {
        return ctx.widgets.component(596, 22).visible();
    }

    @Override
    public void execute() {
        // Temporarily enable login handler
        if (!ctx.properties.getProperty("login.disable").equals("false"))
            ctx.properties.setProperty("login.disable", "false");
    }
}