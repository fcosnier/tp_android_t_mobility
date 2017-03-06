package thales.fr.firsttp.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import thales.fr.firsttp.R;

public class MaterialLoginView extends FrameLayout{
    private TextInputLayout loginUser;
    private TextInputLayout loginPass;
    private FloatingActionButton loginButton;
//    private CheckBox checkBox;

    private MaterialLoginViewListener listener;

    public MaterialLoginView(Context context) {
        this(context, null);
    }

    public MaterialLoginView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialLoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MaterialLoginView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.login_view, this, true);


        loginUser = (TextInputLayout) findViewById(R.id.login_user);
        loginPass = (TextInputLayout) findViewById(R.id.login_pass);

        loginButton = (FloatingActionButton) findViewById(R.id.register_fab);
        Drawable signIn = new IconicsDrawable(context)
                .icon(GoogleMaterial.Icon.gmd_sign_in)
                .color(ContextCompat.getColor(context, R.color.color_dark_blue))
                .sizeDp(24);

        loginButton.setImageDrawable(signIn);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLogin(loginUser, loginPass);
            }
        });

        loginUser.setHint(getResources().getString(R.string.login));
        loginPass.setHint(getResources().getString(R.string.password));

        int color = ContextCompat.getColor(context, R.color.white);
        loginUser.getEditText().setTextColor(color);
        loginPass.getEditText().setTextColor(color);

        loginPass.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                if (isKeyboardShown(loginPass.getRootView())) {
                    loginButton.hide();
                } else {
                    loginButton.show();
                }
            }
        });

//        checkBox = (CheckBox) findViewById(R.id.showPassword);
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//
//
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    loginPass.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//
//                } else {
//                    loginPass.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                }
//            }
//        });
    }

    public void setListener(MaterialLoginViewListener listener) {
        this.listener = listener;
    }

    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    private boolean isKeyboardShown(View rootView) {
    /* 128dp = 32dp * 4, minimum button height 32dp and generic 4 rows soft keyboard */
        final int SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128;

        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
    /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
        int heightDiff = rootView.getBottom() - r.bottom;
    /* Threshold size: dp to pixels, multiply with display density */
        boolean isKeyboardShown = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density;

        Log.d("TEST", "isKeyboardShown ? " + isKeyboardShown + ", heightDiff:" + heightDiff + ", " +
                "density:" + dm.density
                + "root view height:" + rootView.getHeight() + ", rect:" + r);

        return isKeyboardShown;
    }
}
