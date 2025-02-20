import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'font-awesome/css/font-awesome.min.css'
import axios  from 'axios';
import VueAxios from 'vue-axios'

import router from './router'

axios.defaults.baseURL = "http://localhost:8088"
Vue.prototype.$http = axios

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueAxios , axios)

new Vue({
  render: h => h(App),
  router
}).$mount('#app')
