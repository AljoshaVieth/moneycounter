package de.aljoshavieth.moneycounter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static de.aljoshavieth.moneycounter.MainActivity.otherAmount;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OtherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OtherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText other1AmountText;
    private EditText other2AmountText;
    private EditText other3AmountText;
    private EditText other4AmountText;
    private EditText other5AmountText;
    private EditText other6AmountText;
    private EditText other7AmountText;
    private EditText other8AmountText;

    private Button doneButton;

    private OnFragmentInteractionListener mListener;
    private MainActivity mainActivity;

    public OtherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OtherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OtherFragment newInstance(String param1, String param2) {
        OtherFragment fragment = new OtherFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);


        other1AmountText = view.findViewById(R.id.other1Input);
        other2AmountText = view.findViewById(R.id.other2Input);
        other3AmountText = view.findViewById(R.id.other3Input);
        other4AmountText = view.findViewById(R.id.other4Input);
        other5AmountText = view.findViewById(R.id.other5Input);
        other6AmountText = view.findViewById(R.id.other6Input);
        other7AmountText = view.findViewById(R.id.other7Input);
        other8AmountText = view.findViewById(R.id.other8Input);

        mainActivity = (MainActivity) getActivity();
        doneButton = view.findViewById(R.id.otherDoneBtn);
        doneButton.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.otherDoneBtn){
            calculateOtherAmount();
            mainActivity.switchToMainFragment();
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

    private void calculateOtherAmount(){

        double other1Amount = 0.00;
        double other2Amount = 0.00;
        double other3Amount = 0.00;
        double other4Amount = 0.00;
        double other5Amount = 0.00;
        double other6Amount = 0.00;
        double other7Amount = 0.00;
        double other8Amount = 0.00;

        if(!other1AmountText.getText().toString().isEmpty()){
            other1Amount = 1.00 *Double.valueOf(other1AmountText.getText().toString());
        }
        if(!other2AmountText.getText().toString().isEmpty()){
            other2Amount = 1.00 *Double.valueOf(other2AmountText.getText().toString());
        }
        if(!other3AmountText.getText().toString().isEmpty()){
            other3Amount = 1.00 *Double.valueOf(other3AmountText.getText().toString());
        }
        if(!other4AmountText.getText().toString().isEmpty()){
            other4Amount = 1.00 *Double.valueOf(other4AmountText.getText().toString());
        }
        if(!other5AmountText.getText().toString().isEmpty()){
            other5Amount = 1.00 *Double.valueOf(other5AmountText.getText().toString());
        }
        if(!other6AmountText.getText().toString().isEmpty()){
            other6Amount = 1.00 *Double.valueOf(other6AmountText.getText().toString());
        }
        if(!other7AmountText.getText().toString().isEmpty()){
            other7Amount = 1.00 *Double.valueOf(other7AmountText.getText().toString());
        }
        if(!other8AmountText.getText().toString().isEmpty()){
            other8Amount = 1.00 *Double.valueOf(other8AmountText.getText().toString());
        }


        otherAmount = other1Amount + other2Amount + other3Amount + other4Amount
                + other5Amount + other6Amount + other7Amount + other8Amount;
        otherAmount = mainActivity.round(otherAmount, 2);
    }

}
