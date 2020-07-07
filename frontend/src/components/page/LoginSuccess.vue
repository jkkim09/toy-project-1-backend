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
        token: 'testToKen'
      }
    }
    window.opener.postMessage(res, '*')
    window.close()
  }
}
</script>

<style scoped>
    #main-page-loginSuccess {
        width: 100%;
        height: 100%;
    }
</style>
