package xeronic.org.majja.p1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Xeronic on 15-09-14.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "p1.db";
    private static final String TABLE_NAME = "transactions";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_CATEGORY = "category";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addTransaction(Transaction transaction) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHandler.COLUMN_TITLE,transaction.getTitle());
        values.put(DBHandler.COLUMN_AMOUNT,transaction.getAmount());
        values.put(DBHandler.COLUMN_DATE, transaction.getDate().toString());
        values.put(DBHandler.COLUMN_CATEGORY, transaction.getCategory());
        db.insert(DBHandler.TABLE_NAME, "", values);
    }

    public Transaction[] getAllTransactions() {
        int id_index, title_index, amount_index, date_index, category_index;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHandler.TABLE_NAME +
                " ORDER BY " + DBHandler.COLUMN_DATE,null);
        Transaction[] transactions = new Transaction[cursor.getCount()];

        id_index = cursor.getColumnIndex(DBHandler.COLUMN_ID);
        title_index = cursor.getColumnIndex(DBHandler.COLUMN_TITLE);
        amount_index = cursor.getColumnIndex(DBHandler.COLUMN_AMOUNT);
        date_index = cursor.getColumnIndex(DBHandler.COLUMN_DATE);
        category_index = cursor.getColumnIndex(DBHandler.COLUMN_CATEGORY);

        for(int i=0; i<transactions.length; i++) {
            cursor.moveToPosition(i);
            transactions[i] = new Transaction(
                    cursor.getString(title_index),
                    cursor.getInt(amount_index),
                    new java.util.Date(cursor.getLong(date_index)),
                    cursor.getString(category_index));
        }
        return transactions;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AMOUNT + " INTEGER, " +
                COLUMN_DATE + " DATE, " +
                COLUMN_CATEGORY + " TEXT "
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
