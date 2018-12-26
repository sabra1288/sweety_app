package firstapp.om.sweety.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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


public class ChocolateFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<ChocolateItem> arrayList;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private DatabaseReference databaseReference;
    private ChocolateItem chocolateItem;
    private ChocolateAdapter chocolateAdapter;



    public ChocolateFragment() {
        // Required empty public constructor
    }


    public static ChocolateFragment newInstance(String param1, String param2) {
        ChocolateFragment fragment = new ChocolateFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_choclate, container, false);
        recyclerView=view.findViewById(R.id.choclate_list);
        arrayList=new ArrayList<>();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("chocolate");



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    chocolateItem = snapshot.getValue(ChocolateItem.class);

                    arrayList.add(chocolateItem);



                }
                RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
                chocolateAdapter=new ChocolateAdapter(getActivity(),arrayList);
                recyclerView.setAdapter(chocolateAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        return view;
    }





}
