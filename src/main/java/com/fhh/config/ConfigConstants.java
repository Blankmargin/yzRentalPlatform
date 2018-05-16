package com.fhh.config;

public class ConfigConstants {
    //图片服务器上传路径
    private String imageServerUrl = "";
    //FastDFS配置文件
    private String conf = "";
    //首页轮播图contentId
    private long contentId;
    //商品搜索每页默认显示条数
    private int searchRows;
    //Session的过期时间
    private int sessionExpire;

    private String tokenKey="";

    public String getImageServerUrl() {
        return imageServerUrl;
    }

    public void setImageServerUrl(String imageServerUrl) {
        this.imageServerUrl = imageServerUrl;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public int getSearchRows() {
        return searchRows;
    }

    public void setSearchRows(int searchRows) {
        this.searchRows = searchRows;
    }

    public int getSessionExpire() {
        return sessionExpire;
    }

    public void setSessionExpire(int sessionExpire) {
        this.sessionExpire = sessionExpire;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }
}
