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

import static de.aljoshavieth.moneycounter.MainActivity.coinAmount;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CoinsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CoinsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoinsFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private EditText eur2AmountText;
    private EditText eur1AmountText;
    private EditText ct50AmountText;
    private EditText ct20AmountText;
    private EditText ct10AmountText;
    private EditText ct5AmountText;
    private EditText ct2AmountText;
    private EditText ct1AmountText;

    private Button doneButton;
    private MainActivity mainActivity;


    private OnFragmentInteractionListener mListener;

    public CoinsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoinsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoinsFragment newInstance(String param1, String param2) {
        CoinsFragment fragment = new CoinsFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coins, container, false);

        eur2AmountText = view.findViewById(R.id.eur2Input);
        eur1AmountText = view.findViewById(R.id.eur1Input);
        ct50AmountText = view.findViewById(R.id.ct50Input);
        ct20AmountText = view.findViewById(R.id.ct20Input);
        ct10AmountText = view.findViewById(R.id.ct10Input);
        ct5AmountText = view.findViewById(R.id.ct5Input);
        ct2AmountText = view.findViewById(R.id.ct2Input);
        ct1AmountText = view.findViewById(R.id.ct1Input);

        doneButton = view.findViewById(R.id.coinsDoneBtn);
        doneButton.setOnClickListener(this);
        mainActivity = (MainActivity) getActivity();


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
        if(v.getId() == R.id.coinsDoneBtn){
            calculateCoins();
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

    private void calculateCoins(){
        double eur2Amount = 0.00;
        double eur1Amount = 0.00;
        double ct50Amount = 0.00;
        double ct20Amount = 0.00;
        double ct10Amount = 0.00;
        double ct5Amount = 0.00;
        double ct2Amount = 0.00;
        double ct1Amount = 0.00;

        if(!eur2AmountText.getText().toString().isEmpty()){
            eur2Amount = 2.00 *Double.valueOf(eur2AmountText.getText().toString());
        }
        if(!eur1AmountText.getText().toString().isEmpty()){
            eur1Amount = 1.00 *Double.valueOf(eur1AmountText.getText().toString());
        }
        if(!ct50AmountText.getText().toString().isEmpty()){
            ct50Amount = 0.50 *Double.valueOf(ct50AmountText.getText().toString());
        }
        if(!ct20AmountText.getText().toString().isEmpty()){
            ct20Amount = 0.20 *Double.valueOf(ct20AmountText.getText().toString());
        }
        if(!ct10AmountText.getText().toString().isEmpty()){
            ct10Amount = 0.10 *Double.valueOf(ct10AmountText.getText().toString());
        }
        if(!ct5AmountText.getText().toString().isEmpty()){
            ct5Amount = 0.05 *Double.valueOf(ct5AmountText.getText().toString());
        }
        if(!ct2AmountText.getText().toString().isEmpty()){
            ct2Amount = 0.02 *Double.valueOf(ct2AmountText.getText().toString());
        }
        if(!ct1AmountText.getText().toString().isEmpty()){
            ct1Amount = 0.01 *Double.valueOf(ct1AmountText.getText().toString());
        }




        coinAmount = eur2Amount + eur1Amount + ct50Amount + ct20Amount + ct10Amount + ct5Amount + ct2Amount + ct1Amount;
        coinAmount = mainActivity.round(coinAmount, 2);
    }
}
