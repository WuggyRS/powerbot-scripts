package scripts.claysoftener.constants;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

/*
    Stores values for location areas & spots to walk to
 */

public enum Location {
    BANK_AREA(new Area(new Tile(3091, 3497), new Tile(3098, 3093)), new Tile(3096, 3494)),
    FOUNTAIN_AREA(new Area(new Tile(3082, 3505), new Tile(3088, 3500)), new Tile(3086, 3502));

    private Area location;
    private Tile spot;

    Location(Area location, Tile spot) {
        this.location = location;
        this.spot = spot;
    }

    public Area getLocation() {
        return location;
    }

    public Tile getSpot() {
        return spot;
    }
}
