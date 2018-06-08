package com.cyx.mapper;

import com.cyx.pojo.Project;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(String id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> getProjectListByParams(Project record);

    List<Project> queryList();

    List<Project> qryByLeader(String leader);

    List<Project> qryByLeaderOrMember(String leaderOrMember);

    List<Project> getProjectListByParamsAndUserId(Project record);
}