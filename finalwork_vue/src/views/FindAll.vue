<template>
    <div>
        <div>
            <div style="margin-bottom: 20px">
                <el-button @click="deleteSelection">删除选中</el-button>
            </div>
            <el-table
                    ref="multipleTable"
                    :data="tableData"
                    border
                    style="width: 1081px"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        fixed
                        width="50"
                        type="selection">
                </el-table-column>
                <el-table-column
                        prop="id"
                        label="id"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="姓名"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="gender"
                        label="性别"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="age"
                        label="年龄"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="address"
                        label="籍贯"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="qq"
                        label="QQ"
                        width="150">
                </el-table-column>
                <el-table-column
                        prop="email"
                        label="邮箱"
                        width="150">
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button @click="edit(scope.$index,scope.row)" type="text"
                                   size="small">编辑
                        </el-button>
                        <el-button type="text" size="small" @click="del(scope.$index,scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                    background
                    layout="prev, pager, next"
                    page-size="6"
                    :total="total"
                    @current-change="page">
            </el-pagination>
        </div>

        <el-dialog title="编辑用户信息" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="gender">
                    <el-radio-group v-model="form.gender">
                        <el-radio label="男"></el-radio>
                        <el-radio label="女"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="年龄" prop="age">
                    <el-select v-model="form.age">
                        <el-option v-for="item in 127" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="籍贯" prop="address">
                    <el-select v-model="form.address">
                        <el-option v-for="item in array" :value="item"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="QQ" prop="qq">
                    <el-input v-model.number="form.qq"></el-input>
                </el-form-item>
                <el-form-item label="email" prop="email">
                    <el-input v-model="form.email"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="update">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                ids: [],
                multipleSelection: [],
                dialogFormVisible: false,
                tableData: null,
                total: null,
                array: ["北京市", "天津市", "河北省"
                    , "山西省", "辽宁省"
                    , "吉林省", "上海市"
                    , "江苏省", "浙江省", "安徽省"
                    , "福建省", "江西省", "山东省"
                    , "河南省", "湖北省", "湖南省"
                    , "广东省", "海南省"
                    , "重庆市", "四川省", "贵州省"
                    , "云南省", "西藏自治区", "陕西省"
                    , "甘肃省", "青海省", "台湾省"
                    , "黑龙江省", "内蒙古自治区"
                    , "广西壮族自治区", "宁夏回族自治区"
                    , "新疆维吾尔自治区", "香港特别行政区"
                    , "澳门特别行政区"],
                form: {
                    name: '',
                    gender: '',
                    age: '',
                    address: '',
                    qq: '',
                    email: ''
                },
                formRules: {
                    name: [
                        {required: true, message: '请输入用户姓名', trigger: 'blur'},
                    ],
                    gender: [
                        {required: true, message: '请选择用户性别', trigger: 'blur'}
                    ],
                    age: [
                        {required: true, message: '请选择用户年龄', trigger: 'blur'}
                    ],
                    address: [
                        {required: true, message: '请选择用户籍贯', trigger: 'blur'}
                    ],
                    QQ: [
                        {required: true, message: '请输入用户QQ', trigger: 'blur'},
                        {type: 'number', message: '请输入正确的QQ', trigger: ['change', 'blur']}
                    ],
                    email: [
                        {required: true, message: '请输入用户邮箱地址', trigger: 'blur'},
                        {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
                    ]
                }
            }
        },
        methods: {
            page(currentPage) {
                let _this = this;
                axios.get("http://localhost:8181/user/findByPage/" + currentPage + "/6").then(function (resp) {
                    _this.tableData = resp.data.content;
                    _this.total = resp.data.totalElements;
                });
            },
            edit(index, row) {
                this.form = row;
                this.dialogFormVisible = true;
            },
            update() {
                let _this = this;
                axios.post("http://localhost:8181/user/updateUser", _this.form)
                    .then(function (resp) {
                        if (resp.data === "success") {
                            _this.$alert("修改成功！", "提示信息", {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.dialogFormVisible = false;
                                }
                            });
                        } else {
                            _this.$alert("修改失败！请联系技术人员维护！", "警告", {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.dialogFormVisible = false;
                                }
                            });
                        }
                    })
            },
            del(index, row) {
                let _this = this;
                this.$confirm('确认删除吗？', '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    axios.post("http://localhost:8181/user/deleteUser", row)
                        .then(function (resp) {
                            if (resp.data === "success") {
                                _this.$alert("删除成功！", "提示信息", {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        location.reload();
                                    }
                                });
                            } else {
                                _this.$alert("删除失败！请联系技术人员维护！", "警告", {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push("/");
                                    }
                                });
                            }
                        });
                }).catch(function () {
                    _this.$router.push("/FindAll")
                });

            },
            deleteSelection() {
                let arr = [];
                for (let i = 0; i < this.multipleSelection.length; i++) {
                    arr[i] = this.multipleSelection[i].id;
                }
                let _this = this;
                this.$confirm('确认删除吗？', '警告', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    axios.post("http://localhost:8181/user/deleteByIds", arr).then(function (resp) {
                        if (resp.data === "success") {
                            _this.$alert("删除成功！", "提示信息", {
                                confirmButtonText: '确定',
                                callback: action => {
                                    location.reload();
                                }
                            });
                        } else {
                            _this.$alert("删除失败！请联系技术人员维护！", "警告", {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.push("/Index");
                                }
                            });
                        }
                    });
                }).catch(function () {
                    _this.$router.push("/FindAll");
                })
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            }
        },
        created() {
            let _this = this;
            axios.get("http://120.78.161.225:8181/user/findByPage/1/6").then(function (resp) {
                _this.tableData = resp.data.content;
                _this.total = resp.data.totalElements;
            });
        }
    }

</script>