package com.up.lix.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.up.lix.R;

public class ScreenSlidePageFragment extends Fragment {

    int position = 0;
    TextView textView;
    ImageView icon;

    public ScreenSlidePageFragment() {
    }

    public ScreenSlidePageFragment(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.welcome_view, container, false);

        textView = rootView.findViewById(R.id.txt);
        icon = rootView.findViewById(R.id.icon);


        switch (position) {
            case 0:
                textView.setText(getString(R.string.easy_chat));
                icon.setImageResource(R.drawable.logo);
                break;
            case 1:
                textView.setText(getString(R.string.high_perform));
                icon.setImageResource(R.drawable.ic_high_performance);
                break;
            case 2:
                textView.setText(getString(R.string.feel_secure));
                icon.setImageResource(R.drawable.ic_feel_secure);
                break;
            case 3:
                textView.setText(getString(R.string.modern_design));
                icon.setImageResource(R.drawable.ic_modern_design);
                break;
            case 4:
                textView.setText(getString(R.string.light_weii));
                icon.setImageResource(R.drawable.ic_lightweight);
                break;
        }


        return rootView;
    }
}