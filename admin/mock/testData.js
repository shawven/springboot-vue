import Mock from 'mockjs'
const count = 100
let status
const tryList = []

for (let i = 0; i < count; i++) {
  status = Mock.mock('@integer(0, 1)')
  tryList.push(Mock.mock({
    id: '@increment',
    contacts: '@cname',
    phone: '13@string("number", 9)',
    status: status,
    finish: status,
    remark: '@csentence',
    createTime: '@now',
    updateTime: '@now'
  }))
}

const cooperationList = []
for (let i = 0; i < count; i++) {
  status = Mock.mock('@integer(0, 1)')
  cooperationList.push(Mock.mock({
    id: '@increment',
    contacts: '@cname',
    phone: '13@string("number", 9)',
    status: status,
    finish: status,
    remark: '@csentence',
    'proxyType|1': ['个人代理', '企业代理'],
    'proxyLevel|1': ['创客', '合伙人', 'VIP合伙人'],
    company: '@cword(3, 5)有限公司',
    area: '@city(true)',
    createTime: '@now',
    updateTime: '@now'
  }))
}

const articleList = []

for (let i = 0; i < count; i++) {
  articleList.push(Mock.mock({
    id: '@increment',
    subject: '@ctitle',
    author: '@first',
    description: '@cparagraph',
    createTime: '@datetime',
    cover: 'https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3',
    content: '<img src="https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3"/>' + '@cparagraph(3)',
    platforms: '@range(0, 2, 1)',
    categories: '@range(0, 2, 1)',
    enable: '@integer(0, 1)',
    sort: '@integer(0, 100)'
  }))
}

const adList = []

for (let i = 0; i < count; i++) {
  adList.push(Mock.mock({
    id: '@increment',
    name: '@ctitle',
    position: '@ctitle',
    link: 'https://wpimg.wallstcn.com/e4558086-631c-425c-9430-56ffb46e70b3',
    platform: '@integer(0, 1)',
    sortNo: '@integer(0, 10)',
    enable: '@integer(0, 1)',
    startTime: '@now',
    endTime: '@now'
  }))
}

export default [
  {
    url: '/customer/try/list',
    type: 'get',
    response: config => {
      const { page, limit } = config.query
      const pageList = tryList.filter((item, index) => index < limit * page && index >= limit * (page - 1))

      return {
        code: 200,
        data: {
          total: tryList.length,
          items: pageList
        }
      }
    }
  },
  {
    url: '/customer/cooperation/list',
    type: 'get',
    response: config => {
      const { page, limit } = config.query
      const pageList = cooperationList.filter((item, index) => index < limit * page && index >= limit * (page - 1))

      return {
        code: 200,
        data: {
          total: cooperationList.length,
          items: pageList
        }
      }
    }
  },
  {
    url: '/ad/list',
    type: 'get',
    response: config => {
      const { page, limit } = config.query
      const pageList = adList.filter((item, index) => index < limit * page && index >= limit * (page - 1))

      return {
        code: 200,
        data: {
          total: adList.length,
          items: pageList
        }
      }
    }
  },
  {
    url: '/article/list',
    type: 'get',
    response: config => {
      const { page, limit } = config.query
      const pageList = articleList.filter((item, index) => index < limit * page && index >= limit * (page - 1))

      return {
        code: 200,
        data: {
          total: articleList.length,
          items: pageList
        }
      }
    }
  },

  {
    url: '/article/detail',
    type: 'get',
    response: config => {
      const { id } = config.query
      for (const article of articleList) {
        if (article.id === +id) {
          return {
            code: 200,
            data: article
          }
        }
      }
    }
  },

  {
    url: '/article/pv',
    type: 'get',
    response: _ => {
      return {
        code: 200,
        data: {
          pvData: [
            { key: 'PC', pv: 1024 },
            { key: 'mobile', pv: 1024 },
            { key: 'ios', pv: 1024 },
            { key: 'android', pv: 1024 }
          ]
        }
      }
    }
  },

  {
    url: '/article/create',
    type: 'post',
    response: _ => {
      return {
        code: 200,
        data: 'success'
      }
    }
  },

  {
    url: '/article/update',
    type: 'post',
    response: _ => {
      return {
        code: 200,
        data: 'success'
      }
    }
  }
]
