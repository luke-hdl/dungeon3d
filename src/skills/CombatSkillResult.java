package skills;

public class CombatSkillResult {
    protected String message;
    protected Element hitElement;

    public CombatSkillResult(String message, Element element) {
        this.message = message;
        this.hitElement = element;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Element getHitElement() {
        return hitElement;
    }

    public void setHitElement(Element hitElement) {
        this.hitElement = hitElement;
    }
}
