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
import static de.aljoshavieth.moneycounter.MainActivity.rollAmount;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RollsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RollsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RollsFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText rollsEur2AmountText;
    private EditText rollsEur1AmountText;
    private EditText rollsCt50AmountText;
    private EditText rollsCt20AmountText;
    private EditText rollsCt10AmountText;
    private EditText rollsCt5AmountText;
    private EditText rollsCt2AmountText;
    private EditText rollsCt1AmountText;

    private EditText packageEur2AmountText;
    private EditText packageEur1AmountText;
    private EditText packageCt50AmountText;
    private EditText packageCt20AmountText;
    private EditText packageCt10AmountText;
    private EditText packageCt5AmountText;
    private EditText packageCt2AmountText;
    private EditText packageCt1AmountText;

    private Button doneButton;
    private MainActivity mainActivity;

    private OnFragmentInteractionListener mListener;

    public RollsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RollsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RollsFragment newInstance(String param1, String param2) {
        RollsFragment fragment = new RollsFragment();
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
        View view = inflater.inflate(R.layout.fragment_rolls, container, false);


        rollsEur2AmountText = view.findViewById(R.id.rolls2eurRollInput);
        rollsEur1AmountText = view.findViewById(R.id.rolls1eurRollInput);
        rollsCt50AmountText = view.findViewById(R.id.rolls50ctRollInput);
        rollsCt20AmountText = view.findViewById(R.id.rolls20ctRollInput);
        rollsCt10AmountText = view.findViewById(R.id.rolls10ctRollInput);
        rollsCt5AmountText = view.findViewById(R.id.rolls5ctRollInput);
        rollsCt2AmountText = view.findViewById(R.id.rolls2ctRollInput);
        rollsCt1AmountText = view.findViewById(R.id.rolls1ctRollInput);


        packageEur2AmountText = view.findViewById(R.id.rolls2eurPackageInput);
        packageEur1AmountText = view.findViewById(R.id.rolls1eurPackageInput);
        packageCt50AmountText = view.findViewById(R.id.rolls50ctPackageInput);
        packageCt20AmountText = view.findViewById(R.id.rolls20ctPackageInput);
        packageCt10AmountText = view.findViewById(R.id.rolls10ctPackageInput);
        packageCt5AmountText = view.findViewById(R.id.rolls5ctPackageInput);
        packageCt2AmountText = view.findViewById(R.id.rolls2ctPackageInput);
        packageCt1AmountText = view.findViewById(R.id.rolls1ctPackageInput);

        doneButton = view.findViewById(R.id.rollsDoneBtn);
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
        if(v.getId() == R.id.rollsDoneBtn){
            calculateRollsAndPackages();
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

    private void calculateRollsAndPackages(){

        double eur2RollAmount = 0.00;
        double eur1RollAmount = 0.00;
        double ct50RollAmount = 0.00;
        double ct20RollAmount = 0.00;
        double ct10RollAmount = 0.00;
        double ct5RollAmount = 0.00;
        double ct2RollAmount = 0.00;
        double ct1RollAmount = 0.00;

        double eur2PackageAmount = 0.00;
        double eur1PackageAmount = 0.00;
        double ct50PackageAmount = 0.00;
        double ct20PackageAmount = 0.00;
        double ct10PackageAmount = 0.00;
        double ct5PackageAmount = 0.00;
        double ct2PackageAmount = 0.00;
        double ct1PackageAmount = 0.00;

        if(!rollsEur2AmountText.getText().toString().isEmpty()){
            eur2RollAmount = 50.00 *Double.valueOf(rollsEur2AmountText.getText().toString());
        }
        if(!rollsEur1AmountText.getText().toString().isEmpty()){
            eur1RollAmount = 25.00 *Double.valueOf(rollsEur1AmountText.getText().toString());
        }
        if(!rollsCt50AmountText.getText().toString().isEmpty()){
            ct50RollAmount = 20.00 *Double.valueOf(rollsCt50AmountText.getText().toString());
        }
        if(!rollsCt20AmountText.getText().toString().isEmpty()){
            ct20RollAmount = 8.00 *Double.valueOf(rollsCt20AmountText.getText().toString());
        }
        if(!rollsCt10AmountText.getText().toString().isEmpty()){
            ct10RollAmount = 4.00 *Double.valueOf(rollsCt10AmountText.getText().toString());
        }
        if(!rollsCt5AmountText.getText().toString().isEmpty()){
            ct5RollAmount = 2.50 *Double.valueOf(rollsCt5AmountText.getText().toString());
        }
        if(!rollsCt2AmountText.getText().toString().isEmpty()){
            ct2RollAmount = 1.00 *Double.valueOf(rollsCt2AmountText.getText().toString());
        }
        if(!rollsCt1AmountText.getText().toString().isEmpty()){
            ct1RollAmount = 0.50 *Double.valueOf(rollsCt1AmountText.getText().toString());
        }


        if(!packageEur2AmountText.getText().toString().isEmpty()){
            eur2PackageAmount = 500.00 *Double.valueOf(packageEur2AmountText.getText().toString());
        }
        if(!packageEur1AmountText.getText().toString().isEmpty()){
            eur1PackageAmount = 250.00 *Double.valueOf(packageEur1AmountText.getText().toString());
        }
        if(!packageCt50AmountText.getText().toString().isEmpty()){
            ct50PackageAmount = 200.00 *Double.valueOf(packageCt50AmountText.getText().toString());
        }
        if(!packageCt20AmountText.getText().toString().isEmpty()){
            ct20PackageAmount = 80.00 *Double.valueOf(packageCt20AmountText.getText().toString());
        }
        if(!packageCt10AmountText.getText().toString().isEmpty()){
            ct10PackageAmount = 40.00 *Double.valueOf(packageCt10AmountText.getText().toString());
        }
        if(!packageCt5AmountText.getText().toString().isEmpty()){
            ct5PackageAmount = 25.00 *Double.valueOf(packageCt5AmountText.getText().toString());
        }
        if(!packageCt2AmountText.getText().toString().isEmpty()){
            ct2PackageAmount = 10.00 *Double.valueOf(packageCt2AmountText.getText().toString());
        }
        if(!packageCt1AmountText.getText().toString().isEmpty()){
            ct1PackageAmount = 5.00 *Double.valueOf(packageCt1AmountText.getText().toString());
        }

        rollAmount =
                eur2RollAmount + eur1RollAmount + ct50RollAmount + ct20RollAmount + ct10RollAmount
                + ct5RollAmount + ct2RollAmount + ct1RollAmount + eur2PackageAmount
                + eur1PackageAmount + ct50PackageAmount + ct20PackageAmount + ct10PackageAmount
                + ct5PackageAmount + ct2PackageAmount + ct1PackageAmount;
        rollAmount = mainActivity.round(rollAmount, 2);

    }

}
