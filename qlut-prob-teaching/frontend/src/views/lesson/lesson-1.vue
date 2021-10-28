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
      <el-col :span="4"><div class="grid-content  bg-purple"></div></el-col>
      <el-col :span="16">
        <div class="grid-content question" v-for="(question, index) in questions " :key="index">
          <template v-if="index == showIndex">
            <div style="margin-bottom: 10px">
              <p>问题：</p>
              <img v-for="(t, index) in question.desc" :src="t" :key="index">
            </div>
            <div>
              <el-checkbox-group v-model="question.checkList">
                <el-checkbox :label="item.match (/\-\S\./g)[0].replace(/[-|\.]/g,'')" v-for="item in question.choices">
                  <img :src="item" alt="">
                </el-checkbox>
              </el-checkbox-group>
            </div>
            <div style="text-align: right">
              <el-button style="" type="primary" v-on:click="doClick()">确定</el-button>
            </div>
          </template>
        </div>
      </el-col>
      <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
    </el-row>
  </div>
</template>

<script>

import videojs from 'video.js'
import 'video.js/dist/video-js.css'

export default {
  name: 'lesson-1',
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
      let checkList = this.questions[this.showIndex].checkList.sort()
      let answer = this.questions[this.showIndex].answer.sort()
      // console.log(answer.join()==checkList.join())
      if( answer.join()==checkList.join() ) {
        this.showIndex = -1
        this.player.play()
      }
    }
  }
}

</script>

<style>
  .el-row {
    margin-bottom: 20px;
  }
  .question {
    text-align: left;
  }
  .bg-purple {
    /*background: #d3dce6;*/
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .el-checkbox{
    margin-right: 50px;
  }
</style>
