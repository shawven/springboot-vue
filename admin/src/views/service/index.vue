<template>
  <list ref="list" :action="action" :query.sync="query">
    <template slot="filters">
      <el-input v-model="query.title" placeholder="标题" style="width: 200px;" class="filter-item" />
      <el-input v-model="query.date" placeholder="日期" style="width: 200px;" class="filter-item" />
    </template>
    <template slot="buttons">
      <el-button class="filter-item" type="primary" :loading="uploading" @click="click">
        上传<i class="el-icon-upload el-icon--right" />
      </el-button>
    </template>

    <template slot="columns">
      <el-table-column type="selection" width="55" />
      <el-table-column label="ID" prop="id" sortable="custom" align="center" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Date" width="150px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.timestamp | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Author" width="110px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Reviewer" width="110px" align="center">
        <template slot-scope="scope">
          <span style="color:red;">{{ scope.row.reviewer }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Imp" width="80px">
        <template slot-scope="scope">
          <svg-icon v-for="n in +scope.row.importance" :key="n" icon-class="star" class="meta-item__icon" />
        </template>
      </el-table-column>

      <el-table-column label="Status" class-name="status-col" width="100">
        <template slot-scope="{row}">
          <el-tag :type="row.status">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
    </template>
  </list>
</template>

<script>
import { fetchList } from '@/api/customer'

export default {
  name: 'ServiceIndex',
  data() {
    return {
      action: fetchList,
      uploading: false,
      query: {
        title: ''
      }
    }
  },
  methods: {
    created() {
      this.$refs.list.getList()
    },
    updated(row) {
      const list = this.$refs.list.getList()
      list.splice(list.findIndex((item) => item.id === row.id), 1, row)
    },
    selectOne() {
      return this.$refs.list.selectOne()
    },
    click() {
      this.uploading = true
      setTimeout(() => this.uploading = false, 2000)
    }
  }
}
</script>

<style scoped>

</style>
