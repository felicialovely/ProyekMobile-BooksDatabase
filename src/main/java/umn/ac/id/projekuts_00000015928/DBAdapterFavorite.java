package umn.ac.id.projekuts_00000015928;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapterFavorite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static String DB_NAME = "books.db";

    private static final String TABLE_BOOKS = "favoritebooks";

    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_AUTHOR ="AUTHOR";

    SQLiteDatabase mDB;

    public DBAdapterFavorite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,DB_NAME,factory,DATABASE_VERSION);
    }

    public void onOpen(){
        super.onOpen(mDB);
        mDB = this.getWritableDatabase();
    }

    @Override
    public synchronized void close(){super.close();}

    private void insertBooks (Product books){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE,books.getTitle());
        contentValues.put(COLUMN_AUTHOR,books.getAuthor());

        mDB.insert(TABLE_BOOKS,null,contentValues);
    }

    @Override
    public void onCreate (SQLiteDatabase mDB){
        String CREATE_FAVORITE = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + "("
                + COLUMN_TITLE + " TEXT NOT NULL, "
                + COLUMN_AUTHOR + " TEXT NOT NULL " + ")";
        mDB.execSQL(CREATE_FAVORITE);
    }

    public Cursor getAllBooks(){
        SQLiteDatabase mDB = this.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase mDB, int oldVersion, int newVersion){
        mDB.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(mDB);
    }
}

