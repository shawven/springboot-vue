<template>
  <div>
    <base-list ref="list" :action="action" :query.sync="query">
      <template slot="filters">
        <el-input v-model="query.name" placeholder="名称" style="width: 200px;" class="filter-item" clearable />
        <el-input v-model="query.position" placeholder="位置" style="width: 200px;" class="filter-item" clearable />
        <el-select v-model="query.enable" placeholder="状态" style="width: 130px;" class="filter-item" clearable>
          <el-option v-for="(state, index) in states" :key="index" :label="state" :value="state" />
        </el-select>
      </template>
      <template slot="buttons">
        <el-button type="primary" icon="el-icon-edit-outline" @click="showCreator">添加广告</el-button>
      </template>

      <el-table-column label="广告名称" prop="name" width="150" align="center" />
      <el-table-column label="广告位置" prop="position" align="center" />
      <el-table-column label="广告连接" prop="link" align="center" />
      <el-table-column label="缩略图" align="center">
        <template slot-scope="{row}">
          <el-image :src="row.link" fit="cover" lazy>
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline" />
            </div>
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" class-name="status-col">
        <template slot-scope="{row}">
          <el-tag :type="stateColors[row.enable]">{{ states[row.enable] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="所属平台" align="center">
        <template slot-scope="{row}">
          {{ platforms[row.platform] }}
        </template>
      </el-table-column>
      <el-table-column label="排序" prop="sortNo" width="100" align="center" />
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="{row}">
          <template v-if="!row.enable">
            <el-button type="text" @click="showEnableDialog(row)">上架</el-button>
          </template>
          <template v-else>
            <el-button type="text" @click="showEditor(row)">编辑</el-button>
            <el-button type="text" @click="showDisableDialog(row)">下架</el-button>
          </template>
          <el-button type="text" @click="showDeleteDialog(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </base-list>
  </div>
</template>

<script>
import { fetchList } from '@/api/ad'

export default {
  name: 'AdIndex',
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
      states: ['未上架', '已上架'],
      stateColors: ['danger', 'success'],
      platforms: ['财税官网', '云服务软件'],
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

    showCreator() {

    },

    showEnableDialog() {

    },

    showDisableDialog() {

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
