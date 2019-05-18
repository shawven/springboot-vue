<template>
  <div>
    <base-list ref="list" :action="action" :query.sync="query">
      <template slot="filters">
        <el-input v-model="query.contacts" placeholder="姓名" style="width: 200px;" class="filter-item" clearable />
        <el-input v-model="query.phone" placeholder="电话" style="width: 200px;" class="filter-item" clearable />
        <el-select v-model="query.status" placeholder="客户分类" style="width: 130px;" class="filter-item" clearable>
          <el-option v-for="(value, index) in status" :key="index" :label="value" :value="index" />
        </el-select>
        <el-select v-model="query.followUp" placeholder="处理状态" style="width: 130px;" class="filter-item" clearable>
          <el-option v-for="(value, index) in followUpStatus" :key="index" :label="value" :value="index" />
        </el-select>
      </template>

      <el-table-column label="序号" prop="id" sortable="custom" align="center" width="100" />
      <el-table-column label="联系人" prop="contacts" width="150" align="center" />
      <el-table-column label="联系电话" prop="phone" width="110" align="center" />
      <el-table-column label="分类" align="center" width="100" class-name="status-col">
        <template slot-scope="{row}">
         {{ status[row.status] }}
        </template>
      </el-table-column>
      <el-table-column label="处理状态" align="center" width="100" class-name="status-col">
        <template slot-scope="{row}">
          <el-tag :type="followUpColors[row.followUp]">{{ followUpStatus[row.followUp] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" prop="remark" align="center" />
      <el-table-column label="申请时间" prop="createTime" align="center" />
      <el-table-column label="处理时间" prop="updateTime" align="center" />
      <el-table-column label="操作" align="center">
        <template slot-scope="{row}">
          <el-button type="text" @click="showProcessor(row)">{{ followUpActionNames[row.followUp] }}</el-button>
        </template>
      </el-table-column>
    </base-list>

    <el-dialog title="处理试用客户" :visible.sync="editorVisible" width="500px">
      <el-form ref="remarkForm" :model="selectRow" label-position="left" label-width="70px">
        <el-form-item label="备注：" prop="remark" :rules="[{required:true, message:'请输入备注'}]">
          <template v-if="!!selectRow.followUp">
            {{ selectRow.remark }}
          </template>
          <template v-else>
            <el-input
              v-model="selectRow.remark"
              type="textarea"
              placeholder="请输入备注"
              maxlength="50"
              :rows="2"
              show-word-limit
            />
          </template>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="update">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchTryCustomerList, updateTryCustomer } from '@/api/customer'

export default {
  name: 'TryIndex',
  data() {
    return {
      action: fetchTryCustomerList,
      editorVisible: false,
      selectRow: {},
      status: ['试用客户', '正式客户', '已过期'],
      followUpStatus: ['未处理', '已处理'],
      followUpColors: ['danger', 'success'],
      followUpActionNames: ['处理', '查看'],
      query: {
        contacts: null,
        phone: null,
        followUp: null
      }
    }
  },
  methods: {
    update() {
      this.$refs.remarkForm.validate(valid => {
        if (valid) {
          this.selectRow.followUp = 1
          const { id, remark, followUp } = this.selectRow
          updateTryCustomer({ id, remark, followUp }).then(() => {
            this.editorVisible = false
            this.$message.success('处理成功')
            this.$refs.list.updateRow(this.selectRow)
          }).catch(reason => {
            this.$message.error('处理失败')
          })
        }
      })
    },

    showProcessor(row) {
      this.editorVisible = true
      this.selectRow = this.$utils.deepcopy(row)
    }
  }
}
</script>

<style scoped>

</style>
