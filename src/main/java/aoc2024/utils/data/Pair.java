package aoc2024.utils.data;

import lombok.Getter;

@Getter
public class Pair<T> {
    T one;
    T two;
    public Pair(T one, T two) {
        this.one=one;
        this.two=two;
    }
}