<template>
  <div style="margin: 10px auto">
    <el-row>
      <el-col :span="24">
        <video id="my-player" style="display: block; margin: 0 auto"
               class="video-js"
               autoplay
               preload="auto"
               height="300px"
               width="600px"
               language="zh-CN">
          <source src="/video/lesson-1/video.mp4" type="video/mp4"/>
          <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading to a
            web browser that
            <a href="https://videojs.com/html5-video-support/" target="_blank">
              supports HTML5 video
            </a>
          </p>
        </video>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="4"><div class="grid-content"></div></el-col>
      <el-col :span="16">
        <div class="grid-content question" v-for="(question, index) in questions " :key="index">
          <template v-if="index == showIndex">
            <div style="margin-bottom: 10px">
              <p>问题：</p>
              <img v-for="(t, index) in question.desc" :src="t" :key="index">
            </div>
            <div class="box">
              <draggable
                         :list="question.choices"
                         :group="{ name: 'question', pull: 'clone', put: false }" @change="log">
                <el-card shadow="never" v-for="(item, index) in question.choices" :key="index">
                  <img :src="item" class="image">
                </el-card>
              </draggable>
            </div>
            <div class="box answer-box" style="border: 1px dashed;">
              <p>请将上方选项中的标准答案拖入改区域内，并注意顺序。点击答案右上角 [X] 可删除选项。</p>
              <draggable :list="question.checkList" group="question" @change="log">
                <el-card shadow="never" v-for="(item, index) in question.checkList" :key="index">
                  <img :src="item" class="image">
                  <i class="el-icon-close" style="float: right" @click="removeAt(index)"></i>
                </el-card>
              </draggable>
            </div>
            <div style="text-align: right">
              <el-button style="" type="primary" v-on:click="doClick()">确定</el-button>
            </div>
          </template>
        </div>
      </el-col>
      <el-col :span="4"><div class="grid-content"></div></el-col>
    </el-row>
  </div>
</template>

<script>

import videojs from 'video.js'
import 'video.js/dist/video-js.css'
import draggable from 'vuedraggable'

export default {
  name: 'lesson-1',
  components: {
    draggable
  },
  data () {
    return {
      player: null,
      showIndex: null,
      showQuestion: false,
      questions: [
        {
          desc: ['/video/lesson-1/q-1-0.png', '/video/lesson-1/q-1-1.png'],
          choices: [
            '/video/lesson-1/q-1-c-1.png',
            '/video/lesson-1/q-1-c-2.png',
            '/video/lesson-1/q-1-c-3.png',
            '/video/lesson-1/q-1-c-4.png',
            '/video/lesson-1/q-1-c-5.png',
            '/video/lesson-1/q-1-c-6.png'
          ].sort(function () {
            return 0.5 - Math.random()
          }),
          answer: ['1', '2', '3'],
          checkList: []
        },
        {
          desc: ['/video/lesson-1/q-2-0.png', '/video/lesson-1/q-2-1.png'],
          choices: [
            '/video/lesson-1/q-2-c-1.png',
            '/video/lesson-1/q-2-c-2.png',
            '/video/lesson-1/q-2-c-3.png',
            '/video/lesson-1/q-2-c-4.png',
            '/video/lesson-1/q-2-c-5.png',
            '/video/lesson-1/q-2-c-6.png',
            '/video/lesson-1/q-2-c-7.png',
            '/video/lesson-1/q-2-c-8.png'
          ].sort(function () {
            return 0.5 - Math.random()
          }),
          answer: ['1', '2', '3'],
          checkList: []
        },
        {
          desc: ['/video/lesson-1/q-3-0.png'],
          choices: [
            '/video/lesson-1/q-3-c-1.png',
            '/video/lesson-1/q-3-c-2.png',
            '/video/lesson-1/q-3-c-3.png',
            '/video/lesson-1/q-3-c-4.png',
            '/video/lesson-1/q-3-c-5.png',
            '/video/lesson-1/q-3-c-6.png',
            '/video/lesson-1/q-3-c-7.png',
            '/video/lesson-1/q-3-c-8.png',
            '/video/lesson-1/q-3-c-9.png'
          ].sort(function () {
            return 0.5 - Math.random()
          }),
          answer: ['1', '2', '3'],
          checkList: []
        }
      ]
    }
  },
  mounted () {
    this.player = videojs(document.getElementById('my-player'), {}, () => {
      videojs.log('Your player is ready!')

      this.player.on('timeupdate', (event) => {
        let currentTime = parseInt(this.player.currentTime())
        let points = [88, 169, 236]
        // let points = [10, 15, 20]
        // console.log('currentTime', currentTime)

        if (points.includes(currentTime)) {
          let index = points.indexOf(currentTime)
          if(this.questions[index].checkList.length == 0) {
            this.showIndex = index
            this.player.pause()
          }
        }
      })
    })
  },
  beforeDestroy () {
    if (this.player) {
      this.player.dispose()
    }
  },
  methods: {
    doClick() {
      let checkList = this.questions[this.showIndex].checkList.map((e) => {
        return e.match (/\-\S\./g)[0].replace(/[-|\.]/g,'')
      })
      let answer = this.questions[this.showIndex].answer
      // console.log(answer.join()==checkList.join())
      if( answer.join()==checkList.join() ) {
        this.showIndex = -1
        this.player.play()
      }
    },
    removeAt(idx) {
      this.questions[this.showIndex].checkList.splice(idx, 1);
    },
    log(event) {
      // console.log('1', this.questions[0].checkList)
      // console.log('2', this.questions[1].checkList)
      // console.log('3', this.questions[2].checkList)
    }
  }
}

</script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
  }
  .question {
    text-align: left;
  }
  .bg-purple {
    /*background: #d3dce6;*/
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  ::v-deep .el-card {
    min-width: 20px;
    max-width: 200px;
    display: inline-block;
    margin-right: 10px;
    cursor: pointer;
  }
  .image {
    height: 40px;
    width: 40px;
    object-fit: contain;
  }
  .box {
    padding-left: 10px;
    padding-right: 10px;
    min-height: 60px;
    min-width: 100px;
    margin-bottom: 10px;
  }
  .answer-box {
    border: 1px dotted;
  }
  .answer-box p {
    color: darkgray;
  }
  ::v-deep .el-card__body{
    position: relative;
  }
  ::v-deep .el-card__body i{
    position: absolute;
    top: 5px;
    right: 5px;
  }
</style>
