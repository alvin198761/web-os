/**
 * Created by tangzhichao on 2017/2/22.
 */
export const APP_TYPE = {
  DIR: 4,
  APP: 3
}

export function componentIndexOf(tasks, id) {
  for (let i = 0; i < tasks.length; i++) {
    if (tasks[i].el_id === id) {
      return i;
    }
  }
  return -1;
}


export function desktopContextMenu(dispatch, commit) {
  return [
    [{
      text: "新建",
      data: [[
        {
          text: '应用程序',
          func: function () {
            dispatch("taskbar/addAppIcon",  {
              "id": -1,
              "type": 1,
              "title": "添加应用",
              "tip": "添加自定义应用或目录",
              "icon": require("./assets/icon/addAppItem.png"),
              "publish_id": 0,
              "create_time": new Date(),
              "parent_id": 0,
              "route_url": "appAppItem",
              "status": 1
            });
          }
        },
        {
          text: '目录',
          func: function () {

          }
        }
      ]]
    }, {
      text: "关闭所有",
      func: function () {
        // Windows.closeAllWindow();
      }
    }, {
      text: "锁屏",
      func: function () {

      }
    }], [{
      text: "系统设置",
      func: function () {

      }
    }, {
      text: "主题设置",
      func: function () {
        // Windows.openSys({
        //   id: 'themSetting',
        //   title: '设置主题',
        //   width: 650,
        //   height: 500,
        //   content: document.getElementById("themeSetting_wrap")
        // });
      }
    },
      {
        text: "图标设置",
        data: [[{
          text: "大图标",
          func: function () {
            // Deskpanel.desktopsContainer.removeClass("desktopSmallIcon");
          }
        }, {
          text: "小图标",
          func: function () {
            // Deskpanel.desktopsContainer.addClass("desktopSmallIcon");
          }
        }]]
      }],
    [{
      text: "注销",
      func: function () {

      }
    }]
  ]
}
