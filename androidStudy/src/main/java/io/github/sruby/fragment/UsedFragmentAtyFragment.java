package io.github.sruby.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.sruby.androidStudy.R;

/**
 * A placeholder fragment containing a simple view.
 *教程中需要对aty内部类进行手工的重构
 */
public class UsedFragmentAtyFragment extends Fragment implements View.OnClickListener
{

    public UsedFragmentAtyFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_used_fragment_aty, container, false);

        rootView.findViewById(R.id.btnOpenAnother).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnOpenAnother:
                getFragmentManager().beginTransaction().replace(R.id.anoFragment,new AnoFragment()).commit();
        }
    }
}
