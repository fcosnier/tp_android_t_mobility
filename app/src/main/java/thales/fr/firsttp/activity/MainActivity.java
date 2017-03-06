package thales.fr.firsttp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.otto.Subscribe;

import thales.fr.firsttp.R;
import thales.fr.firsttp.Utils.Utils;
import thales.fr.firsttp.application.CoreApplication;
import thales.fr.firsttp.custom.SidenavMenu;
import thales.fr.firsttp.event.AppEvent;
import thales.fr.firsttp.fragments.ProfilFragment;
import thales.fr.firsttp.fragments.SettingFragment;

import static thales.fr.firsttp.R.mipmap.ic_launcher;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";

    /** Activity argument field. Should hold the admin login. */
    public static final String ACTIVITY_ARGS_LOGIN = "login";

    private MainActivity mActivity = null;

    /** Admin data logged to this main activity */
//    private Admin mAdmin = null;

    /** Hold current shown fragment index */
    private long currentFragment = Utils.FRAGMENT_NULL;

    /** Setting fragment instance */
    private SettingFragment mSettingFragment;
    /** Profil fragment instance */
    private ProfilFragment mProfilFragment;

    /** Right side navigation menu instance */
    private SidenavMenu mSidenavMenu;

    /** Register status to the CoreApplication event bus */
    private static boolean isRegistered = false;

    /** Hold the root activity view. */
    private View mView = null;

    /** Activity start */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Handler when the options menu is created.
     * This represent the right hand side menu on the top toolbar.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; add items to the action bar if it is present.

        return true;
    }

    /**
     * Handler when an menu item event occurs
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Activity create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;

        // Set layout to this activity
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        // Retrieve activity argument
//        Bundle b = getIntent().getExtras();
        String login = "";
//        if(b != null) {
//            mAdmin = (Admin) b.getSerializable(ACTIVITY_ARGS_LOGIN);
//            login = mAdmin.getLastname() + " " + mAdmin.getFirstname();
//        }

        // Create all unique fragments
        mSettingFragment = new SettingFragment();
        mProfilFragment = new ProfilFragment();


        // Create left Sidenav menu
        mSidenavMenu = new SidenavMenu(this, savedInstanceState, toolbar, login);


        // Setup thread policy. Necessary ??
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    /**
     * Change fragment using the fragment ID.
     * @param fragmentId See Application.Utils.FRAGMENT_...
     * @return True if the fragment change occurs else false.
     */
    public boolean changeFragment(long fragmentId) {
        Fragment nextFragment = null;

       if (fragmentId == Utils.FRAGMENT_SETTINGS) {
           mSidenavMenu.showBackArrow();
           nextFragment = mSettingFragment;
        } else if(fragmentId == Utils.FRAGMENT_PROFIL) {
            mSidenavMenu.showBackArrow();
            nextFragment = mProfilFragment;
        }

        if(nextFragment != null) {
            // Change fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, nextFragment)
                    .commit();

            // Update fragment
            currentFragment = fragmentId;
        }

        return (nextFragment != null);
    }


    /** Activity resume */
    @Override
    public void onResume() {
        super.onResume();

        tryEventBusRegister();

        mView = findViewById(R.id.frame_container);


        // Update sidenav counter values
//        IDatabaseManager db = CoreApplication.getDatabaseManager();
//        if(mAdmin != null) {
//            mSidenavMenu.updateCountAllUsers(db.userCountByAdmin(mAdmin));
//        } else {
//            mSidenavMenu.updateCountAllUsers(db.userCount());
//        }
    }

    /** Activity pause */
    @Override
    public void onPause() {
        super.onPause();
        tryEventBusUnregister();

        mView = null;
    }

    /** Activity stop */
    @Override
    public void onStop() {
        super.onStop();
    }

    /** Activity destroy */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Hide the keyboard.
     */
    public void hideKeyBoardDisplay() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Show an dialog if back pressed within main activity.
     */
    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed Called");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(getString(R.string.alert_dialog_title));
        builder.setMessage(getString(R.string.alert_dialog_logout));
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                headerResult.setActiveProfile(100);
            }
        });
        builder.show();
    }

    /**
     * Register a bus to send event to the main activity.
     */
    private void tryEventBusRegister() {
        // Register this activity to the application event bus
        if(!isRegistered) {
            CoreApplication.getEventBus().register(this);
            isRegistered = true;
        }
    }

    /**
     * Unregister event bus.
     */
    private void tryEventBusUnregister() {
        // Unregister this activity to the application event bus.
        if(isRegistered) {
            CoreApplication.getEventBus().unregister(this);
            isRegistered=false;
        }
    }

    /**
     * Handle when event sent from bus.
     * @param event
     */
    @Subscribe
    public void onEventCompleted(final AppEvent event) {

        AppEvent.Type type = AppEvent.Type.values()[event
                .getType().ordinal()];

        AppEvent.Status status = AppEvent.Status.values()[event
                .getStatus().ordinal()];
        switch (type) {
            case FRAGMENT:
                Log.i(TAG, "Event on fragment");
                switch (status) {
                    case CHANGE:
                        if(event.getCode() == Utils.FRAGMENT_PROFIL) {
                            mProfilFragment = new ProfilFragment();
                        }
                        changeFragment(event.getCode());
                        break;
                }
        }
    }

    public void closeSidenavMenu() {
        mSidenavMenu.close();
    }

}
