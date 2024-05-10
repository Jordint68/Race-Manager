package org.milaifontanals.racemanager.ui.infoCursa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.milaifontanals.racemanager.R;
import org.milaifontanals.racemanager.databinding.FragmentInfoCursaBinding;
import org.milaifontanals.racemanager.modelsJson.Cursa;

public class infoCursaFragment extends Fragment {
    private Cursa cursa;
    private FragmentInfoCursaBinding mBinding;


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentInfoCursaBinding.inflate(getLayoutInflater());

        mostrarDades();

        return mBinding.getRoot();
    }
}