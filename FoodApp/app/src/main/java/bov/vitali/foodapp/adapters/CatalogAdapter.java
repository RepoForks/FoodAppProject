package bov.vitali.foodapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import bov.vitali.foodapp.R;
import bov.vitali.foodapp.fragments.holders.CategoryViewHolder;
import bov.vitali.foodapp.fragments.holders.OfferViewHolder;
import bov.vitali.foodapp.models.Category;
import bov.vitali.foodapp.models.Offer;

/**
 * Created by vitali on 19.4.17.
 */

public class CatalogAdapter extends ExpandableRecyclerAdapter<Category, Offer, CategoryViewHolder, OfferViewHolder> {

    private LayoutInflater inflater;
    private Context context;

    public CatalogAdapter(Context context, @NonNull List<Category> categoryList) {
        super(categoryList);
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup, int viewType) {
        View categoryView = inflater.inflate(R.layout.category, parentViewGroup, false);
        return new CategoryViewHolder(categoryView);
    }

    @Override
    public OfferViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View offerView = inflater.inflate(R.layout.offer, childViewGroup, false);
        return new OfferViewHolder(context, offerView);
    }


    @Override
    public void onBindParentViewHolder(CategoryViewHolder parentViewHolder, int parentPosition,
                                       Category category) {
        Category categoryList = category;
        parentViewHolder.bind(categoryList);
    }

    @Override
    public void onBindChildViewHolder(OfferViewHolder childViewHolder, int parentPosition,
                                      int childPosition, Offer child) {
        Offer offer = child;
        childViewHolder.bind(context, offer);
    }


}
