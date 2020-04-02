<template>
    <div>
        <el-switch
                v-model="value1"
                active-text="按月付费"
                inactive-text="月费开关">
        </el-switch>
        <el-switch
                style="display: block"
                v-model="value2"
                active-color="#13ce66"
                inactive-color="#ff4949"
                active-text="按年付费"
                inactive-text="年费开关">
        </el-switch>
    </div>

</template>
<script>
    export default {
        data() {
            return {
                value1: true,
                value2: true
            }
        }
    };
</script>