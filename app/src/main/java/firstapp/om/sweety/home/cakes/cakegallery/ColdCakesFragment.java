package firstapp.om.sweety.home.cakes.cakegallery;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import firstapp.om.sweety.R;
import firstapp.om.sweety.home.cakes.CakeAdapter;
import firstapp.om.sweety.home.cakes.CakeFragment;
import firstapp.om.sweety.home.cakes.CakeItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColdCakesFragment extends Fragment implements ColdCakeAdapter.OnClickItme {
    private ArrayList<ColdItem> arrayList;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private DatabaseReference databaseReference;
    private ColdItem coldItem;
    private ColdCakeAdapter coldAdapter;
    private RecyclerView recyclerView;



    public ColdCakesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cold_cakes, container, false);
        recyclerView=view.findViewById(R.id.cold_cake);
        recyclerView.hasFixedSize();
        arrayList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("coldCakes");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    coldItem = snapshot.getValue(ColdItem.class);

                    arrayList.add(coldItem);


                }
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                coldAdapter= new ColdCakeAdapter(getActivity(), arrayList);
                coldAdapter.registerOnclick(ColdCakesFragment.this);

                recyclerView.setAdapter(coldAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        


        return view;
    }

    @Override
    public void onClick(int position) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        coldItem=arrayList.get(position);
//        Toast.makeText(getActivity(),"Psition:"+position,Toast.LENGTH_LONG).show();
        switch (position) {
            case 0:
                fragment =CakeDetail.newInstance(coldItem);
                break;
            case 1:
                fragment =CakeDetail.newInstance(coldItem);
                break;
            case 2:
                fragment =CakeDetail.newInstance(coldItem);

        }
        if (fragment != null) {
            fragmentTransaction.replace(R.id.main_continer, fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commit();

        }
    }
}
