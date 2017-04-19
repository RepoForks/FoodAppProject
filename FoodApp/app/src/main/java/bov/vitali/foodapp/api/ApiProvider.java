package bov.vitali.foodapp.api;

import android.widget.Toast;

import bov.vitali.foodapp.R;
import bov.vitali.foodapp.models.Catalog;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static java.security.AccessController.getContext;

/**
 * Created by vitali on 19.4.17.
 */

public class ApiProvider {

    public Retrofit initRetrofit() {
        Retrofit catalogRetrofit = new Retrofit.Builder()
                .baseUrl("http://ufa.farfor.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        IApiProvider catalogApi = catalogRetrofit.create(IApiProvider.class);
        Call<Catalog> catalogCall = catalogApi.getCatalog();
    }
}
