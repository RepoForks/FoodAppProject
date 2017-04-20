package bov.vitali.foodapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bov.vitali.foodapp.R;
import bov.vitali.foodapp.models.Offer;

/**
 * Created by vitali on 19.4.17.
 */

public class OfferFragment extends Fragment {

    private Offer offer;

    public OfferFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_offer, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View fragmentView = getView();
        if (fragmentView != null && offer != null) {
            TextView tvOfferViewName = (TextView) fragmentView.findViewById(R.id.tvOfferViewName);
            tvOfferViewName.setText(offer.getName());

            ImageView dishImage = (ImageView) fragmentView.findViewById(R.id.ivOfferViewImage);
            Picasso.with(getContext()).load(offer.getPictureUrl()).into(dishImage);

            TextView dishWeight = (TextView) fragmentView.findViewById(R.id.tvOfferViewWeight);
            if (!offer.getWeight().equals("")) {
                String weightText = getString(R.string.weight) + ": " + offer.getWeight();
                dishWeight.setText(weightText);
            }
            else
                dishWeight.setText("");

            TextView tvOfferViewPrice = (TextView) fragmentView.findViewById(R.id.tvOfferViewPrice);
            String priceText = getString(R.string.price) + ": " + offer.getPrice();
            tvOfferViewPrice.setText(priceText);

            TextView tvOfferViewDescription = (TextView) fragmentView.findViewById(R.id.tvOfferViewDescr);
            if (offer.getDescr() != null) {
                if (offer.getDescr().equals(""))
                    tvOfferViewDescription.setText(getString(R.string.no_descr));
                else {
                    String descriptionText = getString(R.string.descr) + ":\n\n" + offer.getDescr();
                    tvOfferViewDescription.setText(descriptionText);
                }
            } else
                tvOfferViewDescription.setText(getString(R.string.no_descr));
        }
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
