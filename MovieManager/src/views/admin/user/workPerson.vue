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
    
     <!-- <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="请输入姓名" v-model="listQuery.name"></el-input>

      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleFilter">搜索</el-button>  -->
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="edit">添加</el-button>
    </div>
    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row style="width: 99%">
	
	   <el-table-column  type="index" label="序号" align="center" width="50px" >
       </el-table-column>

      <el-table-column align="center" label="姓名">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="类型">
        <template slot-scope="scope">
          <span>{{ scope.row.type==1?'售票人员':'检票人员' }}</span>
        </template>
      </el-table-column>
	  
	  <el-table-column align="center" label="性别">
        <template slot-scope="scope">
          <span>{{ scope.row.sex==1?'男':'女' }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="电话">
        <template slot-scope="scope">
          <span>{{ scope.row.tel }}</span>
        </template>
      </el-table-column>
      
    
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="handleUpdate(scope.row)">编辑
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除
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

    <el-form-item label="姓名" prop="name">
      <el-input v-model="form.name" placeholder="姓名"></el-input>
    </el-form-item>

	<el-form-item label="类型" prop="type">
          <el-select class="filter-item" v-model="form.type" placeholder="请选择">
            <el-option
              v-for="(item,index) in typeoptions"
              :key="index"
              :label="item.label"
              :value="item.value"/>
          </el-select>
    </el-form-item>

    <el-form-item label="性别" prop="sex">
          <el-select class="filter-item" v-model="form.sex" placeholder="请选择">
            <el-option
              v-for="(item,index) in sexoptions"
              :key="index"
              :label="item.label"
              :value="item.value"/>
          </el-select>
    </el-form-item>    

    <el-form-item label="初始密码" prop="password">
		  <el-input v-model="form.password" placeholder="初始密码"></el-input>
    </el-form-item>

    <el-form-item label="电话" prop="tel">
		  <el-input v-model="form.tel" placeholder="电话"></el-input>
    </el-form-item>

	  </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('form')">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="create('form')">确 定</el-button>
        <el-button v-else type="primary" @click="update('form')">修 改</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
import {queryPerson} from '@/api/api';
import {queryPersonForName} from '@/api/api';
import {addObj,delObj,putObj} from '@/api/api';
import waves from "@/directive/waves/index.js"; // 水波纹指令
import { mapGetters } from "vuex";

export default {
  name: "table_cc_heat",
  directives: {
    waves
  },
  data() {
    // let validateCharacter = (rule,value,callback) => {
    //     if(this.checkSpecificKey(value)){
    //         callback();
    //     }else{
    //         callback(new Error('请不要输入特殊字符'));
    //     }
    // }
    // let validate = (rule, value, callback) => {
    //   //后台方法
    //   validateName({name:this.form.name,modelId:this.form.modelId,heat:this.form.heat}).then(res => {
    //     if (res.data) {
    //       callback(new Error('该名称已存在'));
    //     } else{
    //       callback();
    //     }
    //   })
    // }
	//   let validateHan = (rule, value, callback) => {
    //   var reg =/^[\u2E80-\u9FFF]+$/;
	// 	  if (reg.test(this.form.name)) {
	// 		  callback();
	//   	} else {
	// 	  	callback(new Error('请输入汉字'));
	//   	}
    // }
    // let validateHeat = (rule, value, callback) => {
    //     var reg1=/^[\u2E80-\u9FFF]+$/;//汉字
    //     var tag = /^[+]{0,1}(\d+)$/;//数字
    //     if(reg1.test(this.form.heat)){
    //       this.heatSign=false;
    //       callback(new Error('输入正整数'));
    //     }else{
    //       if (!tag.test(this.form.heat)) {
    //         this.heatSign=false;
    //         callback(new Error('输入正整数'));
    //       }else{
    //         this.heatSign=true;
    //         callback();
    //       }
    //     }
        
    // }
    return {
      typeoptions: [{
                    value: '1',
                    label: '售票人员'
                },{
                    value: '2',
                    label: '检票人员'
                }],
      sexoptions: [{
                    value: '1',
                    label: '男'
                },{
                    value: '2',
                    label: '女'
                }],
      list: null,
      total: null,
      listLoading: true,
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
      queryPerson(this.listQuery).then(response => {

        console.log(response.data);
        this.list = response.data;
        //this.total = response.data.total;
        this.listLoading = false;
      });
    },
    getListForName() {
      this.listLoading = true;
      this.listQuery.orderByField = "create_time";
      this.listQuery.isAsc = false;
      queryPersonForName(this.listQuery).then(response => {
        console.log(response.data);
        this.list = response.data;
        //this.total = response.data.total;
        this.listLoading = false;
      });
    },
    handleFilter() {
      this.listQuery.page = 1;
      this.getListForName();
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
          "此操作将永久删除:" + row.name + ", 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        ).then(() => {
            delObj(row).then(response => {
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
        this.form.name = row.name;
        this.form.type = row.type;
        this.form.password = row.password;
        this.form.sex = row.sex;
	      this.form.tel=row.tel;
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
            addObj(this.form).then(() => {
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
            putObj(this.form).then(() => {
              this.dialogFormVisible = false;
              this.getList();
              this.$notify({
                title: "成功",
                message: "修改成功",
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
    // update(formName) {
    //   const set = this.$refs;
    //   set[formName].validate(valid => {
    //     if (valid) {
    //       this.dialogFormVisible = false;
    //       putObj(this.form).then(() => {
    //         this.dialogFormVisible = false;
    //         this.getList();
    //         this.$notify({
    //           title: "成功",
    //           message: "修改成功",
    //           type: "success",
    //           duration: 2000
    //         });
    //       });
    //     } else {
    //       return false;
    //     }
    //   });
    // },
	resetTemp() {
        this.form = {
          id: undefined,
          name: "",
          type:'',
          password:'',
		      sex:'',
          tel:''
        };
    },
	resetTempQuery() {
        this.listQuery = {
        };
    }
  }
};
</script>
