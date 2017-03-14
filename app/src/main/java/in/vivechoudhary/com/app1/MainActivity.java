package in.vivechoudhary.com.app1;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_main, "My Activity Name");
    }

    @Override
    public void initViews() {

    }

    @Override
    public void setFonts() {

    }

}
