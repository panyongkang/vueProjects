<template>
    <div>
        <el-container>
            <el-header style="text-align: right; font-size: 12px">
                <el-dropdown>
                    <i class="el-icon-setting" style="margin-right: 15px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>查看</el-dropdown-item>
                        <el-dropdown-item>新增</el-dropdown-item>
                        <el-dropdown-item>删除</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span class="sp">潘多拉</span>
            </el-header>
        </el-container>
        <!--
       :model用于绑定数据的
       ：rules用于绑定校验规则的
       ref="ruleForm"相当于给表单设置名字，才能引用到@click事件才能拿到数据，实现添加弹框和重置操作
   -->
        <el-form style="width: 40%;margin: 30px auto" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="编号" >
                <el-input v-model="ruleForm.id" readonly></el-input>
            </el-form-item>
            <el-form-item label="图书名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="作者" prop="author">
                <el-input v-model="ruleForm.author"></el-input>
            </el-form-item>
            <el-form-item style="text-align: center">
                <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    name: '',
                    author: '',
                },
                rules: {
                    name: [
                        { required: true, message: '请输入图书名称', trigger: 'blur' },
                        { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
                    ],
                    author: [
                        { required: true, message: '请输入作者名称', trigger: 'blur' },
                        { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this=this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //console.log(_this.ruleForm)这里拿到输入的数据之后就可以传给后端了
                        axios.put("http://localhost:8181/book/update",this.ruleForm).then(function (resp) {
                            //console.log(resp)
                            if (resp.data == 'success') {
                                _this.$alert('《'+_this.ruleForm.name+'》修改成功啦！', '友情提示', {
                                    confirmButtonText: '确定',
                                    //回调函数； _this.$router.push表示回调的路径
                                    callback: action => {
                                        _this.$router.push('/BookManage')
                                    }
                                });
                            }

                        })
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created(){
            //测试是否获取需要修改的数据
            //alert(this.$route.query.id)
            const _this=this
            axios.get('http://localhost:8181/book/findById/'+this.$route.query.id).then(function (resp) {
                _this.ruleForm=resp.data
            })
        }
    }
</script>