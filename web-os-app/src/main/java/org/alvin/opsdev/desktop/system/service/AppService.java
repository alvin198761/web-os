package org.alvin.opsdev.desktop.system.service;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.alvin.opsdev.desktop.system.bean.AppBean;
import org.alvin.opsdev.desktop.system.domain.enums.AppType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangzhichao on 2017/3/6.
 */
@Service
public class AppService {

    public List<AppBean> sidebarApps() {
        String mock = "[ {\n" +
                "            id: 'site_001',\n" +
                "                    type: 1,\n" +
                "                title: '远程桌面连接',\n" +
                "                icon: 'rdp.png',\n" +
                "                url: 'rdp'\n" +
                "        },\n" +
                "        {\n" +
                "            id: 'site_002',\n" +
                "                    type: 1,\n" +
                "                title: 'VNC 连接',\n" +
                "                icon: 'vnc.png',\n" +
                "                url: 'vnc'\n" +
                "        },\n" +
                "        {\n" +
                "            id: 'site_003',\n" +
                "                    type: 1,\n" +
                "                title: 'putty',\n" +
                "                icon: 'putty.png',\n" +
                "                url: 'putty'\n" +
                "        },\n" +
                "        {\n" +
                "            id: 'site_004',\n" +
                "                    type: 1,\n" +
                "                title: 'ipmi',\n" +
                "                icon: 'ipmi.png',\n" +
                "                url: 'ipmi'\n" +
                "        },\n" +
                "        {\n" +
                "            id: 'site_005',\n" +
                "                    type: 1,\n" +
                "                title: '邮箱',\n" +
                "                icon: 'mail.png',\n" +
                "                url: 'mail'\n" +
                "        },\n" +
                "        {\n" +
                "            id: 'site_006',\n" +
                "                    type: 1,\n" +
                "                title: '时钟',\n" +
                "                icon: 'time.png',\n" +
                "                url: 'time'\n" +
                "        }]";
        Gson gson = new Gson();
        return gson.fromJson(mock, new TypeToken<List<AppBean>>() {
        }.getType());
    }

    public List<AppBean> fishEyeApps() {
        String mock = "[\n" +
                "      {\n" +
                "        id: '001',\n" +
                "        type: 1,\n" +
                "        title: 'Home',\n" +
                "        icon: 'home.png' \n" +
                "      }, {\n" +
                "        id: '002',\n" +
                "        type: 2,\n" +
                "        title: 'Contact',\n" +
                "        icon: 'email.png' \n" +
                "      }, {\n" +
                "        id: '003',\n" +
                "        type: 3,\n" +
                "        title: 'Portfolio',\n" +
                "        icon: 'portfolio.png' \n" +
                "      }, {\n" +
                "        id: '004',\n" +
                "        type: 4,\n" +
                "        title: 'Music',\n" +
                "        icon: 'music.png' \n" +
                "      }, {\n" +
                "        id: '005',\n" +
                "        type: 5,\n" +
                "        title: 'Video',\n" +
                "        icon: 'video.png' \n" +
                "      }, {\n" +
                "        id: '006',\n" +
                "        type: 6,\n" +
                "        title: 'History',\n" +
                "        icon: 'history.png' \n" +
                "      }, {\n" +
                "        id: '007',\n" +
                "        type: 7,\n" +
                "        title: 'Calendar',\n" +
                "        icon: 'calendar.png' \n" +
                "      }, {\n" +
                "        id: '008',\n" +
                "        type: 8,\n" +
                "        title: 'Rss',\n" +
                "        icon: 'rss.png' \n" +
                "      }\n" +
                "    ]";
        Gson gson = new Gson();
        List<AppBean> list = gson.fromJson(mock, new TypeToken<List<AppBean>>() {
        }.getType());
        for (AppBean app : list) {
            app.setApps(this.subApps(app.getId()));
        }
        return list;
    }

    public List<AppBean> subApps(String id) {
        List<AppBean> list = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            int pic = i % 6 + 1;
            int type = (int) (Math.random() * 10 % AppType.values().length + 1);
            int count = (int) (Math.random() * 10);

            AppBean app = new AppBean();
            app.setId("00" + i);
            app.setType(type);
            app.setTitle("应用测试" + i);
            app.setIcon(pic + ".png");
            app.setUrl("http://www.baidu.com");
            app.setMsgCount(count);
            list.add(app);
        }
        return list;
    }
}
