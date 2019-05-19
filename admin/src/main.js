import Vue from 'vue'
import Element from 'element-ui'

import Cookies from 'js-cookie'

// CSS重置的样式
import 'normalize.css/normalize.css'
// global css
import './styles/element-variables.scss'
// 自定义css
import './styles/index.scss'
import './styles/user.css'

import App from './App'
import store from './store'
import router from './router'

// icon
import './icons'
// 权限控制
import './permission'
// 错误日志
import './utils/error-log'
// 全局过滤器
import * as filters from './filters'

import * as utils from './utils/index'
import BaseList from './components/Base/list'

/**
 * 如果不想使用模拟服务器 想使用MockJs进行模拟api 可以执行：mockXHR（）
 * 目前MockJs将用于生产环境， 请在上网前将其删除！ ！ ！
 */
import { mockXHR } from '../mock'
if (process.env.NODE_ENV === 'production') {
  mockXHR()
}

Vue.use(Element, {
  // 设置 element-ui默认布局大小
  size: Cookies.get('size') || 'medium'
})

// 注册全局过滤器
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

// 注册自定义组件
Vue.prototype.$utils = utils
// 注册工具类
Vue.component('base-list', BaseList)

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
