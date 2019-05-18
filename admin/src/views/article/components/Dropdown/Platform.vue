<template>
  <el-dropdown :hide-on-click="false" :show-timeout="100" trigger="click">
    <el-button plain>
      分类 ({{ platforms.length }})
      <i class="el-icon-caret-bottom el-icon--right" />
    </el-button>
    <el-dropdown-menu slot="dropdown" class="no-border">
      <el-checkbox-group v-model="platforms" style="padding: 5px 15px;">
        <el-checkbox v-for="item in platformsOptions" :key="item.key" :label="item.key">
          {{ item.name }}
        </el-checkbox>
      </el-checkbox-group>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
export default {
  props: {
    value: {
      required: true,
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      platformsOptions: [
        { key: '0', name: '公司动态' },
        { key: '1', name: '财税资讯' },
        { key: '2', name: '财税头条' }
      ]
    }
  },
  computed: {
    platforms: {
      get() {
        return this.value
      },
      set(val) {
        this.$emit('input', val)
      }
    }
  }
}
</script>
