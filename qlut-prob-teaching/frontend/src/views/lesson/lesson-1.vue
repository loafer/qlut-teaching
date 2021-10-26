<template>
  <div>
    <video id="my-player"
           class="video-js"
           controls
           autoplay
           preload="auto"
           height="300"
           width="600"
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
      questions: [
        { desc: '', choices: [''] }
      ]
    }
  },
  mounted () {
    this.player = videojs(document.getElementById('my-player'), {}, function onPlayerReady () {
      videojs.log('Your player is ready!')

      this.on('timeupdate', function (event) {
        var currentTime = parseInt(this.currentTime())
        var duration = this.duration()
        var points = [10, 88, 169, 236]
        console.log('currentTime1', this.currentTime(), 'currentTime2', currentTime, 'duration', duration)

        if (points.includes(currentTime)) {
          this.pause()
        }
      })
    })
  },
  beforeDestroy () {
    if (this.player) {
      this.player.dispose()
    }
  }
}

</script>

<style scoped>

</style>
