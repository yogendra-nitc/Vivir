package com.example.yogendra.vivir;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link finder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link finder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class finder extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // TODO Adding button for testing purpose

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public finder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment finder.
     */
    // TODO: Rename and change types and number of parameters
    public static finder newInstance(String param1, String param2) {
        finder fragment = new finder();
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
        // Button-1
        View view = inflater.inflate(R.layout.fragment_finder, container, false);

        //Left side Button-1
        Button changePassword;
        changePassword = view.findViewById(R.id.changePassword);
        changePassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , changePassword.class);
                startActivity(in);
            }
        });

        //Left side Button-2
        Button complainList;
        complainList = view.findViewById(R.id.complainList);
        complainList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , complainList.class);
                startActivity(in);
            }
        });

        //Left side Button-3
        Button getDashboard;
        getDashboard = view.findViewById(R.id.getDashboard);
        getDashboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , dashboard.class);
                startActivity(in);
            }
        });

        //Left side Button-4
        Button deleteFlatRecord;
        deleteFlatRecord = view.findViewById(R.id.deleteFlatRecord);
        deleteFlatRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , deleteFlatRecord.class);
                startActivity(in);
            }
        });

        //Left-side Button-5
        Button getFlatDetails;
        getFlatDetails = view.findViewById(R.id.getFlatDetails);
        getFlatDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , flatDetails.class);
                startActivity(in);
            }
        });

        //Left side Button-6
        Button notification;
        notification = view.findViewById(R.id.notifications);
        notification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , notification.class);
                startActivity(in);
            }
        });

        //Left side Button-7
        Button setProfile;
        setProfile = view.findViewById(R.id.setProfile);
        setProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , setProfile.class);
                startActivity(in);
            }
        });
        // Left-side Button-8
        Button regUserSearch;
        regUserSearch = view.findViewById(R.id.reg_user_search);
        regUserSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , RegUserSearch.class);
                startActivity(in);
            }
        });


        //Right side Button-1
        Button requestDetails;
        requestDetails = view.findViewById(R.id.requestDetails);
        requestDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , requestDetails.class);
                startActivity(in);
            }
        });

        //Right side Button-2
        Button requestList;
        requestList = view.findViewById(R.id.requestList);
        requestList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , requestList.class);
                startActivity(in);
            }
        });

        //Right side Button-3
        Button updateFlatRecord;
        updateFlatRecord = view.findViewById(R.id.updateFlatRecord);
        updateFlatRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , updateFlatRecord.class);
                startActivity(in);
            }
        });

        //Right side Button-4
        Button uploadFlatDetails;
        uploadFlatDetails = view.findViewById(R.id.upload_flat_details);
        uploadFlatDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , uploadFlatDetails.class);
                startActivity(in);
            }
        });

        //Right side Button-5
        Button uploadFlatImages;
        uploadFlatImages = view.findViewById(R.id.upload_flat_images);
        uploadFlatImages.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , uploadFlatImage.class);
                startActivity(in);
            }
        });
        //Right side Button-4
        Button rentDetails;
        rentDetails = view.findViewById(R.id.upload_rent_details);
        rentDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , uploadRentDetails.class);
                startActivity(in);
            }
        });
        //Right side Button-4
        Button writeComplain;
       writeComplain = view.findViewById(R.id.writeComplain);
        writeComplain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , writeComplain.class);
                startActivity(in);
            }
        });
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
