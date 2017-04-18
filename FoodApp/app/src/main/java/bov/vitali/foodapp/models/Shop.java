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
    private ArrayList<Category> categoryItems;

    @ElementList(name = "offers", entry = "offer")
    private ArrayList<Dish> dishItems;

    public ArrayList<Category> getCategories() {
        return categoryItems;
    }

    public ArrayList<Dish> getDishes() {
        return dishItems;
    }
}
