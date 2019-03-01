package com.example.kaans.harmony;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {
    TextView tv;
    SectionsPageAdapter sectionsPageAdapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().setBackgroundDrawable( new ColorDrawable(Color.parseColor("#6E5D5D")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#8F8484\">" + getString(R.string.app_name) + "</font>"));


        viewPager = findViewById(R.id.container);
        setupPager(viewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.homeicon);
        tabLayout.getTabAt(1).setIcon(R.drawable.matchicon);
        tabLayout.getTabAt(2).setIcon(R.drawable.profileicon);



        Intent inte=getIntent();

    }
    private void setupPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentHome(),"Home");
        adapter.addFragment(new FragmentMatch(),"Match");
        adapter.addFragment(new FragmentProfile(),"Profile");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId()==R.id.log_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent=new Intent(this,LogIn.class);
            startActivity(intent);
        }

        return true;
    }
}
