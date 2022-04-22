package com.example.a1_projetjava_t33;

import java.util.Arrays;

public class Robot {
    private Position[] positions;
    private int activerobot;
    private Iniposi[] iniposis;

    @Override
    public String toString() {
        return "Robot{" +
                "positions=" + Arrays.toString(positions) +
                ", activerobot=" + activerobot +
                ", iniposis=" + Arrays.toString(iniposis) +
                '}';
    }
    public Position[] getPositions() {
        return positions;
    }

    public void setPotions(Position[] positions) {
        this.positions = positions;
    }

    public int getActiverobot(){
        return activerobot;
    }
}

class color extends Robot{

}