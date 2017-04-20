package bov.vitali.foodapp.models;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vitali on 18.4.17.
 */

@Root(name = "shop")
public class Shop implements Serializable {

    @ElementList(name = "categories", entry = "category")
    private ArrayList<Category> categories;

    @ElementList(name = "offers", entry = "offer")
    private ArrayList<Offer> offers;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }
}
