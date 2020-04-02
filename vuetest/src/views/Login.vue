<template>
    <div class="login">
        <el-form style="width:40%;margin: 0px auto" label-width="100px" class="demo-ruleForm">
            <el-form-item  label="用户名" prop="name">
                <el-input placeholder="请输入用户名" v-model="name" clearable class="input_style"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="author">
                <el-input  placeholder="请输入密码" v-model="pwd" show-password class="input_style"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')" class="login_style">登录</el-button>
                <el-button @click="resetForm('ruleForm')" class="login_style">重置</el-button>
                <span style="float:right;color: #A9A9AB"><a href="../components/HelloWorld.vue">忘记密码？</a></span>
            </el-form-item>

        </el-form>
    </div>

</template>

<script>
    export default {
        name: "Login",
        data(){
            return {
                name: '',
                pwd : '',
                error : {
                    name: '',
                    pwd : ''
                }
            }
        },
        methods:{
            login(){
                const { name, pwd, $router} = this
                this.$router.push({
                    name: "Main",
                    params: {
                        username: this.name
                    }
                });
            }
        }
    }
</script>

<style>
    .login{
        margin-top: 100px;
    }
    .input_style{
        width: 280px;
        margin-bottom: 10px;
    }
    .login_style{
        width: 100px;
    }
</style>
