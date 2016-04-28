package britowellington.trickbmx.entidade;

import android.database.DataSetObserver;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

/**
 * Created by Were on 04/04/2016.
 */
public class Manobra implements Parcelable {
    private int id;
    protected String nome;
    protected String descricao;
    protected String dica;

    public Manobra(){

    }

    protected Manobra(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        descricao = in.readString();
        dica = in.readString();
    }

    public static final Creator<Manobra> CREATOR = new Creator<Manobra>() {
        @Override
        public Manobra createFromParcel(Parcel in) {
            return new Manobra(in);
        }

        @Override
        public Manobra[] newArray(int size) {
            return new Manobra[size];
        }
    };

    @Override
    public String toString() {
        return nome  ;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeString(descricao);
        dest.writeString(dica);
    }
}