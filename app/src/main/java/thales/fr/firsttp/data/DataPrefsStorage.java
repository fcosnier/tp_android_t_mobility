package thales.fr.firsttp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * DataPrefsStorage.class
 *
 * Utility class for persisting tags to disk.
 *
 * <p>The default SharedPreferences instance is used as the backing storage. Values are cached
 * in memory for performance.
 *
 * <p>This class is thread-safe.
 */
public class DataPrefsStorage {
    /******* Preferences *******/
    private static String sPrefsLogin = null;
    private static final String PREF_LOGIN = "login";

    private static String sPrefsMDP = null;
    private static final String PREF_MDP = "mdp";

    /** Hold if the first database initialisation from the server occurs with no error. */
    private static Boolean sPrefsFirstDbInitOk = null;
    private static final String PREF_FIRST_DB_INIT_OK = "firstDbInitOk";

    private static final Object sPrefsLock = new Object();

    /**
     * Get user login from the SharedPreferences.
     * @param context The Context.
     * @return User login.
     */
    public static String getsPrefsLogin(Context context) {
        synchronized (sPrefsLock) {
            if (sPrefsLogin == null) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                sPrefsLogin = prefs.getString(PREF_LOGIN, "");
            }
            return sPrefsLogin;
        }
    }

    /**
     * Set the user login to the SharedPreferences.
     * @param context The Context.
     * @param prefsLogin The user login to set.
     */
    public static void setsPrefsLogin(Context context, String prefsLogin) {
        synchronized(sPrefsLock) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit().putString(PREF_LOGIN, prefsLogin).apply();
            sPrefsLogin = prefsLogin;
        }
    }

    /**
     * Get user password from the SharedPreferences.
     * @param context The Context.
     * @return User password.
     */
    public static String getsPrefsMDP(Context context) {
        synchronized (sPrefsLock) {
            if (sPrefsMDP == null) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                sPrefsMDP = prefs.getString(PREF_MDP, "");
            }
            return sPrefsMDP;
        }
    }

    /**
     * Set the user password to the SharedPreferences.
     * @param context The Context.
     * @param prefsMDP The user password to set.
     */
    public static void setsPrefsMDP(Context context, String prefsMDP) {
        synchronized(sPrefsLock) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit().putString(PREF_MDP, prefsMDP).apply();
            sPrefsMDP = prefsMDP;
        }
    }

    /**
     * Get if the first database initialisation occurs with no error from the SharedPreferences.
     * @param context The Context.
     * @return The first database initialisation status.
     */
    public static Boolean getsPrefsFirstDbInitOk(Context context) {
        synchronized (sPrefsLock) {
            if (sPrefsFirstDbInitOk == null) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                sPrefsFirstDbInitOk = prefs.getBoolean(PREF_FIRST_DB_INIT_OK, false);
            }
            return sPrefsFirstDbInitOk;
        }
    }

    /**
     * Set if the first database initialisation occurs with no error to the SharedPreferences.
     * @param context The Context.
     * @param prefsFirstDbInitOk The first database initialisation status.
     */
    public static void setsPrefsFirstDbInitOk(Context context, Boolean prefsFirstDbInitOk) {
        synchronized(sPrefsLock) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit().putBoolean(PREF_FIRST_DB_INIT_OK, prefsFirstDbInitOk).apply();
            sPrefsFirstDbInitOk = prefsFirstDbInitOk;
        }
    }
}
