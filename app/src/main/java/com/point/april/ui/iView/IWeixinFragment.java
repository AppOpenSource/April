package com.point.april.ui.iView;

import com.point.april.bean.WeixinNews;

import java.util.ArrayList;

/**
 * Created by 蔡小木 on 2016/4/22 0022.
 */
public interface IWeixinFragment extends IBaseFragment {
    void updateList(ArrayList<WeixinNews> weixinNewses);
}
