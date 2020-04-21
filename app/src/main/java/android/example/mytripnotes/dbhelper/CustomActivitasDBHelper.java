package android.example.mytripnotes.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CustomActivitasDBHelper extends SQLiteOpenHelper {
    public CustomActivitasDBHelper(@Nullable Context context) {
        super(context, "activitas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String activitas = "create table tb_activitas(id integer primary key autoincrement, activitas text)";
        sqLiteDatabase.execSQL(activitas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_activitas");
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
}
