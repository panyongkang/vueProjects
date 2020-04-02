<template>
    <div>
        <el-table
                :data="tableData"
                border
                style="width: 100%;margin-bottom: 10px;padding-bottom: 20px">
            <el-table-column
                    fixed
                    prop="id"
                    label="编号"
                    width="252px">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="name"
                    label="图书名"
                    width="252px">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="author"
                    label="作者"
                    width="252px">
            </el-table-column>
            <el-table-column
                    fixed
                    label="操作"
                    width="252px">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" type="info" size="mini" >编辑</el-button>
                    <el-button @click="deleteBook(scope.row)" type="danger" size="mini">删除</el-button>
                </template>
            </el-table-column>
            <el-table-column
                    fixed
                    label="备注"
                    width="252px">
                <template slot-scope="scope">
                    <el-input
                            type="textarea"
                            :rows="2"
                            autosize
                            placeholder="请输入内容"
                            v-model="textarea">
                    </el-input>
                </template>
            </el-table-column>
        </el-table>

        <!--分页
             :page-size="3"表示每页3条记录
             :total="30"总记录数30，默认除以10得到共10页
             @current-change="page"表示触发点击事件page()方法
            -->
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
            deleteBook(row){
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    const _this=this
                    axios.delete("http://localhost:8181/book/deleteById/"+row.id).then(function (resp) {
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
            edit(row) {
               // console.log(row);
                this.$router.push({
                    path:'/BookUpdate',
                    //把需要修改的数据传过去
                    query:{
                        id:row.id
                    }
                })
            },
            page(currentPage){

                //动态获取后端数据的分页查询操作
                const _this = this
                axios.get('http://localhost:8181/book/findAll/'+(currentPage-1)+'/6').then(function(resp){
                    //console.log(resp)
                    _this.tableData = resp.data.content
                    _this.pageSize = resp.data.size
                    _this.total = resp.data.totalElements
                })

            }
        },

        data() {
            return {
                textarea: '',
                pageSize:'1',
                total:'11',
                tableData: [{
                    id: '1',
                    name: '解忧杂货铺',
                    author:'东野圭吾'
                }, {
                    id: '2',
                    name: '末日黄昏',
                    author:'蔡柳大'
                }, {
                    id: '3',
                    name: '朝夕',
                    author:'老舍'
                }
                ]
            }
        },
        created(){
            const _this=this
            axios.get('http://localhost:8181/book/findAll/0/6').then(function (resp) {
               // console.log(resp);
                _this.tableData=resp.data.content
                _this.pageSize=resp.data.size
                _this.total=resp.data.totalElements
            })
        }

    }
</script>
