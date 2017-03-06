package thales.fr.firsttp.Utils;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import thales.fr.firsttp.R;

public final class Utils {
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    // Fragments ID
    public final static long FRAGMENT_NULL = -1;
    public final static long FRAGMENT_USER_LIST = 1;
    public final static long FRAGMENT_SEARCH = 2;
    public final static long FRAGMENT_SETTINGS = 3;
    public static final long FRAGMENT_PROFIL = 4;
    public static final long FRAGMENT_SYNC = 5;

    public final static String KEY_USER_LASTNAME = "lastname";
    public final static String KEY_USER_FIRSTNAME = "firstname";
    public final static String KEY_USER_BIRTHDATE = "birthdate";

    public final static String KEY_USER_IDENTIFIER_CODE = "identifierCode";
    public final static String KEY_USER_IDENTIFIER = "identifier";


    private Utils() {}

    /**
     * Convert a Date instance to string using the application format
     * @param birthdate Given bithdate
     * @return Formatted birthdate to Utils.DATE_FORMAT.
     */
    public static String birthDateToString(Date birthdate) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(birthdate);
    }

    /**
     * Convert a string to a Date object using the DATE_FORMAT.
     * @param birthdate
     * @return
     * @throws ParseException
     */
    public static Date birthDateToDate(String birthdate) throws ParseException {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.parse(birthdate);
    }

    /**
     * Compare if 2 fields are the same.
     * @param field1
     * @param field2
     * @return 0 if fields are the same else an interger (see Comparable interface)
     */
    public static int compareField(Comparable field1, Comparable field2) {
        // Null pointer check
        if(field1 == null && field2 != null || field1 != null && field2 == null) {
            return 1;
        } else if(field1 == null && field2 == null) {
            return 0;
        }

        return field1.compareTo(field2);
    }

    public static final int getColor(Context context, int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    /**
     * Parse a full identifier string and set a criteria map with identifier and identifierCode.
     * @param criterias Map to set criterias from search string if any.
     * @param search Full identifier string.
     */
    public static void parseIdentifierSearchInput(Map<String, String> criterias, final String search) {
        String identifier = null, identifierCode = null;

        // Parse identifier field
        String[] idSearchArray = search.split(" ");
        // Try to retrieve identifier and identifier code
        if(idSearchArray.length < 2) {
            // choose if we search the identifier  (digit only)
            if(search.matches("[0-9]+")) {
                identifier = search;
            } else { // Or the identifier code (should be letter only)
                identifierCode = search;
            }
        } else if(idSearchArray.length == 2) {
            identifierCode = idSearchArray[0];
            identifier = idSearchArray[1];
        } // else unknow pattern

        if(identifier != null && identifier.length() > 0) {
            criterias.put(Utils.KEY_USER_IDENTIFIER, identifier);
        }

        if(identifierCode != null && identifierCode.length() > 0) {
            criterias.put(Utils.KEY_USER_IDENTIFIER_CODE, identifierCode);
        }
    }

    public static String removeTrailingWhitespace(String text) {
        return text.trim();
    }

    /**
     * Show error message with a Snack bar.
     * @param view
     * @param message
     */
    public static void showErrorMessage(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(view.getResources().getColor(R.color.material_login_login_error_color));

        snackbar.show();
    }
}
