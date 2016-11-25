package britowellington.trickbmx;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import britowellington.trickbmx.entidade.Manobra;
import britowellington.trickbmx.persistencia.ManobrasDao;

public class NovaManobra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_manobra);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarActivityNovaManobra);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                salvar(view);
            }
        });
    }

    public void salvar(View view) {
        // instanciando objetos
        Manobra manobra = new Manobra();
        ManobrasDao manobrasDAO = new ManobrasDao(this);

        // Recuperando os dado inseridos
        EditText edtNome = (EditText) findViewById(R.id.editTextNomeManobra);
        EditText edtDescricao = (EditText) findViewById(R.id.editTextDescricao);
        // EditText edtTelefone = (EditText) findViewById(R.id.txtTelefone);

        manobra.setNome(edtNome.getText().toString());
        manobra.setDescricao(edtDescricao.getText().toString());
        //pessoa.setTelefone(edtTelefone.getText().toString());

        manobrasDAO.inserir(manobra);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Manobra Salva com Sucesso!").setTitle("Nova Manobra");
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
