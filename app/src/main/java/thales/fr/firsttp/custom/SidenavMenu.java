package thales.fr.firsttp.custom;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.fastadapter.IExpandable;
import com.mikepenz.fastadapter.utils.RecyclerViewCacheUtil;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import thales.fr.firsttp.R;
import thales.fr.firsttp.Utils.Utils;
import thales.fr.firsttp.activity.MainActivity;
import thales.fr.firsttp.fragments.ProfilFragment;

/**
 * Create a Left side navigation menu based on an Activity
 */
public class SidenavMenu {
    /** Activity to attach the sidenav manu */
    private MainActivity mActivity;

    /** Side nav header */
    private AccountHeader headerResult = null;

    /** Sidenav content */
    private Drawer result = null;

    /** Profile used for the sidenav */
    private IProfile profile;

    /** Hold counters to display on this sidenav menu */
    private long userCount, userCountNoPhoto, localPhotoCount;

    /**
     * Create sidenav menu from an activity
     * @param activity
     * @param savedInstanceState
     * @param toolbar
     */
    public SidenavMenu(MainActivity activity, Bundle savedInstanceState, Toolbar toolbar,
                       String login) {
        mActivity = activity;

        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setCancelable(false);
        builder.setTitle(mActivity.getString(R.string.alert_dialog_title));
        builder.setMessage(mActivity.getString(R.string.alert_dialog_logout));
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mActivity.finish();
            }

        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                headerResult.setActiveProfile(100);
            }
        });

        // Create a few sample profile
        // NOTE you have to define the loader logic too. See the CustomApplication for more details
        profile = new ProfileDrawerItem()
                .withName(login)
                .withEmail("florian.cosnier@thalesgroup.com")
                .withIdentifier(100);

        Drawable signOut = new IconicsDrawable(mActivity)
                .icon(GoogleMaterial.Icon.gmd_block_alt)
                .color(ContextCompat.getColor(mActivity, R.color.white))
                .sizeDp(24);

        final IProfile profile2 = new ProfileDrawerItem().withIcon
                (signOut).withIdentifier(101);

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(mActivity)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.sidenav_bgm)
                .withSavedInstance(savedInstanceState)
                .withSelectionListEnabled(false)
                .withSelectionSecondLineShown(true)
                .addProfiles(profile, profile2)
                .withSelectionListEnabledForSingleProfile(true)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile iProfile, boolean
                            currentProfile) {
                        return false;
                    }
                })
                .withOnAccountHeaderProfileImageListener(new AccountHeader.OnAccountHeaderProfileImageListener() {
                    @Override
                    public boolean onProfileImageClick(View view, IProfile iProfile, boolean current) {
                        if (iProfile.equals(profile2)) {
                            // Show alert dialog
                            builder.show();
                        }
                        else if (iProfile.equals(profile)) {
                            mActivity.getSupportFragmentManager().beginTransaction().replace(R.id
                                    .frame_container, new ProfilFragment()).commit();
                        }

                        return false;
                    }

                    @Override
                    public boolean onProfileImageLongClick(View view, IProfile profile, boolean current) {
                        return false;
                    }
                })
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(mActivity)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withItemAnimator(new AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_users)
                                .withIcon(GoogleMaterial.Icon.gmd_account_circle)
                                .withIdentifier(Utils.FRAGMENT_USER_LIST)
                                .withSelectable(false)
                                .withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_red_700)),

                        new PrimaryDrawerItem().withName(R.string.drawer_item_search)
                                .withIcon(GoogleMaterial.Icon.gmd_search)
                                .withIdentifier(Utils.FRAGMENT_SEARCH)
                                .withSelectable(false),

                        new PrimaryDrawerItem().withName(R.string.drawer_item_settings)
                                .withIcon(GoogleMaterial.Icon.gmd_settings)
                                .withIdentifier(Utils.FRAGMENT_SETTINGS)
                                .withSelectable(false)

                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            //if our drawer has collapsible items we check if the clicked items has subItem. if yes we open it
                            if (((IExpandable) drawerItem).getSubItems() != null) {
                                result.getAdapter().toggleExpandable(position);
                                //we consume the event and want no further handling
                                return true;
                            }

                            menuChangeAction(drawerItem.getIdentifier());
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        mActivity.hideKeyBoardDisplay();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        mActivity.hideKeyBoardDisplay();
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        mActivity.hideKeyBoardDisplay();
                    }
                })
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    // Occurs when the navigation button from the toolbar is clicked
                    @Override
                    public boolean onNavigationClickListener(View view) {
                        if (!result.getActionBarDrawerToggle().isDrawerIndicatorEnabled()) {
                            // Case when the back arrow is enabled
                            hideBackArrow();
                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .build();


        //if you have many different types of DrawerItems you can magically pre-cache those items to get a better scroll performance
        //make sure to init the cache after the DrawerBuilder was created as this will first clear the cache to make sure no old elements are in
        //RecyclerViewCacheUtil.getInstance().withCacheSize(2).init(result);
        new RecyclerViewCacheUtil<IDrawerItem>().withCacheSize(2).apply(result.getRecyclerView(), result.getDrawerItems());

        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
            result.setSelection(21, false);

            //set the active profile
//            headerResult.setActiveProfile(profile);
        }
    }

    /**
     * Change fragment and update menu index when a menu item clicked.
     * @param targetIndex Fragment index (see Utils.FRAGMENT_...)
     */
    public void menuChangeAction(final long targetIndex) {
        if(targetIndex == Utils.FRAGMENT_SYNC) {
//            mActivity.syncWithServer();
        } else {
            mActivity.changeFragment(targetIndex);

            if(targetIndex == Utils.FRAGMENT_SEARCH) {
                // open Side search bar
//                mActivity.openSearchBar();
            }
        }
    }

    public void showBackArrow() {
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void hideBackArrow() {
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
    }


    public void close() {
        if(result.isDrawerOpen()) {
            result.closeDrawer();
        }
    }
}
