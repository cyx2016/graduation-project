package com.cyx.pojo;

import org.springframework.stereotype.Component;

@Component
public class Project {
    private String id;

    private String name;

    private String leader;

    private String datetime;

    private String date1;

    private String date2;

    private String status;

    private String duration;

    private String fund;

    private String member;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime == null ? null : datetime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund == null ? null : fund.trim();
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member == null ? null : member.trim();
    }

    public String getDate1() {
        return date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate1(String date1) {
        this.date1 = date1 == null ? null : date1.trim();;
    }

    public void setDate2(String date2) {
        this.date2 = date2 == null ? null : date2.trim();;
    }
}