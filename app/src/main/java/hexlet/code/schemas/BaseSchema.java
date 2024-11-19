package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    protected boolean canBeNull = true;
    protected List<Predicate> predicates = new ArrayList<>();

    public final void addPredicates(Predicate state) {
        predicates.add(state);
    }

    public final boolean isValid(Object obj) {
        if (canBeNull && (obj == null || obj.equals(""))) {
            return true;
        } else if (!canBeNull && (obj == null || obj.equals(""))) {
            return false;
        } else {
            return predicates.stream()
                    .allMatch(predicate -> predicate.test(obj));
        }
    }
}
