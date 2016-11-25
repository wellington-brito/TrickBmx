package britowellington.trickbmx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import britowellington.trickbmx.entidade.Manobra;
import britowellington.trickbmx.persistencia.ManobrasDao;

public class Alterar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivityAlterarManobra);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                salvar(view);
            }
        });

        final Manobra m = (Manobra) getIntent().getSerializableExtra("manobra");
        getSupportActionBar().setTitle("Editar");
        TextView txtNomeManobra = (TextView) findViewById(R.id.editTextNomeManobra);
        TextView txtDescricao = (TextView) findViewById(R.id.editTextDescricao);
        txtNomeManobra.setText(m.getNome());
        txtDescricao.setText(m.getDescricao());

    }

    public void salvar(View view) {

        Manobra manobra = new Manobra();
        ManobrasDao manobrasDAO = new ManobrasDao(this);

        // Recuperando os dado inseridos
        EditText edtNome = (EditText) findViewById(R.id.editTextNomeManobra);
        EditText edtDescricao = (EditText) findViewById(R.id.editTextDescricao);

        manobra.setNome(edtNome.getText().toString());
        manobra.setDescricao(edtDescricao.getText().toString());

        manobrasDAO.alteraRegistro(manobra);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Manobra Alterada com Sucesso!").setTitle("Editar Manobra");
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener()
                {
                    public void onClick (DialogInterface dialog,int id){
                        dialog.dismiss();
                    }
                }
        );

        AlertDialog dialog = builder.create();
        dialog.show();
        edtNome.setText("");
        edtDescricao.setText("");
    }

}
