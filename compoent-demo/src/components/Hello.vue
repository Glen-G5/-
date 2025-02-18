<template>
  <div class="sport-container">
    <h3 class="h3">运动知识</h3>

    <!-- 操作按钮 -->
    <div class="action-buttons">
      <el-button type="primary" @click="handleAdd">新增</el-button>
    </div>
    <!-- 搜索功能 -->
    <div class="search-box">
      <el-input
          v-model="searchKeyword"
          placeholder="输入运动类型搜索"
          @keyup.enter.native="loadSports">
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="loadSports">
      </el-button>
</el-input>
    </div>
    <!-- 表格 -->
    <el-table
      :data="sportData"
      style="width: 100%"
      stripe
      border>
      <el-table-column
        prop="sportType"
        label="运动类型"
        width="180">
      </el-table-column>
      <el-table-column
        prop="suitableTime"
        label="适宜时长"
        width="180">
      </el-table-column>
      <el-table-column
        prop="suitableHeartRate"
        label="目标心率">
      </el-table-column>
      <el-table-column
        prop="suitableFrequency"
        label="建议频率">
      </el-table-column>
      <el-table-column
        prop="recommendedSpeed"
        label="推荐速度">
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 50]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next"
      :total="total">
    </el-pagination>

    

    <!-- 新增/编辑对话框 -->
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      width="30%">
      <el-form :model="form" label-width="100px">
        <el-form-item label="运动类型">
          <el-input v-model="form.sportType"></el-input>
        </el-form-item>
        <el-form-item label="适宜时长">
          <el-input v-model="form.suitableTime"></el-input>
        </el-form-item>
        <el-form-item label="目标心率">
          <el-input v-model="form.suitableHeartRate"></el-input>
        </el-form-item>
        <el-form-item label="建议频率">
          <el-input v-model="form.suitableFrequency"></el-input>
        </el-form-item>
        <el-form-item label="推荐速度">
          <el-input v-model="form.recommendedSpeed"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      sportData: [],
      searchKeyword: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      dialogVisible: false,
      dialogTitle: '新增运动信息',
      form: {
        id: null,
        sportType: '',
        suitableTime: '',
        suitableHeartRate: '',
        suitableFrequency: '',
        recommendedSpeed: ''
      }
    }
  },
  methods: {
    // 加载数据
    loadSports() {
      console.log('当前页:', this.currentPage);
      console.log('每页大小:', this.pageSize);
      console.log('搜索关键字:', this.searchKeyword);
      
      this.$http.get('/api/sports/page', {
        params: {
          page: this.currentPage,
          size: this.pageSize,
          keyword: this.searchKeyword
        }
      }).then(res => {
        console.log('接口返回数据:', res.data);
        this.sportData = res.data.records;
        this.total = res.data.total;
      }).catch(err => {
        console.error('加载数据失败:', err);
        this.$message.error('数据加载失败');
      });
    },

    // 分页大小改变
    handleSizeChange(size) {
      this.pageSize = size;
      this.currentPage = 1; // 重置为第一页
      this.loadSports();
    },
    // 当前页改变
    handleCurrentChange(page) {
      this.currentPage = page;
      this.loadSports();
    },

    
    // 打开新增对话框
    handleAdd() {
        this.dialogTitle = '新增运动信息';
        this.form = {
            id: null,
            sportType: '',
            suitableTime: '',
            suitableHeartRate: '',
            suitableFrequency: '',
            recommendedSpeed: ''
        };
        this.dialogVisible = true;
    },

    // 打开编辑对话框
    handleEdit(row) {
      this.dialogTitle = '编辑运动信息';
      this.form = { ...row };
      this.dialogVisible = true;
    },

    // 提交表单（新增/编辑）
    submitForm() {
        const url = '/api/sports';
        const method = 'post'; // 修改为 POST 方法

        this.$http[method](url, this.form)
          .then(() => {
                this.$message.success('操作成功');
                this.dialogVisible = false;
                this.loadSports();
            })
          .catch(err => {
                console.error('操作失败:', err);
                this.$message.error('操作失败');
            });
    },
    // 删除数据
    handleDelete(id) {
      this.$confirm('确定删除该记录吗？', '提示', {
        type: 'warning'
      }).then(() => {
        this.$http.delete(`/api/sports/${id}`)
          .then(() => {
            this.$message.success('删除成功');
            this.loadSports();
          })
          .catch(err => {
            console.error('删除失败:', err);
            this.$message.error('删除失败');
          });
      }).catch(() => {});
    }
  },
  mounted() {
    this.loadSports();
  }
}
</script>

<style>
.h3 {
  text-align: center;
  margin: 30px 0;
}

.sport-container {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.search-box {
  margin: 20px 0;
  width: 300px;
}

.action-buttons {
  margin-bottom: 20px;
}
</style>