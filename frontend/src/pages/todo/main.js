import Vue from 'vue'
import App from './App.vue'
import 'Common/registerServiceWorker.js'

Vue.config.productionTip = false

// app Vue instance
new Vue({

  render: h => h(App)

}).$mount('#app')
