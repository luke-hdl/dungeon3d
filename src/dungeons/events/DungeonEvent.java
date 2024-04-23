package dungeons.events;

public class DungeonEvent {
    protected String flag;

    public DungeonEvent(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}
