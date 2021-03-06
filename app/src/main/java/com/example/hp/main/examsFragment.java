package com.example.hp.main;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class examsFragment extends Fragment {
    String srmUrl , vitUrl, iitUrl;
    private AVLoadingIndicatorView mProgressBar;
    private TextView mAboutUs, mAboutUsBody;


    public examsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_view, container, false);
        vitUrl = "www.vit.ac.in/admissions/viteee/";
        srmUrl = "www.srmuniv.ac.in/";
        iitUrl = "jeemain.nic.in/";

        mProgressBar = (AVLoadingIndicatorView) rootView.findViewById(R.id.loader_indicator);
//        mProgressBar.setVisibility(View.GONE);
//        mAboutUs = (TextView)rootView.findViewById(R.id.about_us);
//        mAboutUsBody = (TextView)rootView.findViewById(R.id.about_us_body);
        stopAnim();


        final ArrayList<topHundred.Exams> exams = new ArrayList<>();
        exams.add(new topHundred.Exams("SRMJEE", Uri.parse("http://www.srmuniv.ac.in/")));
        exams.add(new topHundred.Exams("VITEEE", Uri.parse("http://www.vit.ac.in/admissions/viteee/")));
        exams.add(new topHundred.Exams("JEE Mains", Uri.parse("http://jeemain.nic.in/")));
        exams.add(new topHundred.Exams("BITSAT ", Uri.parse("http://bitsadmission.com/bitsatmain.aspx")));
        exams.add(new topHundred.Exams("JEE Advanced", Uri.parse("http://jeemain.nic.in/")));
        exams.add(new topHundred.Exams("CET Bangalore", Uri.parse("http://kea.kar.nic.in/cet_2017.htm")));


        examsAdapter Adapter = new examsAdapter(getActivity(), exams);
        ListView listView = (ListView)rootView.findViewById(R.id.list);
        listView.setAdapter(Adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                topHundred.Exams exam = exams.get(position);
                Uri uri = exam.getUrl();

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        return rootView;
    }
    void stopAnim(){
        mProgressBar.hide();
        // or avi.smoothToHide();
    }

}
