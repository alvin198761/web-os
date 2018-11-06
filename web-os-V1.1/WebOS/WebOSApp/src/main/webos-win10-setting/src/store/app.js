import {dsy} from '../utils/dsy.js';
import {nat} from '../utils/nat.js';

import {heartbeat, fetchInfo} from '../service/SessionService';
import {queryList} from '../service/AppIconsService';

export default {
  state: {
    version: '1.0',
    mode: 'prod',
    user: {
      name: 'alvin',
      theme: 0
    },//登录用户,
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  }
};
