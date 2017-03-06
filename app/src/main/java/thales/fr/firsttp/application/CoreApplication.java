package thales.fr.firsttp.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import thales.fr.firsttp.R;


/**
 * Global application state to exchange information between activities, fragments ...
 */
public class CoreApplication extends Application {
    private static final String TAG = "CoreApplication";

    // This should be set to false for release
    public static final boolean DEBUG = false;
    public static final boolean RELEASE = !DEBUG;

    public static final boolean USE_LOCAL_CSV = false;

    /** Application context */
    private static Context context;

    /** Application bus */
    private static Bus sBus;

//    private static RestAPI restAdapter;

    private static String serverAddress;

    /** Database connector */
//    private static IDatabaseManager databaseManager;


    /**
     * Application state create
     */
    @Override
    public void onCreate() {
    super.onCreate();
    Log.d(TAG, "onCreate");
        CoreApplication.context = getApplicationContext();

        // Initialize the Otto Event Bus
        sBus = new Bus(ThreadEnforcer.ANY);

//        initializeRestClient(this);

        // Build server http address
//        serverAddress =  context.getResources().getString(R.string.http)
//                + context.getResources().getString(R.string.IP)
//                +":"+context.getResources().getString(R.string.httpport) + "/";

        //Initialize Database DAOs, etc
//        initializeDatabase(this);
    }

    /**
     * Returns the Otto EventBus instance for Publishing events
     * to Subscribers.
     */
    public static Bus getEventBus(){
        return sBus;
    }

    /**
     * Get the RestAPI.
     * @return a RestAPI object.
     */
//    public static RestAPI getRestAdapter() {
//        return restAdapter;
//    }
//
//    /**
//     * Set the RestAPI object.
//     * @param restAdapter the RestAPI to be set.
//     */
//    public static void setRestAdapter(RestAPI restAdapter) {
//        CoreApplication.restAdapter = restAdapter;
//    }


    public static String getServerAddress() {
        return serverAddress;
    }


    /**
     * Set the event bus.
     * @param sBus
     */
    public static void setsBus(Bus sBus) {
        CoreApplication.sBus = sBus;
    }

    /**
     * Function to initialize the database
     *
     * @param context
     */
    private static void initializeDatabase(Context context) {
        Log.d(TAG, "initializeDatabase..." + context.toString());

        // init database manager
//        databaseManager = new DatabaseManager(context);
        // init database manager
//        databaseManager = DatabaseManager.getInstance(context);
    }

    /** Return the database manager instance */
//    public static IDatabaseManager getDatabaseManager() {
//        return databaseManager;
//    }

//    public static void setDatabaseManager(IDatabaseManager databaseManager) {
//        CoreApplication.databaseManager = databaseManager;
//    }

    /** Retrieve global application context */
    public static Context getContext() {
        return context;
    }
}
