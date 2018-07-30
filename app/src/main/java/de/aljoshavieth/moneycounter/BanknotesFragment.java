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

import static de.aljoshavieth.moneycounter.MainActivity.banknoteAmount;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BanknotesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BanknotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BanknotesFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText eur100AmountText;
    private EditText eur50AmountText;
    private EditText eur20AmountText;
    private EditText eur10AmountText;
    private EditText eur5AmountText;

    private Button doneButton;

    private MainActivity mainActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BanknotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BanknotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BanknotesFragment newInstance(String param1, String param2) {
        BanknotesFragment fragment = new BanknotesFragment();
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
        View view =  inflater.inflate(R.layout.fragment_banknotes, container, false);

        eur100AmountText = view.findViewById(R.id.eur100Input);
        eur50AmountText = view.findViewById(R.id.eur50Input);
        eur20AmountText = view.findViewById(R.id.eur20Input);
        eur10AmountText = view.findViewById(R.id.eur10Input);
        eur5AmountText = view.findViewById(R.id.eur5Input);

        doneButton = view.findViewById(R.id.banknotesDoneBtn);
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
        if(v.getId() == R.id.banknotesDoneBtn){
            calculateBanknotes();
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

    private void calculateBanknotes(){
        double eur100Amount = 0.00;
        double eur50Amount = 0.00;
        double eur20Amount = 0.00;
        double eur10Amount = 0.00;
        double eur5Amount = 0.00;

        if(!eur100AmountText.getText().toString().isEmpty()){
            eur100Amount = 100.00 *Double.valueOf(eur100AmountText.getText().toString());
        }
        if(!eur50AmountText.getText().toString().isEmpty()){
            eur50Amount = 50.00 *Double.valueOf(eur50AmountText.getText().toString());
        }
        if(!eur20AmountText.getText().toString().isEmpty()){
            eur20Amount = 20.00 *Double.valueOf(eur20AmountText.getText().toString());
        }
        if(!eur10AmountText.getText().toString().isEmpty()){
            eur10Amount = 10.00 *Double.valueOf(eur10AmountText.getText().toString());
        }
        if(!eur5AmountText.getText().toString().isEmpty()){
            eur5Amount = 5.00 *Double.valueOf(eur5AmountText.getText().toString());
        }

        banknoteAmount = eur100Amount + eur50Amount + eur20Amount + eur10Amount + eur5Amount;
    }
}
