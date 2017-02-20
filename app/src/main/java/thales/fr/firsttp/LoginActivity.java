package thales.fr.firsttp;

import android.content.Intent;
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
        setContentView(R.layout.activity_login);

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

                if(login.compareTo("Android")!=0){
                    Toast.makeText(LoginActivity.this, "Login n'est pas correct: - Login: "
                            + login, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
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

