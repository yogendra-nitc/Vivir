package com.example.yogendra.vivir;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link search.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search.
     */
    // TODO: Rename and change types and number of parameters
    public static search newInstance(String param1, String param2) {
        search fragment = new search();
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
        List<SearchItem>flatList = new ArrayList<>();
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home1));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home2));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home3));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home4));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home5));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home6));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home7));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home8));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home9));
        flatList.add(new SearchItem("Kunj Nivas Flat-2, Indira Nagar, Lucknow", R.drawable.home10));
        // Inflate the layout for this fragment

        //View view = inflater.inflate(R.layout.fragment_search, container, false);
        //searchResult=(RecyclerView)
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView searchResult;
        searchResult = recyclerView.findViewById(R.id.searchResult);
        searchResult.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        searchResult.setLayoutManager(linearLayoutManager);
        /* Button btnmove;
        btnmove = (Button)view.findViewById(R.id.flatDetails);
        btnmove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new  Intent(getActivity() , PropertyDetails.class);
                startActivity(in);
            }
        });*/
        return searchResult;
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
