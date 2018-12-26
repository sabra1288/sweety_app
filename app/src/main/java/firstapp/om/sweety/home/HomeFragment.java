package firstapp.om.sweety.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import firstapp.om.sweety.R;
import firstapp.om.sweety.home.cakes.CakeAdapter;
import firstapp.om.sweety.home.cakes.CakeFragment;
import firstapp.om.sweety.navigation.NavigationPage;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewPager imageViewPager;
    private TabLayout tabLayout;
    private ViewPager itmeViewPager;
    private HomeFragment.ViewPagerAdapter viewPagerAdapter;
//    private int[] images={R.drawable.cake_gallery,R.drawable.cake1,R.drawable.cake2};




    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view=inflater.inflate(R.layout.scroll, container, false);
        ((NavigationPage)getActivity()).getSupportActionBar().setTitle("Home");

//        imageViewPager=view.findViewById(R.id.vp_image);
//        final ImageViewPagerAdapter imageViewPagerAdapter=new ImageViewPagerAdapter(getActivity());
//        imageViewPager.setAdapter(imageViewPagerAdapter);

        tabLayout=view.findViewById(R.id.tabList);
        itmeViewPager=view.findViewById(R.id.vp_item);
        setUpViewPager(itmeViewPager);




        tabLayout.setupWithViewPager(itmeViewPager);//is used to join the TabLayout with the ViewPager.

//        TimerTask timerTask = new TimerTask() { //viewpager timer
//            @Override
//            public void run() {
//                imageViewPager.post(new Runnable(){
//
//                    @Override
//                    public void run() {
//                        imageViewPager.setCurrentItem((imageViewPager.getCurrentItem()+1)%images.length);
//                    }
//                });
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 3000, 3000);


        return view;
    }

    //Add the fragment
    private void setUpViewPager(ViewPager viewPager){
        viewPagerAdapter=new HomeFragment.ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.AddFragment(new CakeFragment(),"Cake");
        viewPagerAdapter.AddFragment(new HalwaFragment(),"Halwa");
        viewPagerAdapter.AddFragment(new ChocolateFragment(),"Chocolate");

        itmeViewPager.setAdapter(viewPagerAdapter);
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<android.support.v4.app.Fragment> fragmentList=new ArrayList<>();
        private final List<String> titleList=new ArrayList<String>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return titleList.get(position);
        }

        public  void AddFragment(android.support.v4.app.Fragment fragment, String title){
            fragmentList.add(fragment);
            titleList.add(title);

        }
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
