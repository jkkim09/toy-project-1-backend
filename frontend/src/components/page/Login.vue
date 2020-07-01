<template>
    <div id="login-page-container">
        <table>
          <tr>
            <td>
              <div id="login-button-low-area">
                <KakaoLogin
                api-key="97cd7dd00e85b48757982d22b0ba4269"
                image="kakao_account_login_btn_medium_wide"
                :on-success=onSuccess
                :on-failure=onFailure
                />
                <div @click="click">test Click</div>
              </div>
            </td>
          </tr>
        </table>
    </div>
</template>

<script>
import KakaoLogin from 'vue-kakao-login'

export default {
  name: 'Login',
  components: {
    KakaoLogin
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    click: () => {
      const key = '97cd7dd00e85b48757982d22b0ba4269'
      const re = 'http://localhost:8083/api/user/login/kakao'
      window.location.href = 'https://kauth.kakao.com/oauth/authorize?client_id=' + key + '&redirect_uri=' + re + '&response_type=code'
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

    #login-button-low-area > div:nth-child(n+1) {
      margin-top: 15px;
    }
</style>
