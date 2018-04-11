package com.example.yogendra.vivir.user;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.yogendra.vivir.R;
import com.example.yogendra.vivir.database.SharedPrefManager;
import com.example.yogendra.vivir.database.defConstant;
import com.example.yogendra.vivir.network.RequestHandler;
import com.example.yogendra.vivir.owner.OwnerDashboard;
import com.example.yogendra.vivir.tenant.user_dashboard;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PipedOutputStream;
import java.security.acl.Owner;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private EditText editTextEmail, editTextPassword;
    private Button signinButton;
    private ProgressDialog progressDialog;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
       // SharedPrefManager sinstance;
       // sinstance = SharedPrefManager.getInstance(getActivity());
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        //USER LOGIN
      /*  if(sinstance.isLoggedin())
        {
            onDetach();
            startActivity(new Intent(getActivity() , user_dashboard.class));
            onDetach();
        }*/

        editTextEmail = (EditText)view.findViewById(R.id.email);
        editTextPassword = (EditText)view.findViewById(R.id.password);
        signinButton = view.findViewById(R.id.email_sign_in_button);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");

        signinButton.setOnClickListener(this);

        FloatingActionButton floatingActionButton;
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

            floatingActionButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent in = new  Intent(getActivity() , signup.class);
                    startActivity(in);
                }
            });
        return view;
    }

    // User Login Method
    private void userLogin()
    {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                defConstant.URL_LOGIN,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        progressDialog.dismiss();
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){
                                SharedPrefManager.getInstance(getContext())
                                        .userLogin(
                                                obj.getString("email"),
                                                obj.getString("name"),
                                                obj.getString("userType"),
                                                obj.getString("city"),
                                                obj.getString("state"),
                                                obj.getString("contact")
                                        );
                                // REDIRECTION TO DASHBOARD
                                if(obj.getString("userType")=="tenant")
                                {
                                    Intent in = new Intent(getActivity(), user_dashboard.class);
                                    startActivity(in);
                                }
                                else
                                {
                                    Intent in = new Intent(getActivity(), OwnerDashboard.class);
                                    startActivity(in);
                                }

                            }
                            else
                                {
                                Toast.makeText(
                                        getContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(
                                getContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }){
            @Override
            protected Map<String, String>getParams() throws AuthFailureError
            {
                Map <String, String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }

        };
        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
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

    @Override
    public void onClick(View v) {
        if(v == signinButton)
        {
            userLogin();
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
