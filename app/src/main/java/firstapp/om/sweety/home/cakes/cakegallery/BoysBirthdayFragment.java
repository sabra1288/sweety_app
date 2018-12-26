package firstapp.om.sweety.home.cakes.cakegallery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import firstapp.om.sweety.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoysBirthdayFragment extends Fragment {


    public BoysBirthdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boys_birthday, container, false);
    }

}
