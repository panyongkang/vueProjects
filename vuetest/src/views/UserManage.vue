<template>
    <div>
    <el-table
            :data="tableData"
            style="width: 100%">
        <el-table-column
                fixed
                prop="id"
                label="编号"
                width="180">
            <!--<template slot-scope="scope">-->
                <!--<span style="margin-left: 10px">{{ scope.row.id }}</span>-->
            <!--</template>-->
        </el-table-column>
        <el-table-column
                prop="name"
                label="姓名"
                width="180">
            <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                    <p>来了来了</p>
                    <div slot="reference" class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.name }}</el-tag>
                    </div>
                </el-popover>
            </template>
        </el-table-column>
        <el-table-column
                prop="sex"
                label="性别"
                width="180">
            <template slot-scope="scope">
                <span style="margin-left: 10px">{{ scope.row.sex }}</span>
            </template>
        </el-table-column>
        <el-table-column
                prop="birthday"
                label="注册日期"
                width="180">
            <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span style="margin-left: 10px">{{ scope.row.birthday }}</span>
            </template>
        </el-table-column>
        <el-table-column label="操作">
            <template slot-scope="scope">
                <el-button
                        size="mini"
                        @click="handleEdit(scope.row)">编辑</el-button>
                <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-pagination
            background
            layout="prev, pager, next"
            :page-size="pageSize"
            :total="total"
            @current-change="page"
    >
    </el-pagination>
    </div>

</template>

<script>
    export default {

        methods: {
            handleEdit(row) {
                this.$router.push({
                    path:'/UserUpdate',
                    //把需要修改的数据传过去
                    query:{
                        id:row.id
                    }
                })
            },
            handleDelete(row) {
                //console.log(index, row);
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this=this
                    axios.delete("http://localhost:8181/user/deleteById/"+row.id).then(function (resp) {
                        _this.$alert('《'+row.name+'》删除成功啦！', '友情提示', {
                            confirmButtonText: '确定',
                            //回调函数； _this.$router.push表示回调的路径
                            callback: action => {
                                //    动态刷新
                                window.location.reload()
                            }
                        });
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            page(currentPage){

                //动态获取后端数据的分页查询操作
                const _this = this
                axios.get('http://localhost:8181/user/findAll/'+(currentPage-1)+'/4').then(function(resp){
                    //console.log(resp)
                    _this.tableData = resp.data.content
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.totalElements
                })

            }
    },
        data() {
            return {
                pageSize:'1',
                total:'9',
                tableData: [
                    {
                        id:'1',
                        birthday: '2018-05-02',
                        name: '王小虎',
                        sex:'男',
                        address: '上海市普陀区金沙江路'
                    },
                    {
                        id:'2',
                        birthday: '2019-05-04',
                        name: '马晓丽',
                        sex:'女',
                        address: '天津市华南区小草路'
                    },
                    {
                        id:'3',
                        birthday: '2017-05-03',
                        name: '王二小',
                        sex:'男',
                        address: '凯里市未来城1314栋三单元 1516 室'
                    }
                ]
            }
        },
        created(){
            const _this=this
            axios.get('http://localhost:8181/user/findAll/0/4').then(function (resp) {
                console.log(resp);
                _this.tableData=resp.data.content
                _this.pageSize=resp.data.size
                _this.total=resp.data.totalElements
            })
        }
    }
</script>