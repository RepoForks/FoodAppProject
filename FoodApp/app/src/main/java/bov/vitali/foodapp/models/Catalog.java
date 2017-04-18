package bov.vitali.foodapp.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by vitali on 18.4.17.
 */

@Root(name = "yml_catalog")
public class Catalog {

    @Attribute(name = "date")
    private String refreshDate;

    @Element(name = "shop")
    private Shop shop;

    public String getRefreshDate() {
        return refreshDate;
    }

    public void setRefreshDate(String refreshDate) {
        this.refreshDate = refreshDate;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
