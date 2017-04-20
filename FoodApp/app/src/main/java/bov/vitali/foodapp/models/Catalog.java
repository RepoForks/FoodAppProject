package bov.vitali.foodapp.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by vitali on 18.4.17.
 */

@Root(name = "yml_catalog")
public class Catalog implements Serializable {

    @Attribute(name = "date")
    private String date;

    @Element(name = "shop")
    private Shop shop;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
