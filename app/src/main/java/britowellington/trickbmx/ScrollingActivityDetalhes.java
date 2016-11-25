package britowellington.trickbmx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import britowellington.trickbmx.entidade.Manobra;

public class ScrollingActivityDetalhes extends AppCompatActivity {
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_activity_detalhes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });

       final Manobra m = (Manobra) getIntent().getSerializableExtra("manobra");
       getSupportActionBar().setTitle(m.getNome());
       TextView txtNomeManobra = (TextView) findViewById(R.id.nomeManobra);
       txtNomeManobra.setText("Descrção: \n"+ m.getDescricao()+"\n\nDica: \n"+m.getDica());

    }

}
