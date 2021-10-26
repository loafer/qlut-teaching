<template>
  <div class="body">
    <a-form layout="inline" :model="form" :rules="rules">
      <a-form-item>
        n:
        <a-input-number id="inputNumber" v-model="form.value" :min="1000" :max="250000"
                        v-decorator="['note', { rules: [{ required: true, message: 'Please input your note!' }] }]"/>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" :loading="loading" @click="doLoad" >
          确定
        </a-button>
      </a-form-item>
    </a-form>
    <a-row>
      <a-col :xs="{ span: 24, offset: 1}" :lg="{ span: 12, offset: 6 }">
        <div id="main" class="charts-panel"></div>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'law-of-large-numbers.vue',
  data () {
    return {
      title: '大数定律',
      loading: false,
      form: {
        value: 3000
      },
      rules: {
        value: [
          { required: true, message: 'Please input n', trigger: 'blur' }
        ]
      },
      charts: null,
      chartsOption: {
        title: {
          text: '大数定律'
        },
        tooltip: {},
        xAxis: {
          type: 'category',
          axisLabel: {
            interval: 1000
          }
        },
        yAxis: {
          type: 'value'
        },
        dataset: {
          dimensions: ['x', 'y'],
          source: []
        },
        series: [{
          type: 'line',
          encode: {
            x: 'x',
            y: 'y'
          }
        }]
      }
    }
  },
  mounted () {
    this.draw()
  },
  methods: {
    doLoad () {
      this.loading = true
      this.source = []
      const that = this

      that.charts.setOption({
        dataset: {
          source: []
        }
      })

      setTimeout(function () {
        that.charts.setOption({
          dataset: {
            source: that.dataset(that.form.value)
          }
        })
        that.loading = false
      }, 100)
    },
    draw () {
      this.charts = echarts.init(document.getElementById('main'))
      this.charts.setOption(this.chartsOption)
    },
    shuffle () {
      const seeds = [0, 100, 200, 300, 400, 500, 600]
      seeds.sort(function () {
        return 0.5 - Math.random()
      })
      return seeds.slice(0, 5)
    },
    buildY (n) {
      const list = []
      for (let i = 0; i < n; i++) {
        const yi = 3000 - this.shuffle().reduce((x, y) => x + y)
        list.push(yi)
      }
      return list.reduce((x, y) => x + y) / n
    },
    dataset (n) {
      // const start = new Date()
      const list = []
      for (let i = 1; i <= n; i++) {
        list.push([i, this.buildY(i)])
      }
      // const now = new Date()
      // var diff = now.getTime() - start.getTime()
      // console.log(diff / 1000)

      return list
    }
  }
}
</script>

<style scoped>
  .charts-panel {
    height: 400px;
  }
  .body{
    margin: 5px 2px;
  }
</style>
