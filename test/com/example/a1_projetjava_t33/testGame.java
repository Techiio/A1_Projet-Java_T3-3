package com.example.a1_projetjava_t33;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class testGame {
    @Test
    public void testPlay() throws IOException {
        Game game = new Game();
        assertEquals(1,game.playGame());
    }

    @Test
        public void testLambda() throws IOException {
            assertThrows(IndexOutOfBoundsException.class, () -> {
                List<Integer> ints = new LinkedList<>();
                ints.get(1);
            });
    }
}
