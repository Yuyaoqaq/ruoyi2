<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="被借的图书ID" prop="bookId">
        <el-input
          v-model="queryParams.bookId"
          placeholder="请输入被借的图书ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="借书的用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入借书的用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['bookSys:records:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bookSys:records:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bookSys:records:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bookSys:records:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="借书记录的唯一ID" align="center" prop="recordId" />
      <el-table-column label="被借的图书ID" align="center" prop="bookId" />
      <el-table-column label="借书的用户ID" align="center" prop="userId" />
      <el-table-column label="借书日期" align="center" prop="loanDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.loanDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="应还日期" align="center" prop="dueDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dueDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否已还书" align="center" prop="returned" >
      <template slot-scope="scope">
        <!-- 使用三元表达式判断状态并显示不同样式 -->
        <el-tag
          :type="scope.row.returned == 0 ? 'danger' : 'success'"
          size="mini"
        >
          {{ scope.row.returned == 0 ? '没还' : '已还' }}
        </el-tag>
      </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bookSys:records:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bookSys:records:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改借阅信息查询对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="被借的图书ID" prop="bookId">
          <el-input v-model="form.bookId" placeholder="请输入被借的图书ID" />
        </el-form-item>
        <el-form-item label="借书的用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入借书的用户ID" />
        </el-form-item>
        <el-form-item label="借书日期" prop="loanDate">
          <el-date-picker clearable
            v-model="form.loanDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择借书日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="应还日期" prop="dueDate">
          <el-date-picker clearable
            v-model="form.dueDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择应还日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否已还书" prop="returned">
          <el-select
            v-model="form.returned"
            placeholder="请选择图书是否归还"
            clearable
          >
            <!-- 显示的是字符串，但实际值是数字1和0 -->
            <el-option label="已还" value="1" ></el-option>
            <el-option label="没还" value="0" ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecords, getRecords, delRecords, addRecords, updateRecords } from "@/api/bookSys/records"

export default {
  name: "Records",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 借阅信息查询表格数据
      recordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bookId: null,
        userId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {

      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询借阅信息查询列表 */
    getList() {
      this.loading = true
      listRecords(this.queryParams).then(response => {
        this.recordsList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        recordId: null,
        bookId: null,
        userId: null,
        loanDate: null,
        dueDate: null,
        returned: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.recordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加借阅信息查询"
      this.form.returned = "0";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const recordId = row.recordId || this.ids
      getRecords(recordId).then(response => {
        this.form = response.data
        this.form.returned=this.form.returned.toString()
        this.open = true
        this.title = "修改借阅信息查询"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recordId != null) {
            updateRecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addRecords(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const recordIds = row.recordId || this.ids
      this.$modal.confirm('是否确认删除借阅信息查询编号为"' + recordIds + '"的数据项？').then(function() {
        return delRecords(recordIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bookSys/records/export', {
        ...this.queryParams
      }, `records_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
