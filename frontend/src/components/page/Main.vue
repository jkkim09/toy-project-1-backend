<template>
    <div id="main-page-container">
        <h1>test</h1>
    </div>
</template>

<script>

export default {
  name: 'Main',
  components: {
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    click: () => {
      window.location.href = '/oauth2/authorization/kakao'
    },
    onSuccess: (data) => {
      console.log('onSuccess', data)
    },
    onFailure: (data) => {
      console.log('onFailure', data)
    }
  }
}
</script>

<style scoped>
    #main-page-container {
        width: 100%;
        height: 100%;
    }
</style>
