package io.howtoarchitect.wows.players.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.howtoarchitect.wows.players.model.api.Account;
import io.howtoarchitect.wows.players.model.api.Data;

@Entity
@Table(name = "players")
public class Player {
    private long id;
    private String nickname;
    private String region;
    private long levellingTier;
    private long levellingPoints;
    private Boolean hiddenProfile;
    private Date createdAt;
    private Date updatedAt;
    private Date logoutAt;
    private Date statsUpdatedAt;

    public Player(Data playerData, String region) {
        this.id = playerData.getAccount_id();
        this.nickname = playerData.getNickname();
        this.region = region;
    }

    public String toString() {
        return this.getId() + " " + this.getNickname();
    }

    @Id
    @Column(name = "application_id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(length = 25)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(length = 4)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getLevellingTier() {
        return levellingTier;
    }

    public void setLevellingTier(long levellingTier) {
        this.levellingTier = levellingTier;
    }

    public long getLevellingPoints() {
        return levellingPoints;
    }

    public void setLevellingPoints(long levellingPoints) {
        this.levellingPoints = levellingPoints;
    }

    public Boolean getHiddenProfile() {
        return hiddenProfile;
    }

    public void setHiddenProfile(Boolean hiddenProfile) {
        this.hiddenProfile = hiddenProfile;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(Date logoutAt) {
        this.logoutAt = logoutAt;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getStatsUpdatedAt() {
        return statsUpdatedAt;
    }

    public void setStatsUpdatedAt(Date statsUpdatedAt) {
        this.statsUpdatedAt = statsUpdatedAt;
    }

}
