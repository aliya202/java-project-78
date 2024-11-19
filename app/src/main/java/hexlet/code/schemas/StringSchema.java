package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        Predicate<Object> isString = x -> x instanceof String;
        addPredicates(isString);
    }

    public StringSchema required() {
        canBeNull = false;
        return this;
    }

    public StringSchema minLength(int minLen) {
        Predicate<String> lengthPredicate = x -> x.length() >= minLen;
        addPredicates(lengthPredicate);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> containsSubstring = x -> x.contains(str);
        addPredicates(containsSubstring);
        return this;
    }
}
