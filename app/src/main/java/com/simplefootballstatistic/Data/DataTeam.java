package com.simplefootballstatistic.Data;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.simplefootballstatistic.Respon.Chart;
import com.simplefootballstatistic.Respon.Penilaian;
import com.simplefootballstatistic.Respon.Rest;
import com.simplefootballstatistic.Respon.RestAddTeam;
import com.simplefootballstatistic.Respon.RestListPemain;
import com.simplefootballstatistic.Respon.RestListTeam;
import com.simplefootballstatistic.Respon.RestPenilaian;
import com.simplefootballstatistic.Retrofit.ClientRetrofit;
import com.simplefootballstatistic.Retrofit.InterfaceTeam;
import com.simplefootballstatistic.Utils.LogTest;
import com.simplefootballstatistic.Views.ViewsAddPemainActivity;
import com.simplefootballstatistic.Views.ViewsInputNilaiActivity;
import com.simplefootballstatistic.Views.ViewsLihatNilai;
import com.simplefootballstatistic.Views.ViewsListTeamActivity;
import com.simplefootballstatistic.Views.ViewsTambahTimActivity;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class DataTeam {
    private ViewsTambahTimActivity viewsTambahTimActivity;
    private ViewsAddPemainActivity viewsAddPemainActivity;
    private ViewsListTeamActivity viewsListTeamActivity;
    private ViewsInputNilaiActivity viewsInputNilaiActivity;
    private ViewsLihatNilai viewsLihatNilai;
    public void init(ViewsTambahTimActivity viewsTambahTimActivity){
        this.viewsTambahTimActivity = viewsTambahTimActivity;
    }
    public void init(ViewsAddPemainActivity viewsAddPemainActivity){
        this.viewsAddPemainActivity = viewsAddPemainActivity;
    }

    public void init(ViewsListTeamActivity viewsListTeamActivity){
        this.viewsListTeamActivity = viewsListTeamActivity;
    }
    public void init(ViewsInputNilaiActivity viewsInputNilaiActivity){
        this.viewsInputNilaiActivity = viewsInputNilaiActivity;
    }
    public void init(ViewsLihatNilai viewsLihatNilai){
        this.viewsLihatNilai = viewsLihatNilai;
    }



    public void createTeam(Activity activity, HashMap<String, String> hashMap){
        LogTest.look("createTeam hashmap " + new Gson().toJson(hashMap));
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<RestAddTeam> addTeam = interfaceTeam.addTeam(hashMap);
        addTeam.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestAddTeam>() {
                    @Override
                    public void onNext(RestAddTeam restAddTeam) {
                        LogTest.look("createTeam rest " + new Gson().toJson(restAddTeam));
                        if (restAddTeam.getSuccess()){
                            viewsTambahTimActivity.suksesBuatTeam(restAddTeam);
                        }else viewsTambahTimActivity.errorBuatTeam();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogTest.look("createTeam error " + new Gson().toJson(e));
                        viewsTambahTimActivity.errorBuatTeam();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getListPemain(Activity activity, String id_team){
        LogTest.look("getlistTeam id_team " + id_team);
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<RestListPemain> getListPemain = interfaceTeam.getListPemain(id_team);
        getListPemain.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestListPemain>() {
                    @Override
                    public void onNext(RestListPemain restListPemain) {
                        LogTest.look("getListTeam rest " + new Gson().toJson(restListPemain));
                        if (restListPemain.getSuccess()){
                            if (viewsAddPemainActivity!=null)viewsAddPemainActivity.setDataTeam(restListPemain);
                            if (viewsInputNilaiActivity!=null)viewsInputNilaiActivity.setListPemain(restListPemain);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogTest.look("getListTeam error " + new Gson().toJson(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addPemain(Activity activity, String id_team, HashMap<String, String> hashMap){
        LogTest.look("addPemain hash " + new Gson().toJson(hashMap));
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<Rest> addPemain = interfaceTeam.addPemain(id_team, hashMap);
        addPemain.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Rest>() {
                    @Override
                    public void onNext(Rest rest) {
                        LogTest.look("addPemain rest " + new Gson().toJson(rest));
                        if (rest.getSuccess())viewsAddPemainActivity.suksesAddPemain();
                        else viewsAddPemainActivity.errorAddPemain();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogTest.look("addPemain error " + new Gson().toJson(e));
                        viewsAddPemainActivity.errorAddPemain();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getListTeam(Activity activity){
        LogTest.look("getListTeam");
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<RestListTeam> getListTeam = interfaceTeam.getListTeam();
        getListTeam.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestListTeam>() {
                    @Override
                    public void onNext(RestListTeam restListTeam) {
                        LogTest.look("getListeTeam rest " + new Gson().toJson(restListTeam));
                        if (restListTeam.getTeamList()!=null)viewsListTeamActivity.dataListTeam(restListTeam.getTeamList());
                        else viewsListTeamActivity.errorDataListteam();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogTest.look("getListeTeam error " + new Gson().toJson(e));
                        viewsListTeamActivity.errorDataListteam();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void deletePemain(Activity activity, String id_pemain){
        LogTest.look("deletePemain id_pemain " + id_pemain);
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<Rest> deletePemain = interfaceTeam.deletePemain(id_pemain);
        deletePemain.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Rest>() {
                    @Override
                    public void onNext(Rest rest) {
                        LogTest.look("deletePemain rest " + new Gson().toJson(rest));
                        if (rest.getSuccess())viewsAddPemainActivity.suksesAddPemain();
                        else viewsAddPemainActivity.errorAddPemain();
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewsAddPemainActivity.errorAddPemain();
                        LogTest.look("deletePemain error " + new Gson().toJson(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updatePemain(Activity activity, String id_pemain, HashMap<String, String> hashMap){
        LogTest.look("updatePemain id_pemain " + id_pemain + " hash " + new Gson().toJson(hashMap));
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<Rest> updatePemain = interfaceTeam.updatePemain(id_pemain, hashMap);
        updatePemain.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Rest>() {
                    @Override
                    public void onNext(Rest rest) {
                        LogTest.look("updatePemain rest " + new Gson().toJson(rest));
                        if (rest.getSuccess())viewsAddPemainActivity.suksesUpdatePemain();
                        else viewsAddPemainActivity.errorUpdatePemain();
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewsAddPemainActivity.errorAddPemain();
                        LogTest.look("updatePemain error " + new Gson().toJson(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPenilaianTim(Activity activity, String id_team){
        LogTest.look("getPenilaianTim id_team " + id_team);
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<RestPenilaian> getPenilaianTim = interfaceTeam.getPenilaianTim(id_team);
        getPenilaianTim.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestPenilaian>() {
                    @Override
                    public void onNext(RestPenilaian restPenilaian) {
                        LogTest.look("getPenilaianTim rest " + new Gson().toJson(restPenilaian));
                        if (restPenilaian.getSuccess()){
                            if (viewsInputNilaiActivity!=null)viewsInputNilaiActivity.setInputNilai(restPenilaian);
                            if (viewsLihatNilai!=null)viewsLihatNilai.setInputNilai(restPenilaian);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogTest.look("getPenilaianTim error " + new Gson().toJson(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateNilai(Activity activity, String id_nilai, Penilaian penilaian){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("goal", String.valueOf(penilaian.getGoal()));
        hashMap.put("foul", String.valueOf(penilaian.getFoul()));
        hashMap.put("three_step", String.valueOf(penilaian.getThreeStep()));
        hashMap.put("double", String.valueOf(penilaian.get_double()));
        hashMap.put("kartu_kuning", String.valueOf(penilaian.getKartuKuning()));
        hashMap.put("kartu_merah", String.valueOf(penilaian.getKartuMerah()));
        hashMap.put("suspand", String.valueOf(penilaian.getSuspand()));
        hashMap.put("attack", String.valueOf(penilaian.getAttack()));
        LogTest.look("updateNilai id nilai " + id_nilai + " hash " + new Gson().toJson(hashMap));
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<Rest> updateNilai = interfaceTeam.updateNilai(id_nilai, hashMap);
        updateNilai.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Rest>() {
                    @Override
                    public void onNext(Rest rest) {
                        LogTest.look("updateNilai rest " + new Gson().toJson(rest));
                        if (rest.getSuccess())viewsInputNilaiActivity.setSuksesUpdate();
                        else viewsInputNilaiActivity.errorUpdate();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogTest.look("updateNilai error " + new Gson().toJson(e));
                        viewsInputNilaiActivity.errorUpdate();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteTim(final Activity activity, String id_team){
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<Rest> deleteTim = interfaceTeam.deleteTim(id_team);
        deleteTim.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Rest>() {
                    @Override
                    public void onNext(Rest rest) {
                        if (rest.getSuccess())viewsListTeamActivity.refresh();
                        else Toast.makeText(activity, "Terjadi error coba sekali lagi", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(activity, "Terjadi error coba sekali lagi", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getChart(Activity activity, String id_team){
        LogTest.look("getChart id " + id_team);
        InterfaceTeam interfaceTeam = ClientRetrofit.getClient(activity).create(InterfaceTeam.class);
        Observable<Chart> getChart = interfaceTeam.getChart(id_team);
        getChart.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Chart>() {
                    @Override
                    public void onNext(Chart chart) {
                        LogTest.look("getChart rest " + new Gson().toJson(chart));
                        if (chart.getSuccess()!=null||chart.getSuccess())viewsLihatNilai.onDataChart(chart);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogTest.look("getChart e " + new Gson().toJson(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
