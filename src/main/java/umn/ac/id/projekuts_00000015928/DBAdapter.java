package umn.ac.id.projekuts_00000015928;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBAdapter extends SQLiteOpenHelper {
    // path to the database folder
    public static String DB_PATH;
    // database filename
    public static String DATABASE_NAME;
    public SQLiteDatabase db;
    public Context context;
    private static final String TABLE_BOOKS = "books";
    public static final String COLUMN_ASIN = "asin";
    public static final String COLUMN_GRUP = "grup";
    public static final String COLUMN_FORMAT = "format";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_PUBLISHER = "publisher";

    //COLUMN_ASIN, COLUMN_GRUP, COLUMN_FORMAT, COLUMN_TITLE, COLUMN_AUTHOR, COLUMN_PUBLISHER

    public DBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("debug", "test 1");
        //Write full path to the database of your application
        String packageName = context.getPackageName();
        Log.d("debug", "test 2");
        DB_PATH = "/data/data/" + packageName + "/databases/";
        DATABASE_NAME = name;
        Log.d("debug", "test 3");
        this.context = context;
        Log.d("debug", "test 4");
        createDatabase();
        openDatabase();
        Log.d("debug", "test 5");
    }

    public SQLiteDatabase openDatabase() {
        Log.d("debug", "test 6");
        String path = DB_PATH + DATABASE_NAME;
        Log.d("debug", "path: " + path);
        if (db == null) {
            Log.d("debug", "test 7");
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
            db.disableWriteAheadLogging();
            Log.d("debug", "test 8");
        }
        return db;
    }

    // create database if it's not created yet
    public void createDatabase() {
        Log.d("debug", "test 9");
        boolean dbExist = checkDatabase();
        Log.d("debug", "test 10");
        if (!dbExist) {
            Log.d("debug", "test 11");
            this.getReadableDatabase();
            Log.d("debug", "test 12");
            try {
                Log.d("debug", "test 13");
                copyDatabase();
            } catch (IOException e) {
                Log.d("debug", "test 14");
                Log.e(this.getClass().toString(), "Copying error");
                throw new Error("Eror copying database");
            }
        } else {
            Log.d("debug", "test 15");
            Log.i(this.getClass().toString(), "Database already exists");
        }
        Log.d("debug", "test 16");
    }

    private boolean checkDatabase() {
        Log.d("debug", "test 17");
        String path = DB_PATH + DATABASE_NAME;
        Log.d("debug", "test 18");
        File dbfile = new File(path);
        Log.d("debug", "test 19");
        Log.d("debug", "file exists: " + dbfile.exists());
        return dbfile.exists();
    }

    // method to copy the database
    private void copyDatabase() throws IOException {
        Log.d("debug", "test 20");
        // open a stream for reading from the ready made database
        // the stream source is located in the assets
        InputStream externalDBStream = context.getAssets().open(DATABASE_NAME);
        Log.d("debug", "test 21");
        // path to the created empty database on your android device
        String outFileName = DB_PATH + DATABASE_NAME;
        Log.d("debug", "test 22");
        // create a stream for waiting the database byte by byte
        OutputStream localDBStream = new FileOutputStream(outFileName);
        Log.d("debug", "test 23");
        // copying the database
        byte[] buffer = new byte[1024];
        Log.d("debug", "test 24");
        int bytesRead;
        while ((bytesRead = externalDBStream.read(buffer)) > 0) {
            Log.d("debug", "test 25");
            localDBStream.write(buffer, 0, bytesRead);
        }
        Log.d("debug", "test 26");
        localDBStream.flush();
        Log.d("debug", "test 27");
        // close the stream
        Log.d("debug", "test 28");
        externalDBStream.close();
        Log.d("debug", "test 29");
    }

    @Override
    public synchronized void close() {
        if (db != null) {
            Log.d("debug", "test 30");
            db.close();
        }
        super.close();
    }

    // retrieves all s
    public Cursor getAllProducts() {
        Log.d("debug", "test 31");
        Log.d("debug", TABLE_BOOKS);
        /*Cursor cursor = db.query(
                TABLE_BOOKS,
                new String[]{COLUMN_ASIN, COLUMN_GRUP, COLUMN_FORMAT, COLUMN_TITLE, COLUMN_AUTHOR, COLUMN_PUBLISHER},
                null,null,null,null, null);*/
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);
        Log.d("debug", "test 32");
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
