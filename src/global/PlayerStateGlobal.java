package global;

import hero.Roster;

public class PlayerStateGlobal {
    protected static Roster roster;

    public static Roster getRoster() {
        return roster;
    }

    public static void setRoster(Roster newRoster) {
        roster = newRoster;
    }
}
