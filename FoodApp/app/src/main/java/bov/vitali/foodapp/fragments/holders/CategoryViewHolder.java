package bov.vitali.foodapp.fragments.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import bov.vitali.foodapp.R;
import bov.vitali.foodapp.models.Category;

/**
 * Created by vitali on 19.4.17.
 */

public class CategoryViewHolder extends ParentViewHolder {

    private ImageView ivCategoryImage;
    private TextView tvCategoryName;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        ivCategoryImage = (ImageView) itemView.findViewById(R.id.ivCategImg);
        tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategName);
    }

    public void bind(Category category) {
        ivCategoryImage.setImageResource(category.getImage());
        tvCategoryName.setText(category.getTitle());
    }
}
