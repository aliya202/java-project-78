package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    private int minLength = 0;

    public StringSchema() {
        Predicate isString = x -> x instanceof String;
        addPredicates(isString);
    }

    public StringSchema required() {
        canBeNull = false;
        return this;
    }

    public StringSchema minLength(int minLen) {
        this.minLength = minLen;
        Predicate<String> lengthPredicate = x -> x.length() >= this.minLength;
        predicates.removeIf(predicate -> predicate.toString().contains("length"));
        addPredicates(lengthPredicate);
        return this;
    }

    public StringSchema contains(String str) {
        Predicate<String> containsSubstring = x -> x.contains(str);
        addPredicates(containsSubstring);
        return this;
    }
}
