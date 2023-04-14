package com.jackie.domain;

public class News
{
    String time;
    String title;
    String titleUrl;
    String source;
    String sourceUrl;

    @Override
    public String toString() {
        return "news{" +
                "time='" + time + '\'' +
                ",\n title='" + title + '\'' +
                ",\n titleUrl='" + titleUrl + '\'' +
                ",\n source='" + source + '\'' +
                ",\n sourceUrl='" + sourceUrl + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
