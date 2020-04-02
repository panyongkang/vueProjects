<template>
    <div id="app">
        <el-container style="height: 600px; border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <!--2.使用router去设置动态菜单栏
                    使用:index="index+' ' "目的是打开导航1,2不会同时打开，用哪个打开哪个
                    :index="index+'-'+index2"目的是点击页面1,2可以显示默认
                   菜单menu与router的绑定
                        在标签中添加router属性
                        :index="item2.path"是点击哪个页面就跳转哪个页面路径
                        :class="$route.path==item2.path?'is-active':''"是利用三元运算符判断哪个路径就默认显示该状态is-active
                        v-if="item.show"表示true就显示show:true
                         <el-menu router :default-openeds="['0','1']">：:default-openeds="['0','1']"表示设置默认打开的导航栏
                -->
                <el-menu router>
                    <el-submenu v-for="(item,index) in $router.options.routes" :index="index+'' " v-if="item.show">
                        <template slot="title"><i class="el-icon-setting"></i>{{item.name}}</template>
                        <!--<el-menu-item v-for="(item2,index2) in item.children " :index="index+'-'+index2">{{item2.name}}</el-menu-item>-->
                        <el-menu-item v-for="(item2,index2) in item.children " :index="item2.path" :class="$route.path==item2.path?'is-active':''">{{item2.name}}</el-menu-item>
                    </el-submenu>
                </el-menu>

            </el-aside>

            <el-container>

                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>

    </div>
</template>

<style>

</style>

<script>

</script>