package vn.binhld.hola.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import vn.binhld.hola.R;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout = null;
    ViewPager viewPager = null;
    MyAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager); // rang buoc tabLayout voi viewPager

        myAdapter = new MyAdapter(getSupportFragmentManager()); // can co FragmentManager moi tao duoc MyAdapter
        viewPager.setAdapter(myAdapter); // rang buoc viewPager voi myAdapter
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}