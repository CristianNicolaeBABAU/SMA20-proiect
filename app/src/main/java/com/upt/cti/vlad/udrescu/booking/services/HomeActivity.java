package com.upt.cti.vlad.udrescu.booking.services;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upt.cti.vlad.udrescu.booking.R;
import com.upt.cti.vlad.udrescu.booking.common.Common;
import com.upt.cti.vlad.udrescu.booking.fragments.FavoritesFragment;
import com.upt.cti.vlad.udrescu.booking.fragments.HomeFragment;
import com.upt.cti.vlad.udrescu.booking.model.User;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    BottomSheetDialog bottomSheetDialog;

    CollectionReference userRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.home_menu_bottom_navigation);
        userRef = FirebaseFirestore.getInstance().collection("User");

        if (getIntent() != null) {
            boolean isLoggedIn = getIntent().getBooleanExtra(Common.IS_LOGIN, false);
            if (!isLoggedIn) {
                Log.d("Success", "Login");
            }
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home)
                    fragment = new HomeFragment();
                else if (item.getItemId() == R.id.action_favorite)
                    fragment = new FavoritesFragment();
                return loadFragment(fragment);
            }

        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_menu_fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

    private void showUpdateDialog(String email) {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setCancelable(false);
        //View sheetView = getLayoutInflater(R.layout.layout_update_information, null);
    }
}
