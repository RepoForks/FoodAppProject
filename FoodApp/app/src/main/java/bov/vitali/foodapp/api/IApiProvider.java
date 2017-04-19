package bov.vitali.foodapp.api;

import bov.vitali.foodapp.models.Catalog;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vitali on 19.4.17.
 */

public interface IApiProvider {

    @GET("getyml/?key=ukAXxeJYZN")
    Call<Catalog> getCatalog();
}
