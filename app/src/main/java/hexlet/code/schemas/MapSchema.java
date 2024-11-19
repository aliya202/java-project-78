package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> isMap = x -> x instanceof Map;
        addPredicates(isMap);
    }

    public MapSchema required() {
        canBeNull = false;
        return this;
    }

    public void sizeof(int num) {
        Predicate<Map<?, ?>> exactSize = x -> x.size() == num;
        addPredicates(exactSize);
    }

    public void shape(Map<String, BaseSchema> conditionsMap) {
        Predicate<Map<?, ?>> shapePredicate = inputMap -> {
            if (inputMap == null) {
                return false;
            }
            return conditionsMap.entrySet().stream()
                    .allMatch(condition -> {
                        String key = condition.getKey();
                        BaseSchema schema = condition.getValue();

                        Object value = inputMap.get(key);
                        return schema.isValid(value);
                    });
        };

        addPredicates(shapePredicate);
    }
}
