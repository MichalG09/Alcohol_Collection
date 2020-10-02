package pl.mgrzech.alcohols_collection.picture;

import org.springframework.stereotype.Component;
import pl.mgrzech.alcohols_collection.entities.Alcohol;
import pl.mgrzech.alcohols_collection.entities.Picture;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class SortPicture {

    /**
     * Method sorts list of pictures for alcohol.
     * Sort by is main picture (main picture always first).
     * @param alcohol alcohol with pictures to sort
     * @return alcohol with sorted picture by main picture
     */
    public Alcohol sortPictureInAlcoholByIsMainPicture(Alcohol alcohol) {
        alcohol.setPictures(alcohol.getPictures()
                .stream()
                .sorted(Comparator.comparing(Picture::isMainPicture, Comparator.reverseOrder()))
                .collect(Collectors.toList()));
        return alcohol;
    }
}
