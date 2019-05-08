package com.simplefootballstatistic;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.simplefootballstatistic.Data.DataTeam;
import com.simplefootballstatistic.Respon.Chart;
import com.simplefootballstatistic.Respon.Penilaian;
import com.simplefootballstatistic.Respon.RestPenilaian;
import com.simplefootballstatistic.Retrofit.ClientRetrofit;
import com.simplefootballstatistic.Retrofit.InterfaceTeam;
import com.simplefootballstatistic.Utils.LogTest;
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

public class LihatNilaiActivity extends AppCompatActivity implements ViewsLihatNilai {

    List<Penilaian> penilaian_prepare =  new ArrayList<>();
    DataTeam dataTeam;
    RecyclerView rv_penilaian;
    AdapterLihatNilai adapterLihatNilai;
    TextView tv_penilaian_nama_team;
    TextView tv_penilaian_nama_pelatih;
    int id_team;
    DownloadManager downloadManager;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_nilai);
        init();
    }

    private void init(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        progressDialog = new ProgressDialog(this);
        rv_penilaian = findViewById(R.id.rv_penilaian);
        id_team = getIntent().getIntExtra("id_team", 0);
        dataTeam = new DataTeam();
        dataTeam.init(this);
        tv_penilaian_nama_team = findViewById(R.id.tv_penilaian_nama_team);
        tv_penilaian_nama_pelatih = findViewById(R.id.tv_penilaian_nama_pelatih);
        this.findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//                Uri uri = Uri.parse(BASE_URL+"print/"+id_team);
//                DownloadManager.Request request = new DownloadManager.Request(uri);
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                Long preference = downloadManager.enqueue(request);
                downloadPdf();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        dataTeam.getPenilaianTim(this, String.valueOf(id_team));
    }

    @Override
    public void setInputNilai(RestPenilaian restPenilaian) {
        penilaian_prepare = restPenilaian.getPenilaians();
        adapterLihatNilai = new AdapterLihatNilai(this, penilaian_prepare, this);
        rv_penilaian.setLayoutManager(new LinearLayoutManager(this));
        rv_penilaian.setAdapter(adapterLihatNilai);
        adapterLihatNilai.notifyDataSetChanged();
        tv_penilaian_nama_pelatih.setText(restPenilaian.getPenilaians().get(0).getNamaPelatih());
        tv_penilaian_nama_team.setText(restPenilaian.getPenilaians().get(0).getNamaTeam());
    }

    @Override
    public void onDataChart(Chart chart) {

    }


    private void downloadPdf(){
        progressDialog.setMessage("Download file pdf, mohon tunggu sebentar");
        progressDialog.show();
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(this).create(InterfaceTeam.class);
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
                    Toast.makeText(LihatNilaiActivity.this, "Download error, Coba beberapa saat lagi", Toast.LENGTH_SHORT).show();
                    LogTest.look("server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LihatNilaiActivity.this, "Download error, Coba beberapa saat lagi", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(this, "No PDF Viewer Installed", Toast.LENGTH_LONG).show();
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
}
