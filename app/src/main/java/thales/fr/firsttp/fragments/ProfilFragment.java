package thales.fr.firsttp.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import thales.fr.firsttp.R;

/**
 * Handle profil view content.
 */
public class ProfilFragment extends Fragment {
    private static final String TAG = "ProfilFragment";
    private static final String FRAGMENT_ARGS_USER = "user";
    private static final String FRAGMENT_ARGS_ADMIN = "admin";

    private EditText mEditTextLastName, mEditTextFirstName, mEditTextBirthdate, mEditTextDirection, mEditTextDepartment, mEditTextIdentifier, mEditTextUgd;
    private ImageView imageViewFont, imageViewFontProfil;
    private FloatingActionButton FAB;

    public ProfilFragment() {
        // Required empty public constructor
    }

    /**
     * Create a new profile fragment and pass an User as parameter to this fragment.
     * @param user User data
     * @param admin Admin data viewing this user profil
     * @return A profil fragment.
     */
    public static ProfilFragment newInstance(/*User user, Admin admin*/) {
        ProfilFragment f = new ProfilFragment();
        // Supply index input as an argument.
//        Bundle args = new Bundle();
//        args.putSerializable(FRAGMENT_ARGS_USER, user);
//        args.putSerializable(FRAGMENT_ARGS_ADMIN, admin);
//        f.setArguments(args);
        return f;
    }

    /**
     * Fragment view creation.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // don't look at this layout it's just a listView to show how to handle the keyboard
        View view = inflater.inflate(R.layout.profil_view, container, false);

        // Update top toolbar title.
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity()
                .getString(R.string.fragment_profil));

        // Retrieve image view for photo
        imageViewFontProfil = (ImageView) view.findViewById(R.id.imageViewProfil);
        // Retrieve image view for icon
        imageViewFont = (ImageView) view.findViewById(R.id.picture_icon);

        // Set FAB icon
        FAB = (FloatingActionButton) view.findViewById(R.id.photo_fab);
        Drawable photoDrawable = new IconicsDrawable(view.getContext())
                .icon(GoogleMaterial.Icon.gmd_camera)
                .color(ContextCompat.getColor(view.getContext(), R.color.white))
                .sizeDp(12);
        FAB.setImageDrawable(photoDrawable);

//        mUserPhotoOK = (TextView) view.findViewById(R.id.user_profil_photo_ok);

        // Retrieve profil text
        mEditTextLastName = (EditText) view.findViewById(R.id.profil_text_lastname);
        mEditTextFirstName = (EditText) view.findViewById(R.id.profil_text_firstname);
        mEditTextBirthdate = (EditText) view.findViewById(R.id.profil_text_birthdate);

//        photoTakeRefuseSwitch = (SwitchCompat) view.findViewById(R.id
//                .profil_form_take_photo_refuse);
//        photoUseOkSwitch = (SwitchCompat) view.findViewById(R.id.profil_form_use_photo_ok);
//
//        // Retrieve fragment arguments
//        Bundle args = getArguments();
//        mUser = (User) args.getSerializable(FRAGMENT_ARGS_USER);
//        mAdmin = (Admin) args.getSerializable(FRAGMENT_ARGS_ADMIN);
//
//        // Update text value from user data
//        if(mUser != null) {
//            mEditTextLastName.setText(mUser.getLastname());
//            mEditTextFirstName.setText(mUser.getFirstname());
//            mEditTextBirthdate.setText(Utils.birthDateToString(mUser.getBirthdate()));
//            mEditTextDirection.setText(mUser.getDirection());
//            mEditTextDepartment.setText(mUser.getDepartment());
//            mEditTextIdentifier.setText(mUser.getIdentifierCode() + " " + mUser.getIdentifier());
//            mEditTextUgd.setText(mUser.getUgd());
//
//            photoTakeRefuseSwitch.setChecked(mUser.getRefusePhotoShot());
//            photoUseOkSwitch.setChecked(mUser.getAcceptPhotoUse());
//
//            // Load user picture if any.
//            loadUserPicture();
//        }

        return view;
    }

    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");

        // Update button status color if necessary.
//        updatePhotoButtonStatus();
//        Log.e(TAG, "mUser.getPhotoServerOk() " + mUser.getPhotoServerOk());

        // Define photo button action
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mUser != null && !mUser.getPhotoServerOk()) {
//                    Log.e(TAG, "FAB " + new Date());
//                    // start camera activity to take a picture.
//                    dispatchTakePictureIntent();
//                }
            }
        });
    }

    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }
}
