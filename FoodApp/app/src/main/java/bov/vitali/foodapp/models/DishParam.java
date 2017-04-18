package bov.vitali.foodapp.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

import java.io.Serializable;

/**
 * Created by vitali on 18.4.17.
 */

public class DishParam implements Serializable {

    @Attribute(name = "name")
    public String name;

    @Text
    public String paramValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
