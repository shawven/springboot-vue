<template>
  <div class="filter-container" @keydown.enter="$emit('search')">
    <slot />
    <el-button
      icon="el-icon-search"
      class="filter-item"
      type="primary"
      :loading="loading"
      @click="$emit('search')"
    >查询
    </el-button>
    <el-button
      icon="el-icon-refresh"
      class="filter-item"
      @click="$emit('reset')"
    >重置
    </el-button>
    <div class="button-list">
      <slot name="buttons" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'BaseSearcher',
  props: {
    loading: {
      type: Boolean,
      default: false
    }
  }
}
</script>
<style lang="scss" scoped>
.button-list {
  display: inline-block;
  >button {
    display: inline-block;
    vertical-align: middle;
    margin-left: 10px;
    margin-bottom: 10px;
  }
}
</style>
