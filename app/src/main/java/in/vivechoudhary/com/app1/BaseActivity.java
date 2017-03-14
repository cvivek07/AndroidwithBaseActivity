package in.vivechoudhary.com.app1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }

    };
    private ImageView mHamburgerMenuImageView;
    private TextView mToolbarTitleTextView;
    private TextView mNavigationTitleTextView;
    private TextView mBottomMenuTitle;
    private Toolbar mToolbar;
    private FrameLayout mCommonMenuActivityFrameLayoutFrameLayout;
    private BottomNavigationView mNavigationBottomNavigationView;
    private ListView mDrawerListView;
    private DrawerLayout mDrawerLayout;
    public String APP_FONT = "Lato-Regular.ttf"; // Add this font in your assets folder

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initBaseActivityView();
        setBaseActivityFonts();
    }
    private void initBaseActivityView() {
        mHamburgerMenuImageView = (ImageView) findViewById(R.id.hamburger_menu);
        mHamburgerMenuImageView.setOnClickListener(this);
      //  mBottomMenuTitle = (TextView) findViewById(R.id.message);
        mToolbarTitleTextView = (TextView) findViewById(R.id.toolbar_title);
        mNavigationTitleTextView = (TextView) findViewById(R.id.navigation_title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mCommonMenuActivityFrameLayoutFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mDrawerListView = (ListView) findViewById(R.id.commonMenuActivityDrawerListView);
        mDrawerListView.setAdapter(new NavigationMenuAdapter(this));
        View headerView = getLayoutInflater().inflate(R.layout.navigation_header, null, false);
        mDrawerListView.addHeaderView(headerView);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void setBaseActivityFonts() {
        mToolbarTitleTextView.setTypeface(Typeface.createFromAsset(getAssets(), APP_FONT));
        mToolbarTitleTextView.setTextSize(20f);
        mToolbarTitleTextView.setTextColor(Color.WHITE);
    }
    public void setView(int viewLayout, String activityTitle) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(viewLayout, null, false);
        mCommonMenuActivityFrameLayoutFrameLayout.addView(activityView);
        mToolbarTitleTextView.setText(activityTitle);
    }
    public abstract void initViews();
    public abstract void setFonts();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hamburger_menu:
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                break;
        }
    }
    private class NavigationMenuAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private Context mContext;
        private String[] listName = {"Summary", "Profile", "Work Experience","Education","Technical Skills",
                "Accomplishments", "Profile Projects", "Personal Details"};
        private int[] listImage = {
                R.drawable.ic_dns_black_24dp,
                R.drawable.ic_account_circle_black_24dp,
                R.drawable.ic_work_black_24dp,
                R.drawable.ic_school_black_24dp,
                R.drawable.ic_border_color_black_24dp,
                R.drawable.ic_dashboard_black_24dp,
                R.drawable.ic_border_color_black_24dp,
                R.drawable.ic_dashboard_black_24dp
        };
        public NavigationMenuAdapter(Activity context) {
            this.mContext = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return listName.length;
        }

        @Override
        public Object getItem(int position) {
            return listName[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.navigation_listview, parent, false);
            }
            TextView nameTextView = (TextView) convertView.findViewById(R.id.commonNavigationItemTextView);
            nameTextView.setTypeface(Typeface.createFromAsset(mContext.getAssets(), APP_FONT));
            ImageView imageView = (ImageView) convertView.findViewById(R.id.commonNavigationItemImageView);
            nameTextView.setText(listName[position]);
            imageView.setImageResource(listImage[position]);
            return convertView;
        }
    }
}
