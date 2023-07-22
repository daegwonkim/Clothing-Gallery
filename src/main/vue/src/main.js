import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import "@/fontAwesomeIcon.js";

Vue.config.productionTip = false;
Vue.prototype.$axios = axios;
export const eventBus = new Vue();

new Vue({
  router,
  vuetify,
  render: h => h(App)
}).$mount('#app')