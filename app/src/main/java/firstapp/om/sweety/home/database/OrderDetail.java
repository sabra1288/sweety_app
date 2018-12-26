package firstapp.om.sweety.home.database;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import firstapp.om.sweety.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderDetail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDetail extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText fName,phoneN,area,location;
    private Button b_continue;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Order order;

    private OnFragmentInteractionListener mListener;

    public OrderDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderDetail newInstance(String param1, String param2) {
        OrderDetail fragment = new OrderDetail();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_order_detail, container, false);

        fName= view.findViewById(R.id.fName);
        phoneN= view.findViewById(R.id.phoneN);
        area= view.findViewById(R.id.area);
        location= view.findViewById(R.id.location);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Order");


        b_continue= view.findViewById(R.id.b_continue);
//        b_continue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                order = new Order();
//
//                order.setName(fName.getText().toString().trim());
//                order.setArea(area.getText().toString().trim());
//                order.setPhone(phoneN.getText().toString().trim());
//                order.setLocation(location.getText().toString().trim());
//
//
//                String key = databaseReference.push().getKey();
//                databaseReference.child(key).setValue(order);
//
//                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
//                OrderInfo orderInfo=OrderInfo.newInstance(null,null);
//                fragmentTransaction.replace(R.id.main_continer,orderInfo);
//                fragmentTransaction.commit();
//
//            }
//        });



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
