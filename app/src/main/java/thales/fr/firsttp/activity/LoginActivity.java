package thales.fr.firsttp.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import thales.fr.firsttp.R;
import thales.fr.firsttp.application.CoreApplication;
import thales.fr.firsttp.custom.LinearLayoutThatDetectsSoftKeyboard;
import thales.fr.firsttp.custom.MaterialLoginView;
import thales.fr.firsttp.custom.MaterialLoginViewListener;
import thales.fr.firsttp.data.DataPrefsStorage;

public class LoginActivity extends AppCompatActivity implements
        LinearLayoutThatDetectsSoftKeyboard.Listener {
    private static final String TAG = "LoginActivity";

    /** Hold if an error is pending. Should be cleared after the message as been shown. */
    private String mPendingError = null;

    /** Login view instance */
    private View mView = null;
    /** Login input text widget */
    private TextInputLayout mTextInputLayoutLogin;
    /** Password input text widget */
    private TextInputLayout mTextInputLayoutMdp;

    /** Status if server synchronisation is in progress */
    private boolean mSyncInProgress;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Link a view layout to this activity
        setContentView(R.layout.activity_login);

        // No sync in progress by default
        mSyncInProgress = false;
        final LoginActivity activity = this;

        // Retrieve login widget
        final MaterialLoginView login = (MaterialLoginView) findViewById(R.id.login);
        // Listen when the login button clicked
        login.setListener(new MaterialLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
               onLoginConnexion(loginUser, loginPass);
            }
        });
    }

    /**
     * Handler when the user try to connect.
     * @param loginUser Input user login
     * @param loginPass Input user password
     */
    private void onLoginConnexion(TextInputLayout loginUser, TextInputLayout loginPass) {
        boolean error = false;

        if(loginUser == null || loginPass == null) {
            return;
        }

        // Retrieve the new login text value
        String user = loginUser.getEditText().getText().toString();
        // Trim whitespaces
        String trimUserLogin  = user.replaceAll(" ", "");
        // If login text empty
        if (trimUserLogin.isEmpty()) {
            // Show an error message
            loginUser.setError(getString(R.string.user_empty));
            error = true;

            // Check if this login do not match the database
        } else {
            // Clear previous error
            loginUser.setError(null);
        }

        // Retrieve new password text
        String pass = loginPass.getEditText().getText().toString();

        // Check this password match the admin one
//        if (admin != null && !pass.equals(admin.getPassword())) {
//            // If passwords do not match, show an error
//            loginPass.setError(getString(R.string.error_password));
//            error = true;
//        } else {
//            // Clear previous error
//            loginPass.setError(null);
//        }

        if(!error) {
            // Save login and password to preference storage
            DataPrefsStorage.setsPrefsLogin(getApplicationContext(), trimUserLogin);
            DataPrefsStorage.setsPrefsMDP(getApplicationContext(), pass);

            if(CoreApplication.RELEASE) {
                // Reset input password text
                loginPass.getEditText().setText("");
            }

            // Change activity
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            Bundle b = new Bundle();
//            b.putSerializable(MainActivity.ACTIVITY_ARGS_LOGIN, admin);
            myIntent.putExtras(b);
            LoginActivity.this.startActivity(myIntent);
        }
    }

    /**
     * Show on hide the login floating button
     * @param isShowing
     */
    @Override
    public void onSoftKeyboardShown(boolean isShowing) {
        // do whatever you need to do here
        final FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.register_fab);
        if(isShowing){
            FAB.hide();
        } else {
            FAB.show();
        }
    }

    /**
     * Activity resume.
     */
    @Override
    public void onResume() {
        super.onResume();

        // Retrieve login and password text input instances
        mTextInputLayoutLogin = (TextInputLayout)findViewById(R.id.login_user);
        mTextInputLayoutMdp = (TextInputLayout)findViewById(R.id.login_pass);

        mView = findViewById(R.id.login);

        // Set text input content from the data preferences.
        mTextInputLayoutLogin.getEditText().setText(DataPrefsStorage.getsPrefsLogin(this));
        if(CoreApplication.DEBUG) {
            mTextInputLayoutMdp.getEditText().setText(DataPrefsStorage.getsPrefsMDP(this));
        }
    }

    /**
     * Activity pause
     */
    @Override
    public void onPause() {
        super.onPause();
        mView = null;
    }
}

