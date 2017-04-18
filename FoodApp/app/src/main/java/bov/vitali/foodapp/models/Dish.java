package bov.vitali.foodapp.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by vitali on 18.4.17.
 */

@Root(name = "offer", strict = false)
@DatabaseTable(tableName = "dish")
public class Dish {

    @Attribute(name = "id")
    @DatabaseField(columnName = "id", id = true)
    private int id;

    @Element(name = "categoryId")
    @DatabaseField(columnName = "categoryId")
    private int categoryId;

    @Element(name = "name")
    @DatabaseField(columnName = "title")
    private String title;

    @Element(name = "description", required = false)
    @DatabaseField(columnName = "descr")
    private String description;

    @DatabaseField(columnName = "weight")
    private String weight;

    @ElementList(entry = "param", required = false, inline = true)
    public List<DishParam> params;

    @Element(name = "price")
    @DatabaseField(columnName = "price")
    private String price;

    @Element(name = "url")
    @DatabaseField(columnName = "url")
    private String url;

    @Element(name = "picture", required = false)
    @DatabaseField(columnName = "pictureUrl")
    private String pictureUrl;

    private byte[] picture;

    public Dish() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<DishParam> getParams() {
        return params;
    }

    public void setParams(List<DishParam> params) {
        this.params = params;
    }
}
