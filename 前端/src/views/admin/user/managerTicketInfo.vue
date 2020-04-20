<!--
  -    Copyright (c) 2018-2025, lengleng All rights reserved.
  -
  - Redistribution and use in source and binary forms, with or without
  - modification, are permitted provided that the following conditions are met:
  -
  - Redistributions of source code must retain the above copyright notice,
  - this list of conditions and the following disclaimer.
  - Redistributions in binary form must reproduce the above copyright
  - notice, this list of conditions and the following disclaimer in the
  - documentation and/or other materials provided with the distribution.
  - Neither the name of the pig4cloud.com developer nor the names of its
  - contributors may be used to endorse or promote products derived from
  - this software without specific prior written permission.
  - Author: lengleng (wangiegie@gmail.com)
  -->

<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">

      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="请输入客户姓名" v-model="listQuery.username"></el-input>

      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleFilter">搜索</el-button> 
    <!--  <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="edit">添加</el-button> -->
    </div>
    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row style="width: 99%">
	
	   <el-table-column  type="index" label="序号" align="center" width="50px" >
       </el-table-column>

    <!--  <el-table-column disabled="true" align="center" label="id">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>-->

      <el-table-column  align="center" label="客户姓名">
        <template slot-scope="scope" disabled="true">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>  

      <el-table-column align="center" label="电影名">
        <template slot-scope="scope">
          <span>{{ scope.row.movieName }}</span>
        </template>
      </el-table-column>
      <el-table-column :width="columnWidth" align="center" label="详细内容">
        <template slot-scope="scope">
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="开始时间">
        <template slot-scope="scope">
          <span>{{ scope.row.starttime }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="结束时间">
        <template slot-scope="scope">
          <span>{{ scope.row.endtime }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="状态">
        <template slot-scope="scope">
          <span>{{ scope.row.state==1?'可使用':scope.row.state==2?'已退票':'已验票' }}</span>
        </template>
      </el-table-column>
	  
	 <el-table-column align="center" label="验证码">
        <template slot-scope="scope">
          <span>{{ scope.row.verificationcode }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="位置">
        <template slot-scope="scope">
          <span>{{ scope.row.position }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button v-if="scope.row.state==1" size="mini" type="danger" @click="handleDelete(scope.row)">退票</el-button>  
        </template>
      </el-table-column>
    </el-table>

    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {queryAllTicket,queryTicketForUserName} from '@/api/api';
import {queryMoiveForName} from '@/api/api';
import {delTicketObj,buyTicket} from '@/api/api';
import waves from "@/directive/waves/index.js"; // 水波纹指令
import { mapGetters } from "vuex";

export default {
  name: "table_cc_heat",
  directives: {
    waves
  },
  data() {
    return {
      typeoptions: [{
                    value: '1',
                    label: '上架'
                },{
                    value: '0',
                    label: '下架'
                }],
      list: null,
      total: null,
      listLoading: true,
      columnWidth: '300',
      heatSign:true,
      listQuery: {
        page: 1,
        limit: 20
      },
      rules: {
	    id: [
        {
          required: true,
          message: "ID",
          trigger: "blur"
        }],
        name: [
        {
          required: true,
          message: "请输入名称",
          trigger: "blur"
        },{
          min: 0,
          max: 8,
          message: "最高限制8个汉字",
          trigger: "blur"
        }]
    },
      form: {},
      dialogFormVisible: false,
      dialogStatus: "",
      heat_add: false,
      heat_upd: false,
      heat_del: false,
      textMap: {
        update: "编辑",
        create: "创建"
      },
      tableKey: 0
    };
  },
  computed: {
    ...mapGetters(["permissions"])
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        0: "有效",
        1: "无效"
      };
      return statusMap[status];
    }
  },
  created() {
    this.getList();
    this.heat_add = this.permissions["heat_add"];
    this.heat_upd = this.permissions["heat_upd"];
    this.heat_del = this.permissions["heat_del"];
	// brandList().then(response => {
    //     this.brandOptions = response.data;
    // })
  },
  methods: {
    //特殊字符过滤
    checkSpecificKey(str){
        var specialKey = "[`~!#$^&*()=|{}':;',\\[\\].<>/?~！#￥……&*（）——|{}【】‘；：”“'。，、？]‘'";
        for (var i = 0; i < str.length; i++) {
            if (specialKey.indexOf(str.substr(i, 1)) != -1) {
                return false;
            }
        }
        return true;
    },
    getList() {
      this.listLoading = true;
      this.listQuery.orderByField = "create_time";
      this.listQuery.isAsc = false;
      this.listQuery.userId=sessionStorage.getItem("userId");
      queryAllTicket(this.listQuery).then(response => {
        this.list = response.data;
        this.listLoading = false;
      });
    },
    getListForUserName() {
      this.listLoading = true;
      this.listQuery.orderByField = "create_time";
      this.listQuery.isAsc = false;
      queryTicketForUserName(this.listQuery).then(response => {
        this.list = response.data;
        this.listLoading = false;
      });
    },
    handleFilter() {
      this.getListForUserName();
    },
    handleSizeChange(val) {
      this.listQuery.limit = val;
      this.getList();
    },
    handleCurrentChange(val) {
      this.listQuery.page = val;
      this.getList();
    },
    handleDelete(row) {
	  this.$confirm(
          "确定是否退票, 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        ).then(() => {
            delTicketObj(row).then(response => {
              this.dialogFormVisible = false;
              this.getList();
              this.$notify({
                  title: "成功",
                  message: "退票成功",
                  type: "success",
                  duration: 2000
              });
            })
            .cache(response => {
              this.$notify({
                title: "失败",
                message: "删除失败",
                type: "error",
                duration: 2000
              });
            });
        });
    },
    handleUpdate(row) {
	      this.resetTemp();
        this.dialogStatus = "update";
        this.dialogFormVisible = true;
        this.form.userId=sessionStorage.getItem("userId");
        console.log(sessionStorage.getItem("userId"));
        this.form.id=row.id;
        this.form.moviename = row.moviename;
        this.form.content = row.content;
        this.form.starttime = row.starttime;
        this.form.endtime = row.endtime;
        this.form.state=row.state;
        this.form.sumticket=row.sumticket;
    },
    handleCreate() {
        this.resetTemp();
        this.dialogStatus = "create";
        this.dialogFormVisible = true;
    },
    create(formName) {
      const set = this.$refs;
      if(this.heatSign){
        set[formName].validate(valid => {
          if (valid) { 
            addMovieObj(this.form).then(() => {
              this.dialogFormVisible = false;
              this.getList();
              this.$notify({
                title: "成功",
                message: "创建成功",
                type: "success",
                duration: 2000
              });
            });
          } else {
            return false;
          }
        });
      } else{
        return false;
      }
    },
    cancel(formName) {
      this.dialogFormVisible = false;
      const set = this.$refs;
      set[formName].resetFields();
    },
    update(formName) {
      const set = this.$refs;
      if(this.heatSign){
        set[formName].validate(valid => {
          if (valid) { 
            buyTicket(this.form).then((res) => {
              this.dialogFormVisible = false;
              this.getList();
              this.$notify({
                title: "成功",
                message: "电影票验证码:"+res.data,
                type: "success",
                duration: 2000
              });
            });
          } else {
            return false;
          }
        });
      } else{
        return false;
      }
    },
	resetTemp() {
        this.form = {
          id: undefined,
          moviename: "",
          content:'',
          starttime:'',
		  endtime:'',
          state:'',
          sumticket:'',
          buyticket:'',
          userId:''
        };
    },
	resetTempQuery() {
        this.listQuery = {
        };
    }
  }
};
</script>
