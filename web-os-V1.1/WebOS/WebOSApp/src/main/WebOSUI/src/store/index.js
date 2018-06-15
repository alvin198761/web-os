/**
 * Created by tangzhichao on 2017/2/21.
 */

import Vue from 'vue'
import Vuex from 'vuex'
import app from './modules/app';
import desktop from './modules/desktop';
import fisheye from './modules/fisheye';
import sidebar from './modules/sidebar';
import taskbar from './modules/taskbar';
import contentpane from './modules/contentpane';
import browser from './modules/brower';
import notes from './modules/notes';

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    app: app,
    desktop: desktop,
    fisheye: fisheye,
    sidebar: sidebar,
    taskbar: taskbar,
    contentpane: contentpane,
    browser: browser,
    notes: notes
  }
})
