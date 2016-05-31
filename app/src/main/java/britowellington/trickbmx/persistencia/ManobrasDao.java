package britowellington.trickbmx.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import britowellington.trickbmx.entidade.Manobra;

/**
 * Created by Were on 04/04/2016.
 */
public class ManobrasDao {

        private Context contexto;
        private static final String TABELA_MANOBRA = "manobra";
        private static final String SQL_SELECT_TODOS = "SELECT + FROM manobra";
        private BDutil util;
        private SQLiteDatabase db;

        public ManobrasDao(Context context){
            this.contexto =  context;
            this.util = new BDutil(context);
        }

        public void inserir(Manobra manobra){
            ContentValues values = new ContentValues();

            values.put("nome",manobra.getNome());
            values.put("descricao", manobra.getDescricao());
            values.put("dica", manobra.getDica());
           
            BDutil bdUtil = new BDutil(contexto);
            bdUtil.getWritableDatabase().insert(TABELA_MANOBRA,null,values);
            bdUtil.close();
        }
        
    public List<Manobra> listar() throws Exception{

        List<Manobra> lista = new ArrayList<Manobra>();
        
        String sql = "Select nome, descricao from manobra order by nome";

        BDutil bdUtil = new BDutil(contexto);
        Cursor cursor = bdUtil.getReadableDatabase().rawQuery(sql, null);

        try {
            while (cursor.moveToNext()) {
                Manobra manobra = new Manobra();
                manobra.setNome(cursor.getString(0));
                manobra.setDescricao(cursor.getString(1));
                lista.add(manobra);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            cursor.close();
            bdUtil.close();
        }
        return lista;
    }

    //Método deletar
    public void deletar(int id_manobra){
        db = util.getReadableDatabase();
        String where = " _id = "+ String.valueOf(id_manobra);
        db.delete(TABELA_MANOBRA, where, null );
        Toast.makeText(this.contexto, "Feito!", Toast.LENGTH_LONG).show();
        db.close();
    }
}
