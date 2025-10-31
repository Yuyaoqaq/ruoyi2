<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="书名" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入书名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者名称" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者名称"
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
          v-hasPermi="['bookSys:books:add']"
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
          v-hasPermi="['bookSys:books:edit']"
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
          v-hasPermi="['bookSys:books:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bookSys:books:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="booksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图书的唯一ID" align="center" prop="bookId" />
      <el-table-column label="书名" align="center" prop="title" />
      <el-table-column label="作者名称" align="center" prop="author" />
      <el-table-column label="出版日期" align="center" prop="publishDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="国际标准书号" align="center" prop="isbn" />
      <el-table-column label="图书是否可借" align="center" prop="available">
        <template slot-scope="scope">
          <!-- 使用三元表达式判断状态并显示不同样式 -->
          <el-tag
            :type="scope.row.available === 0 ? 'danger' : 'success'"
            size="mini"
          >
            {{ scope.row.available === 0 ? '不可借' : '可借' }}
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
            v-hasPermi="['bookSys:books:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bookSys:books:remove']"
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

    <!-- 添加或修改图书信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="书名" prop="title">
          <el-input v-model="form.title" placeholder="请输入书名" />
        </el-form-item>
        <el-form-item label="作者名称" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者名称" />
        </el-form-item>
        <el-form-item label="出版日期" prop="publishDate">
          <el-date-picker clearable
            v-model="form.publishDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出版日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="国际标准书号" prop="isbn">
          <el-input v-model="form.isbn" placeholder="请输入国际标准书号" />
        </el-form-item>
        <el-form-item label="图书是否可借" prop="available">
          <el-select
            v-model="form.available"
            placeholder="请选择图书是否可借"
            clearable
          >
            <!-- 显示的是字符串，但实际值是数字1和0 -->
            <el-option label="可借" value="1" ></el-option>
            <el-option label="不可借" value="0" ></el-option>
          </el-select>
        </el-form-item>
        <el-divider content-position="center">借阅信息查询信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddLoanRecords">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteLoanRecords">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="loanRecordsList" :row-class-name="rowLoanRecordsIndex" @selection-change="handleLoanRecordsSelectionChange" ref="loanRecords">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="被借的图书ID" prop="bookId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bookId" placeholder="请输入被借的图书ID" />
            </template>
          </el-table-column>
          <el-table-column label="借书的用户ID" prop="userId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userId" placeholder="请输入借书的用户ID" />
            </template>
          </el-table-column>
          <el-table-column label="借书日期" prop="loanDate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.loanDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择借书日期" />
            </template>
          </el-table-column>
          <el-table-column label="应还日期" prop="dueDate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.dueDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择应还日期" />
            </template>
          </el-table-column>
          <el-table-column label="是否已还书" prop="returned" width="150">
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
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBooks, getBooks, delBooks, addBooks, updateBooks } from "@/api/bookSys/books"

export default {
  name: "Books",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedLoanRecords: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 图书信息表格数据
      booksList: [],
      // 借阅信息查询表格数据
      loanRecordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        author: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "书名不能为空", trigger: "blur" }
        ],
        author: [
          { required: true, message: "作者名称不能为空", trigger: "blur" }
        ],
        // 新增/修改available的校验规则
        available: [
          { required: true, message: "请选择图书是否可借", trigger: "change" },
          {
            validator: (rule, value, callback) => {
              // 确保值只能是0或1
              if (value !== "0" && value !== "1") {
                callback(new Error("请选择有效的可借状态"));
              } else {
                callback();
              }
            },
            trigger: "change"
          }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询图书信息列表 */
    getList() {
      this.loading = true
      listBooks(this.queryParams).then(response => {
        this.booksList = response.rows
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
        bookId: null,
        title: null,
        author: null,
        publishDate: null,
        isbn: null,
        available: null
      }
      this.loanRecordsList = []
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
      this.ids = selection.map(item => item.bookId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加图书信息"
      this.form.available = "1";  // 关键代码
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const bookId = row.bookId || this.ids
      getBooks(bookId).then(response => {
        this.form = response.data
        this.form.available = this.form.available.toString()
        this.loanRecordsList = response.data.loanRecordsList
        this.open = true
        this.title = "修改图书信息"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.loanRecordsList = this.loanRecordsList
          if (this.form.bookId != null) {
            updateBooks(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addBooks(this.form).then(response => {
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
      const bookIds = row.bookId || this.ids
      this.$modal.confirm('是否确认删除图书信息编号为"' + bookIds + '"的数据项？').then(function() {
        return delBooks(bookIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
	/** 借阅信息查询序号 */
    rowLoanRecordsIndex({ row, rowIndex }) {
      row.index = rowIndex + 1
    },
    /** 借阅信息查询添加按钮操作 */
    handleAddLoanRecords() {
      let obj = {}
      obj.bookId = ""
      obj.userId = ""
      obj.loanDate = ""
      obj.dueDate = ""
      obj.returned = ""
      this.loanRecordsList.push(obj)
    },
    /** 借阅信息查询删除按钮操作 */
    handleDeleteLoanRecords() {
      if (this.checkedLoanRecords.length == 0) {
        this.$modal.msgError("请先选择要删除的借阅信息查询数据")
      } else {
        const loanRecordsList = this.loanRecordsList
        const checkedLoanRecords = this.checkedLoanRecords
        this.loanRecordsList = loanRecordsList.filter(function(item) {
          return checkedLoanRecords.indexOf(item.index) == -1
        })
      }
    },
    /** 复选框选中数据 */
    handleLoanRecordsSelectionChange(selection) {
      this.checkedLoanRecords = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bookSys/books/export', {
        ...this.queryParams
      }, `books_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
