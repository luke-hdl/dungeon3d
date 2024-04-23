package skills;

public enum Element {
    CUT,
    PUNCTURE,
    SMASH,
    FIRE,
    ICE,
    STORM,
    HEALING,
    WHIFF, //failures to hit are considered Whiff element, for chasers and the like
    FAIL,  //failures that shouldn't trigger chasers - like for no valid target
}
