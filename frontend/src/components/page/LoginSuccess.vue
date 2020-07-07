<template>
    <div id="main-page-loginSuccess">
      <button @click="click">test</button>
    </div>
</template>

<script>

export default {
  name: 'LoginSuccess',
  components: {
  },
  data () {
    return {
    }
  },
  methods: {
    click: function () {
      console.log('webview post message')
      window.postMessage('test web view')
    }
  },
  mounted () {
    const res = {
      code: 200,
      messasge: 'LoginSuccess',
      data: {
        token: 'testToKen'
      }
    }
    console.log(window.opener)
    if (window.opener) {
      window.opener.postMessage(res, '*')
      window.close()
    }
  }
}
</script>

<style scoped>
    #main-page-loginSuccess {
        width: 100%;
        height: 100%;
    }

    button {
      width: 200px;
      height: 50px;
    }
</style>
