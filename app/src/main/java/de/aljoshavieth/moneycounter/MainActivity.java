package de.aljoshavieth.moneycounter;

import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity
        implements MainFragment.OnFragmentInteractionListener,
        CoinsFragment.OnFragmentInteractionListener,
        RollsFragment.OnFragmentInteractionListener,
        BanknotesFragment.OnFragmentInteractionListener,
        OtherFragment.OnFragmentInteractionListener{

    private MainFragment mainFragment;
    private CoinsFragment coinsFragment;
    private BanknotesFragment banknotesFragment;
    private OtherFragment otherFragment;
    private RollsFragment rollsFragment;
    
    public static double totalAmount;
    public static double coinAmount;
    public static double banknoteAmount;
    public static double rollAmount;
    public static double otherAmount;
    public static double cashAmount;
    public static double amountShouldBe;
    public static double differenceAmount;
    private boolean doubleBackToExitPressedOnce = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button coinBtn = findViewById(R.id.coinsBtn);
        //coinBtn.setOnClickListener(this);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            resetAll();

            // Create new Fragments to be placed in the activity layout
            mainFragment = new MainFragment();
            coinsFragment = new CoinsFragment();
            banknotesFragment = new BanknotesFragment();
            otherFragment = new OtherFragment();
            rollsFragment = new RollsFragment();


            // Add the main fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, mainFragment).commit();
        }

    }

    public void handleButtonClicks(View v) {
        switch(v.getId()) {
            case R.id.coinsBtn:
                switchToFragment(coinsFragment);
                break;
            case R.id.rollsOfCoinsBtn:
                switchToFragment(rollsFragment);
                break;
            case R.id.banknotesBtn:
                switchToFragment(banknotesFragment);
                break;
            case R.id.otherBtn:
                switchToFragment(otherFragment);
                break;
            case R.id.resetBtn:
                resetAll();
                break;

        }
    }



    private void switchToFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, fragment);
        // Commit the transaction
        transaction.commit();
    }

    public void switchToMainFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, mainFragment);
        // Commit the transaction
        transaction.commit();
    }

    public void resetAll(){
        totalAmount = 0.00;
        coinAmount = 0.00;
        banknoteAmount = 0.00;
        rollAmount = 0.00;
        otherAmount = 0.00;
    }

    public void calculateTotalAmount(){
        totalAmount = coinAmount + banknoteAmount + rollAmount + otherAmount;
        mainFragment.setTotalAmount();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.back, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
