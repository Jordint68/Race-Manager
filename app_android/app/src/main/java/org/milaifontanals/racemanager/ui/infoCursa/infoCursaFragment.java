package org.milaifontanals.racemanager.ui.infoCursa;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.milaifontanals.racemanager.API.APIManager;
import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.adapters.CategoriesAdapter;
import org.milaifontanals.racemanager.adapters.CircuitsAdapter;
import org.milaifontanals.racemanager.databinding.FragmentInfoCursaBinding;
import org.milaifontanals.racemanager.modelsJson.Circuit;
import org.milaifontanals.racemanager.modelsJson.Cursa;
import org.milaifontanals.racemanager.modelsJson.ResponseGetCircuits;
import org.milaifontanals.racemanager.selectedListeners.ICategoriaSelectedListener;
import org.milaifontanals.racemanager.selectedListeners.ICircuitSelectedListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class infoCursaFragment
        extends Fragment
        implements ICircuitSelectedListener, ICategoriaSelectedListener {
    private Cursa cursa;
    private FragmentInfoCursaBinding mBinding;
    private CircuitsAdapter mCircuitsAdapter;
    private CategoriesAdapter mCategoriesAdapter;

    private Context mContext;

    private List<Circuit> lCircuits = new ArrayList<>();

    private infoCursaFragment thisFragment = this;


    private Circuit circuitSeleccionat = null;
    private String categoriaSeleccionada =  null;


    public infoCursaFragment() {
        // Required empty public constructor
    }
    public static infoCursaFragment newInstance(String param1, String param2) {
        infoCursaFragment fragment = new infoCursaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            cursa = (Cursa) bundle.getSerializable("Cursa");
        }
    }

    private void mostrarDades() {
        String imgUrl = cursa.getFoto().toString();
        ImageLoader.getInstance().displayImage(imgUrl, mBinding.imvFoto);
        mBinding.txvNom.setText(cursa.getNom());
        mBinding.txvLloc.setText(cursa.getLloc());
        mBinding.txvDataInici.setText(cursa.getDataInici());
        mBinding.txvDataFi.setText(cursa.getDataFi());
        mBinding.txvLink.setText(cursa.getWeb());

//        mBinding.btnParticipar.setVisibility(View.INVISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentInfoCursaBinding.inflate(getLayoutInflater());

        mContext = this.getContext();

        mostrarDades();

        carregar_cc();

        return mBinding.getRoot();
    }

    /*
        Funció per a carregar les dades dels circuits i les categories
     */
    private void carregar_cc() {
        APIManager.getInstance().getCircuits(Integer.parseInt(cursa.getId()), new Callback<ResponseGetCircuits>() {
            @Override
            public void onResponse(Call<ResponseGetCircuits> call, Response<ResponseGetCircuits> response) {
                lCircuits = response.body().getCircuits();

                mBinding.rcvCircuits.setLayoutManager(new LinearLayoutManager(infoCursaFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
                mBinding.rcvCircuits.setHasFixedSize(true);
                mCircuitsAdapter = new CircuitsAdapter(mContext, lCircuits, thisFragment);
                mBinding.rcvCircuits.setAdapter(mCircuitsAdapter);

                omplirCategories(lCircuits.get(0).getCategories());
            }

            @Override
            public void onFailure(Call<ResponseGetCircuits> call, Throwable t) {
                Log.e("XXX", t.toString());
            }
        });
    }

    @Override
    public void onCircuitSelected(Circuit c) {
        circuitSeleccionat = c;

        List<String> lCats = circuitSeleccionat.getCategories();
//        omplirCategories(lCats);

        mostrarBoto();
    }

    private void mostrarBoto() {
        if ((circuitSeleccionat != null) && (categoriaSeleccionada != null)) {
            Log.d("XXX", "Setting btnParticipar to VISIBLE");
            mBinding.btnParticipar.setVisibility(View.VISIBLE);
            Log.d("XXX", String.valueOf(mBinding.btnParticipar.getVisibility()));
        }
    }

    @Override
    public void onCategoriaSelected(String c) {
        categoriaSeleccionada = c;

        mostrarBoto();
    }


    private void omplirCategories(List<String> lCats) {
        mBinding.rcvCategories.setLayoutManager(new LinearLayoutManager(infoCursaFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.rcvCategories.setHasFixedSize(true);
        mCategoriesAdapter = new CategoriesAdapter(mContext, lCats, thisFragment);
        mBinding.rcvCategories.setAdapter(mCategoriesAdapter);
    }
}