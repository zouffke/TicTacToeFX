package be.kdg.tic_tac_toe.model;

import java.util.Objects;

public class Piece {
    // vars aanmaken
    private final Sort SORT;
    private final int X;
    private final int Y;
    //constructor van de var
    Piece(Sort sort, int y, int x) {
        this.X = x;
        this.Y = y;
        this.SORT = sort;
    }

    public boolean equalsSort(Sort sort) {
        //zorgt ervoor om te checken of dat teken het teken is dat ze zeggen wat het is
        // als het null is is het zowiezo false
        if (sort == null) {
            //het kan nooit null zijn anders was der niks om mee te vergelijken daarom altijd false
            return false;
        } else {
            //dikke sort is wat je bent en dat vergelijk je met de kleine sort wat er gevraagd word of je dat bent
            //ben je die sort geef je true en dan kan het doorgaan
            //ben je het niet dan stopt ie en gaat het spel verder --> false
            return this.getSORT().equals(sort);
        }
    }

    Sort getSORT() {
        //de sort returnen die je bent
        return SORT;
    }
    @Override
    public int hashCode() {
        //returned de uitgerekende hashcode
        return Objects.hash(SORT, X, Y);
    }




    @Override
    public boolean equals(Object o) {
        //het is hetzelfde dus gelijk --> true
        if (this == o) return true;
        //als o null is is het zwz false en of als de klasse van o niet gelijk is aan de klasse van het opgegeven var
        if (o == null || getClass() != o.getClass()) return false;
        //nieuwe var en we weten dat het o is omdat we dat ervoor hebben gecheckt
        Piece piece = (Piece) o;
        // als de hashcode gelijk is dan zijn alle var ook gelijk aan elkaar
        if (this.hashCode() == o.hashCode()) {
            return X == piece.X && Y == piece.Y && SORT == piece.SORT;
        }
        return false;
    }


    @Override
    public String toString() {
        //een string van da gecheckte var teruggeven
        return String.format("%s", this.getSORT());
    }
}
