package bov.vitali.foodapp.models;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitali on 18.4.17.
 */

@Root(name = "category", strict = false)
@DatabaseTable(tableName = "category")
public class Category implements Serializable, Parent {

    public Category() {
    }

    @Attribute(name = "id")
    @DatabaseField(columnName = "id", id = true)
    private int id;

    @Text
    @DatabaseField(columnName = "title")
    private String title;

    private int image;

    private ArrayList<Offer> offers = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public List<?> getChildList() {
        return offers;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
