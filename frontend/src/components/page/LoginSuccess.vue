<template>
    <div id="main-page-loginSuccess">
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
  },
  mounted () {
    const res = {
      code: 200,
      messasge: 'LoginSuccess',
      data: {
        token: 'ToKen'
      }
    }

    if (window.opener) {
      window.opener.postMessage(res, '*')
      window.close()
    }

    window.postMessage(res)
    if (window.ReactNativeWebView) {
      window.ReactNativeWebView.postMessage(res)
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
