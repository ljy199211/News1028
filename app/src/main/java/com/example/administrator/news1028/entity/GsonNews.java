package com.example.administrator.news1028.entity;

import java.util.List;

/**
 * Created by ${ljy} on 2016/10/21.
 */

public class GsonNews {
    private List<Ab> ads;
    private String alias;
    private String boardid;
    private String cid;
    private String digest;
    private String docid;
    private String ename;
    private int hasAD;
    private boolean hasCover;
    private int hasHead;
    private boolean hasIcon;
    private int hasImg;
    private List<Imge> imgextra;
    private String imgsrc;
    private int imgsum;
    private String lmodify;
    private int order;
    private String photosetID;
    private String postid;
    private int priority;
    private String ptime;
    private int replyCount;
    private String skipID;
    private String skipType;
    private String source;
    private String template;
    private String title;
    private String tname;
    private int votecount;

    public GsonNews(List<Ab> ads, String alias, String boardid, String cid, String digest, String docid, String ename, int hasAD, boolean hasCover, int hasHead, boolean hasIcon, int hasImg, List<Imge> imgextra, String imgsrc, int imgsum, String lmodify, int order, String photosetID, String postid, int priority, String ptime, int replyCount, String skipID, String skipType, String source, String template, String title, String tname, int votecount) {
        this.ads = ads;
        this.alias = alias;
        this.boardid = boardid;
        this.cid = cid;
        this.digest = digest;
        this.docid = docid;
        this.ename = ename;
        this.hasAD = hasAD;
        this.hasCover = hasCover;
        this.hasHead = hasHead;
        this.hasIcon = hasIcon;
        this.hasImg = hasImg;
        this.imgextra = imgextra;
        this.imgsrc = imgsrc;
        this.imgsum = imgsum;
        this.lmodify = lmodify;
        this.order = order;
        this.photosetID = photosetID;
        this.postid = postid;
        this.priority = priority;
        this.ptime = ptime;
        this.replyCount = replyCount;
        this.skipID = skipID;
        this.skipType = skipType;
        this.source = source;
        this.template = template;
        this.title = title;
        this.tname = tname;
        this.votecount = votecount;
    }

    public List<Ab> getAds() {
        return ads;
    }

    public void setAds(List<Ab> ads) {
        this.ads = ads;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public List<Imge> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<Imge> imgextra) {
        this.imgextra = imgextra;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public int getImgsum() {
        return imgsum;
    }

    public void setImgsum(int imgsum) {
        this.imgsum = imgsum;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    @Override
    public String toString() {
        return "GsonNews{" +
                "ads=" + ads +
                ", alias='" + alias + '\'' +
                ", boardid='" + boardid + '\'' +
                ", cid='" + cid + '\'' +
                ", digest='" + digest + '\'' +
                ", docid='" + docid + '\'' +
                ", ename='" + ename + '\'' +
                ", hasAD=" + hasAD +
                ", hasCover=" + hasCover +
                ", hasHead=" + hasHead +
                ", hasIcon=" + hasIcon +
                ", hasImg=" + hasImg +
                ", imgextra=" + imgextra +
                ", imgsrc='" + imgsrc + '\'' +
                ", imgsum=" + imgsum +
                ", lmodify='" + lmodify + '\'' +
                ", order=" + order +
                ", photosetID='" + photosetID + '\'' +
                ", postid='" + postid + '\'' +
                ", priority=" + priority +
                ", ptime='" + ptime + '\'' +
                ", replyCount=" + replyCount +
                ", skipID='" + skipID + '\'' +
                ", skipType='" + skipType + '\'' +
                ", source='" + source + '\'' +
                ", template='" + template + '\'' +
                ", title='" + title + '\'' +
                ", tname='" + tname + '\'' +
                ", votecount=" + votecount +
                '}';
    }

    public static class Ab {
        private String docid;
        private String imgsrc;
        private String subtitle;
        private String tag;
        private String title;
        private String url;

        public Ab(String docid, String imgsrc, String subtitle, String tag, String title, String url) {
            this.docid = docid;
            this.imgsrc = imgsrc;
            this.subtitle = subtitle;
            this.tag = tag;
            this.title = title;
            this.url = url;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Ab{" +
                    "docid='" + docid + '\'' +
                    ", imgsrc='" + imgsrc + '\'' +
                    ", subtitle='" + subtitle + '\'' +
                    ", tag='" + tag + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public class Imge {
        private String imgsrc;

        public Imge(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }
}
