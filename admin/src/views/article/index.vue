<template>
  <div>
    <base-list ref="list" :action="action" :query.sync="query">
      <template slot="filters">
        <el-input v-model="query.subject" placeholder="标题" style="width: 200px;" class="filter-item" clearable />
        <el-select v-model="query.platform" placeholder="栏目" style="width: 130px;" class="filter-item" clearable>
          <el-option v-for="(category, index) in categories" :key="index" :label="category" :value="index" />
        </el-select>
        <el-select v-model="query.platform" placeholder="所属平台" style="width: 130px;" class="filter-item" clearable>
          <el-option v-for="(platform, index) in platforms" :key="index" :label="platform" :value="index" />
        </el-select>
      </template>
      <template slot="buttons">
        <el-button type="primary" icon="el-icon-edit-outline" @click="toCreator">发布文章</el-button>
      </template>

      <el-table-column label="序号" prop="id" width="100" align="center" />
      <el-table-column label="标题" prop="subject" width="150" align="center" show-overflow-tooltip />
      <el-table-column label="简介" prop="description" align="center" />
      <el-table-column label="封面" width="150" align="center">
        <template slot-scope="{row}">
          <el-image :src="row.cover" fit="cover" lazy>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline" />
            </div>
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="作者" prop="author" width="100" align="center" />
      <el-table-column label="栏目" width="100" align="center">
        <template slot-scope="{row}">
          <el-tag v-for="(value, index) in row.categories" :key="index" size="small" class="m-1">{{ categories[value] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="排序" prop="sort" width="100" align="center" />
      <el-table-column label="添加时间" prop="createTime" width="150" align="center" />
      <el-table-column label="操作" width="150px" align="left">
        <template slot-scope="{row}">
          <router-link :to="'/article/edit/'+row.id">
            <el-button type="text">编辑</el-button>
          </router-link>
          <el-button type="text" @click="showDeleteDialog(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </base-list>
  </div>
</template>

<script>
import { fetchList } from '@/api/article'

export default {
  name: 'ArticleIndex',
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      action: fetchList,
      uploading: false,
      editorVisible: false,
      creatorVisible: false,
      currentRow: {},
      currentIndex: null,
      platforms: ['财税官网', '云服务软件'],
      categories: ['公司动态', '财税资讯', '财税头条'],
      query: {
        contacts: null,
        phone: null,
        followUp: null
      }
    }
  },
  methods: {
    created() {
      this.$refs.list.getList()
    },

    update() {
      this.$message.success('处理成功')
      this.$refs.list.updateRowByIndex(this.currentIndex, this.currentRow)
    },

    selectOne() {
      return this.$refs.list.selectOne()
    },

    showEditor(index, row) {
      this.editorVisible = true
      this.currentIndex = index
      this.currentRow = this.$utils.deepcopy(row)
    },

    toCreator() {
      this.$router.push({ path: '/article/create' })
    },

    showDeleteDialog(id) {

    }
  }
}
</script>

<style scoped>
  .edit-input {
    padding-right: 100px;
  }

  .cancel-btn {
    position: absolute;
    right: 15px;
    top: 10px;
  }
</style>
