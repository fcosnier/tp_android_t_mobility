package thales.fr.firsttp;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private String login;
    private String mdp;
    private TextInputLayout mTextInputLayoutLogin;
    private TextInputLayout mTextInputLayoutMdp;
    private EditText mEditTextLogin;
    private EditText mEditTextMdp;
    private Button mButtonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextInputLayoutLogin = (TextInputLayout)
                findViewById(R.id.activity_main_textinputlayout_login);
        mTextInputLayoutMdp = (TextInputLayout)
                findViewById(R.id.activity_main_textinputlayout_mdp);

        mButtonLogin = (Button) findViewById(R.id.activity_main_button);
        mEditTextLogin = (EditText) findViewById(R.id.activity_main_textinputlayout_login_edittext);
        mEditTextMdp = (EditText) findViewById(R.id.activity_main_textinputlayout_mdp_edittext);
    }

    @Override
    public void onResume() {
        super.onResume();

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = mEditTextLogin.getText().toString();
                Toast.makeText(LoginActivity.this, "Login: " + login, Toast.LENGTH_SHORT).show();
                //startActivity(MainActivity);
            }
        });

    }


    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }
}

