package main.finders;

import java.util.HashMap;
import java.util.Map;

public enum EqualState {
    notEqual(1),
    almostEqual(2),
    equal(3);

    private int value;
    private static Map<Integer, EqualState> map = new HashMap<>();

    private EqualState(int value){
        this.value = value;
    }

    static {
        for (EqualState equalState: EqualState.values())
            map.put(equalState.value, equalState);
    }

    public static EqualState valueOf(int equalState){
        return (EqualState) map.get(equalState);
    }

    public int getValue() {
        return value;
    }

    public EqualState max(EqualState state) {
        return value < state.value
                ? state
                : this;
    }
}
