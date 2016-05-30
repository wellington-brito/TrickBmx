package britowellington.trickbmx.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import britowellington.trickbmx.Detalhes;
import britowellington.trickbmx.R;
import britowellington.trickbmx.entidade.Manobra;
import britowellington.trickbmx.persistencia.ManobrasDao;


public class OneFragment extends Fragment {

    ListView mListView;
    private List<Manobra> listaManobras;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override  // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
         public void onResume() {
        try {
            super.onResume();
            this.carregarLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void carregarLista(){

        ArrayAdapter<Manobra> adapter;
        int adapterLayout = android.R.layout.simple_list_item_1;
        ManobrasDao manobrasDao = new ManobrasDao(this.getContext());

        try {
            this.listaManobras = manobrasDao.listar();
            adapter = new ArrayAdapter<Manobra>(this.getContext(), adapterLayout, listaManobras);
            mListView = (ListView)getActivity().findViewById(R.id.listViewManobras);
            this.mListView.setAdapter(adapter);
            registerForContextMenu(mListView);                                                   /// registrar a listview no menu de conteexto senão o menus de opções não carrega
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

     //método para carregar o menu de opçoes no item da listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        menu.setHeaderTitle(listaManobras.get(info.position).getNome());
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_listview, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        int id = item.getItemId();

        if (id == R.id.action_Menu_Apagar) {
            ManobrasDao m = new ManobrasDao(this.getContext());
            Manobra manobra = listaManobras.get(info.position);
            m.deletar(manobra.getId());

        }

        if (id == R.id.action_Menu_Alterar) {
            return true;
        }

        if (id == R.id.action_Menu_Detalhes) {
            Intent intent;
            intent = new Intent(this.getContext(), Detalhes.class);
            intent.putExtra("manobra", listaManobras.get(info.position));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
        //return true;
    }
}