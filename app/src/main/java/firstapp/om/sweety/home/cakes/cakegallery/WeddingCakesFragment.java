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
public class WeddingCakesFragment extends Fragment {
    private RecyclerView recyclerView;


    public WeddingCakesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_create_cake, container, false);
        recyclerView=view.findViewById(R.id.weddingCake);


        return view;
    }

}
