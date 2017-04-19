package bov.vitali.foodapp.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by vitali on 18.4.17.
 */

@Root(name = "shop")
public class Shop {

    @ElementList(name = "categories", entry = "category")
    private ArrayList<Category> category;

    @ElementList(name = "offers", entry = "offer")
    private ArrayList<Offer> offer;

    public ArrayList<Category> getCategories() {
        return category;
    }

    public ArrayList<Offer> getOffer() {
        return offer;
    }
}
