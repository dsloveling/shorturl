<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <el-input v-model="input" placeholder="请输入待转换的url" style="width: 30%"/>
    <el-row style="margin-top: 5px">
      <el-button type="primary" v-on:click="getShortUrl(input)">转换</el-button>
      <el-button type="primary" v-on:click="clearInput()">取消</el-button>
    </el-row>
    <p>转换后的url为</p>
    <el-input v-model="shortUrl" :readonly=true style="width: 30%"/>
  </div>
</template>

<script>
  import Axios from 'axios'

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      input: '',
      shortUrl: ''
    }
  },
  methods: {
    getShortUrl: function (sourceUrl) {
      if(sourceUrl.length <= 20) {
        this.shortUrl =  sourceUrl;
        return
      }
      Axios.get('/api/generate?sourceUrl=' + sourceUrl).then(response => {
        console.log(response)
        this.shortUrl = response.data
      }).catch(function (error) {
        console.log(error)
        alert("服务出错,请联系管理员")
      })
    },
    clearInput() {
      this.input = ''
      this.shortUrl = ''
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
