package com.example.helloworld.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;




import com.baomidou.mybatisplus.annotation.TableName;



@TableName("sport_info") // 指定表名
public class SportInfo {
    @TableId(type  = IdType.AUTO)
    private int id; // 主键
    private String sportType; // 运动类型
    private String suitableTime; // 适宜时长
    private String suitableHeartRate; // 适宜心率
    private String suitableFrequency; // 适宜频率
    private String recommendedSpeed; // 推荐速度
    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String getSuitableTime() {
        return suitableTime;
    }

    public void setSuitableTime(String suitableTime) {
        this.suitableTime = suitableTime;
    }

    public String getSuitableHeartRate() {
        return suitableHeartRate;
    }

    public void setSuitableHeartRate(String suitableHeartRate) {
        this.suitableHeartRate = suitableHeartRate;
    }

    public String getSuitableFrequency() {
        return suitableFrequency;
    }

    public void setSuitableFrequency(String suitableFrequency) {
        this.suitableFrequency = suitableFrequency;
    }

    public String getRecommendedSpeed() {
        return recommendedSpeed;
    }

    public void setRecommendedSpeed(String recommendedSpeed) {
        this.recommendedSpeed = recommendedSpeed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
