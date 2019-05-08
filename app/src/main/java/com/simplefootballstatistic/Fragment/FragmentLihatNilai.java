package com.simplefootballstatistic.Fragment;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.simplefootballstatistic.ActivityMain;
import com.simplefootballstatistic.AdapterLihatNilai;
import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.R;
import com.simplefootballstatistic.Respon.Chart;
import com.simplefootballstatistic.Respon.Penilaian;
import com.simplefootballstatistic.Respon.RestPenilaian;
import com.simplefootballstatistic.Retrofit.ClientRetrofit;
import com.simplefootballstatistic.Retrofit.InterfaceTeam;
import com.simplefootballstatistic.Utils.LogTest;
import com.simplefootballstatistic.Utils.Session;
import com.simplefootballstatistic.Utils.XNameFormater;
import com.simplefootballstatistic.Views.ViewsActivityMain;
import com.simplefootballstatistic.Views.ViewsLihatNilai;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLihatNilai extends Fragment implements ViewsLihatNilai, OnChartValueSelectedListener {

    View view;
    ActivityMain activityMain;
    ViewsActivityMain viewsActivityMain;
    Session session;

    List<Penilaian> penilaian_prepare =  new ArrayList<>();
    DataTeam dataTeam;
    RecyclerView rv_penilaian;
    AdapterLihatNilai adapterLihatNilai;
    TextView tv_penilaian_nama_team;
    TextView tv_penilaian_nama_pelatih;
    int id_team;
    DownloadManager downloadManager;

    ProgressDialog progressDialog;

    public FragmentLihatNilai(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activityMain = (ActivityMain) getActivity();
        assert activityMain != null;
        this.viewsActivityMain = activityMain.getViews();
        view = inflater.inflate(R.layout.fragment_lihat_nilai, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        session = new Session(getContext());
        progressDialog = new ProgressDialog(getContext());
        rv_penilaian = view.findViewById(R.id.rv_penilaian);
        id_team = session.getSessionInteger("id_team", -1);
        dataTeam = new DataTeam();
        dataTeam.init(this);
        tv_penilaian_nama_team = view.findViewById(R.id.tv_penilaian_nama_team);
        tv_penilaian_nama_pelatih = view.findViewById(R.id.tv_penilaian_nama_pelatih);
        view.findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadPdf();
            }
        });
        view.findViewById(R.id.ivback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewsActivityMain.pindah(3);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        dataTeam.getPenilaianTim(getActivity(), String.valueOf(id_team));
        dataTeam.getChart(getActivity(), String.valueOf(id_team));
    }



    @Override
    public void setInputNilai(RestPenilaian restPenilaian) {
        penilaian_prepare = restPenilaian.getPenilaians();
        adapterLihatNilai = new AdapterLihatNilai(getActivity(), penilaian_prepare, this);
        rv_penilaian.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_penilaian.setAdapter(adapterLihatNilai);
        adapterLihatNilai.notifyDataSetChanged();
        tv_penilaian_nama_pelatih.setText(restPenilaian.getPenilaians().get(0).getNamaPelatih());
        tv_penilaian_nama_team.setText(restPenilaian.getPenilaians().get(0).getNamaTeam());
    }

    @Override
    public void onDataChart(Chart chart){
        List<BarEntry> barChartList = new ArrayList<>();
        HorizontalBarChart horizontalBarChart = view.findViewById(R.id.hz_chart);
        horizontalBarChart.setOnChartValueSelectedListener(this);
        horizontalBarChart.setDrawBarShadow(false);
        horizontalBarChart.setDrawValueAboveBar(true);
        horizontalBarChart.getDescription().setEnabled(false);

        ValueFormatter custom = new XNameFormater();
        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(9);
        xAxis.setValueFormatter(custom);

        barChartList.add(new BarEntry((float) 1, (float) chart.getKartuMerah()));
        barChartList.add(new BarEntry((float) 2, (float) chart.getKartuKuning()));
        barChartList.add(new BarEntry((float) 3, (float) chart.getThreeStep()));
        barChartList.add(new BarEntry((float) 4, (float) chart.getDobel()));
        barChartList.add(new BarEntry((float) 5, (float) chart.getSuspand()));
        barChartList.add(new BarEntry((float) 6, (float) chart.getFoul()));
        barChartList.add(new BarEntry((float) 7, (float) chart.getGoal()));
        barChartList.add(new BarEntry((float) 8, (float) chart.getAttack()));

        BarDataSet dataSet = new BarDataSet(barChartList, "Nilai");

        int startColor1 = activityMain.getResources().getColor(R.color.biru_tua);
        int startColor2 = activityMain.getResources().getColor(R.color.black);
        int startColor3 = activityMain.getResources().getColor(R.color.biru_muda);
        int startColor4 = activityMain.getResources().getColor(R.color.yellow);
        int startColor5 = activityMain.getResources().getColor(R.color.red);

        List<Integer> color = new ArrayList<>();
        color.add(startColor5);
        color.add(startColor4);
        color.add(startColor3);
        color.add(startColor3);
        color.add(startColor2);
        color.add(startColor2);
        color.add(startColor1);
        color.add(startColor1);

        dataSet.setColors(color);

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(0.9f);
        horizontalBarChart.setData(barData);
        horizontalBarChart.setFitBars(true);
        horizontalBarChart.invalidate();

    }

    private void downloadPdf(){
        progressDialog.setMessage("Download file pdf, mohon tunggu sebentar");
        progressDialog.show();
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(getContext()).create(InterfaceTeam.class);
        Call<ResponseBody> call = interfaceTeam.downloadNilai(String.valueOf(id_team));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    LogTest.look("server contacted and has file");

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                            LogTest.look("file download was a success? " + writtenToDisk);
                            progressDialog.dismiss();
                            return null;
                        }
                    }.execute();
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Download error, Coba beberapa saat lagi", Toast.LENGTH_SHORT).show();
                    LogTest.look("server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Download error, Coba beberapa saat lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        String nama_team = tv_penilaian_nama_team.getText().toString();
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String todayString = formatter.format(todayDate);
        try {

            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!dir.exists()) {
                dir.mkdir();
            }

            LogTest.look("dir " + dir.exists());


            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(dir, File.separator + nama_team + "-" + todayString+".pdf");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            LogTest.look("lokasi file " + futureStudioIconFile.getPath() + " " + futureStudioIconFile.getAbsolutePath());

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    LogTest.look("file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();


                try
                {
                    Intent intentUrl = new Intent(Intent.ACTION_VIEW);
                    intentUrl.setDataAndType(Uri.fromFile(futureStudioIconFile), "application/pdf");
                    intentUrl.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentUrl);
                }
                catch (ActivityNotFoundException e)
                {
                    Toast.makeText(getContext(), "No PDF Viewer Installed", Toast.LENGTH_LONG).show();
                }
                return true;
            } catch (IOException e) {
                LogTest.look("error IOException 1 " + e.getMessage());
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            LogTest.look("error IOException 2 " + e.getMessage());
            return false;
        }
    }


    /*
    * Chart
    * */
    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
