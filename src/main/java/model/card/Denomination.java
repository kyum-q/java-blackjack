package model.card;

import java.util.List;

public enum Denomination {
    ACE(List.of(1, 11)),
    TWO(List.of(2)),
    THREE(List.of(3)),
    FOUR(List.of(4)),
    FIVE(List.of(5)),
    SIX(List.of(6)),
    SEVEN(List.of(7)),
    EIGHT(List.of(8)),
    NINE(List.of(9)),
    TEN(List.of(10)),
    KING(List.of(10)),
    QUEEN(List.of(10)),
    JACK(List.of(10)),
    ;

    private final List<Integer> numbers;

    Denomination(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int minimumNumber() {
        return numbers.stream()
                .mapToInt(number -> number)
                .min()
                .orElseThrow(() -> new IllegalStateException("숫자가 존재하지 않습니다."));
    }

    public int maximumNumber() {
        return numbers.stream()
                .mapToInt(number -> number)
                .max()
                .orElseThrow(() -> new IllegalStateException("숫자가 존재하지 않습니다."));
    }
}
