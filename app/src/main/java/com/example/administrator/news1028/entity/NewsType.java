package com.example.administrator.news1028.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ${ljy} on 2016/10/29.
 */

public class NewsType implements Serializable {
    private ArrayList<SubTypeList> tList;

    public NewsType(ArrayList<SubTypeList> tList) {
        this.tList = tList;
    }

    public ArrayList<SubTypeList> gettList() {
        return tList;
    }

    public void settList(ArrayList<SubTypeList> tList) {
        this.tList = tList;
    }

    public static class SubTypeList implements Serializable{
        private String tid;
        private String tname;

        public SubTypeList(String tid, String tname) {
            this.tid = tid;
            this.tname = tname;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }
    }
}
