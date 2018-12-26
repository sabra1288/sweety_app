package firstapp.om.sweety.home.cakes.cakegallery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import firstapp.om.sweety.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GirlsBirthdayFragment extends Fragment {
    private RecyclerView recyclerView;


    public GirlsBirthdayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_custom_cakes, container, false);
        recyclerView=view.findViewById(R.id.girls_b);

        return view;
    }

}
