package com.simplefootballstatistic.Views;

import com.simplefootballstatistic.Respon.Team;

import java.util.List;

public interface ViewsListTeamActivity {
    void errorDataListteam();

    void dataListTeam(List<Team> teamList);

    void bukaTeam();

    void refresh();
}
