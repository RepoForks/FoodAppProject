package bov.vitali.foodapp.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vitali on 18.4.17.
 */

@Root(name = "offer", strict = false)
@DatabaseTable(tableName = "offer")
public class Offer implements Serializable {

    public Offer() {
    }

    @Attribute(name = "id")
    @DatabaseField(columnName = "id", id = true)
    private int id;

    @Element(name = "url")
    @DatabaseField(columnName = "url")
    private String url;

    @Element(name = "name")
    @DatabaseField(columnName = "name")
    private String name;

    @Element(name = "price")
    @DatabaseField(columnName = "price")
    private String price;

    @Element(name = "description", required = false)
    @DatabaseField(columnName = "description")
    private String description;

    @Element(name = "picture", required = false)
    @DatabaseField(columnName = "pictureUrl")
    private String pictureUrl;

    private byte[] picture;

    @Element(name = "categoryId")
    @DatabaseField(columnName = "categoryId")
    private int categoryId;

    @ElementList(entry = "param", required = false, inline = true)
    public List<OfferParam> params;

    @DatabaseField(columnName = "weight")
    private String weight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescr() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<OfferParam> getParams() {
        return params;
    }

    public void setParams(List<OfferParam> params) {
        this.params = params;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
