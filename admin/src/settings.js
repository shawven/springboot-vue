module.exports = {
  /**
   * @type {string}
   * @description 系统名称
   */
  title: '后台管理系统',

  /**
   * @type {string}
   * @description 侧边栏LOGO连接
   */
  logo: 'https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png',

  /**
   * @type {boolean} true | false
   * @description 是否显示右侧面板的设置
   */
  showSettings: true,

  /**
   * @type {boolean} true | false
   * @description 是否需要标签页
   */
  tagsView: true,

  /**
   * @type {boolean} true | false
   * @description 是否固定头部
   */
  fixedHeader: true,

  /**
   * @type {boolean} true | false
   * @description 是否在侧边栏中显示LOGO
   */
  sidebarLogo: true,

  /**
   * @type {string | array} 'production' | ['production', 'development']
   * @description 需要显示错误日志组件
   * 默认值仅用于生产环境
   * 如果你想在dev中使用它，你可以通过['production'，'development']
   */
  errorLog: 'production'
}
