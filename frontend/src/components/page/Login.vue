<template>
    <div id="login-page-container">
        <table>
          <tr>
            <td>
              <div id="login-button-low-area">
                <div @click="click" class="kakao-login-button"></div>
                <div @click="test">test2 Click</div>
              </div>
            </td>
          </tr>
        </table>
    </div>
</template>

<script>

export default {
  name: 'Login',
  components: {
  },
  data () {
    return {
      Http: {},
      name: 'test name'
    }
  },
  methods: {
    click: function () {
      const options = 'width=500, height=500, scrollbars, resizable, location=no, menubar=no, status=no, toolbar=no'
      window.open('/oauth2/authorization/kakao', '_blank', options)
    },
    test: function () {
      // window.location.href = '/kakao'
      this.$http.get('/kakao').then((e) => {
        console.log(e)
      })
    },
    onSuccess: function (data) {
      console.log(data)
    },
    onFailure: function (data) {
      console.log('onFailure', data)
    },
    listener: function (e) {
      let re = e.data
      if (typeof e.data === 'string') {
        re = JSON.parse(re)
      }

      if (re.code === 200) {
        this.$router.push('/view/main')
      }
    }
  },
  mounted () {
    window.onmessage = this.listener
  }
}
</script>

<style scoped>
    #login-page-container {
        width: 100%;
        height: 100%;
    }

    #login-page-container > table {
      width: 100%;
      height: 100%;
      text-align: center;
    }

    #login-button-low-area {
      width: 300px;
      height: auto;
      display: inline-block;
    }

    #login-button-low-area > div:nth-child(n+2) {
      margin-top: 15px;
    }

    .kakao-login-button {
      background: url('../../assets/img/kakao_login_medium_wide.png') no-repeat;
      width: 300px;
      height: 45px;
    }
</style>
