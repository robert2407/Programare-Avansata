package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Token {
    public final int number;

    public Token(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Token{" +
                "number=" + number +
                '}';
    }
}