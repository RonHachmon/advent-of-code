package twelve;

import java.util.Objects;

public class Memorization {
    private final String map;
    private final Integer index;

    public Memorization(String map, Integer index) {
        this.map = map;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Memorization that = (Memorization) o;
        return map.equals(that.map) && index.equals(that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, index);
    }

    public String getMap() {
        return map;
    }

    public Integer getIndex() {
        return index;
    }
}
