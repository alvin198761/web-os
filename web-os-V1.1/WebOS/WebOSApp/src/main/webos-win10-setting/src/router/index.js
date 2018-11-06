import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import CommonRouter from '@/components/commons/CommonRouter'
import RDPList from '@/components/protocol/RDPList'
import VncList from '@/components/protocol/VncList'
import SSHList from '@/components/protocol/SSHList'
import SFTPList from '@/components/protocol/SFTPList'
import IpmiList from '@/components/protocol/IpmiList'
import AddApp from '@/components/settings/AddApp'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Home,
      children: [
        {
          path: 'protocol',
          component: CommonRouter,
          children: [
            {
              path: 'rdp',
              component: RDPList
            }
            , {
              path: 'vnc',
              component: VncList
            }
            , {
              path: 'ssh',
              component: SSHList
            }
            , {
              path: 'sftp',
              component: SFTPList
            }
            , {
              path: 'ipmi',
              component: IpmiList
            }
          ]
        },
        {
          path: 'settings',
          component: CommonRouter,
          children: [
            {
              path: 'addApp',
              component: AddApp
            }
          ]
        }

      ]
    }

  ]
})
