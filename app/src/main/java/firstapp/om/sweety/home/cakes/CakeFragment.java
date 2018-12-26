package firstapp.om.sweety.home.cakes;

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
import firstapp.om.sweety.home.cakes.cakegallery.CakeGallery;


public class CakeFragment extends Fragment implements CakeAdapter.ItemOnClick {


    private RecyclerView recyclerView;
    private ArrayList<CakeItem> arrayList;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private DatabaseReference databaseReference;
    private CakeItem cakeItem;
    private CakeAdapter cakeAdapter;


    public CakeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cake, container, false);
        recyclerView = view.findViewById(R.id.cake_list);
        recyclerView.hasFixedSize();
        arrayList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("cakes");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cakeItem = snapshot.getValue(CakeItem.class);

                    arrayList.add(cakeItem);


                }
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(manager);
                cakeAdapter = new CakeAdapter(getActivity(), arrayList);
                cakeAdapter.registerOnclick(CakeFragment.this);

                recyclerView.setAdapter(cakeAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//loadMenu();
        return view;
    }

    @Override
    public void onItemClick(int position) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
//        Toast.makeText(getActivity(),"Psition:"+position,Toast.LENGTH_LONG).show();
        switch (position) {
            case 0:
                fragment = new CakeGallery();

        }
        if (fragment != null) {
            fragmentTransaction.replace(R.id.main_continer, fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commit();

        }


    }
}

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


