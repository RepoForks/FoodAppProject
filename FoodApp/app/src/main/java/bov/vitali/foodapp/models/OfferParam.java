package bov.vitali.foodapp.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

import java.io.Serializable;

/**
 * Created by vitali on 18.4.17.
 */

public class OfferParam implements Serializable {

    @Attribute(name = "name")
    public String name;

    @Text
    public String param;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
