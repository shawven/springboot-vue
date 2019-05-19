<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">

      <sticky :z-index="10" :class-name="navbarClass">
        <PlatformDropdown v-model="postForm.categories" />
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm">
          发布
        </el-button>
        <el-button v-loading="loading" type="warning" @click="draftForm">
          草稿
        </el-button>
      </sticky>

      <div class="createPost-main-container">
        <el-row>
          <el-col :span="24">
            <el-form-item prop="subject">
              <MDinput v-model="postForm.subject" :maxlength="100">
                标题
              </MDinput>
              <span v-show="subjectLength" class="word-counter">{{ subjectLength }}字</span>
            </el-form-item>
          </el-col>
        </el-row>
        <div style="color:#606266; font-size: 18px" class="mb-1">简介</div>
        <el-form-item prop="description" style="margin-bottom: 40px;">
          <el-input v-model="postForm.description" :rows="1" type="textarea" class="article-textarea" autosize placeholder="请输入简介" />
          <span v-show="descLength" class="word-counter">{{ descLength }}字</span>
        </el-form-item>
        <div style="color:#606266; font-size: 18px" class="mb-1">封面</div>
        <el-form-item prop="cover" style="margin-bottom: 30px;" class="cover">
          <Upload v-model="postForm.cover" />
        </el-form-item>
        <el-form-item prop="content" style="margin-bottom: 30px;">
          <Tinymce ref="editor" v-model="postForm.content" :height="400" />
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce/index'
import Upload from '@/components/Upload/SingleImage3'
import MDinput from '@/components/MDinput/index'
import Sticky from '@/components/Sticky/index' // 粘性header组件
import { validURL } from '@/utils/validate'
import { fetchArticle } from '@/api/article'

import { PlatformDropdown } from './Dropdown'

export default {
  name: 'ArticleDetail',
  components: { Tinymce, MDinput, Upload, Sticky, PlatformDropdown },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    const validateSourceUri = (rule, value, callback) => {
      if (value && !validURL(value)) {
        callback(new Error('无效的URL地址'))
      } else {
        callback()
      }
    }

    const defaultForm = {
      id: null,
      subject: '',
      description: '',
      content: '',
      createTime: null,
      cover: null,
      platforms: [],
      categories: [],
      sort: null
    }
    return {
      defaultForm,
      postForm: Object.assign({}, defaultForm),
      loading: false,
      userListOptions: [],
      rules: {
        subject: [{ required: true, message: '标题不能为空' }],
        description: [{ required: true, message: '简介不能为空' }],
        content: [{ required: true, message: '内容不能为空' }],
        cover: [{ required: true, validator: validateSourceUri }]
      },
      tempRoute: {}
    }
  },
  computed: {
    navbarClass() {
      return 'sub-navbar ' + (this.postForm.enable ? 'published' : 'draft')
    },
    subjectLength() {
      return this.postForm.subject.length
    },
    descLength() {
      return this.postForm.description.length
    }
  },
  created() {
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id
      this.fetchData(id)
    } else {
      this.postForm = Object.assign({}, this.defaultForm)
    }

    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route)
  },
  methods: {
    fetchData(id) {
      fetchArticle(id).then(response => {
        this.postForm = response.data
        // Set tagsview title
        this.setTagsViewTitle()
      }).catch(err => {
        console.log(err)
      })
    },
    setTagsViewTitle() {
      const title = '编辑文章'
      const route = Object.assign({}, this.tempRoute, { title: `${title}-${this.postForm.id}` })
      this.$store.dispatch('tagsView/updateVisitedView', route)
    },
    submitForm() {
      console.log(this.postForm)
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$notify.success({
            title: '成功',
            message: '发布文章成功',
            duration: 2000
          })
          this.postForm.status = 'published'
          this.loading = false
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    draftForm() {
      if (this.postForm.content.length === 0 || this.postForm.subject.length === 0) {
        this.$message.error({
          message: '请填写必要的标题和内容'
        })
        return
      }
      this.$message.success({
        message: '保存成功',
        showClose: true,
        duration: 1000
      })
      this.postForm.status = 'draft'
    }
  }
}
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";

.createPost-container {
  position: relative;

  .createPost-main-container {
    padding: 40px 45px 20px 50px;

    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;

      .postInfo-container-item {
        float: left;
      }
    }
    .word-counter {
      width: 40px;
      position: absolute;
      right: 10px;
      top: 0px;
    }
  }
}

.article-textarea /deep/ {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
}

.cover {
  label {
    font-weight: normal !important;
    font-size: 18px !important;
  }
}

</style>
