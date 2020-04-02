<template>
    <!--
        :model用于绑定数据的
        ：rules用于绑定校验规则的
        ref="ruleForm"相当于给表单设置名字，才能引用到@click事件才能拿到数据，实现添加弹框和重置操作
    -->
    <div>
        <el-form style="width:40%;margin: 30px auto"  :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item  label="图书名" prop="name">
                <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="作者" prop="author">
                <el-input v-model="ruleForm.author"></el-input>
            </el-form-item>
            <el-form-item style="text-align: center">
                <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
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
                        axios.post("http://localhost:8181/book/save",this.ruleForm).then(function (resp) {
                            //console.log(resp)
                            if (resp.data == 'success') {
                                _this.$alert('《'+_this.ruleForm.name+'》添加成功啦！', '友情提示', {
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
        }
    }
</script>