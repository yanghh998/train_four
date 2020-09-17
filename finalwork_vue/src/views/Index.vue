<template>
    <div>
        <el-container style="height: 600px; border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu router :default-openeds="['0']">
                    <el-submenu index="0">
                        <template slot="title"><i class="el-icon-menu"></i>用户管理</template>
                        <el-menu-item v-for="item in $router.options.routes[0].children"
                                      :class="$route.path===item.path ? 'is-active':''"
                                      :index="item.path">{{item.name}}
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>

            <el-container>
                <el-header style="text-align: right; font-size: 12px">
                    <span>当前管理员: {{ruleForm.username}}&nbsp;&nbsp;</span>
                    <el-dropdown>
                        <i class="el-icon-setting" style="margin-right: 15px"></i>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item @click.native="change">修改密码</el-dropdown-item>
                            <el-dropdown-item @click.native="quit">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-header>
                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>

        <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
            <el-form :model="ruleForm" :rules="rules">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="ruleForm.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="原密码" prop="oldPass">
                    <el-input type="password" v-model="ruleForm.oldPass"
                              autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password"
                              autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass"
                              autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="changePassword">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<style>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }
</style>

<script>
    export default {
        data() {
            let validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            let validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            let validatePass3 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码!'));
                } else if (value !== this.$route.params.password) {
                    callback(new Error('密码错误!'));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    username: '',
                    oldPass: '',
                    password: '',
                    checkPass: ''
                },
                dialogFormVisible: false,
                rules: {
                    oldPass: [
                        {validator: validatePass3, trigger: 'blur'}
                    ],
                    password: [
                        {validator: validatePass, trigger: 'blur'}
                    ],
                    checkPass: [
                        {validator: validatePass2, trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            quit() {
                this.$router.push({path: '/'});
            },
            change() {
                this.dialogFormVisible = true;
            },
            changePassword() {
                let _this = this;
                axios.post("httP://120.78.161.225:8181/admin/changePassword", _this.ruleForm).then(function (resp) {
                    if (resp.data === "success") {
                        _this.$alert("修改成功！请重新登录！", "提示信息", {
                            confirmButtonText: '确认',
                            callback: action => {
                                _this.$router.push("/");
                            }
                        });
                    } else {
                        _this.$alert("修改失败！请联系技术人员维护！", "警告", {
                            confirmButtonText: '确定',
                            callback: action => {
                                _this.$router.push('/Index');
                            }
                        });
                    }
                });
            }
        },
        created() {
            this.ruleForm.username = this.$route.params.username;
        }
    };
</script>