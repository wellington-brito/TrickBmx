package britowellington.trickbmx.persistencia;

/**
 * Created by Were on 04/04/2016.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDutil extends SQLiteOpenHelper {

    private static final int VERSAO=1;
    private static final String TABELA_MANOBRA = "manobra";
    private static final String DATABASE = "db_manobra.db";


    public BDutil(Context context){
        super(context,DATABASE,null,VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddlManobra = "CREATE TABLE "+ TABELA_MANOBRA + " ( "
                +"[_id] INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"nome TEXT, "
                +"descricao TEXT, "
                +"dica TEXT) ";
        db.execSQL(ddlManobra);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
