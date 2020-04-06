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
    <el-row>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-form :model="ruleForm2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
            <el-form-item  label="ID" prop="id">
              <el-input disabled v-model="ruleForm2.id" placeholder="用户ID"></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="name">
              <el-input type="text" :value="ruleForm2.name"></el-input> 
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="ruleForm2.password" auto-complete="off"></el-input> 
            </el-form-item>
            <el-form-item  label="类型" prop="type">
              <el-select disabled class="filter-item" v-model="ruleForm2.type" placeholder="请选择">
              <el-option
              v-for="(item,index) in typeoptions"
              :key="index"
              :label="item.label"
              :value="item.value"/>
              </el-select>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-select class="filter-item" v-model="ruleForm2.sex" placeholder="请选择">
                <el-option
                  v-for="(item,index) in sexoptions"
                  :key="index"
                  :label="item.label"
                  :value="item.value"/>
              </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="tel">
              <el-input v-model="ruleForm2.tel" placeholder="电话"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>


<script>
import {queryPersonById,putObj} from '@/api/api';
import { getToken } from "@/util/auth";
import waves from "@/directive/waves/index.js"; // 水波纹指令
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      ruleForm2: {
        id:'',
        name:'',
        password: '',
        type: '',
        sex: '',
        tel:''
      },
                sexoptions: [{
                    value: '1',
                    label: '男'
                },{
                    value: '2',
                    label: '女'
                }],
                typeoptions: [{
                    value: '1',
                    label: '售票人员'
                },{
                    value: '2',
                    label: '检票人员'
                },{
                    value: '3',
                    label: '观众'
                }]
    }
  },
  created() {
    this.ruleForm2.id=sessionStorage.getItem("userId");
    this.getUserData();
  },
  methods: {
    getUserData() {
      this.ruleForm2.id=sessionStorage.getItem("userId");
      queryPersonById(this.ruleForm2).then(response => {
        this.ruleForm2.name = response.data.name;
        this.ruleForm2.password=response.data.password;
        this.ruleForm2.type=response.data.type;
        this.ruleForm2.sex=response.data.sex;
        this.ruleForm2.tel=response.data.tel;
      });
    },
    submitForm(formName) {
      putObj(this.ruleForm2).then(response => {
        this.getUserData();
        this.$notify({
                  title: "成功",
                  message: "修改成功",
                  type: "success",
                  duration: 2000
        });
      });
    }
  }
};
</script>
