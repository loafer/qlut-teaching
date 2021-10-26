<template>
  <div class="body">
    <a-list item-layout="horizontal" :data-source="data">
      <a-list-item slot="renderItem" slot-scope="item">
        <a-list-item-meta>
          <div slot="title">
            <a-icon slot="icon" type="arrow-right" style="margin-right: 10px;"/>
            <a :href="item.url">{{ item.title }}</a>
          </div>
        </a-list-item-meta>
      </a-list-item>
    </a-list>
  </div>
</template>
<script>
const data = [
  { title: '大数定律视频', url: '/lessons/lesson-1' }
]

export default {
  name: 'lesson-list',
  data () {
    return {
      data
    }
  }
}
</script>

<style scoped>

</style>
