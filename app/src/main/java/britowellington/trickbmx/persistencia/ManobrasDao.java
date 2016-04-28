package britowellington.trickbmx.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import britowellington.trickbmx.entidade.Manobra;

/**
 * Created by Were on 04/04/2016.
 */
public class ManobrasDao {

        private Context contexto;
        private static final String TABELA_MANOBRA="manobra";
        private static final String SQL_SELECT_TODOS = "SELECT + FROM manobra";
        private SQLiteDatabase bd;
        //Construtor- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        public ManobrasDao(Context context){
            this.contexto =  context;
        }
        //Construtor para o método deletarManobra da classe Tab2 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -- - - - - - - - - - -
        public ManobrasDao(){

        }
        //Método Inserir- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        public void inserir(Manobra manobra){
            //Objeto para armazenar os valores dos campos
            ContentValues values = new ContentValues();

            //Definição de valores dos campos das tabelas - - - - - - - - - - - - - - - - - - - - - - -
            values.put("nome",manobra.getNome());
            values.put("descricao", manobra.getDescricao());
            values.put("dica", manobra.getDica());
            // values.put("pathFoto",pessoa.getPathFoto());

            BDutil bdUtil = new BDutil(contexto);
            bdUtil.getWritableDatabase().insert(TABELA_MANOBRA,null,values);
            bdUtil.close();
        }

    // Método Listar................................................................................
    public List<Manobra> listar() throws Exception{

        // Definição da lista de Manobras
        List<Manobra> lista = new ArrayList<Manobra>();

        // Definição da Instrução SQL
        String sql = "Select nome, descricao from manobra order by nome";

        // Objeto que recebe os registros do banco de dados
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
            // fecha a conexão com o banco
            cursor.close();
            bdUtil.close();
        }
        return lista;
    }

    //Método deletar
    public void deletar(Manobra manobra){
        bd.delete("manobra", "_id = " + manobra.getId(), null);
        Toast.makeText(this.contexto,"Deletado com sucesso!!", Toast.LENGTH_LONG).show();
    }
}
