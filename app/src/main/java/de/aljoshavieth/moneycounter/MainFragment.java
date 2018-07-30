package de.aljoshavieth.moneycounter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static de.aljoshavieth.moneycounter.MainActivity.amountShouldBe;
import static de.aljoshavieth.moneycounter.MainActivity.banknoteAmount;
import static de.aljoshavieth.moneycounter.MainActivity.cashAmount;
import static de.aljoshavieth.moneycounter.MainActivity.coinAmount;
import static de.aljoshavieth.moneycounter.MainActivity.differenceAmount;
import static de.aljoshavieth.moneycounter.MainActivity.otherAmount;
import static de.aljoshavieth.moneycounter.MainActivity.rollAmount;
import static de.aljoshavieth.moneycounter.MainActivity.totalAmount;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivity mainActivity;

    private TextView totalAmountText;
    private TextView coinAmountText;
    private TextView banknoteAmountText;
    private TextView otherAmountText;
    private TextView rollAmountText;

    private TextView differenceText;

    private EditText cashAmountEditText;
    private EditText amountShouldBeEditText;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mainActivity = (MainActivity) getActivity();

        Button coinBtn = view.findViewById(R.id.coinsBtn);
        coinBtn.setOnClickListener(this);

        Button rollsBtn = view.findViewById(R.id.rollsOfCoinsBtn);
        rollsBtn.setOnClickListener(this);

        Button banknotesBtn = view.findViewById(R.id.banknotesBtn);
        banknotesBtn.setOnClickListener(this);

        Button otherBtn = view.findViewById(R.id.otherBtn);
        otherBtn.setOnClickListener(this);

        Button resetBtn = view.findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(this);

        totalAmountText = view.findViewById(R.id.actualAmount);

        coinAmountText = view.findViewById(R.id.coinsAmount);
        banknoteAmountText = view.findViewById(R.id.banknotesAmount);
        otherAmountText = view.findViewById(R.id.otherAmount);
        rollAmountText = view.findViewById(R.id.rollsOfCoinsAmount);

        differenceText = view.findViewById(R.id.differenceAmount);


        cashAmountEditText = view.findViewById(R.id.cashAmountInput);
        amountShouldBeEditText = view.findViewById(R.id.amountShouldBeInput);
        cashAmountEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        cashAmountEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    cashAmount = 0.00;
                    if(!cashAmountEditText.getText().toString().isEmpty()){
                        cashAmount = Double.valueOf(cashAmountEditText.getText().toString());
                    }
                    calculateDifference();
                    setAmounts();
                    setTotalAmount();
                    hideKeyboardFrom(getContext(), cashAmountEditText);
                    return true;
                }
                return false;
            }
        });

        amountShouldBeEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    amountShouldBe = 0.00;
                    if(!amountShouldBeEditText.getText().toString().isEmpty()){
                        amountShouldBe = Double.valueOf(amountShouldBeEditText.getText().toString());
                    }
                    calculateDifference();
                    setAmounts();
                    setTotalAmount();
                    hideKeyboardFrom(getContext(), cashAmountEditText);
                    return true;
                }
                return false;
            }
        });

        setAmounts();
        setTotalAmount();
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
        if(v.getId() == R.id.resetBtn){
            mainActivity.resetAll();
            setAmounts();
        } else {
            mainActivity.handleButtonClicks(v);
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

    public void setTotalAmount(){
        totalAmount  = coinAmount + rollAmount + banknoteAmount + otherAmount + cashAmount;
        totalAmountText.setText(String.valueOf(totalAmount) + " €");
        calculateDifference();
    }

    private void setAmounts(){
        coinAmountText.setText(String.valueOf(coinAmount + " €"));
        banknoteAmountText.setText(String.valueOf(banknoteAmount + " €"));
        otherAmountText.setText(String.valueOf(otherAmount + " €"));
        rollAmountText.setText(String.valueOf(rollAmount + " €"));
        totalAmountText.setText(String.valueOf(totalAmount + " €"));
    }

    private static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void calculateDifference(){
        if(amountShouldBe == 0.00){
            differenceText.setText("-");
        } else {
            differenceAmount = amountShouldBe - totalAmount;
            differenceAmount = mainActivity.round(differenceAmount, 2);
            differenceText.setText(String.valueOf(Math.round(-1.00*differenceAmount * 100.0) / 100.0) + " €");
        }
    }

}
