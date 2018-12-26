package firstapp.om.sweety.home.database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import firstapp.om.sweety.MapsActivity;
import firstapp.om.sweety.R;
import firstapp.om.sweety.home.User;
import firstapp.om.sweety.home.cakes.cakegallery.CakeGallery;
import firstapp.om.sweety.home.cakes.cakegallery.ColdItem;
import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference requests;
    private TextView textTotal;
    private FButton placeOrder;
    private ArrayList<ColdItem> cart;
    private CartAdapter cartAdapter;
    private ColdItem coldItem;



    private OnFragmentInteractionListener mListener;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cart, container, false);

        database=FirebaseDatabase.getInstance();
        requests=database.getReference("Requests");

        recyclerView=view.findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);

        textTotal=view.findViewById(R.id.total);
        placeOrder=view.findViewById(R.id.replace_order);

        final ArrayList<ColdItem> cart=new ArrayList<>();



        requests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    coldItem = snapshot.getValue(ColdItem.class);


                    cart.add(coldItem);

                }
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(layoutManager);
                cartAdapter =new CartAdapter(getActivity(),cart);
                recyclerView.setAdapter(cartAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                }


        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
//                OrderDetail orderDetail=OrderDetail.newInstance(null,null);
//                fragmentTransaction.replace(R.id.main_continer,orderDetail);
//                fragmentTransaction.commit();


                showAlertDialog();

            }
        });


        loadFood();



        return view;
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("one more step");
        alertDialog.setMessage("Enter your location and PhoneNumber");


        final EditText name = new EditText(getActivity());
        name.setHint("Name");
        final EditText area = new EditText(getActivity());
        area.setHint("Area");
        final EditText phone = new EditText(getActivity());
        phone.setHint("Phone Number");
        phone.setText("00968");
        final Button address = new Button(getActivity());
        address.setHint("Address");
        address.setTextColor(0000);
        Drawable d = getResources().getDrawable(R.drawable.button);
        address.setBackgroundDrawable(d);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MapsActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout lp= new LinearLayout(getActivity());
        lp.setOrientation(LinearLayout.VERTICAL);
       lp.addView(phone);
        lp.addView(name);
        lp.addView(area);
       lp.addView(address);
       alertDialog.setView(lp);//add editText to alert dialog
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);




//        LinearLayout.LayoutParams lPhone= new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        );
//        editText.setLayoutParams(lPhone);
//         //add editText to alert dialog
////        alertDialog.setIcon(R.drawable.ic_call_black_24dp);


        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
//                  Order order=new Order(name.getText().toString(),phone.getText().toString(),area.getText().toString(),
//                          address.getText().toString(),textTotal.getText().toString(),
//                          cart);
//
//
//
//
//                requests.child(String.valueOf(System.currentTimeMillis())).setValue(order);

//                new DataBase(getActivity()).cleanCart();
//                Toast.makeText(getActivity(),"Thank you,Order place",Toast.LENGTH_LONG).show();
//                return;

            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();



    }

    private void loadFood() {

cart= new ArrayList<>();

        int total=0;

        for (ColdItem coldItem:cart)

            total+=(Integer.parseInt(coldItem.getPrice()))*(coldItem.getQuantity());

        Locale locale =new Locale("en","US");
        NumberFormat format= NumberFormat.getCurrencyInstance(locale);

        textTotal.setText(format.format(total));

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
