package com.example.a1_projetjava_t33;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class InterfaceTest {

    @Test
    public void testblacklist()throws Exception {
        List<Integer> blacklist = new ArrayList();
        File dir = new File(this.getClass().getResource("Token/").toExternalForm().replaceAll("file:/",""));
        File[] files = dir.listFiles();

        int nbToken = files.length;;
        int randomIndex = (int) (Math.random() * nbToken);
        blacklist.add(randomIndex);
        boolean b = blacklist.get(0) >= 0 && blacklist.get(0)<=17;

    }
}