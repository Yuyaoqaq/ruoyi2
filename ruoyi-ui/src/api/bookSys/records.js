import request from '@/utils/request'

// 查询借阅信息查询列表
export function listRecords(query) {
  return request({
    url: '/bookSys/records/list',
    method: 'get',
    params: query
  })
}

// 查询借阅信息查询详细
export function getRecords(recordId) {
  return request({
    url: '/bookSys/records/' + recordId,
    method: 'get'
  })
}

// 新增借阅信息查询
export function addRecords(data) {
  return request({
    url: '/bookSys/records',
    method: 'post',
    data: data
  })
}

// 修改借阅信息查询
export function updateRecords(data) {
  return request({
    url: '/bookSys/records',
    method: 'put',
    data: data
  })
}

// 删除借阅信息查询
export function delRecords(recordId) {
  return request({
    url: '/bookSys/records/' + recordId,
    method: 'delete'
  })
}
