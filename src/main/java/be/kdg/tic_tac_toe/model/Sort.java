package be.kdg.tic_tac_toe.model;

import java.util.Random;

public enum Sort {
    X, O;

    static Sort randomSort() {
        Random random = new Random();
        return Sort.values()[random.nextInt(2)];
    }

    static Sort oppositSort(Sort sort) {
        if (sort.equals(Sort.O)) {
            return Sort.X;
        } else {
            return Sort.O;
        }
    }
}
