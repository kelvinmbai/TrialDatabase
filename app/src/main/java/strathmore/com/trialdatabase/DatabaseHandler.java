package strathmore.com.trialdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilson on 13/12/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper
{

private static final int DATABASE_VERSION = 2;

private static final String DATABASE_NAME = "TrialDatbase";

//table user




    private static final String TABLE_USER = "User";

    private static final String TABLE_BUSCOMPANY = "BusCompany";


//Table users columns
  private static final String KEY_ID = "user_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";

//Table bus company columns
    private static final String KEY_BUS_CO_ID = "Bus_co_id";
    private static final String KEY_BUS_CO_NAME = "Bus_co_name";



    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT," + KEY_PHONE + " TEXT," + KEY_PASSWORD +" TEXT"+")";
        String CREATE_BUS_CO_TABLE = "CREATE TABLE " + TABLE_BUSCOMPANY + "(" + KEY_BUS_CO_ID + " INTEGER PRIMARY KEY," + KEY_BUS_CO_NAME + " TEXT"+")";

        db.execSQL(CREATE_BUS_CO_TABLE);
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUSCOMPANY);
        onCreate(db);
    }

    //1//Adding a new user
    public void addUser(Users user){
  SQLiteDatabase db = this.getWritableDatabase();

  ContentValues values = new ContentValues();
  values.put(KEY_USERNAME, user.getUser_name());
  values.put(KEY_PHONE, user.getPhonenumber());
  values.put(KEY_PASSWORD, user.getPassword());

  //insert a row
        db.insert(TABLE_USER,null,values);

    }

    //1/.1/Adding a new bus company
    public void addBusCompany(BusCompany company){
                  SQLiteDatabase db = this.getWritableDatabase();
                           ContentValues values = new ContentValues();
                           values.put(KEY_BUS_CO_NAME, company.getBus_company_name());
                          //insert a row
                           db.insert(TABLE_BUSCOMPANY,null,values);

    }





        //2//Getting a single user
        public Users getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[] { KEY_ID, KEY_USERNAME, KEY_PHONE, KEY_PASSWORD}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
          if(cursor != null)
              cursor.moveToFirst();
          Users user = new Users(Integer.parseInt(cursor.getString(0)),
                  cursor.getString(1),
                  cursor.getString(2),
                  cursor.getString(3)
          );
          //return user
        return user;
    }

    //2.1//Getting a single user
    public BusCompany getBusCompany(int bus){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BUSCOMPANY, new String[] { KEY_BUS_CO_ID, KEY_BUS_CO_NAME}, KEY_BUS_CO_ID + "=?", new String[] { String.valueOf(bus) }, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        BusCompany company = new BusCompany(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1)
                );
        //return user
        return company;
    }



    //3//Getting all users
    public List<Users> getAllUsers(){
        List<Users> usersList = new ArrayList<Users>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all rows adding to list

        if(cursor.moveToFirst()){
            do{
                Users user = new Users();
                user.setUser_id(Integer.parseInt(cursor.getString(0)));
                user.setUser_name(cursor.getString(1));
                user.setPhonenumber(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                usersList.add(user);
            }while(cursor.moveToNext());
        }

     return usersList;
    }

    //3//Getting all users
    public List<BusCompany>getAllBusCompanies(){
        List<BusCompany>companyList = new ArrayList<BusCompany>();
        String selectQuery = "SELECT * FROM " + TABLE_BUSCOMPANY;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        //looping through all rows adding to list

        if(cursor.moveToFirst()){
            do{
                BusCompany company = new BusCompany();
                company.setBus_company_id(Integer.parseInt(cursor.getString(0)));
                company.setBus_company_name(cursor.getString(1));
                companyList.add(company);
            }while(cursor.moveToNext());
        }

        return companyList;
    }


    //4//Getting users count
    public int getUsersCount(){
        String countQuery ="SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursors = db.rawQuery(countQuery, null);
        cursors.close();
        //return count
        return cursors.getCount();

    }
    //4.1//Getting buses count
    public int getBusCompanyCount(){
        String countQuery ="SELECT * FROM " + TABLE_BUSCOMPANY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursors = db.rawQuery(countQuery, null);
        cursors.close();
        //return count
        return cursors.getCount();

    }



    //5//Updating a single user
    public int updateUser(Users user){
 SQLiteDatabase db = this.getWritableDatabase();
 ContentValues values = new ContentValues();
 values.put(KEY_USERNAME, user.getUser_name());
 values.put(KEY_PHONE, user.getPhonenumber());
 values.put(KEY_PASSWORD, user.getPassword());

 //updating a row
        return db.update(TABLE_USER, values, KEY_ID + "+?", new String[] { String.valueOf(user.getUser_id())});


    }

    //5.1//Updating a single user
    public int updateBusCompany(BusCompany company){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BUS_CO_NAME, company.getBus_company_name());


        //updating a row
        return db.update(TABLE_BUSCOMPANY, values, KEY_BUS_CO_ID + "+?", new String[] { String.valueOf(company.getBus_company_id())});


    }




    //6//Deleting a single user
    public void deleteUser(Users user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + "=?", new String[] { String.valueOf(user.getUser_id())});
        db.close();


    }
    //6//Deleting a single user
    public void deleteBusCompany(BusCompany company){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BUSCOMPANY, KEY_BUS_CO_ID + "=?", new String[] { String.valueOf(company.getBus_company_id())});
        db.close();


    }





}
