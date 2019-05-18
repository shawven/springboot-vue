import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/ad/list',
    method: 'get',
    params: query
  })
}
