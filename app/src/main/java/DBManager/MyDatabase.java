package DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Satyam Poddar on 02-Apr-16.
 */

public class MyDatabase {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "user_name";
    public static final String KEY_NUMBER = "user_number";
    public static final String KEY_LOCATION = "user_location";

    private static final String DATABASE_NAME = "login";
    private static final String DATABASE_TABLE = "usertable";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DbHelper extends SQLiteOpenHelper {


        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(
                    "CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_NAME + " TEXT NOT NULL, " +
                            KEY_NUMBER + " TEXT NOT NULL"+
                            KEY_LOCATION + "TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

    }

    public MyDatabase(Context c) {
        ourContext = c;
    }

    public MyDatabase openandwrite() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }

    public long createEntry(String name, String number,String location) {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_NUMBER, number);
        cv.put(KEY_LOCATION,location);
        Log.d("hello", name + " " + number+ " " +location);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public Object[] getData(int ch) {
        // TODO Auto-generated method stub

        ArrayList<String> location = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> number = new ArrayList<String>();
        //String result1 = "";
        String[] s = new String[]{ KEY_ROWID, KEY_NAME, KEY_NUMBER,KEY_LOCATION};
        Cursor c = ourDatabase.query(DATABASE_TABLE, s, null, null, null, null, null);
        //int iRow = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int iNumber = c.getColumnIndex(KEY_NUMBER);
        int iLocation = c.getColumnIndex(KEY_LOCATION);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
//            if(ch == 0)
//                result.add("\n" + c.getString(iName)+"\n");
            name.add(c.getString(iName));
            number.add(c.getString(iNumber));
            location.add(c.getString(iLocation));
            // = result + /*c.getString(iRow) +*/ " " + c.getString(iName);
            //result1 = result1 + /*c.getString(iRow) +*/ " " + c.getString(iAddress);
            //result1[i] = c.getString(iAddress);
        }
        //if(ch == 0)
        //v.setBackgroundColor(Color.parseColor("#a1cc3b"));

        Object[] obj = {name, number,location};

        return obj;



    }

    public void deleteTitleGivenName(String myName)
    {
        //return ourDatabase.delete(DATABASE_TABLE, KEY_NAME + "=?", new String[] { myName });
        ourDatabase.delete(DATABASE_TABLE, KEY_NAME + "=?", new String[] { myName });
        Log.d("data deleted = ", "yes");
        //ourDatabase.delete(DATABASE_TABLE, KEY_NAME+"="+myName , null);
    }
}

