package com.health.projetofilme.Media_Activity.Overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.health.projetofilme.MediaActivity;
import com.health.projetofilme.R;

public class Overview extends Fragment {



    public Overview(){}


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.overview, container, false);

        TextView overview  = v.findViewById(R.id.overview);
        TextView originaltitle  = v.findViewById(R.id.original_title);
        TextView originallang  = v.findViewById(R.id.original_language);
        TextView budget  = v.findViewById(R.id.budget);
        TextView revenue  = v.findViewById(R.id.revenue);
        TextView homepage  = v.findViewById(R.id.homepage);
        TextView productions  = v.findViewById(R.id.productions);
        TextView releases  = v.findViewById(R.id.releases);
        overview.setText(((MediaActivity) getActivity()).getoverview());
        originaltitle.setText(((MediaActivity) getActivity()).getOriginal_title());
        originallang.setText(((MediaActivity) getActivity()).getOriginal_lang());
        budget.setText(((MediaActivity) getActivity()).getBudget());
        revenue.setText(((MediaActivity) getActivity()).getRevenue());
        homepage.setText(((MediaActivity) getActivity()).getHomepage());
        productions.setText(((MediaActivity) getActivity()).getProductions());
        releases.setText(((MediaActivity) getActivity()).getReleases());




        return v;
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }
}