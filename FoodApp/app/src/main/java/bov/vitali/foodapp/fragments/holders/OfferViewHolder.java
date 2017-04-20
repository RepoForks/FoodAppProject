package bov.vitali.foodapp.fragments.holders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.squareup.picasso.Picasso;

import bov.vitali.foodapp.MainActivity;
import bov.vitali.foodapp.R;
import bov.vitali.foodapp.models.Offer;

/**
 * Created by vitali on 19.4.17.
 */

public class OfferViewHolder extends ChildViewHolder implements View.OnClickListener {

    private Context context;
    private Offer offer;

    private ImageView ivOfferImage;
    private TextView tvOfferName;
    private TextView tvOfferWeight;
    private TextView tvOfferPrice;

    public OfferViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        ivOfferImage = (ImageView) itemView.findViewById(R.id.ivOfferImage);
        tvOfferName = (TextView) itemView.findViewById(R.id.tvOfferName);
        tvOfferWeight = (TextView) itemView.findViewById(R.id.tvOfferWeight);
        tvOfferPrice = (TextView) itemView.findViewById(R.id.tvOfferPrice);
        itemView.setOnClickListener(this);
    }

    public void bind(Context context, Offer offer) {
        this.offer = offer;
        Picasso.with(context).load(offer.getPictureUrl()).into(ivOfferImage);
        tvOfferName.setText(offer.getName());
        tvOfferWeight.setText(offer.getWeight());
        tvOfferPrice.setText(offer.getPrice());
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) context).offerClick(offer);
    }
}
