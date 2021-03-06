package firstapp.om.sweety.home.cakes.cakegallery;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import firstapp.om.sweety.R;
import firstapp.om.sweety.home.HomeFragment;
import firstapp.om.sweety.navigation.NavigationPage;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CakeDetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CakeDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CakeDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<ColdItem> List;
    private TextView x_button,cakeName,price,description;
    private ImageView imageView;
    private FloatingActionButton cart;
    private ElegantNumberButton numberButton;
    private OnFragmentInteractionListener mListener;
    private ColdItem coldItem;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public CakeDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CakeDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static CakeDetail newInstance(ColdItem coldItem) {
        CakeDetail fragment = new CakeDetail();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,coldItem);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           coldItem=(ColdItem)getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cake_detail, container, false);
        x_button= view.findViewById(R.id.x_button);
        cart= view.findViewById(R.id.Cake_cart);
        cakeName= view.findViewById(R.id.cakeName);
        description=view.findViewById(R.id.description);
        imageView=view.findViewById(R.id.cake_image);
        numberButton=view.findViewById(R.id.number_button);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Requests");


        firebaseStorage=FirebaseStorage.getInstance();

        x_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                CakeGallery cakeGallery=CakeGallery.newInstance(null,null);
                fragmentTransaction.replace(R.id.main_continer,cakeGallery);
                fragmentTransaction.commit();
            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coldItem = new ColdItem();

                coldItem.setName(cakeName.getText().toString().trim());
                coldItem.setDescription(description.getText().toString().trim());

                int value=Integer.parseInt(numberButton.getNumber());
                coldItem.setQuantity(value);
                coldItem.setPrice(price.getText().toString().trim());


                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(coldItem);


                Toast.makeText(getActivity(),"Added to Cart",Toast.LENGTH_LONG).show();
            }
        });





        String str1=coldItem.getDescription();
        String str3=coldItem.getName();
        cakeName.setText(str3);
        price=view.findViewById(R.id.price);
        price.setText(coldItem.getPrice());
        description.setText(str1);
        Picasso.with(getActivity()).load(coldItem.getImage()).into(imageView);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
