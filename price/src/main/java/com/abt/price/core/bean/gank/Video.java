package com.abt.price.core.bean.gank;

import java.io.Serializable;
import java.util.List;

/**
 * @描述： @Video
 * @作者： @黄卫旗
 * @创建时间： @10/06/2018
 */
public class Video implements Serializable {

    private boolean error;
    private List<Gank> results;

    public boolean isError() {
        return error;
    }

    public List<Gank> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Video{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
