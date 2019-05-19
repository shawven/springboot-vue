<template>
  <div>
    <base-list ref="list" :action="action" :query.sync="query">
      <template slot="filters">
        <el-input v-model="query.contacts" placeholder="姓名" style="width: 200px;" class="filter-item" clearable />
        <el-input v-model="query.phone" placeholder="电话" style="width: 200px;" class="filter-item" clearable />
        <el-select v-model="query.followUp" placeholder="状态" style="width: 130px;" class="filter-item" clearable>
          <el-option v-for="(state, index) in states" :key="index" :label="state" :value="state" />
        </el-select>
        <el-select v-model="query.type" placeholder="代理类型" style="width: 130px;" class="filter-item" clearable>
          <el-option v-for="(value, index) in proxyTypes" :key="index" :label="value" :value="index" />
        </el-select>
        <el-select v-model="query.level" placeholder="代理级别" style="width: 130px;" class="filter-item" clearable>
          <el-option v-for="(value, index) in proxyLevels" :key="index" :label="value" :value="index" />
        </el-select>
      </template>

      <el-table-column label="序号" prop="id" sortable="custom" align="center" width="100" />
      <el-table-column label="联系人" prop="contacts" width="150" align="center" />
      <el-table-column label="联系电话" prop="phone" width="110" align="center" />
      <el-table-column label="公司名称" prop="company" align="center" />
      <el-table-column label="代理类型" width="100" align="center">
        <template slot-scope="{row}">
          {{ proxyTypes[row.type] }}
        </template>
      </el-table-column>
      <el-table-column label="代理级别" width="100" align="center">
        <template slot-scope="{row}">
          {{ proxyLevels[row.level] }}
        </template>
      </el-table-column>
      <el-table-column label="所在地区" align="center">
        <template slot-scope="{row}">
          {{ row.province }} {{ row.city }}
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" width="100" class-name="status-col">
        <template slot-scope="{row}">
          <el-tag :type="stateColors[row.status]">{{ states[row.status] }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" prop="remark" align="center" />
      <el-table-column label="处理时间" prop="updateTime" align="center" />
      <el-table-column label="申请时间" prop="createTime" align="center" />
      <el-table-column label="操作" align="center">
        <template slot-scope="{row}">
          <el-button type="text" @click="showProcessor(row)">{{ actionNames[row.status] }}</el-button>
        </template>
      </el-table-column>
    </base-list>

    <el-dialog title="处理招商合作" :visible.sync="editorVisible" width="500px">
      <el-form ref="remarkForm" :model="selectRow" label-position="left" label-width="70px">
        <el-form-item label="备注：" prop="remark" :rules="[{required:true, message:'请输入备注'}]">
          <template v-if="selectRow.status">
            {{ selectRow.remark }}
          </template>
          <template v-else>
            <el-input
              v-model="selectRow.remark"
              type="textarea"
              placeholder="请输入备注"
              maxlength="100"
              :rows="4"
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
import { fetchCopartnerList, updateCopartnerCustomer } from '@/api/customer'

export default {
  name: 'CopartnerIndex',
  data() {
    return {
      action: fetchCopartnerList,
      editorVisible: false,
      selectRow: {},
      proxyTypes: ['个人代理', '企业代理'],
      proxyLevels: ['创客', '合伙人', 'VIP合伙人'],
      states: ['未处理', '已处理'],
      stateColors: ['danger', 'success'],
      actionNames: ['处理', '查看'],
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
        this.$refs.remarkForm.validate(valid => {
          if (valid) {
            this.selectRow.status = 1
            const { id, remark, status } = this.selectRow
            updateCopartnerCustomer({ id, remark, status }).then((response) => {
              const { updateTime } = response.data
              this.editorVisible = false
              this.$message.success('处理成功')
              this.$refs.list.updateRow({ ...this.selectRow, updateTime })
            }).catch(reason => {
              this.$message.error(reason.message)
            })
          }
        })
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
