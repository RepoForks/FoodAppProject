package bov.vitali.foodapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bov.vitali.foodapp.R;
import bov.vitali.foodapp.adapters.CatalogAdapter;
import bov.vitali.foodapp.api.IApiProvider;
import bov.vitali.foodapp.api.NetworkUtils;
import bov.vitali.foodapp.database.DbHelper;
import bov.vitali.foodapp.models.Catalog;
import bov.vitali.foodapp.models.Category;
import bov.vitali.foodapp.models.Offer;
import bov.vitali.foodapp.models.OfferParam;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by vitali on 19.4.17.
 */

public class CategoriesFragment extends Fragment {

    private FrameLayout flOfferInfo;

    private RecyclerView rvCategories;
    private DownloadFragment downloadFragment;

    private ArrayList<Category> category = new ArrayList<>();

    private CatalogAdapter catalogAdapter;

    private DbHelper dbHelper;
    private Dao<Category, Integer> categoryDao;
    private Dao<Offer, Integer> offerDao;

    public CategoriesFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        downloadFragment = new DownloadFragment();
        downloadFragment.setCancelable(false);        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View categoriesFragmentView = inflater.inflate(R.layout.fragment_categories, container, false);

        flOfferInfo = (FrameLayout) categoriesFragmentView.findViewById(R.id.aboutOfferFragment);

        rvCategories = (RecyclerView) categoriesFragmentView.findViewById(R.id.rvCategories);
        if (catalogAdapter != null) {
            rvCategories.setAdapter(catalogAdapter);
            rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return categoriesFragmentView;
    }

    public boolean isDetailFrameExist() {
        return (flOfferInfo != null);
    }

    private void loadData() {
        downloadFragment.show(getFragmentManager(), "WaitDialog");
        tryLoadFromDb();
        if (category.size() == 0)
            downloadData();
        else {
            catalogAdapter = new CatalogAdapter(getContext(), category);
            if (rvCategories != null) {
                rvCategories.setAdapter(catalogAdapter);
                rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            downloadFragment.dismiss();
        }
    }

    private boolean tryLoadFromDb() {
        try {
            categoryDao = getDbHelper().getCategoryDao();
            offerDao = getDbHelper().getOfferDao();
            putDishesInCategories((ArrayList<Category>) categoryDao.queryForAll(),
                    (ArrayList<Offer>) offerDao.queryForAll());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveDataInDb(ArrayList<Category> categories, ArrayList<Offer> offers) {
        try {
            categoryDao = getDbHelper().getCategoryDao();
            offerDao = getDbHelper().getOfferDao();

            for (Offer offer : offers) {
                List<OfferParam> params = offer.getParams();
                if (params != null) {
                    for (OfferParam param : params) {
                        if (param.getName().equals("Вес"))
                            offer.setWeight(param.getParam());
                    }
                }
            }
            for (Category category : categories)
                categoryDao.createOrUpdate(category);
            for (Offer offer : offers)
                offerDao.createOrUpdate(offer);
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), getString(R.string.error_save_db), Toast.LENGTH_SHORT).show();
        }
    }

    private DbHelper getDbHelper() {
        if (dbHelper == null)
            dbHelper = OpenHelperManager.getHelper(getContext(), DbHelper.class);
        return dbHelper;
    }

    private void downloadData() {
        if (!NetworkUtils.isNetworkAvailable(getContext())) {
            downloadFragment.dismiss();
            Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_LONG).show();
            return;
        }

        Retrofit catalogRetrofit = new Retrofit.Builder()
                .baseUrl("http://ufa.farfor.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        IApiProvider catalogApi = catalogRetrofit.create(IApiProvider.class);
        Call<Catalog> catalogCall = catalogApi.getCatalog();
        catalogCall.enqueue(catalogCallStatus);
    }

    private void putDishesInCategories(ArrayList<Category> categories, ArrayList<Offer> offers) {
        category.clear();
        category = categories;

        for (Category category : categories) {
            Iterator<Offer> dishIterator = offers.iterator();
            int categoryId = category.getId();
            while (dishIterator.hasNext()) {
                Offer offer = dishIterator.next();
                if (offer.getWeight() == null)
                    offer.setWeight("");
                if (offer.getCategoryId() == categoryId) {
                    category.getOffers().add(offer);
                    dishIterator.remove();
                }
            }
            category.setImage(setCategoryIcon(category));
        }

        if (offers.size() > 0) {
            for (Category category : categories) {
                if (category.getTitle().equals("Другое")) {
                    category.setOffers(offers);
                    return;
                }
            }
            Category otherCategory = new Category();
            otherCategory.setTitle("Другое");
            otherCategory.setImage(R.drawable.food);
            otherCategory.setOffers(offers);
            categories.add(otherCategory);
        }
    }

    private int setCategoryIcon(Category category) {
        int id = category.getId();
        switch (id) {
            case 1:
                return R.drawable.pizza;
            case 18:
                return R.drawable.rolls;
            case 5:
                return R.drawable.sushi;
            case 10:
                return R.drawable.dessert;
            case 9:
                return R.drawable.drink;
            case 6:
                return R.drawable.sup;
            default:
                return R.drawable.food;
        }
    }

    private Callback<Catalog> catalogCallStatus = new Callback<Catalog>() {
        @Override
        public void onResponse(Call<Catalog> call, Response<Catalog> response) {
            Catalog catalog = response.body();
            saveDataInDb(catalog.getShop().getCategories(), catalog.getShop().getOffers());
            putDishesInCategories(catalog.getShop().getCategories(), catalog.getShop().getOffers());

            catalogAdapter = new CatalogAdapter(getContext(), category);
            rvCategories.setAdapter(catalogAdapter);
            rvCategories.setLayoutManager(new LinearLayoutManager(getContext()));
            downloadFragment.dismiss();
        }

        @Override
        public void onFailure(Call<Catalog> call, Throwable t) {
            Toast.makeText(getContext(), getString(R.string.error_downloading), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            OpenHelperManager.releaseHelper();
            dbHelper = null;
        }
    }

}
