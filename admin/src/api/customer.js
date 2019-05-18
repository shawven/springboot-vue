import request from '@/utils/request'

export function fetchTryCustomerList(query) {
  return request.get('/trialCustomer', { params: query })
}

export function updateTryCustomer(data) {
  return request.put('/trialCustomer', data)
}

export function fetchCopartnerList(query) {
  return request('/partner', { params: query })
}

export function updateCopartnerCustomer(data) {
  return request.put('/partner', data)
}
