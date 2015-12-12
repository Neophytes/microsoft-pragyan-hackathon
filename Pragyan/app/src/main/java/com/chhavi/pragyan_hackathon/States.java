package com.chhavi.pragyan_hackathon;

import com.orm.SugarRecord;

/**
 * Created by chhavi on 12/12/15.
 */
public class States extends SugarRecord<States> {

    public States(String name, String capital) {
        this.name = name;
        this.capital = capital;

    }

    public States(String name, String capital, String region, String chief_minister, String main_language) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.chief_minister = chief_minister;
        this.main_language = main_language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    String name;
    String capital;
    String region;
    String chief_minister;
    String main_language;

    public States(){}


}
