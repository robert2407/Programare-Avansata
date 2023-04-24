package org.example;
import java.util.*;
import org.example.Token;

public class SharedMemory {
    public final Collection<Token> tokens;

    public SharedMemory(int n) {
        tokens = new ArrayList<>(n);
        for (int i = 1; i <= n * n * n; i++) {
            tokens.add(new Token(i));
        }
        Collections.shuffle((List<Token>) tokens);
    }

    public synchronized List<Token> extractTokens(int howMany) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            extracted.add(((ArrayDeque<Token>) tokens).pollFirst());
        }
        return extracted;
    }
}
