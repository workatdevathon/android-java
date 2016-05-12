package com.example.crypsis.send;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NavigationDraw extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView listView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    String string="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navdrawer);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        string=(String)getTitle();
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        listView=(ListView)findViewById(R.id.left_drawer);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.drawable.menu,R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(string);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Select a Planet");
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.drawer_list_item,getResources().getStringArray(R.array.planets_array));
        listView.setAdapter(arrayAdapter);
        ActionBar actionBar=getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] planets=getResources().getStringArray(R.array.planets_array);
                string=planets[position];
               switch (position)
               {
                   case 0:  Dialog d=new Dialog(NavigationDraw.this);
                       d.setContentView(R.layout.dialog);
                       d.show();
                       break;
                   case 1:
                       Toast.makeText(NavigationDraw.this,"hello1",Toast.LENGTH_SHORT).show();
                       break;
                   default:
                       Toast.makeText(NavigationDraw.this,"hello2",Toast.LENGTH_SHORT).show();

               }
//                PlanetFragment planetFragment=new PlanetFragment();
//                Bundle bundle=new Bundle();
//                bundle.putInt("position", position);
//                planetFragment.setArguments(bundle);
//                FragmentManager fragmentManager=getFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.content_frame,planetFragment);
//                fragmentTransaction.commit();
                drawerLayout.closeDrawer(listView);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();

        if (id == R.id.add) {
            Dialog d=new Dialog(NavigationDraw.this);
            d.setContentView(R.layout.dialog);
            d.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen=drawerLayout.isDrawerOpen(listView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
}