package android.example.mytripnotes.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CustomActivitasDBHelper extends SQLiteOpenHelper {
    private String activitas = "create table tb_activitas(id integer primary key autoincrement, activitas text)";
    private String packing = "create table tb_packing(id integer primary key autoincrement, item text, id_activitas integer)";

    public CustomActivitasDBHelper(@Nullable Context context) {
        super(context, "activitas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(activitas);
        sqLiteDatabase.execSQL(packing);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_activitas");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_packing");
        onCreate(sqLiteDatabase);
    }

    public void insert(String activitas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("activitas", activitas);
        db.insert("tb_activitas", "id", values);
    }

    public void delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tb_activitas", "id = ?", new String[]{id});
    }

    public void insertPacking(String item, int id_activitas) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("item", item);
        values.put("id_activitas", id_activitas);
        db.insert("tb_packing", "id", values);
    }

    public void deletePacking(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tb_packing", "id = ?", new String[]{id});
    }

}
