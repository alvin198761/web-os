<template>
  <div class="dock" id="dock" v-if="menus.length === 8">
    <div class="dock-container">
      <a v-for="menu in menus" @click="changeCard(menu)" class="dock-item" href="javascript:void(0)">
        <img :src="menu.icon" :alt="menu.icon"/><span>{{menu.title}}</span>
      </a>
    </div>
  </div>
</template>
<script>
  import {mapGetters} from 'vuex';

  export default{
    data: function () {
      return {}
    },
    computed: {
      ...mapGetters({
        menus: 'fisheye/_menus'
      })
    },
    components: {},
    methods: {
      changeCard: function (menu) {
        let index = this.menus.indexOf(menu);
        this.$store.commit('content/changeCard', {item: index});
      }
    },
    mounted: function () {
      this.$store.commit('fisheye/initComponent');
    },
    created: function () {
      this.$store.commit('fisheye/fetch', this.$store);
    },
    updated: function () {
      $('#dock').Fisheye({
        maxWidth: 80,
        items: 'a',
        itemsText: 'span',
        container: '.dock-container',
        itemWidth: 40,
        proximity: 90,
        halign: 'center'
      })
    }
  }
</script>
<style>

</style>
