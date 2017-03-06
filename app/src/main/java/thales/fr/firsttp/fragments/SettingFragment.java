package thales.fr.firsttp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thales.fr.firsttp.R;

/**
 * Fragment for the settings view
 */
public class SettingFragment extends Fragment {
    private static final String TAG = "SettingFragment";

    public SettingFragment() {

    }

    /**
     * Fragment view create
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.setting_view, container, false);

        // Set fragment title
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getActivity()
                .getString(R.string.fragment_setting));

        // Retrieve copyright text to set the current year
//        mTextViewCopyright = (TextView) view.findViewById(R.id.textViewCopyright);
//        String copyright = mTextViewCopyright.getText().toString();
//        Calendar calendar = Calendar.getInstance();
//        copyright = copyright.replace("XXXX", ""+calendar.get(Calendar.YEAR));
//        mTextViewCopyright.setText(copyright);

        return view;
    }

    /** Fragment resume */
    public void onResume() {
        super.onResume();
    }
}
