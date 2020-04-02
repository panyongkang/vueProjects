<template>
    <div>
        <el-container>
            <el-header style="text-align: right; font-size: 12px">
                <el-dropdown>
                    <i class="el-icon-setting" style="margin-right: 15px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>用户信息</el-dropdown-item>
                        <el-dropdown-item @click.native="loginout">退出</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span class="sp">{{name}}</span>
            </el-header>
        </el-container>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        methods: {
            loginout() {
                //console.log("大师傅还是犯傻")
                axios.get('http://localhost:8080/')
                    .then(response => {
                        this.$router.push('/login');
                    });
            }
        },
        data(){
           return{
               name:'潘少主'
           }
       }
    }
</script>

<style>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }
    .sp{
        color: rgba(2, 0, 4, 1);
        font-size: 14px;
    }
</style>