package finders;

import java.io.Serializable;

public interface FinderSimilarLines extends  Serializable {
    public EqualState getEqualRatio(String firstLine, String secondLine);
}
