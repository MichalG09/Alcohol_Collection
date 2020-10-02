package pl.mgrzech.alcohols_collection.alcohol.model;

import pl.mgrzech.alcohols_collection.entities.Manufacturer;

/**
 * Class of alcohol to search with more advanced parameter to search alcohol.
 */
public class AlcoholToSearch {

    private Integer id;

    /**
     * Name bottle
     */
    private String name = "";

    /**
     * Type of alcohol in bottle
     */
    private String type = "";

    /**
     * Min capacity alcohol bottle [ml]
     */
    private int capacityMax = 1000;

    /**
     * Max capacity alcohol bottle [ml]
     */
    private int capacityMin;
    /**
     * Min amount of alcohol [%]
     */
    private int amountOfAlcoholMin;

    /**
     * Max amount of alcohol [%]
     */
    private int amountOfAlcoholMax = 100;

    /**
     * Additional comment for bottle alcohol
     */
    private String comment = "";

    /**
     * Producer who made the bottle
     */
    private Manufacturer manufacturer;

    /**
     * Place when bottle was bought
     */
    private String placePurchase = "";

    /**
     * The exact place where it is stored
     */
    private String placeInStorage = "";

    public AlcoholToSearch() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacityMax() {
        return capacityMax;
    }

    public void setCapacityMax(int capacityMax) {
        this.capacityMax = capacityMax;
    }

    public int getCapacityMin() {
        return capacityMin;
    }

    public void setCapacityMin(int capacityMin) {
        this.capacityMin = capacityMin;
    }

    public int getAmountOfAlcoholMin() {
        return amountOfAlcoholMin;
    }

    public void setAmountOfAlcoholMin(int amountOfAlcoholMin) {
        this.amountOfAlcoholMin = amountOfAlcoholMin;
    }

    public int getAmountOfAlcoholMax() {
        return amountOfAlcoholMax;
    }

    public void setAmountOfAlcoholMax(int amountOfAlcoholMax) {
        this.amountOfAlcoholMax = amountOfAlcoholMax;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPlacePurchase() {
        return placePurchase;
    }

    public void setPlacePurchase(String placePurchase) {
        this.placePurchase = placePurchase;
    }

    public String getPlaceInStorage() {
        return placeInStorage;
    }

    public void setPlaceInStorage(String placeInStorage) {
        this.placeInStorage = placeInStorage;
    }
}
