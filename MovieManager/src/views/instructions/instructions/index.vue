<template>
  <div class="app-container calendar-list-container">
    <!-- 查询条件 -->
    <div class="filter-container">
      <el-form :inline="true" :model="listQuery" class="demo-form-inline">
        <el-form-item label="手册名称">
          <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="手册名称"
                    v-model="listQuery.name">
          </el-input>
        </el-form-item>

        <el-form-item label="版本名称">
          <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="版本名称"
                    v-model="listQuery.versionName">
          </el-input>
        </el-form-item>

        <el-form-item label="品牌" @keyup.enter.native="handleFilter" class="filter-item">
          <el-select placeholder="请选择" v-model="listQuery.brandId" @change="changeBrands($event)">
            <el-option
              v-for="item in brands"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="车型" @keyup.enter.native="handleFilter" class="filter-item">
          <el-select placeholder="请选择" v-model="listQuery.modelId" @change="changeModel($event)">
            <el-option
              v-for="item in models"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="年款" @keyup.enter.native="handleFilter" class="filter-item">
          <el-select placeholder="请选择" v-model="listQuery.annualId" @change="changeAnnual($event)">
            <el-option
              v-for="item in annuals"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleFilter">搜索</el-button>
          <el-button class="filter-item" style="margin-left: 10px;" @click="editAdd" type="primary" icon="edit">添加
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 列表 -->
    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit
              highlight-current-row style="width: 100%">

      <el-table-column
        type="index"
        label="序号"
        align="center"
        width="50px"
      >
      </el-table-column>

      <el-table-column align="center" label="品牌">
        <template slot-scope="scope">
          <span>{{scope.row.brandNaem}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="车型">
        <template slot-scope="scope">
          <span>
            {{scope.row.modelName}}
          </span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="年款">
        <template slot-scope="scope">
          <span>{{scope.row.annualName}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="类型">
        <template slot-scope="scope">
          <span>{{scope.row.ccInstructions.instructionsType == 0?'车机端':'手机端'}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="手册名称">
        <template slot-scope="scope">
          <span>{{scope.row.ccInstructions.name}} </span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="版本名称">
        <template slot-scope="scope">
          <span>{{scope.row.ccInstructions.versionName}} </span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="创建时间">
        <template slot-scope="scope">
          <span>{{scope.row.ccInstructions.createTime | parseTime('{y}-{m}-{d}')}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="最后修改时间">
        <template slot-scope="scope">
          <span>{{scope.row.ccInstructions.updateTime | parseTime('{y}-{m}-{d}')}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="版本时间">
        <template slot-scope="scope">
          <span>{{scope.row.ccInstructions.versionTimeStart}}至{{scope.row.ccInstructions.versionTimeEnd}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="备注">
        <template slot-scope="scope">
          <span>{{scope.row.ccInstructions.remark}}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" class-name="status-col" label="状态">
        <template slot-scope="scope">
          <span>{{scope.row.ccInstructions.online == 0?'未上线':'已上线'}}</span>
        </template>
      </el-table-column>


      <el-table-column
        fixed="right"
        label="操作"
        width="220">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="editOnline(scope.row)">
            编辑
          </el-button>
          <el-button size="mini" type="danger" @click="editDelete(scope.row)">
            删除
          </el-button>
          <el-button size="mini" type="primary" @click="showDetail(scope.row)">
            详情
          </el-button>
        </template>
      </el-table-column>


    </el-table>
    <!-- 分页 -->
    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit"
                     layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
    <!-- 编辑/添加 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form" label-width="120px">
        <el-form-item label="品牌" prop="brandName" v-if="dialogStatus=='add'">
          <el-select placeholder="请选择" v-model="form.brandName" @change="changeBrands($event)">
            <el-option
              v-for="item in brands"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车型" prop="modelName" v-if="dialogStatus=='add'">
          <el-select placeholder="请选择" v-model="form.modelName" @change="changeModel($event)">
            <el-option
              v-for="item in models"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年款" prop="annualId" v-if="dialogStatus=='add'">
          <el-select placeholder="请选择" v-model="form.annualId">
            <el-option
              v-for="item in annuals"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手册名称" prop="name">
          <el-input v-model="form.name" placeholder="请输手册名称"></el-input>
        </el-form-item>
        <el-form-item label="版本名称" prop="versionName">
          <el-input v-model="form.versionName" placeholder="请输版本名称"></el-input>
        </el-form-item>

        <el-form-item label="版本时间" prop="versionTime">
          <div class="block" style="width: 25%;float: left">
            <el-date-picker
              v-model="startYear"
              type="year"
              placeholder="选择开始年">
            </el-date-picker>
          </div>
          <div class="block" style="width: 25%;float: left">
            <el-select class="filter-item" v-model="startMonth" placeholder="选择开始月"
                       @change="versionTimeConvert('form')">
              <el-option label="1" value="01"></el-option>
              <el-option label="2" value="02"></el-option>
              <el-option label="3" value="03"></el-option>
              <el-option label="4" value="04"></el-option>
              <el-option label="5" value="05"></el-option>
              <el-option label="6" value="06"></el-option>
              <el-option label="7" value="07"></el-option>
              <el-option label="8" value="08"></el-option>
              <el-option label="9" value="09"></el-option>
              <el-option label="10" value="10"></el-option>
              <el-option label="11" value="11"></el-option>
              <el-option label="12" value="12"></el-option>
            </el-select>
          </div>

          <div class="block" style="width: 25%;float: left">
            <el-date-picker
              v-model="endYear"
              type="year"
              placeholder="选择结束年">
            </el-date-picker>
          </div>
          <div class="block" style="width: 25%;float: left">
            <el-select class="filter-item" v-model="endMonth" placeholder="选择结束月" @change="versionTimeConvert('form')">
              <el-option label="1" value="01"></el-option>
              <el-option label="2" value="02"></el-option>
              <el-option label="3" value="03"></el-option>
              <el-option label="4" value="04"></el-option>
              <el-option label="5" value="05"></el-option>
              <el-option label="6" value="06"></el-option>
              <el-option label="7" value="07"></el-option>
              <el-option label="8" value="08"></el-option>
              <el-option label="9" value="09"></el-option>
              <el-option label="10" value="10"></el-option>
              <el-option label="11" value="11"></el-option>
              <el-option label="12" value="12"></el-option>
            </el-select>
          </div>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输手册备注"></el-input>
        </el-form-item>

        <el-form-item label="手册类型" prop="instructionsType">
          <el-select class="filter-item" v-model="instructionsTypeName" placeholder="请选择"
                     @change="echoInstructionsType($event)">
            <el-option label="车机端" value="0"></el-option>
            <el-option label="手机端" value="1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="是否上线" prop="online">
          <el-select class="filter-item" v-model="onlineName" placeholder="请选择" @change="echoOnline($event)">
            <el-option label="下线" value="0"></el-option>
            <el-option label="上线" value="1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="上传文件" prop="file" v-if="dialogStatus=='add'">
          <el-upload
            ref="upload"
            action="/instructions/instructions/uploadPushContent"
            :data="form"
            :limit="1"
            accept="application/zip,application/rar"
            :on-success="successFun"
            :on-change="onUploadChange"
            :auto-upload="false"
            :show-file-list="true">
            <el-button slot="trigger" size="small" type="primary">选取</el-button>
            <!--<el-button style="margin-left: 10px;" size="small" type="success" @click="create" >上传</el-button>-->
            <div slot="tip" class="el-upload__tip">只能上传zip文件,文件中必须包含index.html</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('form')">取 消</el-button>
        <el-button v-if="dialogStatus=='add'" type="primary" @click="create('form')" :loading="addDisabled">确 定
        </el-button>
        <el-button v-else type="primary" @click="update('form')">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {fetchList, delObj, putObj, checkVersionTimeRepetition} from "@/api/instructions";

  import {brandList} from "@/api/motorcycle/brand";
  import {getModelList} from "@/api/motorcycle/model";
  import {getAnnualList} from "@/api/motorcycle/annual";

  import waves from "@/directive/waves/index.js";
  import {mapGetters} from "vuex";
  import ElRadioGroup from "element-ui/packages/radio/src/radio-group";
  import ElOption from "element-ui/packages/select/src/option";

  export default {

    data() {
      let validateCharacter = (rule,value,callback) => {
        if(this.checkSpecificKey(value)){
            callback();
        }else{
            callback(new Error('请不要输入特殊字符'));
        }
    }
     /* var checkFile = (rule, value, callback) => {
        console.log(this.form.file == null)
        if (null != this.form.file) {
          callback();
        }else{
          callback(new Error("请选择文件!"));
        }
      };*/
      var checkRemark = (rule, value, callback) => {
        if (value != null && value != '' && value.length > 100) {
          callback(new Error("1-100个汉字或对应字符"));
        } else {
          callback();
        }
      };
      var checkVersionTime = (rule, value, callback) => {

        if (this.startYear != null && this.endYear != null && this.startYear != '' && this.endYear != '') {
          // 获取开始年
          var startY = this.startYear.getFullYear();
          var endY = this.endYear.getFullYear();
          this.form.versionTimeStart = startY + '-' + this.startMonth;
          this.form.versionTimeEnd = endY + '-' + this.endMonth;
          var startDate = new Date(startY + '-' + this.startMonth);
          var endDate = new Date(endY + '-' + this.endMonth);
          // 比较时间前后
          if (startDate > endDate) {
            callback(new Error("开始时间不能大于结束时间!"));
          } else {
            callback();
          }
        } else {
          callback(new Error("请选择版本时间!"));
        }
      };
      return {
        filePath: 'http://10.1.11.131/file/',
        //filePath: 'http://gweiManager.gwm.cn/file/',
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20
        },
        list: [],
        tableKey: 0,
        total: null,
        form: {
          name: undefined,
          remark: '',
          id: undefined,
          instructionsType: undefined,
          online: undefined,
          configurationId: undefined,
          brandName: undefined,
          modelName: undefined,
          annualName: undefined,
          annualId: undefined,
          versionName: undefined,
          versionTimeEnd: undefined,
          versionTimeStart: undefined,
          versionTime: undefined,
          file: undefined
        },
        dialogFormVisible: false,
        textMap: {
          update: "编辑",
          create: "创建"
        },
        dialogStatus: "",
        rules: {
          name: [
            {
              required: true,
              message: "请输入名称",
              trigger: "blur"
            },
            {
              min: 1,
              max: 20,
              message: "1-20个汉字或对应字符",
              trigger: "blur"
            },	      
            {
            validator:validateCharacter,
            trigger: 'blur'
            }	 
          ],
          versionName: [
            {
              required: true,
              message: "请输入版本名称",
              trigger: "blur"
            },
            {
              min: 1,
              max: 20,
              message: "1-20个汉字或对应字符",
              trigger: "blur"
            }
          ],
          annualId: [
            {
              required: true,
              message: "请选择年款",
              trigger: "change"
            }
          ],
          modelName: [
            {
              required: true,
              message: "请选择车型",
              trigger: "change"
            }
          ],
          brandName: [
            {
              required: true,
              message: "请选择品牌",
              trigger: "change"
            }
          ],
          online: [
            {
              required: true,
              message: "请选择是否上线",
              trigger: "change"
            }
          ],
          /*file: [
            {
              validator: checkFile,
              trigger: 'blur'
            }
          ],*/
          remark: [
            {
              validator: checkRemark,
              trigger: 'blur'
            }
          ],
          instructionsType: [
            {
              required: true,
              message: "请选择手册类型",
              trigger: "change"
            }
          ], versionTime: [
            {
              validator: checkVersionTime,
              trigger: 'change'
            }
          ],
        },
        brands: [],
        annuals: [],
        models: [],
        instructionsTypeName: '',
        onlineName: '',
        startYear: '',
        startMonth: '',
        endYear: '',
        endMonth: '',
        addDisabled: false
      };
    },
    // 初始化
    created() {
      this.getList();
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
      handleDept() {
        fetchDeptTree().then(response => {
          this.treeDeptData = response.data;
        this.dialogDeptVisible = true;
      })
        ;
      },
      handleFilter() {
        this.listQuery.page = 1;
        this.getList();
      },
      handleSizeChange(val) {
        this.listQuery.limit = val;
        this.getList();
      },
      handleCurrentChange(val) {
        this.listQuery.page = val;
        this.getList();
      },
      handleCreate() {
        this.resetTemp();
        this.dialogStatus = "create";
        this.dialogFormVisible = true;
      },
      handleUpdate(row) {
        getObj(row.userId).then(response => {
          this.form = response.data;
        this.dialogFormVisible = true;
        this.dialogStatus = "update";
        this.role = [];
        for (var i = 0; i < row.roleList.length; i++) {
          this.role[i] = row.roleList[i].roleId;
        }
        deptRoleList(response.data.deptId).then(response => {
          this.rolesOptions = response.data;
      })
        ;
      })
        ;
      },
      // 查询列表
      getList() {
        this.listLoading = true;
        this.listQuery.isAsc = false;
        fetchList(this.listQuery).then(response => {
          this.list = response.data.records
        this.total = response.data.total;
        this.listLoading = false;
      })
        ;
        brandList().then(response => {
          this.brands = response.data
      })
        ;
      },
      // 修改页面打开
      editOnline(row) {
        this.form = row.ccInstructions;
        this.dialogFormVisible = true;
        this.dialogStatus = "update";
        this.echoInstructionsType(row.ccInstructions.instructionsType);
        this.echoOnline(row.ccInstructions.online);
        var ts = row.ccInstructions.versionTimeStart
        var ts2 = row.ccInstructions.versionTimeEnd
        this.startYear = new Date(ts.split('-')[0]);
        this.startMonth = ts.split('-')[1];
        this.endYear = new Date(ts2.split('-')[0]);
        this.endMonth = ts2.split('-')[1];
      },
      // 添加页面打开
      editAdd() {
        this.form = {
          name: undefined,
          remark: '',
          id: undefined,
          instructionsType: undefined,
          online: undefined,
          configurationId: undefined,
          brandName: undefined,
          modelName: undefined,
          annualName: undefined,
          annualId: undefined,
          versionTimeEnd: undefined,
          versionTimeStart: undefined
        }
        brandList().then(response => {
          this.brands = response.data
      })
        ;
        this.dialogFormVisible = true;
        this.dialogStatus = "add";
        this.instructionsTypeName = '';
        this.onlineName = '';
        this.startMonth = '';
        this.startYear = '';
        this.endYear = '';
        this.endMonth = '';
      },
      // 选择品牌
      changeBrands(id) {
        delete this.listQuery.annualId;
        delete this.listQuery.modelId;
        this.form.modelName = undefined;
        this.form.annualId = undefined;
        getModelList(id).then(response => {
          this.models = response.data.data
      })
        ;
      },
      // 修改手册类型
      echoInstructionsType(type) {
        this.instructionsTypeName = type == 0 ? '车机端' : '手机端';
        this.form.instructionsType = type;
      },
      // 修改上下线
      echoOnline(type) {
        this.onlineName = type == 0 ? '下线' : '上线';
        this.form.online = type;
      },
      // 选择车型
      changeModel(id) {
        delete this.listQuery.annualId;
        this.form.annualId = undefined;
        getAnnualList(id).then(response => {
          this.annuals = response.data.data
      })
        ;
      },
      // 删除
      editDelete(row) { // 删除
        this.$confirm(
          "此操作将永久删除该手册(手册名称:" + row.ccInstructions.name + "), 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
        ).then(() => {
          delObj(row.ccInstructions.id
      )
      .
        then(() => {
          this.getList();
        this.$notify({
          title: "成功",
          message: "删除成功",
          type: "success",
          duration: 2000
        });
      })
      .
        cache(() => {
          this.$notify({
          title: "失败",
          message: "删除失败",
          type: "error",
          duration: 2000
        });
      })
        ;
      })
        ;
      },
      // 取消修改
      cancel(formName) {
        this.getList();
        this.dialogFormVisible = false;
        this.$refs[formName].resetFields();
      },
      // 提交修改
      update(formName) {
        const set = this.$refs;
        this.form.role = this.role;
//      this.versionTimeConvert();
        set[formName].validate(valid => {
          if(valid) {
            this.dialogFormVisible = false;
            putObj(this.form).then(response => {
              this.dialogFormVisible = false;
            this.getList();
            if (response.data.data == 0) {
              this.$notify({
                title: "失败",
                message: "修改失败",
                type: "error",
                duration: 5000
              });
            } else if (response.data.data == -2) {
              this.$notify({
                title: "失败",
                message: "该车型年款版本时间重复!",
                type: "error",
                duration: 2000
              });
            } else if (response.data.data == 1) {
              this.$notify({
                title: "成功",
                message: "修改成功",
                type: "success",
                duration: 2000
              });
            }
          })
            ;
          } else {
            return false;
      }
      })
        ;
      },
      // 选取图片校验
      onUploadChange(file) {
//        console.log(file);
//        debugger;
        const isIMAGE = (file.raw.type === 'application/zip' || file.raw.type === 'application/x-zip-compressed');
        if (!isIMAGE) {
          this.$message.error('只能上传zip文件!');
          return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file.raw);
        reader.onload = function (e) {
        }
        if (file.status == 'ready') {
          this.form.file = 'true';
        }
      },
      // 添加提交
      create(formName) {
        this.addDisabled = true;
//      this.versionTimeConvert();
        const set = this.$refs;
        this.form.role = this.role;
        set[formName].validate(valid => {
          if(valid) {
            checkVersionTimeRepetition(this.form).then(response => {
              if(response.data.data == 0){
              this.$notify({
                title: "错误",
                message: "该车型年款版本时间重复!",
                type: "error",
                duration: 5000
              });
              this.addDisabled = false;
            }
          else
            {
              console.log(this.form.file);
              if (this.form.file == 'true') {
                this.$refs.upload.submit();
              }else {
                this.$notify({
                  title: "错误",
                  message: "请选择文件!",
                  type: "error",
                  duration: 5000
                });
              }
              delete this.form.file;
            }
          });
          } else {
            this.addDisabled = false;
          }
        });
        this.addDisabled = false;
      },
      // 文件上传回调
      successFun(response, file, fileList) {
        this.$refs.upload.clearFiles();
        delete this.form.file;
        if (response.code == 0) {
          this.$notify({
            title: "成功",
            message: "文件上传成功",
            type: "success",
            duration: 2000
          });
          this.dialogFormVisible = false;
          this.getList();
          this.addDisabled = false;
        } else {
          this.$notify({
            title: "错误",
            message: response.msg+",请选择文件重新上传!",
            type: "error",
            duration: 5000
          });
          this.addDisabled = false;
        }
      },
      // 查看详情
      showDetail(row) {
        console.log(this.filePath + "/" + row.ccInstructions.fileUrl + '/index.html');
        window.open(this.filePath + "/" + row.ccInstructions.fileUrl + '/index.html', '_blank');
      },
      // 时间转换
      versionTimeConvert(formName) {
        this.$refs[formName].validateField('versionTime', (error) => {
          if(
        !error
      )
        {
        }
      else
        {
          return false;
        }
      })
        ;
      },
    },
  }
</script>
