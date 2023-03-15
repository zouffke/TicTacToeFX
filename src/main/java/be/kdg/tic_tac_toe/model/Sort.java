package be.kdg.tic_tac_toe.model;

import java.util.Random;

public enum Sort {
    //de 2 enums die onze pieces worden
    X, O;

    static Sort randomSort() {
        //een random aanmaken
        Random random = new Random();
        //kies een getal van 0-1 en die waarde geef je terug --> X of O
        return Sort.values()[random.nextInt(2)];
    }

    static Sort oppositSort(Sort sort) {
        //als de eerste sort gelijk is aan O dan moet speler 2 X krijgen om af te wisselen
        if (sort.equals(Sort.O)) {
            return Sort.X;
            //is de eerste sort X dan krijgt speler 2 O om mee te spelen
        } else {
            return Sort.O;
        }
    }
}
