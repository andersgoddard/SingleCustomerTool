package Directory;

import Associaters.Associatable;
import java.util.List;

public interface Directory {
    void add(Associatable associatable);
    int size();
    void clear();
    List<Associatable> get();
    static Directory getInstance() {
        return null;
    }
}
