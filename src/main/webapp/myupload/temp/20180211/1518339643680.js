//app.js
App({
  onLaunch: function () {
    var that = this;
    wx.checkSession({
      success: function () {

      },
      fail: function () {
        //调用登录接口
        wx.login({
          success: function (r) {
            var code = r.code;//登录凭证
            wx.getUserInfo({
              success: function (res) {
                //请求自己的服务器，解密用户信息 获取unionId等加密信息
                wx.request({
                  url: that.globalData.baseUrl + '/wechatApp/decodeUserInfo',//自己的服务接口地址
                  method: 'post',
                  header: {
                    'content-type': 'application/x-www-form-urlencoded'
                  },
                  data: { encryptedData: res.encryptedData, iv: res.iv, code: code },
                  success: function (rsp) {
                    if (rsp.data.success) {
                      var session_id = rsp.data.data;
                      if (session_id != null && session_id != "") {
                        wx.setStorageSync('LCSSESSIONID', session_id) //如果本地没有就说明第一次请求 把返回的session id 存入本地                   
                      }
                    }
                  },
                  fail: function () {
                    console.log('系统错误')
                  }
                })
              }
            })
          }
        })
      }
    })
  },
  globalData:{
    baseUrl: "https://m.lecaishui.com",
    loginUser: null
  }
})