package strathmore.com.trialdatabase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        DatabaseHandler db = new DatabaseHandler(this);
        //Inserting users
        Log.d("Insert: ", "Insert...");

        db.addUser(new Users(1,"Wiltord","0789008904","plspro"));

        //Reading all contacts
        Log.d("Reading: ", "Reading.....");
        List<Users> user = db.getAllUsers();

        for(Users u: user){
            String log = "Id"+ u.getUser_id()+ " ,Name: " + u.getUser_name() +" ,Phone"+ u.getPhonenumber() +" ,Password: "+u.getPassword();
            //Writing contacts to log
            Log.d("Name", log);
        }

        //Inserting users
        Log.e("Insert: ", "Insert...");

        db.addBusCompany(new BusCompany(1,"Wagwaan company"));

        //Reading all contacts
        Log.e("Reading: ", "Reading.....");
        List<BusCompany>company = db.getAllBusCompanies();

        for(BusCompany c: company){
            String bus = "Id"+ c.getBus_company_id()+ " ,Name: " + c.getBus_company_name();
            //Writing contacts to log
            Log.e("Work", bus);
        }






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
