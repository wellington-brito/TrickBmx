package britowellington.trickbmx;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;


public class TelaPrincipal extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TelaPrincipal.this,NovaManobra.class);
                startActivity(i);
            }
        });

        // pegando o id da tab host da view
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);

        // criar as abas da tabhost
        TabHost.TabSpec tab1 = tabHost.newTabSpec("Primeira Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Segunda Tab");
       // TabHost.TabSpec tab3 = tabHost.newTabSpec("Terceira tab");

        // titulo ou icone da tabhost na view
        tab1.setIndicator("Treinos");
        //tab1.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher)); // icone na tabe host ao inves do nome
        tab1.setContent(new Intent(this, Tab1.class));

        // titulo ou icone da tabhost na view
        tab2.setIndicator("Manobras");
        //tab1.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher)); // icone na tabe host ao inves do nome
        tab2.setContent(new Intent(this, Tab2.class));

        // titulo ou icone da tabhost na view
       // tab3.setIndicator("Dicas");
        //tab1.setIndicator("", getResources().getDrawable(R.mipmap.ic_launcher)); // icone na tabe host ao inves do nome
        //tab3.setContent(new Intent(this, Tab3.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        //tabHost.addTab(tab3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        MenuItem m1 = menu.add(0, 0, 0,"Item 1");
        // m1.setIcon(R.mipmap.ic_launcher);
        m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem m2 = menu.add(0, 1, 1,"Item 2");
        //m1.setIcon(R.mipmap.ic_launcher);
        m2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);


        MenuItem m3 = menu.add(0, 2, 2,"Item 3");
        //m1.setIcon(R.mipmap.ic_launcher);
        m3.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        return(true);
    }

    @Override
    public boolean onMenuItemSelected(int panel, MenuItem item){
        switch (item.getItemId()){
            case 0:

                Toast.makeText(this, "Item1 selecionado", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this,"Item2 selecionado", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"Item3 selecionado", Toast.LENGTH_SHORT).show();
                break;

        }
        return(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
