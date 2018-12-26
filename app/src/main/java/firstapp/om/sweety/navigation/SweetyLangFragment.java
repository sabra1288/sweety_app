package firstapp.om.sweety.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import firstapp.om.sweety.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SweetyLangFragment extends Fragment {
    private Button arabic,english;


    public SweetyLangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.language, container, false);




        return view;


    }

}
