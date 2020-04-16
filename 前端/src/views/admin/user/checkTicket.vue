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

     <!-- <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="请输入电影名" v-model="listQuery.moivename"></el-input>

      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleFilter">搜索</el-button>  
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="edit">添加</el-button> -->
    </div>
    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row style="width: 99%">
	
	   <el-table-column  type="index" label="序号" align="center" width="50px" >
       </el-table-column>

      <el-table-column :width="columnWidth" align="center" label="电影名">
        <template slot-scope="scope">
          <span>{{ scope.row.moviename }}</span>
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
          <span>{{ scope.row.state==1?'上架':'下架' }}</span>
        </template>
      </el-table-column>
	  
	 <el-table-column align="center" label="电影票总数">
        <template slot-scope="scope">
          <span>{{ scope.row.sumticket }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="已卖票数">
        <template slot-scope="scope">
          <span>{{ scope.row.purchasedticket }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="剩余票数">
        <template slot-scope="scope">
          <span>{{ scope.row.surplusticket }}</span>
        </template>
      </el-table-column>
      
    
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="handleUpdate(scope.row)">验票
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form" label-width="100px">

    <el-form-item label="电影名" prop="moviename">
      <el-input disabled v-model="form.moviename" placeholder="电影名"></el-input>
    </el-form-item>

    <el-form-item label="详细内容" prop="content">
      <el-input disabled v-model="form.content" placeholder="电影名"></el-input>
    </el-form-item>

	<el-form-item label="状态" prop="state">
          <el-select disabled class="filter-item" v-model="form.state" placeholder="请选择">
            <el-option
              v-for="(item,index) in typeoptions"
              :key="index"
              :label="item.label"
              :value="item.value"/>
          </el-select>
    </el-form-item>

    <el-form-item label="开始时间" prop="starttime">
      <el-input disabled v-model="form.starttime" placeholder="输入格式：2020-01-01"></el-input>
    </el-form-item>

    <el-form-item label="结束时间" prop="endtime">
      <el-input disabled v-model="form.endtime" placeholder="输入格式：2020-01-01"></el-input>
    </el-form-item>

    <el-form-item label="电影票总数" prop="sumticket">
      <el-input disabled v-model="form.sumticket" placeholder="电影票总数"></el-input>
    </el-form-item>

    <el-form-item label="验证码" prop="verificationcode">
      <el-input v-model="form.verificationcode" placeholder="验证码"></el-input>
    </el-form-item>

	  </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('form')">取 消</el-button>
        <el-button type="primary" @click="update('form')">验证</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
import {queryMovie} from '@/api/api';
import {queryMoiveForName} from '@/api/api';
import {checkTicket} from '@/api/api';
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
      columnWidth: '300',
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
      queryMovie(this.listQuery).then(response => {
        this.list = response.data;
        this.listLoading = false;
      });
    },
    getListForMoiveName() {
      this.listLoading = true;
      this.listQuery.orderByField = "create_time";
      this.listQuery.isAsc = false;
      queryMoiveForName(this.listQuery).then(response => {
        console.log(response.data);
        this.list = response.data;
        //this.total = response.data.total;
        this.listLoading = false;
      });
    },
    handleFilter() {
      this.listQuery.page = 1;
      this.getListForMoiveName();
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
          "此操作将永久删除:" + row.moviename + ", 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        ).then(() => {
            delMovieObj(row).then(response => {
              this.dialogFormVisible = false;
              this.getList();
              this.$notify({
                  title: "成功",
                  message: "删除成功",
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
            console.log("111");

            // checkTicket(formName).then(response => {
            //   this.dialogFormVisible = false;
            //   this.getList();
            //   this.$notify({
            //       title: "成功",
            //       message: "删除成功",
            //       type: "success",
            //       duration: 2000
            //   });
            // })
            // .cache(response => {
            //   this.$notify({
            //     title: "失败",
            //     message: "删除失败",
            //     type: "error",
            //     duration: 2000
            //   });
            // });

            checkTicket(this.form).then((res) => {
              this.dialogFormVisible = false;
              this.getList(); 
                if(res.data===true){
                  this.$notify({
                    title: "成功",
                    message: "验票成功",
                    type: "success",
                    duration: 2000
                  }); 
                }else{
                  this.$notify({
                    title: "失败",
                    message: "验票失败",
                    type: "error",
                    duration: 2000
                  }); 
                }
                
            });
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
          verificationcode:''
        };
    },
	resetTempQuery() {
        this.listQuery = {
        };
    }
  }
};
</script>
