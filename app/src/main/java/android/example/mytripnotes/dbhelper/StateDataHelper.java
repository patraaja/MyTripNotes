package android.example.mytripnotes.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StateDataHelper extends SQLiteOpenHelper {
    public StateDataHelper(@Nullable Context context) {
        super(context, "state.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String data = "create table tb_data(id integer primary key autoincrement, tujuan text, " +
                "tanggal text, " +
                "tipe text, " +
                "note text, " +
                "suhu text, " +
                "keadaan text)";
        sqLiteDatabase.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_data");
        onCreate(sqLiteDatabase);
    }

    public void insert(String tujuan, String tanggal, String tipe, String note, String suhu, String keadaan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tujuan", tujuan);
        values.put("tanggal", tanggal);
        values.put("tipe", tipe);
        values.put("note", note);
        values.put("suhu", suhu);
        values.put("keadaan", keadaan);
        db.insert("tb_data", "id", values);
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from tb_data");
    }
}
