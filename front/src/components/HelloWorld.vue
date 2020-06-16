<template>
    <div class="hello">
        <h1>{{ msg }}</h1>
        <el-input v-model="input" placeholder="请输入待转换的url" style="width: 30%"/>
        <el-row style="margin-top: 5px;margin-bottom: 5px">
            <el-button type="primary" v-on:click="getShortUrl(input)">转换</el-button>
            <el-button type="primary" v-on:click="clearInput()">取消</el-button>
        </el-row>
        <el-input v-model="shortUrl" :readonly=true style="width: 30%"/>
        <div class="visit" style="position: center">
            <el-table :data="tableData" style="width: 100%">
                <el-table-column
                        prop="rank"
                        label="排名"
                        width="50px">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="网址"
                        width="500px">
                </el-table-column>
                <el-table-column
                        prop="score"
                        label="访问频率">
                </el-table-column>
            </el-table>
        </div>
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
                timer: null,
                input: '',
                shortUrl: '',
                tableData: [{
                    rank: 1,
                    address: 'http://www.baidu.com',
                    score: 10
                }]
            }
        },
        beforeMount() {
            this.timer = setInterval(()=> {
                Axios.get('/api/rank').then(response => {
                    console.log(response);
                    this.tableData = response.data;
                }).catch(function (error) {
                    console.log(error);
                    alert('获取访问热榜失败');
                })
            }, 1000);
        },
        beforeDestroy() {
            clearInterval(this.timer);
            this.timer = null;
        },
        methods: {
            getShortUrl: function (sourceUrl) {
                if (sourceUrl.length <= 20) {
                    this.shortUrl = sourceUrl;
                    return
                }
                Axios.get('/api/generate?source=' + sourceUrl).then(response => {
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
