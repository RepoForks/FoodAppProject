package bov.vitali.foodapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import bov.vitali.foodapp.R;

/**
 * Created by vitali on 19.4.17.
 */

public class AboutFragment extends Fragment implements OnMapReadyCallback {

    private ScrollView scrollView;

    public AboutFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.mapFragment, mapFragment).commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_about, container, false);

        final TextView tvPhoneNumber = (TextView) fragmentView.findViewById(R.id.tvPhoneNumber);
        tvPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + tvPhoneNumber.getText().toString();
                Intent phoneIntent = new Intent();
                phoneIntent.setAction(Intent.ACTION_VIEW);
                phoneIntent.setData(Uri.parse(uri));
                startActivity(phoneIntent);
            }
        });

        scrollView = (ScrollView) fragmentView.findViewById(R.id.svAbout);

        ImageView transparentImg = (ImageView) fragmentView.findViewById(R.id.ivTransparent);
        transparentImg.setOnTouchListener(mapTouchListener);

        return fragmentView;
    }

    private View.OnTouchListener mapTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    scrollView.requestDisallowInterceptTouchEvent(true);
                    return false;

                default:
                    return true;
            }
        }
    };

    @Override
    public void onMapReady(GoogleMap map) {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                scrollView.requestDisallowInterceptTouchEvent(true);
            }
        });

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(53.909909, 27.554678))
                .zoom(13)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.moveCamera(cameraUpdate);

        map.addMarker(new MarkerOptions()
                .position(new LatLng(53.909909, 27.554678))
                .title("Остров слёз, Минск"));
    }
}
