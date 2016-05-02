package britowellington.trickbmx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import britowellington.trickbmx.entidade.Manobra;
import britowellington.trickbmx.persistencia.ManobrasDao;

public class Tab2 extends Activity {

    private ListView mListView;
    private List<Manobra> listaManobras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        // Ligacao dos componentes de TELA a lista da Activity
        mListView = (ListView) findViewById(R.id.listViewManobras);

        registerForContextMenu(mListView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                intent = new Intent(getBaseContext(), Detalhes.class);
                intent.putExtra("manobra", listaManobras.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        try {
            super.onResume();
            this.carregarLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Método para carregar a lista................................................
    public void carregarLista() throws Exception {
        ArrayAdapter<Manobra> adapter;
        int adapterLayout = android.R.layout.simple_list_item_1;

        ManobrasDao manobrasDao = new ManobrasDao(this);
        this.listaManobras = manobrasDao.listar();

        // O objeto ArrayAdapter sabe converter listas ou vetores em View
        adapter = new ArrayAdapter<Manobra>(this, adapterLayout, listaManobras);

        // Associacao do Adapter a ListView
        this.mListView.setAdapter(adapter);
        registerForContextMenu(this.mListView);
    }


    // método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_listview, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        int id = item.getItemId();

        if (id == R.id.action_Menu_Apagar) {
            ManobrasDao m = new ManobrasDao();
            m.deletar(listaManobras.get(info.position));
        }

        if (id == R.id.action_Menu_Alterar) {
            return true;
        }

        if (id == R.id.action_Menu_Detalhes) {
            Intent intent;
            intent = new Intent(getBaseContext(), Detalhes.class);
            intent.putExtra("manobra", listaManobras.get(info.position));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
        //return true;
    }
}
