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
  { title: '大数定律', url: '/law-of-large-numbers' }
]

export default {
  name: 'Home',
  data () {
    return {
      data
    }
  }
}
</script>

<style>
  .body{
    margin: 5px 2px;
  }
</style>
